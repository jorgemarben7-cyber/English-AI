package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.data.EducationalContent
import com.example.data.LanguageMode
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.theme.CosmicViolet
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.RocketOrange
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold
import com.example.ui.theme.SuccessGreen

@Composable
fun ProgressScreen(
    xp: Int,
    alias: String,
    avatar: String,
    completedMissions: Set<String>,
    unlockedBadges: Set<String>,
    diagnosticScores: Triple<Int, Int, Int>, // vocab, grammar, freq
    isDiagnosticDone: Boolean,
    rubricScores: Map<String, Int>,
    languageMode: LanguageMode,
    onResetProgress: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showResetDialog by remember { mutableStateOf(false) }

    val missionKeys = listOf("m_zero", "m_routines", "m_vocab_lab", "m_grammar", "m_reading", "m_wall", "m_final")
    val completedCount = missionKeys.count { completedMissions.contains(it) }

    val explorerLevel = when {
        xp >= 800 -> "Galaxy Master / Maestro Galáctico"
        xp >= 500 -> "Orbit Navigator / Navegante Orbital"
        xp >= 250 -> "Star Explorer / Explorador Estelar"
        else -> "Cosmic Cadet / Cadete Cósmico"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(StarGold.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🏆", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "PROGRESS & BADGES / PROGRESO",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = StarGold
                )
                Text(
                    text = "Logros, Puntos XP y Comparativa de Aprendizaje",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Explorer Stats Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = avatar, fontSize = 36.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = alias,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = explorerLevel,
                            fontSize = 12.sp,
                            color = ElectricCyan,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatBox("⭐ Total XP", "$xp XP", StarGold, Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(8.dp))
                    StatBox("🚀 Missions", "$completedCount / 7", ElectricCyan, Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(8.dp))
                    StatBox("🏅 Badges", "${unlockedBadges.size} / 6", CosmicViolet, Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Diagnostic vs Progress Comparison Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "📊 LEARNING EVOLUTION / COMPARATIVA:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color.White,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Initial Diagnostic Baseline vs Current Mastery / Diagnóstico vs Desempeño actual",
                    fontSize = 11.sp,
                    color = SpanishHintColor
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (isDiagnosticDone) {
                    val (v, g, f) = diagnosticScores
                    ComparisonBar(
                        component = "Daily Routines Vocabulary",
                        diagScore = v,
                        diagMax = 5,
                        currentStatus = if (completedMissions.contains("m_routines") || completedMissions.contains("m_vocab_lab")) "Mastered (+40% gain)" else "In progress"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ComparisonBar(
                        component = "Present Simple & 3rd Person",
                        diagScore = g,
                        diagMax = 5,
                        currentStatus = if (completedMissions.contains("m_grammar")) "Mastered (Rules applied)" else "In progress"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ComparisonBar(
                        component = "Adverbs of Frequency",
                        diagScore = f,
                        diagMax = 5,
                        currentStatus = if (completedMissions.contains("m_wall")) "4+ Adverbs Used in Writing" else "In progress"
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SpaceCardElevated)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Complete Mission Zero (Diagnostic) to view your baseline comparison!",
                            fontSize = 11.sp,
                            color = StarGold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Badges Showcase
        Text(
            text = "INSIGNIAS Y LOGROS / BADGES SHOWCASE:",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        EducationalContent.badges.forEach { badge ->
            val isUnlocked = unlockedBadges.contains(badge.id)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isUnlocked) SpaceCardElevated else SpaceCardNavy.copy(alpha = 0.6f)
                ),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(if (isUnlocked) StarGold else SpaceBorder)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(if (isUnlocked) StarGold.copy(alpha = 0.2f) else SpaceBorder.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isUnlocked) {
                            Text(text = badge.iconEmoji, fontSize = 22.sp)
                        } else {
                            Icon(imageVector = Icons.Default.Lock, contentDescription = "Locked", tint = Color.Gray, modifier = Modifier.size(18.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${badge.titleEn} / ${badge.titleEs}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (isUnlocked) StarGold else Color.Gray
                        )
                        Text(
                            text = badge.descriptionEn,
                            fontSize = 11.sp,
                            color = if (isUnlocked) Color.White else Color.Gray
                        )
                        if (languageMode != LanguageMode.EN_ONLY) {
                            Text(
                                text = badge.descriptionEs,
                                fontSize = 10.sp,
                                color = SpanishHintColor
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Privacy & Reset Progress
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = ElectricCyan, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Privacy & Local Storage / Privacidad Local:", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ElectricCyan)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "All progress, XP, and written routines are stored locally on this device. No personal data, real names, or emails are requested or transmitted.\n\nTodos los datos y avances se guardan únicamente en este navegador/dispositivo. No se recopilan datos personales.",
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    lineHeight = 14.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    onClick = { showResetDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.DeleteForever, contentDescription = null, tint = ErrorRose, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Reset All Progress / Reiniciar Progreso", color = ErrorRose, fontSize = 12.sp)
                }
            }
        }
    }

    // Reset Confirmation Dialog
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset Progress? / ¿Reiniciar?", color = Color.White) },
            text = {
                Text(
                    "Are you sure you want to reset your XP, badges, and completed missions? This cannot be undone.\n\n¿Estás seguro de que deseas reiniciar todos tus puntos, insignias y misiones?",
                    color = Color.LightGray,
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onResetProgress()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRose)
                ) {
                    Text("Confirm Reset / Confirmar", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancel", color = Color.Gray)
                }
            },
            containerColor = SpaceCardNavy
        )
    }
}

@Composable
private fun StatBox(label: String, value: String, accent: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SpaceCardElevated)
            .border(1.dp, SpaceBorder, RoundedCornerShape(10.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, fontSize = 10.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = accent)
        }
    }
}

@Composable
private fun ComparisonBar(component: String, diagScore: Int, diagMax: Int, currentStatus: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = component, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            Text(text = "Baseline: $diagScore / $diagMax", fontSize = 11.sp, color = StarGold)
        }
        Spacer(modifier = Modifier.height(2.dp))
        LinearProgressIndicator(
            progress = { diagScore.toFloat() / diagMax.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = StarGold,
            trackColor = SpaceBorder
        )
        Text(text = "Current: $currentStatus", fontSize = 10.sp, color = SuccessGreen, fontWeight = FontWeight.SemiBold)
    }
}
