package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey val lessonId: String,
    val courseId: String,
    val isCompleted: Boolean = false,
    val lastReadTimestamp: Long = System.currentTimeMillis(),
    val completionTimestamp: Long? = null
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val lessonId: String,
    val courseId: String,
    val lessonTitle: String,
    val courseTitle: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_notes")
data class UserNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lessonId: String,
    val lessonTitle: String,
    val courseId: String,
    val noteContent: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_scores")
data class QuizScoreEntity(
    @PrimaryKey val quizId: String,
    val courseId: String,
    val lessonId: String,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val isPassed: Boolean,
    val attemptedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "practical_completions")
data class PracticalCompletionEntity(
    @PrimaryKey val taskId: String,
    val lessonId: String,
    val isVerifiedInApp: Boolean,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "app_settings")
data class AppSettingsEntity(
    @PrimaryKey val key: String,
    val value: String
)
