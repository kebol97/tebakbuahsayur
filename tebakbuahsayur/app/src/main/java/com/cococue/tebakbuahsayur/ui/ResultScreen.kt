package com.cococue.tebakbuahsayur.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cococue.tebakbuahsayur.ads.BannerAdView
import com.cococue.tebakbuahsayur.ads.NativeAdCard
import com.cococue.tebakbuahsayur.data.ScoreManager
import com.cococue.tebakbuahsayur.data.ScoreRecord

@Composable
fun ResultScreen(
    score: Int,
    maxScore: Int,
    categoryName: String,
    onBackToMenu: () -> Unit,
    onPlayAgain: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    LaunchedEffect(Unit) {
        val record = ScoreRecord(
            categoryName = categoryName,
            score = score,
            totalQuestions = maxScore
        )
        ScoreManager.saveScore(context, record)
    }

    val percentage = if (maxScore > 0) (score * 100) / maxScore else 0
    val (congratsText, emoji) = when {
        percentage >= 80 -> "Luar Biasa! Hebat Sekali! 🌟" to "🏆"
        percentage >= 50 -> "Bagus! Terus Semangat! 👍" to "⭐"
        else -> "Ayo Coba Lagi Pasti Bisa! 💪" to "😊"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00B4DB),
                        Color(0xFF0083B0)
                    )
                )
            )
            .safeDrawingPadding()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFEE93)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = emoji, fontSize = 50.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Hasil Permainan",
                    color = Color(0xFF2D3142),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = categoryName,
                    color = Color(0xFF7F8C8D),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Score Display
                Text(
                    text = "$score",
                    color = Color(0xFF27AE60),
                    fontSize = 64.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Dari Total Nilai $maxScore",
                    color = Color(0xFF95A5A6),
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = congratsText,
                    color = Color(0xFFE67E22),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // AdMob Native Ad Card
                NativeAdCard()

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onPlayAgain,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27AE60)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "🔄 Main Lagi",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onBackToMenu,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3498DB)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "🏠 Menu Utama",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
