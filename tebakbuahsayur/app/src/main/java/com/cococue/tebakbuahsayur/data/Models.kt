package com.cococue.tebakbuahsayur.data

enum class GameCategory {
    FRUIT_VEG,
    ANIMAL
}

data class Question(
    val id: Int,
    val category: GameCategory,
    val title: String,
    val imageRes: Int, // Drawable resource ID for image assets (e.g. R.drawable.apel)
    val options: List<String>,
    val correctAnswer: String
)

data class ScoreRecord(
    val id: String = java.util.UUID.randomUUID().toString(),
    val categoryName: String,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long = System.currentTimeMillis()
)
