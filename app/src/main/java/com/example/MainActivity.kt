package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.ui.navigation.Screen
import com.example.ui.screens.courses.CourseDetailScreen
import com.example.ui.screens.courses.CoursesLibraryScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.lab.PracticalLabScreen
import com.example.ui.screens.lesson.LessonReaderScreen
import com.example.ui.screens.mylearning.MyLearningScreen
import com.example.ui.screens.quiz.QuizScreen
import com.example.ui.screens.search.SearchScreen
import com.example.ui.screens.settings.ContentValidationScreen
import com.example.ui.screens.settings.SettingsScreen
import com.example.ui.theme.HRBluePrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.MainViewModel

data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val themeMode by mainViewModel.themeMode.collectAsState()

            val isDarkTheme = when (themeMode) {
                "DARK" -> true
                "LIGHT" -> false
                else -> isSystemInDarkTheme()
            }

            MyApplicationTheme(darkTheme = isDarkTheme) {
                HRComputerSikhoApp(viewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun HRComputerSikhoApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        BottomNavItem(
            route = Screen.Home.route,
            label = "होम",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            testTag = "nav_home"
        ),
        BottomNavItem(
            route = Screen.Courses.route,
            label = "कोर्सेस",
            selectedIcon = Icons.Filled.MenuBook,
            unselectedIcon = Icons.Outlined.MenuBook,
            testTag = "nav_courses"
        ),
        BottomNavItem(
            route = Screen.Lab.route,
            label = "लैब",
            selectedIcon = Icons.Filled.Science,
            unselectedIcon = Icons.Outlined.Science,
            testTag = "nav_lab"
        ),
        BottomNavItem(
            route = Screen.MyLearning.route,
            label = "मेरी पढ़ाई",
            selectedIcon = Icons.Filled.School,
            unselectedIcon = Icons.Outlined.School,
            testTag = "nav_learning"
        ),
        BottomNavItem(
            route = Screen.Settings.route,
            label = "सेटिंग्स",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            testTag = "nav_settings"
        )
    )

    // Show bottom bar only on primary top-level tabs
    val showBottomBar = bottomNavItems.any { it.route == currentRoute }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    modifier = Modifier.testTag("main_bottom_nav_bar")
                ) {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentRoute == item.route
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) },
                            modifier = Modifier.testTag(item.testTag)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToCourse = { courseId ->
                        navController.navigate(Screen.CourseDetail.createRoute(courseId))
                    },
                    onNavigateToLesson = { lessonId ->
                        navController.navigate(Screen.LessonReader.createRoute(lessonId))
                    },
                    onNavigateToLab = {
                        navController.navigate(Screen.Lab.route)
                    },
                    onNavigateToSearch = {
                        navController.navigate(Screen.Search.route)
                    },
                    onNavigateToValidation = {
                        navController.navigate(Screen.ContentValidation.route)
                    }
                )
            }

            composable(Screen.Courses.route) {
                CoursesLibraryScreen(
                    viewModel = viewModel,
                    onNavigateToCourse = { courseId ->
                        navController.navigate(Screen.CourseDetail.createRoute(courseId))
                    },
                    onNavigateToSearch = {
                        navController.navigate(Screen.Search.route)
                    }
                )
            }

            composable(Screen.Lab.route) {
                PracticalLabScreen(viewModel = viewModel)
            }

            composable(Screen.MyLearning.route) {
                MyLearningScreen(
                    viewModel = viewModel,
                    onNavigateToLesson = { lessonId ->
                        navController.navigate(Screen.LessonReader.createRoute(lessonId))
                    }
                )
            }

            composable(Screen.Settings.route) {
                SettingsScreen(
                    viewModel = viewModel,
                    onNavigateToValidation = {
                        navController.navigate(Screen.ContentValidation.route)
                    }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToCourse = { courseId ->
                        navController.navigate(Screen.CourseDetail.createRoute(courseId))
                    },
                    onNavigateToLesson = { lessonId ->
                        navController.navigate(Screen.LessonReader.createRoute(lessonId))
                    }
                )
            }

            composable(
                route = Screen.CourseDetail.route,
                arguments = listOf(navArgument("courseId") { type = NavType.StringType })
            ) { backStackEntry ->
                val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
                CourseDetailScreen(
                    courseId = courseId,
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToLesson = { lessonId ->
                        navController.navigate(Screen.LessonReader.createRoute(lessonId))
                    }
                )
            }

            composable(
                route = Screen.LessonReader.route,
                arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
            ) { backStackEntry ->
                val lessonId = backStackEntry.arguments?.getString("lessonId") ?: ""
                LessonReaderScreen(
                    lessonId = lessonId,
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToLesson = { nextId ->
                        navController.navigate(Screen.LessonReader.createRoute(nextId)) {
                            popUpTo(Screen.LessonReader.route) { inclusive = true }
                        }
                    },
                    onNavigateToQuiz = { quizId ->
                        navController.navigate(Screen.QuizScreen.createRoute(quizId))
                    }
                )
            }

            composable(
                route = Screen.QuizScreen.route,
                arguments = listOf(navArgument("quizId") { type = NavType.StringType })
            ) { backStackEntry ->
                val quizId = backStackEntry.arguments?.getString("quizId") ?: ""
                QuizScreen(
                    quizId = quizId,
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(Screen.ContentValidation.route) {
                ContentValidationScreen(
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
