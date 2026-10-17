package com.example.data.repository

import android.content.Context
import com.example.data.content.CourseCatalog
import com.example.data.content.ShortcutsAndTroubleshootingData
import com.example.data.local.*
import com.example.data.model.*
import com.example.data.validator.ContentValidator
import com.example.data.validator.ValidationReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

data class SearchResultItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val type: SearchResultType,
    val targetCourseId: String = "",
    val targetLessonId: String = ""
)

enum class SearchResultType {
    COURSE,
    LESSON,
    SHORTCUT,
    TROUBLESHOOTING
}

class AppRepository(context: Context) {

    private val db = AppDatabase.getDatabase(context)
    private val progressDao = db.lessonProgressDao()
    private val bookmarkDao = db.bookmarkDao()
    private val noteDao = db.userNoteDao()
    private val quizDao = db.quizScoreDao()
    private val practicalDao = db.practicalCompletionDao()
    private val settingsDao = db.settingsDao()

    // Content Queries
    fun getCourses(): List<Course> = CourseCatalog.getAllCourses()

    fun getCourse(courseId: String): Course? = CourseCatalog.getCourseById(courseId)

    fun getLesson(lessonId: String): Lesson? = CourseCatalog.getLessonById(lessonId)

    fun getAllLessons(): List<Lesson> = CourseCatalog.getAllLessons()

    fun getNextLesson(lessonId: String): Lesson? = CourseCatalog.getNextLesson(lessonId)

    fun getPreviousLesson(lessonId: String): Lesson? = CourseCatalog.getPreviousLesson(lessonId)

    fun getShortcuts(): List<ShortcutItem> = ShortcutsAndTroubleshootingData.allShortcuts

    fun getTroubleshootingGuides(): List<TroubleshootingGuide> =
        ShortcutsAndTroubleshootingData.allTroubleshootingGuides

    // Real computed search across courses, lessons, shortcuts, and troubleshooting
    suspend fun search(query: String): List<SearchResultItem> = withContext(Dispatchers.Default) {
        if (query.isBlank()) return@withContext emptyList()
        val q = query.trim().lowercase()
        val results = mutableListOf<SearchResultItem>()

        // 1. Search Courses
        getCourses().forEach { course ->
            if (course.title.lowercase().contains(q) ||
                course.englishTitle.lowercase().contains(q) ||
                course.hindiTitle.lowercase().contains(q) ||
                course.description.lowercase().contains(q)
            ) {
                results.add(
                    SearchResultItem(
                        id = "course_${course.id}",
                        title = course.hindiTitle,
                        subtitle = "कोर्स • ${course.englishTitle} • ${course.category}",
                        type = SearchResultType.COURSE,
                        targetCourseId = course.id
                    )
                )
            }
        }

        // 2. Search Lessons
        getAllLessons().forEach { lesson ->
            val matchesTitle = lesson.title.lowercase().contains(q) || lesson.englishTitle.lowercase().contains(q)
            val matchesSections = lesson.sections.any {
                it.heading.lowercase().contains(q) || it.content.lowercase().contains(q) ||
                        it.steps.any { step -> step.lowercase().contains(q) }
            }
            if (matchesTitle || matchesSections) {
                results.add(
                    SearchResultItem(
                        id = "lesson_${lesson.id}",
                        title = lesson.title,
                        subtitle = "पाठ • ${lesson.englishTitle} • ${lesson.durationMinutes} मिनट",
                        type = SearchResultType.LESSON,
                        targetCourseId = lesson.courseId,
                        targetLessonId = lesson.id
                    )
                )
            }
        }

        // 3. Search Shortcuts
        getShortcuts().forEach { shortcut ->
            if (shortcut.keyCombo.lowercase().contains(q) ||
                shortcut.actionHindi.lowercase().contains(q) ||
                shortcut.actionEnglish.lowercase().contains(q)
            ) {
                results.add(
                    SearchResultItem(
                        id = "shortcut_${shortcut.id}",
                        title = "${shortcut.keyCombo} : ${shortcut.actionHindi}",
                        subtitle = "शॉर्टकट • ${shortcut.actionEnglish} (${shortcut.category})",
                        type = SearchResultType.SHORTCUT
                    )
                )
            }
        }

        // 4. Search Troubleshooting
        getTroubleshootingGuides().forEach { guide ->
            if (guide.symptomHindi.lowercase().contains(q) ||
                guide.symptomEnglish.lowercase().contains(q) ||
                guide.possibleCauses.any { it.lowercase().contains(q) } ||
                guide.stepByStepSolution.any { it.lowercase().contains(q) }
            ) {
                results.add(
                    SearchResultItem(
                        id = "tb_${guide.id}",
                        title = guide.symptomHindi,
                        subtitle = "समस्या निवारण • ${guide.category}",
                        type = SearchResultType.TROUBLESHOOTING
                    )
                )
            }
        }

        results
    }

    // Progress
    fun observeAllProgress(): Flow<List<LessonProgressEntity>> = progressDao.getAllProgress()

    fun observeLessonProgress(lessonId: String): Flow<LessonProgressEntity?> =
        progressDao.observeProgressForLesson(lessonId)

    fun observeMostRecentProgress(): Flow<LessonProgressEntity?> =
        progressDao.getMostRecentProgress()

    fun observeCompletedCount(): Flow<Int> = progressDao.getCompletedLessonsCount()

