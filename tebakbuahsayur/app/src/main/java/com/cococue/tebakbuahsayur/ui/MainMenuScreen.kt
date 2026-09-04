package com.cococue.tebakbuahsayur.ui

import androidx.compose.foundation.background
import com.cococue.tebakbuahsayur.ads.BannerAdView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuScreen(
    onNavigateToCategory: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToHowToPlay: () -> Unit,
    onNavigateToAbout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6DD5FA),
                        Color(0xFF2980B9)
                    )
                )
            )
            .safeDrawingPadding()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            // Header / Title banner
            Text(
                text = "🎮 DUNIA CERIA 🎮",
                color = Color.Yellow,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tebak Buah, Sayur & Hewan",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(36.dp))

            // 2 x 2 Grid
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 1. Play Button
                    MenuCard(
                        title = "Bermain",
                        icon = "🚀",
                        backgroundColor = Color(0xFFFF6B6B),
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToCategory
                    )
                    // 2. History Score Button
                    MenuCard(
                        title = "Histori Skor",
                        icon = "🏆",
                        backgroundColor = Color(0xFF4ECDC4),
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToHistory
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 3. How to Play Button
                    MenuCard(
                        title = "Cara Main",
                        icon = "📖",
                        backgroundColor = Color(0xFFFFD166),
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToHowToPlay
                    )
                    // 4. About & Disclaimer Button
                    MenuCard(
                        title = "Tentang",
                        icon = "ℹ️",
                        backgroundColor = Color(0xFF6C5CE7),
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToAbout
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Versi 1.0 • Aman untuk Anak & Keluarga",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            BannerAdView()
        }
    }
}

@Composable
fun MenuCard(
    title: String,
    icon: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 42.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
