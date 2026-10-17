package com.example.data.model

enum class CourseLevel(val labelHindi: String, val labelEnglish: String) {
    BEGINNER("प्रारंभिक (Beginner)", "Beginner"),
    INTERMEDIATE("इंटरमीडिएट (Intermediate)", "Intermediate"),
    ADVANCED("एडवांस्ड (Advanced)", "Advanced")
}

enum class ContentStatus {
    COMPLETED,
    IN_PROGRESS,
    PLANNED
}

enum class MediaType {
    DIAGRAM,
    IMAGE_PLACEHOLDER,
    VERIFIED_VIDEO,
    EXTERNAL_LINK_PENDING
}

enum class VerificationType {
    SELF_REPORTED,
    INTERACTIVE_SIMULATOR,
    SHORTCUT_TESTER,
    QUIZ_VERIFIED
}

data class MediaReference(
    val type: MediaType,
    val caption: String,
    val isVerified: Boolean = false,
    val resourceIdentifier: String = ""
)

data class LessonSection(
    val heading: String,
    val content: String,
    val steps: List<String> = emptyList(),
    val examples: List<String> = emptyList(),
    val callout: String? = null,
    val mediaRef: MediaReference? = null
)

data class PracticalTask(
    val id: String,
    val title: String,
    val instructions: List<String>,
    val keyboardShortcuts: List<String> = emptyList(),
    val isTechnicallyVerifiable: Boolean = false,
    val verificationType: VerificationType = VerificationType.SELF_REPORTED,
    val targetExpectedInput: String = ""
)

data class Question(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

data class Quiz(
    val id: String,
    val lessonId: String,
    val title: String,
    val passingScorePercentage: Int = 70,
    val questions: List<Question>
)

data class Lesson(
    val id: String,
    val courseId: String,
    val moduleId: String,
    val title: String,
    val englishTitle: String,
    val durationMinutes: Int,
    val learningObjectives: List<String>,
    val prerequisites: List<String> = emptyList(),
    val sections: List<LessonSection>,
    val safetyNotes: List<String> = emptyList(),
    val commonMistakes: List<String> = emptyList(),
    val proTips: List<String> = emptyList(),
    val summary: List<String> = emptyList(),
    val practicalTasks: List<PracticalTask> = emptyList(),
    val quiz: Quiz? = null,
    val relatedLessonIds: List<String> = emptyList(),
    val status: ContentStatus = ContentStatus.COMPLETED
)

data class Module(
    val id: String,
    val courseId: String,
    val title: String,
    val description: String,
    val order: Int,
    val lessons: List<Lesson>
)

data class Course(
    val id: String,
    val title: String,
    val englishTitle: String,
    val hindiTitle: String,
    val description: String,
    val level: CourseLevel,
    val category: String,
    val estimatedHours: String,
    val prerequisites: List<String>,
    val iconKey: String,
    val modules: List<Module>
)

data class ShortcutItem(
    val id: String,
    val keyCombo: String,
    val actionHindi: String,
    val actionEnglish: String,
    val category: String,
    val appCategory: String = "सामान्य (General)"
)

data class TroubleshootingGuide(
    val id: String,
    val symptomHindi: String,
    val symptomEnglish: String,
    val category: String,
    val possibleCauses: List<String>,
    val stepByStepSolution: List<String>,
    val safetyWarning: String
)