    suspend fun recordLessonRead(lessonId: String, courseId: String) {
        val existing = progressDao.getProgressForLesson(lessonId)
        val entity = existing?.copy(lastReadTimestamp = System.currentTimeMillis())
            ?: LessonProgressEntity(
                lessonId = lessonId,
                courseId = courseId,
                isCompleted = false,
                lastReadTimestamp = System.currentTimeMillis()
            )
        progressDao.insertOrUpdateProgress(entity)
    }

    suspend fun setLessonCompletion(lessonId: String, courseId: String, isCompleted: Boolean) {
        val existing = progressDao.getProgressForLesson(lessonId)
        val entity = (existing ?: LessonProgressEntity(lessonId = lessonId, courseId = courseId))
            .copy(
                isCompleted = isCompleted,
                completionTimestamp = if (isCompleted) System.currentTimeMillis() else null,
                lastReadTimestamp = System.currentTimeMillis()
            )
        progressDao.insertOrUpdateProgress(entity)
    }

    // Bookmarks
    fun observeAllBookmarks(): Flow<List<BookmarkEntity>> = bookmarkDao.getAllBookmarks()

    fun isBookmarked(lessonId: String): Flow<Boolean> = bookmarkDao.isBookmarked(lessonId)

    suspend fun toggleBookmark(lesson: Lesson, courseTitle: String) {
        val currentlyBookmarked = withContext(Dispatchers.IO) {
            // Check directly
            db.openHelper.readableDatabase.compileStatement(
                "SELECT COUNT(*) FROM bookmarks WHERE lessonId = '${lesson.id}'"
            ).simpleQueryForLong() > 0
        }
        if (currentlyBookmarked) {
            bookmarkDao.removeBookmark(lesson.id)
        } else {
            bookmarkDao.addBookmark(
                BookmarkEntity(
                    lessonId = lesson.id,
                    courseId = lesson.courseId,
                    lessonTitle = lesson.title,
                    courseTitle = courseTitle
                )
            )
        }
    }

    // Notes
    fun observeAllNotes(): Flow<List<UserNoteEntity>> = noteDao.getAllNotes()

    fun observeNotesForLesson(lessonId: String): Flow<List<UserNoteEntity>> =
        noteDao.getNotesForLesson(lessonId)

    suspend fun saveNote(
        id: Long = 0,
        lessonId: String,
        lessonTitle: String,
        courseId: String,
        content: String
    ) {
        if (id == 0L) {
            noteDao.insertNote(
                UserNoteEntity(
                    lessonId = lessonId,
                    lessonTitle = lessonTitle,
                    courseId = courseId,
                    noteContent = content.trim()
                )
            )
        } else {
            noteDao.updateNote(
                UserNoteEntity(
                    id = id,
                    lessonId = lessonId,
                    lessonTitle = lessonTitle,
                    courseId = courseId,
                    noteContent = content.trim(),
                    updatedAt = System.currentTimeMillis()
                )
            )
        }
    }

    suspend fun deleteNote(noteId: Long) {
        noteDao.deleteNote(noteId)
    }

    // Quizzes
    fun observeAllQuizScores(): Flow<List<QuizScoreEntity>> = quizDao.getAllQuizScores()

    fun observeQuizScore(quizId: String): Flow<QuizScoreEntity?> = quizDao.observeQuizScore(quizId)

    suspend fun saveQuizScore(
        quizId: String,
        courseId: String,
        lessonId: String,
        score: Int,
        totalQuestions: Int,
        percentage: Int,
        isPassed: Boolean
    ) {
        quizDao.saveQuizScore(
            QuizScoreEntity(
                quizId = quizId,
                courseId = courseId,
                lessonId = lessonId,
                score = score,
                totalQuestions = totalQuestions,
                percentage = percentage,
                isPassed = isPassed,
                attemptedAt = System.currentTimeMillis()
            )
        )
    }

    // Practical Tasks
    fun isTaskCompleted(taskId: String): Flow<Boolean> = practicalDao.isTaskCompleted(taskId)

    suspend fun toggleTaskCompletion(taskId: String, lessonId: String, isVerifiedInApp: Boolean) {
        val isCompleted = withContext(Dispatchers.IO) {
            db.openHelper.readableDatabase.compileStatement(
                "SELECT COUNT(*) FROM practical_completions WHERE taskId = '$taskId'"
            ).simpleQueryForLong() > 0
        }
        if (isCompleted) {
            practicalDao.removeCompletion(taskId)
        } else {
            practicalDao.saveCompletion(
                PracticalCompletionEntity(
                    taskId = taskId,
                    lessonId = lessonId,
                    isVerifiedInApp = isVerifiedInApp
                )
            )
        }
    }

    // Settings
    fun observeFontSizeScale(): Flow<String> =
        settingsDao.observeSetting("font_size_scale").map { it ?: "NORMAL" }

    suspend fun setFontSizeScale(scale: String) {
        settingsDao.setSetting(AppSettingsEntity("font_size_scale", scale))
    }

    fun observeThemeMode(): Flow<String> =
        settingsDao.observeSetting("theme_mode").map { it ?: "SYSTEM" }

    suspend fun setThemeMode(mode: String) {
        settingsDao.setSetting(AppSettingsEntity("theme_mode", mode))
    }

    // Validation
    fun runContentValidation(): ValidationReport {
        return ContentValidator.validateCatalog()
    }
}
