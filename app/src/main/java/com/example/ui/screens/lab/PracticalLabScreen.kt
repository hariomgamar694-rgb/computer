package com.example.ui.screens.lab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@Composable
fun PracticalLabScreen(
    viewModel: MainViewModel
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("शॉर्टकट ट्रेनर", "टाइपिंग टेस्ट", "फाइल सिमुलेटर", "एक्सेल लैब")

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Science,
                        contentDescription = null,
                        tint = HRBluePrimary,
                        modifier = Modifier.size(26.dp)
                    )
                    Column {
                        Text(
                            text = "प्रैक्टिकल कंप्यूटर लैब (Practical Lab)",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "हाथों-हाथ अभ्यास करें और तकनीकी रूप से सत्यापित करें",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                ScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    edgePadding = 0.dp,
                    divider = {},
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = HRBluePrimary
                        )
                    }
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = {
                                Text(
                                    text = title,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selectedTab == index) HRBluePrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            modifier = Modifier.testTag("lab_tab_$index")
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (selectedTab) {
                0 -> ShortcutTrainerLab()
                1 -> TypingSpeedLab()
                2 -> FileExplorerSimulatorLab()
                3 -> ExcelFormulaSandboxLab()
            }
        }
    }
}

// 1. Shortcut Trainer
@Composable
private fun ShortcutTrainerLab() {
    val questions = remember {
        listOf(
            Triple("किसी चयनित फाइल या टेक्स्ट की कॉपी बनाने के लिए क्या दबाएंगे?", "Ctrl + C", listOf("Ctrl", "C")),
            Triple("विंडोज में सभी खुली खिड़कियां तुरंत मिनिमाइज कर डेस्कटॉप देखने के लिए?", "Win + D", listOf("Win", "D")),
            Triple("किए गए कार्य को सुरक्षित सहेजने (Save) का शॉर्टकट?", "Ctrl + S", listOf("Ctrl", "S")),
            Triple("गलती होने पर तुरंत पूर्व स्थिति (Undo) में जाने का शॉर्टकट?", "Ctrl + Z", listOf("Ctrl", "Z")),
            Triple("खुले हुए प्रोग्राम्स के बीच तुरंत स्विच करने के लिए?", "Alt + Tab", listOf("Alt", "Tab")),
            Triple("विंडोज में सीधे टास्क मैनेजर (Task Manager) खोलने का शॉर्टकट?", "Ctrl + Shift + Esc", listOf("Ctrl", "Shift", "Esc"))
        )
    }

    var currentIndex by remember { mutableStateOf(0) }
    var selectedKeys by remember { mutableStateOf<List<String>>(emptyList()) }
    var verificationResult by remember { mutableStateOf<Boolean?>(null) }
    var score by remember { mutableStateOf(0) }

    val currentQ = questions[currentIndex]
    val availableKeys = listOf("Ctrl", "Alt", "Shift", "Win", "C", "V", "X", "D", "E", "S", "Z", "Esc", "Tab")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = HRBlueContainer),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = HRBluePrimary
                        ) {
                            Text(
                                text = "तकनीकी सत्यापन अभ्यास",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        Text(
                            text = "स्कोर: $score / ${questions.size}",
                            fontWeight = FontWeight.Bold,
                            color = HRBluePrimary
                        )
                    }

                    Text(
                        text = "प्रश्न ${currentIndex + 1} / ${questions.size}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = currentQ.first,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = HROnBlueContainer
                    )
                }
            }
        }

        // Selected Keys Display
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "आपके द्वारा चुनी गई कुंजियां (Selected Combination):",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (selectedKeys.isEmpty()) {
                            Text(
                                text = "नीचे दिए गए कीबोर्ड बटनों पर क्लिक करें...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            selectedKeys.forEach { k ->
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = HRBluePrimary
                                ) {
                                    Text(
                                        text = k,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Virtual Keys Grid
        item {
            Text(
                text = "कीबोर्ड कुंजियां चुनें:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { selectedKeys = emptyList(); verificationResult = null },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("रीसेट (Clear)", color = MaterialTheme.colorScheme.onSurface)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Key buttons wrap
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    listOf("Ctrl", "Alt", "Shift", "Win"),
                    listOf("C", "V", "X", "S", "Z"),
                    listOf("D", "E", "Esc", "Tab")
                ).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        row.forEach { k ->
                            FilledTonalButton(
                                onClick = {
                                    if (!selectedKeys.contains(k)) {
                                        selectedKeys = selectedKeys + k
                                        verificationResult = null
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(k, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Verify Button & Result
        item {
            Button(
                onClick = {
                    val isCorrect = selectedKeys.toSet() == currentQ.third.toSet()
                    verificationResult = isCorrect
                    if (isCorrect) score++
                },
                enabled = selectedKeys.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("verify_shortcut_btn"),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary)
            ) {
                Text("सत्यापित करें (Verify Shortcut)", fontWeight = FontWeight.Bold)
            }

            AnimatedVisibility(visible = verificationResult != null) {
                val ok = verificationResult == true
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (ok) HRSuccessGreen.copy(alpha = 0.15f) else HRErrorRed.copy(alpha = 0.15f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = if (ok) Icons.Default.CheckCircle else Icons.Default.Cancel,
                            contentDescription = null,
                            tint = if (ok) HRSuccessGreen else HRErrorRed
                        )
                        Column {
                            Text(
                                text = if (ok) "✓ बिल्कुल सही! ($currentQ.second)" else "✕ गलत उत्तर! सही शॉर्टकट: ${currentQ.second}",
                                fontWeight = FontWeight.Bold,
                                color = if (ok) HRSuccessGreen else HRErrorRed
                            )
                        }
                    }
                }
            }

            if (verificationResult != null && currentIndex < questions.size - 1) {
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    onClick = {
                        currentIndex++
                        selectedKeys = emptyList()
                        verificationResult = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("अगला प्रश्न (Next Question)")
                }
            }
        }
    }
}

// 2. Typing Speed & Accuracy Drill
@Composable
private fun TypingSpeedLab() {
    val sentences = listOf(
        "कंप्यूटर एक तेज और सटीक इलेक्ट्रॉनिक उपकरण है।",
        "राम ने कार्यालय में एमएस वर्ड पर नया पत्र तैयार किया।",
        "डेटा एंट्री में गति से ज्यादा शुद्धता जरूरी होती है।",
        "The quick brown fox jumps over the lazy dog."
    )

    var sentenceIndex by remember { mutableStateOf(0) }
    var userInput by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf(0L) }
    var isStarted by remember { mutableStateOf(false) }
    var wpm by remember { mutableStateOf(0) }
    var accuracy by remember { mutableStateOf(100) }

    val target = sentences[sentenceIndex]

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HRBlueContainer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "टाइपिंग स्पीड और एक्यूरेसी ड्रिल (Speed Tester)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = HROnBlueContainer
                    )
                    Text(
                        text = "नीचे दिए गए वाक्य को देखकर ठीक वैसा ही टाइप करें। ऐप तुरंत आपकी गति (WPM) और शुद्धता (%) मापेगा।",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        // Target Text
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "लक्ष्य वाक्य (Type this):",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = target,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Live Stats
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HRTealAccent.copy(alpha = 0.12f))
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("स्पीड (Speed)", style = MaterialTheme.typography.labelSmall)
                        Text("$wpm WPM", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = HRTealAccent)
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HRBluePrimary.copy(alpha = 0.12f))
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("शुद्धता (Accuracy)", style = MaterialTheme.typography.labelSmall)
                        Text("$accuracy%", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = HRBluePrimary)
                    }
                }
            }
        }

        // Input Field
        item {
            OutlinedTextField(
                value = userInput,
                onValueChange = { text ->
                    userInput = text
                    if (!isStarted && text.isNotEmpty()) {
                        isStarted = true
                        startTime = System.currentTimeMillis()
                    }
                    if (isStarted && startTime > 0) {
                        val elapsedMinutes = (System.currentTimeMillis() - startTime).coerceAtLeast(1000) / 60000.0
                        val words = text.split("\\s+".toRegex()).filter { it.isNotBlank() }.size
                        wpm = (words / elapsedMinutes).toInt().coerceAtLeast(0)

                        // Compute accuracy
                        var correctChars = 0
                        val compareLength = minOf(text.length, target.length)
                        for (i in 0 until compareLength) {
                            if (text[i] == target[i]) correctChars++
                        }
                        accuracy = if (text.isNotEmpty()) ((correctChars * 100) / text.length).coerceIn(0, 100) else 100
                    }
                },
                label = { Text("यहां टाइप करना शुरू करें...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("typing_input_field"),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        userInput = ""
                        isStarted = false
                        startTime = 0L
                        wpm = 0
                        accuracy = 100
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("दोबारा शुरू करें (Reset)")
                }
                OutlinedButton(
                    onClick = {
                        sentenceIndex = (sentenceIndex + 1) % sentences.size
                        userInput = ""
                        isStarted = false
                        startTime = 0L
                        wpm = 0
                        accuracy = 100
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("अगला वाक्य")
                }
            }
        }
    }
}

