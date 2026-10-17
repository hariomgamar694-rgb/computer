package com.example.data.validator

import com.example.data.content.CourseCatalog
import com.example.data.content.ShortcutsAndTroubleshootingData

data class ValidationIssue(
    val level: IssueLevel,
    val component: String,
    val identifier: String,
    val message: String
)

enum class IssueLevel {
    ERROR,
    WARNING,
    INFO
}

data class ValidationReport(
    val totalCourses: Int,
    val totalModules: Int,
    val totalLessons: Int,
    val totalQuizzes: Int,
    val totalQuestions: Int,
    val totalShortcuts: Int,
    val totalTroubleshootingGuides: Int,
    val issues: List<ValidationIssue>,
    val isValid: Boolean,
    val generatedAt: Long = System.currentTimeMillis()
)

object ContentValidator {

    fun validateCatalog(): ValidationReport {
        val issues = mutableListOf<ValidationIssue>()

        val courses = CourseCatalog.getAllCourses()
        val allLessons = CourseCatalog.getAllLessons()
        val allQuizzes = allLessons.mapNotNull { it.quiz }
        val allQuestions = allQuizzes.flatMap { it.questions }

        // 1. Check duplicate Course IDs
        val courseIds = mutableSetOf<String>()
        courses.forEach { course ->
            if (course.id.isBlank()) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Course", course.title, "Course ID cannot be blank"))
            } else if (!courseIds.add(course.id)) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Course", course.id, "Duplicate Course ID found: ${course.id}"))
            }
            if (course.title.isBlank()) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Course", course.id, "Course title is empty"))
            }
            if (course.modules.isEmpty()) {
                issues.add(ValidationIssue(IssueLevel.WARNING, "Course", course.id, "Course has no modules"))
            }
        }

        // 2. Check duplicate Module IDs
        val moduleIds = mutableSetOf<String>()
        var totalModuleCount = 0
        courses.forEach { course ->
            course.modules.forEach { module ->
                totalModuleCount++
                if (module.id.isBlank()) {
                    issues.add(ValidationIssue(IssueLevel.ERROR, "Module", module.title, "Module ID cannot be blank"))
                } else if (!moduleIds.add(module.id)) {
                    issues.add(ValidationIssue(IssueLevel.ERROR, "Module", module.id, "Duplicate Module ID: ${module.id}"))
                }
                if (module.courseId != course.id) {
                    issues.add(ValidationIssue(IssueLevel.ERROR, "Module", module.id, "Module courseId mismatch (${module.courseId} != ${course.id})"))
                }
            }
        }

        // 3. Check duplicate Lesson IDs & missing fields
        val lessonIds = mutableSetOf<String>()
        allLessons.forEach { lesson ->
            if (lesson.id.isBlank()) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Lesson", lesson.title, "Lesson ID is blank"))
            } else if (!lessonIds.add(lesson.id)) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Lesson", lesson.id, "Duplicate Lesson ID found: ${lesson.id}"))
            }
            if (lesson.title.isBlank()) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Lesson", lesson.id, "Lesson title is empty"))
            }
            if (lesson.sections.isEmpty()) {
                issues.add(ValidationIssue(IssueLevel.WARNING, "Lesson", lesson.id, "Lesson has no content sections"))
            }
            // Check media links and placeholders
            lesson.sections.forEach { section ->
                val media = section.mediaRef
                if (media != null && !media.isVerified) {
                    issues.add(
                        ValidationIssue(
                            IssueLevel.INFO,
                            "Media",
                            "${lesson.id}/${media.caption}",
                            "Media item is marked pending verification: '${media.caption}'"
                        )
                    )
                }
            }
            // Check related lesson references
            lesson.relatedLessonIds.forEach { relId ->
                if (CourseCatalog.getLessonById(relId) == null) {
                    issues.add(
                        ValidationIssue(
                            IssueLevel.ERROR,
                            "Lesson Reference",
                            lesson.id,
                            "Broken related lesson reference: '$relId' does not exist"
                        )
                    )
                }
            }
        }

        // 4. Validate Quizzes & Questions
        val quizIds = mutableSetOf<String>()
        val questionIds = mutableSetOf<String>()
        allQuizzes.forEach { quiz ->
            if (quiz.id.isBlank()) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Quiz", quiz.title, "Quiz ID is blank"))
            } else if (!quizIds.add(quiz.id)) {
                issues.add(ValidationIssue(IssueLevel.ERROR, "Quiz", quiz.id, "Duplicate Quiz ID: ${quiz.id}"))
            }
            if (quiz.questions.isEmpty()) {
                issues.add(ValidationIssue(IssueLevel.WARNING, "Quiz", quiz.id, "Quiz has no questions"))
            }
            quiz.questions.forEach { q ->
                if (!questionIds.add(q.id)) {
                    issues.add(ValidationIssue(IssueLevel.ERROR, "Question", q.id, "Duplicate Question ID: ${q.id}"))
                }
                if (q.options.size < 2) {
                    issues.add(ValidationIssue(IssueLevel.ERROR, "Question", q.id, "Question must have at least 2 options"))
                }
                if (q.correctAnswerIndex < 0 || q.correctAnswerIndex >= q.options.size) {
                    issues.add(
                        ValidationIssue(
                            IssueLevel.ERROR,
                            "Question",
                            q.id,
                            "Invalid correctAnswerIndex (${q.correctAnswerIndex}) for question '${q.id}' with ${q.options.size} options"
                        )
                    )
                }
            }
        }

        val hasErrors = issues.any { it.level == IssueLevel.ERROR }

        return ValidationReport(
            totalCourses = courses.size,
            totalModules = totalModuleCount,
            totalLessons = allLessons.size,
            totalQuizzes = allQuizzes.size,
            totalQuestions = allQuestions.size,
            totalShortcuts = ShortcutsAndTroubleshootingData.allShortcuts.size,
            totalTroubleshootingGuides = ShortcutsAndTroubleshootingData.allTroubleshootingGuides.size,
            issues = issues,
            isValid = !hasErrors
        )
    }
}
