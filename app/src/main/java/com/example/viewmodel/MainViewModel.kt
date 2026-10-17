package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.BookmarkEntity
import com.example.data.local.LessonProgressEntity
import com.example.data.local.QuizScoreEntity
import com.example.data.local.UserNoteEntity
import com.example.data.model.Course
import com.example.data.model.Lesson
import com.example.data.model.ShortcutItem
import com.example.data.model.TroubleshootingGuide
import com.example.data.repository.AppRepository
import com.example.data.repository.SearchResultItem
import com.example.data.validator.ValidationReport
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class HomeUiState(
    val courses: List<Course> = emptyList(),
    val totalLessonsCount: Int = 0,
    val completedLessonsCount: Int = 0,
    val completedLessonIds: Set<String> = emptySet(),
    val recentLesson: Lesson? = null,
    val recentCourse: Course? = null,
    val totalQuizScoreAverage: Int = 0,
    val totalBookmarksCount: Int = 0
)

data class LessonUiState(
    val lesson: Lesson? = null,
    val course: Course? = null,
    val isCompleted: Boolean = false,
    val isBookmarked: Boolean = false,
    val notes: List<UserNoteEntity> = emptyList(),
    val previousLesson: Lesson? = null,
    val nextLesson: Lesson? = null,
    val quizScore: QuizScoreEntity? = null,
    val completedTaskIds: Set<String> = emptySet()
)

data class QuizUiState(
    val lesson: Lesson? = null,
    val course: Course? = null,
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val userAnswers: MutableMap<Int, Int> = mutableMapOf(),
    val isFinished: Boolean = false,
    val score: Int = 0,
    val percentage: Int = 0,
    val isPassed: Boolean = false
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository = AppRepository(application)

    // Global settings
    val fontSizeScale: StateFlow<String> = repository.observeFontSizeScale()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "NORMAL")

    val themeMode: StateFlow<String> = repository.observeThemeMode()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "SYSTEM")

    // Progress State
    val allProgress: StateFlow<List<LessonProgressEntity>> = repository.observeAllProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allBookmarks: StateFlow<List<BookmarkEntity>> = repository.observeAllBookmarks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allNotes: StateFlow<List<UserNoteEntity>> = repository.observeAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allQuizScores: StateFlow<List<QuizScoreEntity>> = repository.observeAllQuizScores()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val completedLessonsCount: StateFlow<Int> = repository.observeCompletedCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val mostRecentProgress: StateFlow<LessonProgressEntity?> = repository.observeMostRecentProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Home state combined
    val homeUiState: StateFlow<HomeUiState> = combine(
        allProgress,
        allBookmarks,
        allQuizScores,
        mostRecentProgress
    ) { progressList, bookmarks, quizzes, recent ->
        val courses = repository.getCourses()
        val allLessons = repository.getAllLessons()
        val completedIds = progressList.filter { it.isCompleted }.map { it.lessonId }.toSet()

        val recentLesson = recent?.let { repository.getLesson(it.lessonId) } ?: allLessons.firstOrNull()
        val recentCourse = recentLesson?.let { repository.getCourse(it.courseId) }

        val avgQuiz = if (quizzes.isNotEmpty()) quizzes.map { it.percentage }.average().toInt() else 0

        HomeUiState(
            courses = courses,
            totalLessonsCount = allLessons.size,
            completedLessonsCount = completedIds.size,
            completedLessonIds = completedIds,
            recentLesson = recentLesson,
            recentCourse = recentCourse,
            totalQuizScoreAverage = avgQuiz,
            totalBookmarksCount = bookmarks.size
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState())

    // Search state
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<SearchResultItem>>(emptyList())
    val searchResults: StateFlow<List<SearchResultItem>> = _searchResults.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            if (query.isBlank()) {
                _searchResults.value = emptyList()
            } else {
                _isSearching.value = true
                _searchResults.value = repository.search(query)
                _isSearching.value = false
            }
        }
    }

    // Actions
    fun markLessonCompleted(lessonId: String, courseId: String, completed: Boolean) {
        viewModelScope.launch {
            repository.setLessonCompletion(lessonId, courseId, completed)
        }
    }

    fun recordLessonVisited(lessonId: String, courseId: String) {
        viewModelScope.launch {
            repository.recordLessonRead(lessonId, courseId)
        }
    }

    fun toggleBookmark(lesson: Lesson, courseTitle: String) {
        viewModelScope.launch {
            repository.toggleBookmark(lesson, courseTitle)
        }
    }

    fun saveNote(id: Long = 0, lessonId: String, lessonTitle: String, courseId: String, content: String) {
        viewModelScope.launch {
            repository.saveNote(id, lessonId, lessonTitle, courseId, content)
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    fun setFontSize(scale: String) {
        viewModelScope.launch {
            repository.setFontSizeScale(scale)
        }
    }

    fun setTheme(mode: String) {
        viewModelScope.launch {
            repository.setThemeMode(mode)
        }
    }

    fun getValidationReport(): ValidationReport {
        return repository.runContentValidation()
    }
}
