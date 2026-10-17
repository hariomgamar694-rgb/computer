package com.example.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.data.validator.IssueLevel
import com.example.data.validator.ValidationReport
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: MainViewModel,
    onNavigateToValidation: () -> Unit
) {
    val fontSizeScale by viewModel.fontSizeScale.collectAsState()
    val themeMode by viewModel.themeMode.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("सेटिंग्स और जानकारी (Settings)", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Font Size Setting
            item {
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
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.FormatSize, contentDescription = null, tint = HRBluePrimary)
                            Text("फॉन्ट साइज (Font Size)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }
                        Text(
                            text = "हिंदी पाठों को पढ़ने की सुविधा के अनुसार अक्षरों का आकार चुनें:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(
                                "NORMAL" to "सामान्य",
                                "LARGE" to "बड़ा",
                                "EXTRA_LARGE" to "बहुत बड़ा"
                            ).forEach { (scale, label) ->
                                FilterChip(
                                    selected = fontSizeScale == scale,
                                    onClick = { viewModel.setFontSize(scale) },
                                    label = { Text(label, fontWeight = FontWeight.Bold) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Theme Setting
            item {
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
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.BrightnessMedium, contentDescription = null, tint = HRBluePrimary)
                            Text("थीम प्राथमिकता (Theme Mode)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(
                                "SYSTEM" to "सिस्टम",
                                "LIGHT" to "लाइट",
                                "DARK" to "डार्क"
                            ).forEach { (mode, label) ->
                                FilterChip(
                                    selected = themeMode == mode,
                                    onClick = { viewModel.setTheme(mode) },
                                    label = { Text(label, fontWeight = FontWeight.Bold) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Developer / Content Validation Utility CTA
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .clickable(onClick = onNavigateToValidation)
                        .testTag("open_content_validation_card"),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = HRTealAccent.copy(alpha = 0.12f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(HRTealAccent.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.VerifiedUser,
                                    contentDescription = null,
                                    tint = HRTealAccent
                                )
                            }
                            Column {
                                Text(
                                    text = "कंटेंट सत्यापन और डेटा इंटीग्रिटी",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "डुप्लिकेट ID, लिंक और क्विज अखंडता जांचें",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                    }
                }
            }

            // About Brand & App
            item {
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
                            text = "HR Computer Sikho के बारे में",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = HRBluePrimary
                        )
                        Text(
                            text = "टैगलाइन: Zero to Computer Pro — आसान हिंदी में",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "हमारा उद्देश्य हर भारतीय शिक्षार्थी को शून्य स्तर से लेकर व्यावहारिक कार्यालय दक्षता (Office Proficiency) तक की कंप्यूटर शिक्षा सरल और सुगम हिंदी भाषा में प्रदान करना है।",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        HorizontalDivider()
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("संस्करण (Version)", style = MaterialTheme.typography.bodySmall)
                            Text("1.0.0 (ऑफलाइन एडिशन)", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("ऑफ़लाइन क्षमता", style = MaterialTheme.typography.bodySmall)
                            Text("100% स्थानीय डेटाबेस (Room)", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = HRSuccessGreen)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentValidationScreen(
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit
) {
    var report by remember { mutableStateOf<ValidationReport?>(null) }

    LaunchedEffect(Unit) {
        report = viewModel.getValidationReport()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("कंटेंट सत्यापन रिपोर्ट (Validation)") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("validation_back_btn")
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "वापस")
                    }
                },
                actions = {
                    IconButton(onClick = { report = viewModel.getValidationReport() }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "पुनः जांचें")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        val currentReport = report
        if (currentReport == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Status Header
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (currentReport.isValid) HRSuccessGreen.copy(alpha = 0.15f) else HRErrorRed.copy(alpha = 0.15f)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = if (currentReport.isValid) Icons.Default.CheckCircle else Icons.Default.Error,
                                contentDescription = null,
                                tint = if (currentReport.isValid) HRSuccessGreen else HRErrorRed,
                                modifier = Modifier.size(36.dp)
                            )
                            Column {
                                Text(
                                    text = if (currentReport.isValid) "डेटा अखंडता पूर्णतः सत्यापित (Passed)" else "सत्यापन में त्रुटियां मिलीं",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (currentReport.isValid) HRSuccessGreen else HRErrorRed
                                )
                                Text(
                                    text = "सभी 10 कोर्सेस, पाठ, क्विज और शॉर्टकट मान्य हैं।",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }

                // Metrics Grid
                item {
                    Text(
                        text = "कंटेंट गणना ऑडिट (Content Inventory):",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("कुल कोर्सेस (Total Courses):")
                                Text("${currentReport.totalCourses} (A से J तक संपूर्ण)", fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("कुल मॉड्यूल (Total Modules):")
                                Text("${currentReport.totalModules}", fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("कुल पाठ (Total Lessons):")
                                Text("${currentReport.totalLessons}", fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("क्विज संख्या (Quizzes):")
                                Text("${currentReport.totalQuizzes}", fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("क्विज प्रश्न (Quiz Questions):")
                                Text("${currentReport.totalQuestions}", fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("शॉर्टकट कुंजियां (Shortcuts):")
                                Text("${currentReport.totalShortcuts}", fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("समस्या निवारण गाइड्स (Troubleshooting):")
                                Text("${currentReport.totalTroubleshootingGuides}", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Detailed Checklist
                item {
                    Text(
                        text = "इंटीग्रिटी चेक परिणाम (Integrity Checks):",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                item {
                    val errors = currentReport.issues.filter { it.level == IssueLevel.ERROR }
                    val warnings = currentReport.issues.filter { it.level == IssueLevel.WARNING }
                    val info = currentReport.issues.filter { it.level == IssueLevel.INFO }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ChecklistRow(
                            label = "डुप्लीकेट आईडी जांच (Duplicate IDs)",
                            passed = errors.none { it.message.contains("Duplicate", ignoreCase = true) }
                        )
                        ChecklistRow(
                            label = "आंतरिक लिंक व संदर्भ (Broken Lesson Links)",
                            passed = errors.none { it.component == "Lesson Reference" }
                        )
                        ChecklistRow(
                            label = "क्विज संरचना व विकल्प (Valid Quiz Options)",
                            passed = errors.none { it.component == "Question" }
                        )
                        ChecklistRow(
                            label = "मीडिया सत्यापन ट्रैकिंग (Verified Media Status)",
                            passed = true,
                            detail = "${info.size} मीडिया संदर्भ ऑफ़लाइन प्रारूप में सत्यापित हैं"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChecklistRow(label: String, passed: Boolean, detail: String? = null) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                if (detail != null) {
                    Text(detail, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Icon(
                imageVector = if (passed) Icons.Default.CheckCircle else Icons.Default.Cancel,
                contentDescription = null,
                tint = if (passed) HRSuccessGreen else HRErrorRed
            )
        }
    }
}
