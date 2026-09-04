package com.cococue.tebakbuahsayur.ui

import android.app.Activity
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import com.cococue.tebakbuahsayur.ads.InterstitialAdManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cococue.tebakbuahsayur.data.GameCategory
import com.cococue.tebakbuahsayur.data.GameRepository
import com.cococue.tebakbuahsayur.ui.theme.TebakBuahSayurTheme
import java.util.Locale

@Composable
fun GameScreen(
    category: GameCategory,
    onGameFinished: (score: Int, total: Int, categoryName: String) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val questions = remember { GameRepository.getQuestions(category).shuffled().take(10) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isAnswerCorrect by remember { mutableStateOf<Boolean?>(null) }

    // Sound / Voice Guide state & TTS engine
    var isVoiceEnabled by remember { mutableStateOf(true) }
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var isTtsReady by remember { mutableStateOf(false) }

    DisposableEffect(context) {
        lateinit var textToSpeech: TextToSpeech
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale("id", "ID")

                // Friendly & cheerful voice adjustments for kids
                textToSpeech.setPitch(1.22f)       // Cheerful pitch
                textToSpeech.setSpeechRate(0.88f)   // Natural, clear articulation speed

                // Try to pick the best high-quality voice for Indonesian
                try {
                    val voices = textToSpeech.voices
                    if (!voices.isNullOrEmpty()) {
                        val indonesianVoice = voices.find { v ->
                            v.locale.language == "id" && v.quality == Voice.QUALITY_HIGH
                        } ?: voices.find { v ->
                            v.locale.language == "id"
                        }
                        if (indonesianVoice != null) {
                            textToSpeech.voice = indonesianVoice
                        }
                    }
                } catch (_: Exception) {
                    // Fallback to default
                }

                isTtsReady = true
            }
        }
        tts = textToSpeech

        onDispose {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }

    val currentQuestion = questions.getOrNull(currentIndex)
    val categoryTitle = if (category == GameCategory.FRUIT_VEG) "Tebak Buah & Sayur" else "Tebak Hewan"

    // Construct friendly, natural spoken text with expressive pauses
    val fullSpokenText = remember(currentQuestion) {
        currentQuestion?.let { q ->
            val optionsWithLetters = q.options.mapIndexed { index, option ->
                val label = listOf("A", "B", "C", "D").getOrElse(index) { "" }
                "$label: $option"
            }.joinToString(". ")
            "Halo adik-adik! ${q.title} Pilihannya yaitu... $optionsWithLetters."
        } ?: ""
    }

    // Speak question AND options when current question changes or voice is toggled on
    LaunchedEffect(currentIndex, isVoiceEnabled, isTtsReady) {
        if (isVoiceEnabled && isTtsReady && fullSpokenText.isNotEmpty()) {
            tts?.speak(fullSpokenText, TextToSpeech.QUEUE_FLUSH, null, "QuestionTTS")
        } else if (!isVoiceEnabled) {
            tts?.stop()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF833AB4),
                        Color(0xFFFD1D1D),
                        Color(0xFFFCB045)
                    )
                )
            )
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        if (currentQuestion == null) {
            // Safety check if questions are empty
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    tts?.stop()
                    onBack()
                }) {
                    Text(text = "Kembali ke Menu")
                }
            }
            return@Box
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Info
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Button(
                    onClick = {
                        tts?.stop()
                        onBack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.3f))
                ) {
                    Text(text = "✕ Keluar", color = Color.White)
                }

                Text(
                    text = categoryTitle,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

                // Voice Guide Toggle Button
                Button(
                    onClick = {
                        isVoiceEnabled = !isVoiceEnabled
                        if (!isVoiceEnabled) {
                            tts?.stop()
                        } else if (isTtsReady && fullSpokenText.isNotEmpty()) {
                            tts?.speak(fullSpokenText, TextToSpeech.QUEUE_FLUSH, null, "QuestionTTS")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isVoiceEnabled) Color.White.copy(alpha = 0.4f) else Color.Red.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = if (isVoiceEnabled) "🔊 Suara ON" else "🔇 Suara OFF",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Progress bar
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / questions.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                color = Color.Yellow,
                trackColor = Color.White.copy(alpha = 0.3f),
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Soal ${currentIndex + 1} dari ${questions.size}",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Question Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Visual Image container
                        Box(
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFF3B0)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = currentQuestion.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = currentQuestion.title,
                            color = Color(0xFF2D3142),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Replay Voice Button (Reads Question + All Options)
                        Button(
                            onClick = {
                                if (isTtsReady && fullSpokenText.isNotEmpty()) {
                                    tts?.speak(fullSpokenText, TextToSpeech.QUEUE_FLUSH, null, "QuestionTTS")
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4ECDC4)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text(
                                text = "🔊 Putar Ulang Soal & Pilihan",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Options
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp)
                    ) {
                        currentQuestion.options.forEach { option ->
                            val isSelected = selectedAnswer == option
                            val buttonColor = when {
                                selectedAnswer == null -> Color(0xFF3498DB)
                                isSelected && isAnswerCorrect == true -> Color(0xFF27AE60)
                                isSelected && isAnswerCorrect == false -> Color(0xFFE74C3C)
                                option == currentQuestion.correctAnswer -> Color(0xFF27AE60).copy(alpha = 0.7f)
                                else -> Color(0xFFBDC3C7)
                            }

                            Button(
                                onClick = {
                                    if (selectedAnswer == null) {
                                        selectedAnswer = option
                                        val correct = option == currentQuestion.correctAnswer
                                        isAnswerCorrect = correct
                                        if (correct) {
                                            score += 10
                                            if (isVoiceEnabled && isTtsReady) {
                                                tts?.speak("Hore! Kamu memilih $option. Jawabanmu benar sekali!", TextToSpeech.QUEUE_FLUSH, null, "FeedbackTTS")
                                            }
                                        } else {
                                            if (isVoiceEnabled && isTtsReady) {
                                                tts?.speak("Kamu memilih $option. Ups, jawaban belum tepat! Yang benar adalah ${currentQuestion.correctAnswer}. Tetap semangat ya!", TextToSpeech.QUEUE_FLUSH, null, "FeedbackTTS")
                                            }
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = option,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // Next button when answered
                    if (selectedAnswer != null) {
                        Button(
                            onClick = {
                                selectedAnswer = null
                                isAnswerCorrect = null
                                if (currentIndex < questions.size - 1) {
                                    currentIndex++
                                } else {
                                    tts?.stop()
                                    val activity = context as? Activity
                                    if (activity != null) {
                                        InterstitialAdManager.showAd(activity) {
                                            onGameFinished(score, questions.size * 10, categoryTitle)
                                        }
                                    } else {
                                        onGameFinished(score, questions.size * 10, categoryTitle)
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B)),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = if (currentIndex < questions.size - 1) "Soal Berikutnya ➡️" else "Lihat Hasil 🎉",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    TebakBuahSayurTheme {
        GameScreen(
            category = GameCategory.FRUIT_VEG,
            onGameFinished = { _, _, _ -> },
            onBack = {}
        )
    }
}
