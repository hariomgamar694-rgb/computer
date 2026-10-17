package com.example.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.Course
import com.example.data.model.Lesson
import com.example.ui.components.CourseCard
import com.example.ui.components.HRBrandHeader
import com.example.ui.components.StatCard
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onNavigateToCourse: (String) -> Unit,
    onNavigateToLesson: (String) -> Unit,
    onNavigateToLab: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToValidation: () -> Unit
) {
    val homeState by viewModel.homeUiState.collectAsState()

    var showShortcutsSheet by remember { mutableStateOf(false) }
    var showTroubleshootingSheet by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen_lazy_column"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. HR Brand Header
        item {
            HRBrandHeader(
                onSearchClick = onNavigateToSearch
            )
        }

        // 2. Learning Stats Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "पूर्ण पाठ",
                    value = "${homeState.completedLessonsCount}/${homeState.totalLessonsCount}",
                    subtitle = "कुल प्रगति",
                    icon = Icons.Default.CheckCircle,
                    containerColor = HRSuccessGreen.copy(alpha = 0.12f),
                    contentColor = HRSuccessGreen
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "क्विज औसत",
                    value = "${homeState.totalQuizScoreAverage}%",
                    subtitle = "स्कोर रेट",
                    icon = Icons.Default.Grade,
                    containerColor = HRBlueContainer,
                    contentColor = HRBluePrimary
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "बुकमार्क",
                    value = "${homeState.totalBookmarksCount}",
                    subtitle = "सहेजे गए",
                    icon = Icons.Default.Bookmark,
                    containerColor = HRSecondaryContainer,
                    contentColor = HRSecondary
                )
            }
        }

        // 3. Continue Learning Card
        item {
            val recentLesson = homeState.recentLesson
            val recentCourse = homeState.recentCourse

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("continue_learning_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayCircleFilled,
                                contentDescription = null,
                                tint = HRBluePrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "सीखना जारी रखें (Continue Learning)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (recentLesson != null) {
                        Text(
                            text = recentLesson.title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${recentCourse?.hindiTitle ?: ""} • ${recentLesson.durationMinutes} मिनट",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { onNavigateToLesson(recentLesson.id) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("resume_lesson_button"),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = HRBluePrimary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoStories,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "पाठ पढ़ें (Resume Lesson)",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Text(
                            text = "कंप्यूटर के मूल सिद्धांत से शुरुआत करें",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // 4. Quick Action Shortcuts
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "त्वरित सुविधाएं (Quick Access)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionButton(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Keyboard,
                        label = "शॉर्टकट",
                        sublabel = "चीटशीट",
                        color = HRBluePrimary,
                        onClick = { showShortcutsSheet = true },
                        tag = "btn_quick_shortcuts"
                    )
                    QuickActionButton(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Science,
                        label = "प्रैक्टिकल लैब",
                        sublabel = "टाइपिंग व एक्सेल",
                        color = HRTealAccent,
                        onClick = onNavigateToLab,
                        tag = "btn_quick_lab"
                    )
                    QuickActionButton(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Build,
                        label = "समस्या निवारण",
                        sublabel = "धीमा PC / वाईफाई",
                        color = HRAmberWarning,
                        onClick = { showTroubleshootingSheet = true },
                        tag = "btn_quick_trouble"
                    )
                }
            }
        }

        // 5. Course Catalog Heading
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "संपूर्ण कोर्स सूची (All 10 Courses)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${homeState.courses.size} कोर्सेस",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // 6. Courses List
        items(homeState.courses, key = { it.id }) { course ->
            val allCourseLessons = course.modules.flatMap { it.lessons }
            val completedCount = allCourseLessons.count { homeState.completedLessonIds.contains(it.id) }
            val percentage = if (allCourseLessons.isNotEmpty()) {
                (completedCount * 100) / allCourseLessons.size
            } else 0

            CourseCard(
                course = course,
                progressPercentage = percentage,
                onClick = { onNavigateToCourse(course.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    // Modal Sheet for Shortcuts Quick View
    if (showShortcutsSheet) {
        val shortcuts = viewModel.repository.getShortcuts()
        AlertDialog(
            onDismissRequest = { showShortcutsSheet = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Keyboard, contentDescription = null, tint = HRBluePrimary)
                    Text("कीबोर्ड शॉर्टकट चीटशीट", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(shortcuts) { sc ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = sc.actionHindi,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = sc.actionEnglish,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = HRBluePrimary
                                ) {
                                    Text(
                                        text = sc.keyCombo,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showShortcutsSheet = false }) {
                    Text("बंद करें")
                }
            }
        )
    }

    // Modal Sheet for Troubleshooting Quick View
    if (showTroubleshootingSheet) {
        val guides = viewModel.repository.getTroubleshootingGuides()
        AlertDialog(
            onDismissRequest = { showTroubleshootingSheet = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null, tint = HRAmberWarning)
                    Text("त्वरित समस्या निवारण", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(guides) { guide ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = guide.symptomHindi,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.error
                                )
                                Text(
                                    text = guide.symptomEnglish,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "समाधान के कदम:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                guide.stepByStepSolution.forEach { step ->
                                    Text(
                                        text = step,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showTroubleshootingSheet = false }) {
                    Text("बंद करें")
                }
            }
        )
    }
}

@Composable
fun QuickActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    sublabel: String,
    color: Color,
    onClick: () -> Unit,
    tag: String
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .testTag(tag),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = sublabel,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
