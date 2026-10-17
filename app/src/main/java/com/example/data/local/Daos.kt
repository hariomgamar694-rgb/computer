package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonProgressDao {
    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId LIMIT 1")
    suspend fun getProgressForLesson(lessonId: String): LessonProgressEntity?

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId LIMIT 1")
    fun observeProgressForLesson(lessonId: String): Flow<LessonProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProgress(progress: LessonProgressEntity)

    @Query("SELECT * FROM lesson_progress ORDER BY lastReadTimestamp DESC LIMIT 1")
    fun getMostRecentProgress(): Flow<LessonProgressEntity?>

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE isCompleted = 1")
    fun getCompletedLessonsCount(): Flow<Int>
}

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY createdAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE lessonId = :lessonId)")
    fun isBookmarked(lessonId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE lessonId = :lessonId")
    suspend fun removeBookmark(lessonId: String)
}

@Dao
interface UserNoteDao {
    @Query("SELECT * FROM user_notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<UserNoteEntity>>

    @Query("SELECT * FROM user_notes WHERE lessonId = :lessonId ORDER BY updatedAt DESC")
    fun getNotesForLesson(lessonId: String): Flow<List<UserNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: UserNoteEntity): Long

    @Update
    suspend fun updateNote(note: UserNoteEntity)

    @Query("DELETE FROM user_notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Long)
}

@Dao
interface QuizScoreDao {
    @Query("SELECT * FROM quiz_scores ORDER BY attemptedAt DESC")
    fun getAllQuizScores(): Flow<List<QuizScoreEntity>>

    @Query("SELECT * FROM quiz_scores WHERE quizId = :quizId LIMIT 1")
    suspend fun getQuizScore(quizId: String): QuizScoreEntity?

    @Query("SELECT * FROM quiz_scores WHERE quizId = :quizId LIMIT 1")
    fun observeQuizScore(quizId: String): Flow<QuizScoreEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuizScore(score: QuizScoreEntity)
}

@Dao
interface PracticalCompletionDao {
    @Query("SELECT * FROM practical_completions")
    fun getAllCompletions(): Flow<List<PracticalCompletionEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM practical_completions WHERE taskId = :taskId)")
    fun isTaskCompleted(taskId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCompletion(completion: PracticalCompletionEntity)

    @Query("DELETE FROM practical_completions WHERE taskId = :taskId")
    suspend fun removeCompletion(taskId: String)
}

@Dao
interface SettingsDao {
    @Query("SELECT value FROM app_settings WHERE `key` = :key LIMIT 1")
    suspend fun getSetting(key: String): String?

    @Query("SELECT value FROM app_settings WHERE `key` = :key LIMIT 1")
    fun observeSetting(key: String): Flow<String?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSetting(setting: AppSettingsEntity)
}
