package com.example.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Courses : Screen("courses")
    object Lab : Screen("lab")
    object MyLearning : Screen("my_learning")
    object Settings : Screen("settings")
    object Search : Screen("search")
    object CourseDetail : Screen("course_detail/{courseId}") {
        fun createRoute(courseId: String) = "course_detail/$courseId"
    }
    object LessonReader : Screen("lesson_reader/{lessonId}") {
        fun createRoute(lessonId: String) = "lesson_reader/$lessonId"
    }
    object QuizScreen : Screen("quiz/{quizId}") {
        fun createRoute(quizId: String) = "quiz/$quizId"
    }
    object ContentValidation : Screen("content_validation")
}
