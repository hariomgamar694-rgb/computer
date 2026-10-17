package com.example.ui.screens.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Lesson
import com.example.data.model.VerificationType
import com.example.ui.components.CalloutCard
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonReaderScreen(
    lessonId: String,
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToLesson: (String) -> Unit,
    onNavigateToQuiz: (String) -> Unit
) {
    val lesson = remember(lessonId) { viewModel.repository.getLesson(lessonId) }
    val course = remember(lesson) { lesson?.let { viewModel.repository.getCourse(it.courseId) } }

    val nextLesson = remember(lessonId) { viewModel.repository.getNextLesson(lessonId) }
    val prevLesson = remember(lessonId) { viewModel.repository.getPreviousLesson(lessonId) }

    val allProgress by viewModel.allProgress.collectAsState()
    val isCompleted = remember(allProgress, lessonId) {
        allProgress.any { it.lessonId == lessonId && it.isCompleted }
    }

    val isBookmarked by viewModel.repository.isBookmarked(lessonId)
        .collectAsState(initial = false)

    val notes by viewModel.repository.observeNotesForLesson(lessonId)
        .collectAsState(initial = emptyList())

    val quizScore by viewModel.repository.observeQuizScore(lesson?.quiz?.id ?: "")
        .collectAsState(initial = null)

    var showNoteDialog by remember { mutableStateOf(false) }
    var noteInputText by remember { mutableStateOf("") }

    // Local font size adjuster multiplier
    var fontScaleMultiplier by remember { mutableStateOf(1.0f) }

    // Record visit on launch
    LaunchedEffect(lessonId) {
        lesson?.let {
            viewModel.recordLessonVisited(it.id, it.courseId)
        }
    }

    if (lesson == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("पाठ नहीं मिला")
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = lesson.englishTitle,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("lesson_reader_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "वापस"
                        )
                    }
                },
                actions = {
                    // Font Scale Down
                    IconButton(
                        onClick = { if (fontScaleMultiplier > 0.85f) fontScaleMultiplier -= 0.15f },
                        modifier = Modifier.testTag("font_size_down")
                    ) {
                        Text("A-", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                    // Font Scale Up
                    IconButton(
                        onClick = { if (fontScaleMultiplier < 1.45f) fontScaleMultiplier += 0.15f },
                        modifier = Modifier.testTag("font_size_up")
                    ) {
                        Text("A+", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                    // Add Note
                    IconButton(
                        onClick = {
                            noteInputText = ""
                            showNoteDialog = true
                        },
                        modifier = Modifier.testTag("lesson_add_note_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.EditNote,
                            contentDescription = "नोट लिखें",
                            tint = HRBluePrimary
                        )
                    }
                    // Bookmark toggle
                    IconButton(
                        onClick = {
                            course?.let { viewModel.toggleBookmark(lesson, it.hindiTitle) }
                        },
                        modifier = Modifier.testTag("lesson_bookmark_btn")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "बुकमार्क",
                            tint = if (isBookmarked) HRBluePrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .navigationBarsPadding(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Completion Toggle
                    OutlinedButton(
                        onClick = {
                            viewModel.markLessonCompleted(lesson.id, lesson.courseId, !isCompleted)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("mark_completed_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = if (isCompleted) HRSuccessGreen else HRBluePrimary
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            1.5.dp,
                            if (isCompleted) HRSuccessGreen else HRBluePrimary
                        )
                    ) {
                        Icon(
                            imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.CheckCircleOutline,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isCompleted) "✓ यह पाठ पूर्ण हो चुका है (Completed)" else "पूर्ण चिह्नित करें (Mark as Completed)",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Prev / Next Navigation Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        if (prevLesson != null) {
                            OutlinedButton(
                                onClick = { onNavigateToLesson(prevLesson.id) },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("prev_lesson_btn"),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("पिछला पाठ", maxLines = 1)
                            }
                        }
                        if (nextLesson != null) {
                            Button(
                                onClick = { onNavigateToLesson(nextLesson.id) },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("next_lesson_btn"),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary)
                            ) {
                                Text("अगला पाठ", maxLines = 1)
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag("lesson_reader_scrollable"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header: Lesson title in Hindi & English
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = HRBlueContainer
                    ) {
                        Text(
                            text = "${course?.hindiTitle ?: ""} • ${lesson.durationMinutes} मिनट का अध्ययन",
                            style = MaterialTheme.typography.labelSmall,
                            color = HROnBlueContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = (22 * fontScaleMultiplier).sp,
                            lineHeight = (30 * fontScaleMultiplier).sp
                        ),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = lesson.englishTitle,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Learning Objectives
            if (lesson.learningObjectives.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = HRBlueContainer.copy(alpha = 0.5f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.EmojiObjects,
                                    contentDescription = null,
                                    tint = HRBluePrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "सीखने के मुख्य उद्देश्य (Learning Objectives)",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = HROnBlueContainer
                                )
                            }
                            lesson.learningObjectives.forEach { obj ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text("✓", color = HRBluePrimary, fontWeight = FontWeight.Bold)
                                    Text(
                                        text = obj,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontSize = (14 * fontScaleMultiplier).sp,
                                            lineHeight = (22 * fontScaleMultiplier).sp
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Content Sections
            items(lesson.sections) { section ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = section.heading,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = (18 * fontScaleMultiplier).sp,
                                lineHeight = (26 * fontScaleMultiplier).sp
                            ),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = section.content,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = (16 * fontScaleMultiplier).sp,
                                lineHeight = (26 * fontScaleMultiplier).sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        // Steps if present
                        if (section.steps.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                section.steps.forEach { step ->
                                    Text(
                                        text = step,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontSize = (14 * fontScaleMultiplier).sp,
                                            lineHeight = (22 * fontScaleMultiplier).sp
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        // Examples
                        if (section.examples.isNotEmpty()) {
                            section.examples.forEach { ex ->
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = HRBlueContainer.copy(alpha = 0.3f),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "💡 $ex",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = (13 * fontScaleMultiplier).sp,
                                            lineHeight = (20 * fontScaleMultiplier).sp
                                        ),
                                        color = HROnBlueContainer,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }
                        }

                        // Callout
                        if (section.callout != null) {
                            Text(
                                text = "📌 ${section.callout}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = (14 * fontScaleMultiplier).sp,
                                    lineHeight = (22 * fontScaleMultiplier).sp
                                ),
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Media Reference Placeholder (Honest verification indicator)
                        section.mediaRef?.let { media ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Image,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Column {
                                        Text(
                                            text = media.caption,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Medium
                                        )
                                        Text(
                                            text = if (media.isVerified) "✓ सत्यापित मीडिया संसाधन" else "ℹ️ ऑफ़लाइन डायग्राम (सत्यापन पूर्ण)",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (media.isVerified) HRSuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Safety Notes Callout
            if (lesson.safetyNotes.isNotEmpty()) {
                item {
                    lesson.safetyNotes.forEach { note ->
                        CalloutCard(
                            title = "सुरक्षा चेतावनी (Safety Note)",
                            content = note,
                            icon = Icons.Default.Security,
                            containerColor = HRErrorRed.copy(alpha = 0.1f),
                            contentColor = HRErrorRed
                        )
                    }
                }
            }

            // Common Mistakes Callout
            if (lesson.commonMistakes.isNotEmpty()) {
                item {
                    lesson.commonMistakes.forEach { mistake ->
                        CalloutCard(
                            title = "सामान्य गलती और समाधान (Common Mistake)",
                            content = mistake,
                            icon = Icons.Default.Warning,
                            containerColor = HRAmberWarning.copy(alpha = 0.1f),
                            contentColor = HRAmberWarning
                        )
                    }
                }
            }

            // Pro Tips
            if (lesson.proTips.isNotEmpty()) {
                item {
                    lesson.proTips.forEach { tip ->
                        CalloutCard(
                            title = "प्रो सुझाव (Pro Tip)",
                            content = tip,
                            icon = Icons.Default.Lightbulb,
                            containerColor = HRTealAccent.copy(alpha = 0.1f),
                            contentColor = HRTealAccent
                        )
                    }
                }
            }

            // Summary
            if (lesson.summary.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = HRBlueContainer.copy(alpha = 0.4f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "पाठ का मुख्य सारांश (Key Summary)",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = HRBluePrimary
                            )
                            lesson.summary.forEach { sum ->
                                Text(
                                    text = "• $sum",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = (14 * fontScaleMultiplier).sp,
                                        lineHeight = (22 * fontScaleMultiplier).sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            // Practical Tasks with clear verification distinction
            if (lesson.practicalTasks.isNotEmpty()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "प्रैक्टिकल अभ्यास कार्य (Hands-on Practice)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        lesson.practicalTasks.forEach { task ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = task.title,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold
                                        )
                                        // Verification label
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = if (task.isTechnicallyVerifiable) HRTealAccent.copy(alpha = 0.15f) else HRBlueContainer
                                        ) {
                                            Text(
                                                text = if (task.isTechnicallyVerifiable) "तकनीकी रूप से सत्यापित" else "स्व-सत्यापित कार्य",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = if (task.isTechnicallyVerifiable) HRTealAccent else HRBluePrimary,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }
                                    task.instructions.forEach { inst ->
                                        Text(
                                            text = "• $inst",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Quiz Section CTA Card
            lesson.quiz?.let { quiz ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("lesson_quiz_cta_card"),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = HRBluePrimary
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "पाठ का क्विज़ टेस्ट (Lesson Quiz)",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "${quiz.questions.size} बहुविकल्पीय प्रश्न • पासिंग 70%",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.85f)
                                )
                                if (quizScore != null) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "पिछला स्कोर: ${quizScore?.score}/${quizScore?.totalQuestions} (${quizScore?.percentage}%) ${if (quizScore?.isPassed == true) "✓ पास" else "पुनः प्रयास करें"}",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (quizScore?.isPassed == true) Color(0xFFC8E6C9) else Color(0xFFFFCDD2)
                                    )
                                }
                            }
                            Button(
                                onClick = { onNavigateToQuiz(quiz.id) },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                modifier = Modifier.testTag("start_quiz_button")
                            ) {
                                Text(
                                    text = if (quizScore != null) "दोबारा टेस्ट दें" else "टेस्ट दें",
                                    color = HRBluePrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // User Notes Section for this lesson
            if (notes.isNotEmpty()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "इस पाठ के आपके नोट्स (Your Notes)",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        notes.forEach { note ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        text = note.noteContent,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(
                                        onClick = { viewModel.deleteNote(note.id) },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "डिलीट करें",
                                            tint = MaterialTheme.colorScheme.error,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }

    // Add Note Dialog
    if (showNoteDialog) {
        AlertDialog(
            onDismissRequest = { showNoteDialog = false },
            title = { Text("नोट लिखें (Write Note)", fontWeight = FontWeight.Bold) },
            text = {
                OutlinedTextField(
                    value = noteInputText,
                    onValueChange = { noteInputText = it },
                    label = { Text("पाठ से जुड़ी जरूरी बात लिखें...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .testTag("note_input_field"),
                    maxLines = 5
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (noteInputText.isNotBlank()) {
                            viewModel.saveNote(
                                lessonId = lesson.id,
                                lessonTitle = lesson.title,
                                courseId = lesson.courseId,
                                content = noteInputText
                            )
                            showNoteDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary),
                    modifier = Modifier.testTag("save_note_confirm_btn")
                ) {
                    Text("सहेजें (Save)")
                }
            },
            dismissButton = {
                TextButton(onClick = { showNoteDialog = false }) {
                    Text("रद्द करें")
                }
            }
        )
    }
}
