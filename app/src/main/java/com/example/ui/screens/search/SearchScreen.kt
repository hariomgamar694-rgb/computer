package com.example.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.data.repository.SearchResultItem
import com.example.data.repository.SearchResultType
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToCourse: (String) -> Unit,
    onNavigateToLesson: (String) -> Unit
) {
    val query by viewModel.searchQuery.collectAsState()
    val results by viewModel.searchResults.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    var selectedFilter by remember { mutableStateOf<SearchResultType?>(null) }
    var selectedItemDetail by remember { mutableStateOf<SearchResultItem?>(null) }

    val quickPills = listOf("Ctrl+C", "विंडोज 11", "धीमा कंप्यूटर", "SUM फॉर्मूला", "प्रिंटर", "पासवर्ड", "IPO चक्र")

    val filteredResults = remember(results, selectedFilter) {
        if (selectedFilter == null) results
        else results.filter { it.type == selectedFilter }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("search_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "वापस जाएं"
                        )
                    }

                    OutlinedTextField(
                        value = query,
                        onValueChange = { viewModel.updateSearchQuery(it) },
                        placeholder = { Text("कोर्स, पाठ, शॉर्टकट या समस्या खोजें...") },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("search_text_field"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = {
                            if (query.isNotEmpty()) {
                                IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                                    Icon(imageVector = Icons.Default.Close, contentDescription = "साफ करें")
                                }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Type Filters
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        FilterChip(
                            selected = selectedFilter == null,
                            onClick = { selectedFilter = null },
                            label = { Text("सभी (${results.size})") }
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedFilter == SearchResultType.COURSE,
                            onClick = { selectedFilter = SearchResultType.COURSE },
                            label = { Text("कोर्स") }
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedFilter == SearchResultType.LESSON,
                            onClick = { selectedFilter = SearchResultType.LESSON },
                            label = { Text("पाठ (Lessons)") }
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedFilter == SearchResultType.SHORTCUT,
                            onClick = { selectedFilter = SearchResultType.SHORTCUT },
                            label = { Text("शॉर्टकट्स") }
                        )
                    }
                    item {
                        FilterChip(
                            selected = selectedFilter == SearchResultType.TROUBLESHOOTING,
                            onClick = { selectedFilter = SearchResultType.TROUBLESHOOTING },
                            label = { Text("समस्या निवारण") }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Quick suggestion pills if query is empty
            if (query.isBlank()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "लोकप्रिय खोज सुझाव (Popular Searches):",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        quickPills.take(3).forEach { pill ->
                            SuggestionChip(
                                onClick = { viewModel.updateSearchQuery(pill) },
                                label = { Text(pill) }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        quickPills.drop(3).forEach { pill ->
                            SuggestionChip(
                                onClick = { viewModel.updateSearchQuery(pill) },
                                label = { Text(pill) }
                            )
                        }
                    }
                }
            } else if (isSearching) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = HRBluePrimary)
                }
            } else if (filteredResults.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.SearchOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "'$query' के लिए कोई परिणाम नहीं मिला",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "कृपया कोई अन्य कीवर्ड जैसे 'Word', 'Excel', 'Print', या 'शॉर्टकट' खोजें।",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("search_results_list"),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredResults, key = { it.id }) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    when (item.type) {
                                        SearchResultType.COURSE -> onNavigateToCourse(item.targetCourseId)
                                        SearchResultType.LESSON -> onNavigateToLesson(item.targetLessonId)
                                        SearchResultType.SHORTCUT, SearchResultType.TROUBLESHOOTING -> {
                                            selectedItemDetail = item
                                        }
                                    }
                                }
                                .testTag("search_item_${item.id}"),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                val (icon, iconBg, iconColor) = when (item.type) {
                                    SearchResultType.COURSE -> Triple(Icons.Default.School, HRBlueContainer, HRBluePrimary)
                                    SearchResultType.LESSON -> Triple(Icons.Default.MenuBook, HRSecondaryContainer, HRSecondary)
                                    SearchResultType.SHORTCUT -> Triple(Icons.Default.Keyboard, HRTealAccent.copy(alpha = 0.15f), HRTealAccent)
                                    SearchResultType.TROUBLESHOOTING -> Triple(Icons.Default.Build, HRAmberWarning.copy(alpha = 0.15f), HRAmberWarning)
                                }

                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(iconBg),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = iconColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = item.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Detail Dialog for Shortcut or Troubleshooting result
    selectedItemDetail?.let { item ->
        AlertDialog(
            onDismissRequest = { selectedItemDetail = null },
            title = { Text(item.title, fontWeight = FontWeight.Bold) },
            text = { Text(item.subtitle, style = MaterialTheme.typography.bodyMedium) },
            confirmButton = {
                TextButton(onClick = { selectedItemDetail = null }) {
                    Text("ठीक है")
                }
            }
        )
    }
}