// 3. File Explorer Simulator
@Composable
private fun FileExplorerSimulatorLab() {
    var folders by remember { mutableStateOf(listOf("दस्तावेज (Documents)", "तस्वीरें (Pictures)", "ऑफिस फाइल्स (Office)")) }
    var clipboardItem by remember { mutableStateOf<String?>(null) }
    var clipboardMode by remember { mutableStateOf<String?>(null) } // "COPY" or "CUT"
    var recycleBin by remember { mutableStateOf<List<String>>(emptyList()) }
    var newFolderName by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("फाइल एक्सप्लोरर सिमुलेटर तैयार है।") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = HRBlueContainer),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "विंडोज फाइल व फोल्डर सिमुलेटर",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = HROnBlueContainer
                    )
                    Text(
                        text = "यहां आप फोल्डर बनाने, कट, कॉपी, पेस्ट और डिलीट करने की वास्तविक क्रियाओं का अभ्यास कर सकते हैं।",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        // Action Toolbar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = newFolderName,
                    onValueChange = { newFolderName = it },
                    placeholder = { Text("नया फोल्डर नाम...") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
                Button(
                    onClick = {
                        if (newFolderName.isNotBlank()) {
                            folders = folders + newFolderName.trim()
                            statusMessage = "नया फोल्डर '${newFolderName.trim()}' बनाया गया।"
                            newFolderName = ""
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HRBluePrimary)
                ) {
                    Text("+ बनाएं")
                }
            }
        }

        // Current status indicator
        item {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "सिस्टम स्थिति: $statusMessage",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Folder List in Drive
        item {
            Text(
                text = "ड्राइव C: \\ फोल्डर सूची (${folders.size} फोल्डर):",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }

        items(folders) { folder ->
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Folder, contentDescription = null, tint = HRAmberWarning)
                        Text(text = folder, fontWeight = FontWeight.SemiBold)
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        IconButton(
                            onClick = {
                                clipboardItem = folder
                                clipboardMode = "COPY"
                                statusMessage = "'$folder' को कॉपी किया गया (Ctrl+C)।"
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", modifier = Modifier.size(16.dp))
                        }
                        IconButton(
                            onClick = {
                                clipboardItem = folder
                                clipboardMode = "CUT"
                                statusMessage = "'$folder' को कट किया गया (Ctrl+X)।"
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(imageVector = Icons.Default.ContentCut, contentDescription = "Cut", modifier = Modifier.size(16.dp))
                        }
                        IconButton(
                            onClick = {
                                folders = folders - folder
                                recycleBin = recycleBin + folder
                                statusMessage = "'$folder' डिलीट होकर रीसायकल बिन में चला गया।"
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }

        // Paste action if clipboard active
        if (clipboardItem != null) {
            item {
                Button(
                    onClick = {
                        val item = clipboardItem!!
                        if (clipboardMode == "CUT") {
                            folders = folders - item + "$item (Moved)"
                            statusMessage = "'$item' पेस्ट (Move) हो गया।"
                            clipboardItem = null
                        } else {
                            folders = folders + "$item (Copy)"
                            statusMessage = "'$item' की प्रतिलिपि पेस्ट हो गई (Ctrl+V)।"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HRTealAccent)
                ) {
                    Text("क्लिपबोर्ड से यहां पेस्ट करें (Paste: $clipboardItem)")
                }
            }
        }

        // Recycle Bin section
        if (recycleBin.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(imageVector = Icons.Default.DeleteSweep, contentDescription = null, tint = HRErrorRed)
                            Text(
                                text = "रीसायकल बिन (Recycle Bin - ${recycleBin.size} फाइलें)",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        recycleBin.forEach { deleted ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(deleted, style = MaterialTheme.typography.bodySmall)
                                TextButton(onClick = {
                                    recycleBin = recycleBin - deleted
                                    folders = folders + deleted
                                    statusMessage = "'$deleted' को वापस रीस्टोर (Restore) कर दिया गया।"
                                }) {
                                    Text("रीस्टोर करें")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// 4. Excel Formula Sandbox
@Composable
private fun ExcelFormulaSandboxLab() {
    var num1 by remember { mutableStateOf("50") }
    var num2 by remember { mutableStateOf("75") }
    var num3 by remember { mutableStateOf("60") }
    var num4 by remember { mutableStateOf("90") }
    var num5 by remember { mutableStateOf("85") }

    var selectedFormula by remember { mutableStateOf("SUM") }

    val numbers = remember(num1, num2, num3, num4, num5) {
        listOf(
            num1.toDoubleOrNull() ?: 0.0,
            num2.toDoubleOrNull() ?: 0.0,
            num3.toDoubleOrNull() ?: 0.0,
            num4.toDoubleOrNull() ?: 0.0,
            num5.toDoubleOrNull() ?: 0.0
        )
    }

    val calculationResult = remember(numbers, selectedFormula) {
        when (selectedFormula) {
            "SUM" -> numbers.sum().toString()
            "AVERAGE" -> String.format("%.2f", numbers.average())
            "MAX" -> numbers.maxOrNull()?.toString() ?: "0"
            "MIN" -> numbers.minOrNull()?.toString() ?: "0"
            "COUNT" -> numbers.size.toString()
            "IF_PASS" -> {
                val avg = numbers.average()
                if (avg >= 60) "प्रथम श्रेणी (First Division - Pass)" else "द्वितीय श्रेणी (Second Division - Pass)"
            }
            else -> "0"
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = HRBlueContainer),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "एक्सेल फॉर्मूला प्लेग्राउंड (Live Calculation)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = HROnBlueContainer
                    )
                    Text(
                        text = "नीचे दिए गए सेलों में संख्याएं बदलें और फॉर्मूला चुनकर लाइव परिणाम देखें।",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        // Spreadsheet Data Grid
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "स्प्रेडशीट डेटा (मार्क्स / संख्याएं):",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            Triple("A1", num1) { s: String -> num1 = s },
                            Triple("A2", num2) { s: String -> num2 = s },
                            Triple("A3", num3) { s: String -> num3 = s },
                            Triple("A4", num4) { s: String -> num4 = s },
                            Triple("A5", num5) { s: String -> num5 = s }
                        ).forEach { (cell, value, setter) ->
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(cell, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                                OutlinedTextField(
                                    value = value,
                                    onValueChange = setter,
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth(),
                                    textStyle = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Formula Selector Buttons
        item {
            Text(
                text = "फॉर्मूला चुनें:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf(
                    "SUM" to "=SUM(A1:A5)",
                    "AVERAGE" to "=AVERAGE(A1:A5)",
                    "MAX" to "=MAX(A1:A5)",
                    "MIN" to "=MIN(A1:A5)"
                ).forEach { (key, label) ->
                    FilterChip(
                        selected = selectedFormula == key,
                        onClick = { selectedFormula = key },
                        label = { Text(key, fontWeight = FontWeight.Bold) }
                    )
                }
            }
            FilterChip(
                selected = selectedFormula == "IF_PASS",
                onClick = { selectedFormula = "IF_PASS" },
                label = { Text("=IF(Average>=60, '1st', '2nd')", fontWeight = FontWeight.Bold) }
            )
        }

        // Formula Output Screen
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HRTealAccent.copy(alpha = 0.12f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "फॉर्मूला सिंटेक्स: =${if (selectedFormula == "IF_PASS") "IF(AVERAGE(A1:A5)>=60, \"1st Div\", \"2nd Div\")" else "$selectedFormula(A1:A5)"}",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Divider()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "गणना का परिणाम (Live Output):",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = calculationResult,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = HRTealAccent
                        )
                    }
                }
            }
        }
    }
}
