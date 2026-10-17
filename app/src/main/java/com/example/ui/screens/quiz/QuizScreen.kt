package com.example.ui.screens.quiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.content.CourseCatalog
import com.example.data.model.Quiz
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quizId: String,
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit
) {
    val allQuizzes = remember { CourseCatalog.getAllLessons().mapNotNull { it.quiz } }
    val quiz: Quiz? = remember(quizId) { allQuizzes.find { it.id == quizId } }
    val lesson = remember(quiz) { quiz?.let { CourseCatalog.getLessonById(it.lessonId) } }

    if (quiz == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("क्विज नहीं मिला")
        }
        return
    }

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }

    // Maps: questionIndex -> chosenOptionIndex
    val userAnswers = remember { mutableStateMapOf<Int, Int>() }
    var isQuizCompleted by remember { mutableStateOf(false) }
    var isReviewMode by remember { mutableStateOf(false) }

    val currentQuestion = quiz.questions[currentQuestionIndex]
    val totalQuestions = quiz.questions.size

    val totalCorrect = remember(userAnswers.toMap(), isQuizCompleted) {
        quiz.questions.indices.count { idx ->
            userAnswers[idx] == quiz.questions[idx].correctAnswerIndex
        }
    }
    val percentage = if (totalQuestions > 0) (totalCorrect * 100) / totalQuestions else 0
    val isPassed = percentage >= quiz.passingScorePercentage

    // On Quiz finish, save to Room database
    LaunchedEffect(isQuizCompleted) {
        if (isQuizCompleted && lesson != null) {
            viewModel.repository.saveQuizScore(
                quizId = quiz.id,
                courseId = lesson.courseId,
                lessonId = lesson.id,
                score = totalCorrect,
                totalQuestions = totalQuestions,
                percentage = percentage,
                isPassed = isPassed
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(quiz.title, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("quiz_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "वापस"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        if (isQuizCompleted && !isReviewMode) {
            // Summary Screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
                    .testTag("quiz_summary_view"),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(
                            if (isPassed) HRSuccessGreen.copy(alpha = 0.15f)
                            else HRErrorRed.copy(alpha = 0.15f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPassed) Icons.Default.CheckCircle else Icons.Default.Cancel,
                        contentDescription = null,
                        tint = if (isPassed) HRSuccessGreen else HRErrorRed,
                        modifier = Modifier.size(54.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isPassed) "बधाई हो! आप उत्तीर्ण हुए!" else "पुनः प्रयास की आवश्यकता है",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isPassed) HRSuccessGreen else HRErrorRed
                )

                Text(
                    text = "पासिंग मार्क: ${quiz.passingScorePercentage}%",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = HRBlueContainer)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("सही उत्तर", style = MaterialTheme.typography.labelSmall)
                            Text("$totalCorrect / $totalQuestions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = HRBluePrimary)
                        }
                        Divider(modifier = Modifier.height(36.dp).width(1.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("प्रतिशत", style = MaterialTheme.typography.labelSmall)
                            Text("$percentage%", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = HRBluePrimary)
                        }
                        Divider(modifier = Modifier.height(36.dp).width(1.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("स्थिति", style = MaterialTheme.typography.labelSmall)
                            Text(if (isPassed) "PASS" else "RETRY", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = if (isPassed) HRSuccessGreen else HRErrorRed)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { isReviewMode = true },
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("समीक्षा करें")
                    }
                    Button(
                        onClick = {
                            currentQuestionIndex = 0
                            selectedOptionIndex = null
                            isSubmitted = false
                            userAnswers.clear()
                            isQuizCompleted = false
                        },
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("दोबारा टेस्ट")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = onNavigateBack) {
                    Text("पाठ पर वापस जाएं (Back to Lesson)", color = MaterialTheme.colorScheme.primary)
                }
            }
        } else if (isReviewMode) {
            // Review Mode
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "उत्तरों की विस्तृत समीक्षा (Review Answers)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                itemsIndexed(quiz.questions) { index, q ->
                    val userAns = userAnswers[index]
                    val isAnsCorrect = userAns == q.correctAnswerIndex

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "प्रश्न ${index + 1}",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = if (isAnsCorrect) "✓ सही" else "✕ गलत",
                                    color = if (isAnsCorrect) HRSuccessGreen else HRErrorRed,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Text(text = q.question, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)

                            // Options display
                            q.options.forEachIndexed { optIndex, opt ->
                                val isChosen = userAns == optIndex
                                val isCorrectOpt = q.correctAnswerIndex == optIndex

                                val bg = when {
                                    isCorrectOpt -> HRSuccessGreen.copy(alpha = 0.15f)
                                    isChosen && !isCorrectOpt -> HRErrorRed.copy(alpha = 0.15f)
                                    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                }
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = bg,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "${optIndex + 1}. $opt ${if (isCorrectOpt) "(सही उत्तर)" else if (isChosen) "(आपका उत्तर)" else ""}",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(10.dp),
                                        fontWeight = if (isCorrectOpt || isChosen) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }

                            // Explanation
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = HRBlueContainer.copy(alpha = 0.4f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "💡 व्याख्या: ${q.explanation}",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(10.dp),
                                    color = HROnBlueContainer
                                )
                            }
                        }
                    }
                }

                item {
                    Button(
                        onClick = { isReviewMode = false },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary)
                    ) {
                        Text("स्कोर कार्ड पर लौटें")
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        } else {
            // Active Quiz Question View
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    // Progress Indicator
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "प्रश्न ${currentQuestionIndex + 1} / $totalQuestions",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${((currentQuestionIndex + 1) * 100) / totalQuestions}%",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    LinearProgressIndicator(
                        progress = { (currentQuestionIndex + 1f) / totalQuestions },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = HRBluePrimary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )

                    // Question Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Text(
                                text = currentQuestion.question,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Options List
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        currentQuestion.options.forEachIndexed { index, option ->
                            val isSelected = selectedOptionIndex == index
                            val isCorrect = currentQuestion.correctAnswerIndex == index

                            val borderColor = when {
                                isSubmitted && isCorrect -> HRSuccessGreen
                                isSubmitted && isSelected && !isCorrect -> HRErrorRed
                                isSelected -> HRBluePrimary
                                else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                            }

                            val bgColor = when {
                                isSubmitted && isCorrect -> HRSuccessGreen.copy(alpha = 0.12f)
                                isSubmitted && isSelected && !isCorrect -> HRErrorRed.copy(alpha = 0.12f)
                                isSelected -> HRBlueContainer
                                else -> MaterialTheme.colorScheme.surface
                            }

                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable(enabled = !isSubmitted) {
                                        selectedOptionIndex = index
                                    }
                                    .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                                    .testTag("quiz_option_$index"),
                                color = bgColor
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(if (isSelected) HRBluePrimary else MaterialTheme.colorScheme.surfaceVariant),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = ('A' + index).toString(),
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Text(
                                        text = option,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (isSubmitted) {
                                        if (isCorrect) {
                                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = HRSuccessGreen)
                                        } else if (isSelected) {
                                            Icon(imageVector = Icons.Default.Cancel, contentDescription = null, tint = HRErrorRed)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Explanation Card after submission
                    AnimatedVisibility(visible = isSubmitted) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = HRBlueContainer.copy(alpha = 0.4f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null, tint = HRBluePrimary)
                                Column {
                                    Text("उत्तर का स्पष्टीकरण:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                                    Text(currentQuestion.explanation, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                        }
                    }
                }

                // Bottom Action Button
                Column(modifier = Modifier.fillMaxWidth()) {
                    if (!isSubmitted) {
                        Button(
                            onClick = {
                                if (selectedOptionIndex != null) {
                                    isSubmitted = true
                                    userAnswers[currentQuestionIndex] = selectedOptionIndex!!
                                }
                            },
                            enabled = selectedOptionIndex != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("quiz_submit_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary)
                        ) {
                            Text("उत्तर सबमिट करें (Submit Answer)", fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Button(
                            onClick = {
                                if (currentQuestionIndex < totalQuestions - 1) {
                                    currentQuestionIndex++
                                    selectedOptionIndex = null
                                    isSubmitted = false
                                } else {
                                    isQuizCompleted = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("quiz_next_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary)
                        ) {
                            Text(
                                text = if (currentQuestionIndex < totalQuestions - 1) "अगला प्रश्न (Next Question)" else "परिणाम देखें (View Results)",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
