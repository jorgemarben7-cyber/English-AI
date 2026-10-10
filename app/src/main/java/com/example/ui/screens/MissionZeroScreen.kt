package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DiagnosticQuestion
import com.example.data.EducationalContent
import com.example.data.LanguageMode
import com.example.data.QuestionCategory
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.components.PedagogicalFeedbackCard
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.RocketOrange
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpaceDeepNight
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold
import com.example.ui.theme.SuccessGreen

@Composable
fun MissionZeroScreen(
    languageMode: LanguageMode,
    onSaveResults: (vocab: Int, grammar: Int, freq: Int) -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val questions = EducationalContent.diagnosticQuestions
    var currentIndex by remember { mutableIntStateOf(0) }
    val userAnswers = remember { mutableStateMapOf<Int, Int>() } // questionId -> selectedIndex
    var isSubmitted by remember { mutableStateOf(false) }
    var showReviewList by remember { mutableStateOf(false) }

    val currentQuestion = questions[currentIndex]
    val selectedOptionIndex = userAnswers[currentQuestion.id]

    // Calculate scores
    val vocabScore = questions.filter { it.category == QuestionCategory.VOCABULARY }
        .count { userAnswers[it.id] == it.correctIndex }
    val grammarScore = questions.filter { it.category == QuestionCategory.GRAMMAR }
        .count { userAnswers[it.id] == it.correctIndex }
    val freqScore = questions.filter { it.category == QuestionCategory.FREQUENCY }
        .count { userAnswers[it.id] == it.correctIndex }
    val totalScore = vocabScore + grammarScore + freqScore

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Mission Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(RocketOrange.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🎯", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "MISSION ZERO: INITIAL DIAGNOSTIC",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = RocketOrange
                )
                Text(
                    text = "Diagnóstico Inicial • Grado 7°",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (!isSubmitted) {
            // Assessment Progress & Instructions
            BilingualInstructionCard(
                enInstruction = "Read each question and choose the best answer. The correct answers will be revealed in your diagnostic report at the end.",
                esInstruction = "Lee cada pregunta y selecciona la mejor respuesta. Las respuestas correctas se revelarán en tu reporte diagnóstico final.",
                languageMode = languageMode
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Progress Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${currentIndex + 1} of ${questions.size}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = when (currentQuestion.category) {
                        QuestionCategory.VOCABULARY -> "Vocab 🗂️"
                        QuestionCategory.GRAMMAR -> "Grammar ⚡"
                        QuestionCategory.FREQUENCY -> "Frequency ⏳"
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = StarGold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / questions.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = ElectricCyan,
                trackColor = SpaceBorder
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Question Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    BilingualText(
                        en = currentQuestion.promptEn,
                        es = currentQuestion.promptEs,
                        languageMode = languageMode,
                        enSize = 16.sp,
                        esSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Options List
                    currentQuestion.optionsEn.forEachIndexed { optIndex, optionEn ->
                        val optionEs = currentQuestion.optionsEs.getOrElse(optIndex) { "" }
                        val isSelected = selectedOptionIndex == optIndex

                        val itemBg = if (isSelected) ElectricCyan.copy(alpha = 0.2f) else SpaceCardElevated
                        val itemBorder = if (isSelected) ElectricCyan else SpaceBorder

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(itemBg)
                                .border(1.5.dp, itemBorder, RoundedCornerShape(12.dp))
                                .clickable {
                                    userAnswers[currentQuestion.id] = optIndex
                                }
                                .padding(12.dp)
                                .testTag("diag_opt_${currentQuestion.id}_$optIndex"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) ElectricCyan else SpaceBorder),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ('A' + optIndex).toString(),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color.Black else Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = optionEn,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = Color.White
                                )
                                if (languageMode != LanguageMode.EN_ONLY && optionEs.isNotBlank()) {
                                    Text(
                                        text = optionEs,
                                        fontSize = 11.sp,
                                        color = SpanishHintColor
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Navigation Buttons (Prev / Next / Finish)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Previous / Anterior")
                }

                if (currentIndex < questions.size - 1) {
                    Button(
                        onClick = { currentIndex++ },
                        enabled = selectedOptionIndex != null,
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Next / Siguiente", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Button(
                        onClick = {
                            isSubmitted = true
                            onSaveResults(vocabScore, grammarScore, freqScore)
                        },
                        enabled = userAnswers.size >= questions.size,
                        colors = ButtonDefaults.buttonColors(containerColor = RocketOrange),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("submit_diagnostic_button")
                    ) {
                        Text("Complete / Finalizar", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        } else {
            // DIAGNOSTIC REPORT RESULTS
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ElectricCyan))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "DIAGNOSTIC RESULTS REPORT",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Reporte Diagnóstico Inicial • Grado 7°",
                        fontSize = 13.sp,
                        color = ElectricCyan
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Notice explaining diagnostic purpose
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(StarGold.copy(alpha = 0.15f))
                            .border(1.dp, StarGold.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Row {
                            Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = StarGold, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Notice: This initial diagnostic is designed to identify prior knowledge and guide differentiated practice, not as a final grade!\n\nAviso: Este diagnóstico inicial permite identificar tus saberes previos y orientar el refuerzo, ¡no es una calificación definitiva!",
                                fontSize = 11.sp,
                                color = Color.White,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Total Score
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "Overall Performance:", fontSize = 13.sp, color = Color.Gray)
                            Text(
                                text = "$totalScore / ${questions.size} Points (${(totalScore * 100 / questions.size)}%)",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (totalScore >= 11) SuccessGreen else StarGold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(ElectricCyan.copy(alpha = 0.2f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = when {
                                    totalScore >= 13 -> "Superior / Excelente"
                                    totalScore >= 10 -> "High / Alto"
                                    totalScore >= 7 -> "Basic / Básico"
                                    else -> "Initial / Inicial"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = ElectricCyan
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Component Breakdown
                    Text(
                        text = "Results by Component / Resultados por Componente:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    ComponentScoreRow("Vocabulary / Vocabulario", vocabScore, 5, "Daily routines (wake up, take a shower, etc.)")
                    ComponentScoreRow("Grammar / Gramática", grammarScore, 5, "Present Simple affirmative, 3rd person singular -s/-es")
                    ComponentScoreRow("Frequency Adverbs / Adverbios", freqScore, 5, "always, usually, sometimes, never position & hierarchy")

                    Spacer(modifier = Modifier.height(16.dp))

                    // Strengths & Recommendations
                    Text(
                        text = "🌟 Strengths Identified / Fortalezas:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = StarGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (vocabScore >= 4) {
                        Text(text = "• Strong routine vocabulary recognition! (Excelente reconocimiento de vocabulario de rutinas)", fontSize = 12.sp, color = Color.LightGray)
                    }
                    if (grammarScore >= 4) {
                        Text(text = "• Solid mastery of 3rd person singular in Present Simple! (Sólido dominio de la 3ª persona del singular)", fontSize = 12.sp, color = Color.LightGray)
                    }
                    if (freqScore >= 4) {
                        Text(text = "• Clear understanding of frequency adverb hierarchy and positioning! (Clara comprensión de adverbios de frecuencia)", fontSize = 12.sp, color = Color.LightGray)
                    }
                    if (vocabScore < 4 && grammarScore < 4 && freqScore < 4) {
                        Text(text = "• Great initial engagement! You are ready to explore and learn step by step.", fontSize = 12.sp, color = Color.LightGray)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "🎯 Recommended Practice / Rutas de Práctica:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = ElectricCyan
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (vocabScore < 4) {
                        Text(text = "• Practice in Mission 1 (Daily Routines Flashcards) & Mission 2 (Vocabulary Lab).", fontSize = 12.sp, color = Color.LightGray)
                    }
                    if (grammarScore < 4) {
                        Text(text = "• Focus on Mission 3 (Grammar Challenge): review 3rd person -s/-es/-ies rules and negative doesn't.", fontSize = 12.sp, color = Color.LightGray)
                    }
                    if (freqScore < 4) {
                        Text(text = "• Review the frequency percentage chart in the Help section and practice adverb positioning before verbs.", fontSize = 12.sp, color = Color.LightGray)
                    }
                    Text(text = "• Read stories in Mission 4 to observe language in authentic teenager contexts.", fontSize = 12.sp, color = Color.LightGray)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Toggle Detailed Answers Review
                    Button(
                        onClick = { showReviewList = !showReviewList },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = SpaceCardElevated),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = if (showReviewList) "Hide Answer Key / Ocultar Solucionario" else "Review All Answers & Explanations / Ver Respuestas Explicadas",
                            color = ElectricCyan,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Detailed Answers
                    AnimatedVisibility(visible = showReviewList) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            questions.forEachIndexed { idx, q ->
                                val userChoice = userAnswers[q.id] ?: -1
                                val isCorrect = userChoice == q.correctIndex

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = SpaceDeepNight),
                                    border = CardDefaults.outlinedCardBorder().copy(
                                        brush = androidx.compose.ui.graphics.SolidColor(if (isCorrect) SuccessGreen else ErrorRose)
                                    )
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                                                contentDescription = null,
                                                tint = if (isCorrect) SuccessGreen else ErrorRose,
                                                modifier = Modifier.size(18.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "Item ${idx + 1}: ${q.promptEn}",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.sp,
                                                color = Color.White
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        val correctText = q.optionsEn.getOrNull(q.correctIndex) ?: ""
                                        val yourText = q.optionsEn.getOrNull(userChoice) ?: "None"
                                        Text(
                                            text = "Your answer: $yourText ${if (isCorrect) "✓" else "✗"}\nCorrect: $correctText",
                                            fontSize = 12.sp,
                                            color = if (isCorrect) SuccessGreen else ErrorRose
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Pedagogical Note / Explicación:\n${q.explanationEn}\n${q.explanationEs}",
                                            fontSize = 11.sp,
                                            color = SpanishHintColor,
                                            lineHeight = 15.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Continue to Mission 1 Button
                    Button(
                        onClick = { onNavigate(AppScreen.ACTIVATE_EXPLORE) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("continue_to_routines_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Continue to Mission 1 (Daily Routines) / Ir a Misión 1", color = Color.Black, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.Black, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ComponentScoreRow(name: String, score: Int, max: Int, description: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            Text(text = "$score / $max", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ElectricCyan)
        }
        Spacer(modifier = Modifier.height(2.dp))
        LinearProgressIndicator(
            progress = { score.toFloat() / max.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = if (score >= 4) SuccessGreen else if (score >= 2) StarGold else ErrorRose,
            trackColor = SpaceBorder
        )
        Text(text = description, fontSize = 10.sp, color = Color.Gray)
    }
}
