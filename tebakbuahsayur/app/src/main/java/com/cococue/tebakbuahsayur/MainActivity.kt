package com.cococue.tebakbuahsayur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cococue.tebakbuahsayur.data.GameCategory
import com.cococue.tebakbuahsayur.ui.AboutScreen
import com.cococue.tebakbuahsayur.ui.CategorySelectionScreen
import com.cococue.tebakbuahsayur.ui.GameScreen
import com.cococue.tebakbuahsayur.ui.HistoryScreen
import com.cococue.tebakbuahsayur.ui.HowToPlayScreen
import com.cococue.tebakbuahsayur.ui.MainMenuScreen
import com.cococue.tebakbuahsayur.ui.ResultScreen
import com.cococue.tebakbuahsayur.ui.SplashScreen
import com.cococue.tebakbuahsayur.ui.theme.TebakBuahSayurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TebakBuahSayurTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TebakAppNavHost()
                }
            }
        }
    }
}

@Composable
fun TebakAppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate("main_menu") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("main_menu") {
            MainMenuScreen(
                onNavigateToCategory = {
                    navController.navigate("category_selection")
                },
                onNavigateToHistory = {
                    navController.navigate("history")
                },
                onNavigateToHowToPlay = {
                    navController.navigate("how_to_play")
                },
                onNavigateToAbout = {
                    navController.navigate("about")
                }
            )
        }

        composable("category_selection") {
            CategorySelectionScreen(
                onSelectCategory = { category ->
                    navController.navigate("game/${category.name}")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "game/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val catStr = backStackEntry.arguments?.getString("categoryName") ?: GameCategory.FRUIT_VEG.name
            val category = try {
                GameCategory.valueOf(catStr)
            } catch (e: Exception) {
                GameCategory.FRUIT_VEG
            }

            GameScreen(
                category = category,
                onGameFinished = { score, total, catName ->
                    navController.navigate("result/$score/$total/$catName") {
                        popUpTo("main_menu")
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "result/{score}/{maxScore}/{categoryName}",
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("maxScore") { type = NavType.IntType },
                navArgument("categoryName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val maxScore = backStackEntry.arguments?.getInt("maxScore") ?: 100
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "Game"

            ResultScreen(
                score = score,
                maxScore = maxScore,
                categoryName = categoryName,
                onBackToMenu = {
                    navController.navigate("main_menu") {
                        popUpTo("main_menu") { inclusive = true }
                    }
                },
                onPlayAgain = {
                    val category = if (categoryName.contains("Hewan")) GameCategory.ANIMAL else GameCategory.FRUIT_VEG
                    navController.navigate("game/${category.name}") {
                        popUpTo("category_selection")
                    }
                }
            )
        }

        composable("history") {
            HistoryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("how_to_play") {
            HowToPlayScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("about") {
            AboutScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
