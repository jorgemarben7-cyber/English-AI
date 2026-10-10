package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.EducationalContent
import com.example.data.LanguageMode
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.RocketOrange
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold
import com.example.ui.theme.SuccessGreen

@Composable
fun HomeScreen(
    xp: Int,
    alias: String,
    avatar: String,
    completedMissions: Set<String>,
    languageMode: LanguageMode,
    onNavigate: (AppScreen) -> Unit,
    onUpdateAlias: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showEditProfile by remember { mutableStateOf(false) }
    var editedAlias by remember { mutableStateOf(alias) }
    var editedAvatar by remember { mutableStateOf(avatar) }
    var showFirstTimeGuide by remember { mutableStateOf(completedMissions.isEmpty()) }

    val missionKeys = listOf("m_zero", "m_routines", "m_vocab_lab", "m_grammar", "m_reading", "m_wall", "m_final")
    val completedCount = missionKeys.count { completedMissions.contains(it) }
    val progressFraction = completedCount.toFloat() / missionKeys.size.toFloat()

    // Determine next mission to continue
    val nextScreen = when {
        !completedMissions.contains("m_zero") -> AppScreen.MISSION_ZERO
        !completedMissions.contains("m_routines") -> AppScreen.ACTIVATE_EXPLORE
        !completedMissions.contains("m_vocab_lab") -> AppScreen.VOCABULARY_LAB
        !completedMissions.contains("m_grammar") -> AppScreen.GRAMMAR_CHALLENGE
        !completedMissions.contains("m_reading") -> AppScreen.READING_MISSION
        !completedMissions.contains("m_wall") -> AppScreen.ROUTINE_WALL
        !completedMissions.contains("m_final") -> AppScreen.FINAL_MISSION
        else -> AppScreen.PROGRESS
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 48.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Banner Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_hero_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_space_adventure),
                        contentDescription = "Cosmic space adventure banner",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, SpaceCardNavy.copy(alpha = 0.95f))
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(RocketOrange)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "GRADE 7TH / GRADO 7°",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(ElectricCyan.copy(alpha = 0.25f))
                                    .border(1.dp, ElectricCyan, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "DAILY ROUTINES",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ElectricCyan
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "ENGLISH ADVENTURE",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "Learn, Play and Communicate",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ElectricCyan
                        )
                        Text(
                            text = "Every day is a new adventure! / ¡Cada día es una nueva aventura!",
                            fontSize = 12.sp,
                            color = StarGold
                        )
                    }
                }
            }
        }

        // Student Profile & Progress Summary
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardElevated),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(ElectricBlue, SpaceBorder)))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(ElectricCyan.copy(alpha = 0.2f))
                                .border(1.5.dp, ElectricCyan, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = avatar, fontSize = 26.sp)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Cadet: $alias",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                            Text(
                                text = "A1-A2 Beginner Explorer • Explorador Inicial",
                                fontSize = 12.sp,
                                color = SpanishHintColor
                            )
                        }
                        IconButton(
                            onClick = { showEditProfile = !showEditProfile },
                            modifier = Modifier.testTag("edit_profile_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit profile",
                                tint = ElectricCyan,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Edit Profile Expandable Box
                    AnimatedVisibility(visible = showEditProfile) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(SpaceCardNavy)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Choose Explorer Alias & Avatar:",
                                fontSize = 12.sp,
                                color = ElectricCyan,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                listOf("🧑‍🚀", "🚀", "👩‍🚀", "🛸", "⭐", "👾").forEach { emoji ->
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(if (editedAvatar == emoji) ElectricCyan.copy(alpha = 0.4f) else Color.Transparent)
                                            .border(1.dp, if (editedAvatar == emoji) ElectricCyan else Color.Transparent, CircleShape)
                                            .clickable { editedAvatar = emoji },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = emoji, fontSize = 20.sp)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = editedAlias,
                                onValueChange = { editedAlias = it },
                                label = { Text("Explorer Alias (no personal data)") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedBorderColor = ElectricCyan,
                                    unfocusedBorderColor = SpaceBorder
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = { showEditProfile = false }) {
                                    Text("Cancel", color = Color.Gray)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        if (editedAlias.isNotBlank()) {
                                            onUpdateAlias(editedAlias.trim(), editedAvatar)
                                        }
                                        showEditProfile = false
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan)
                                ) {
                                    Text("Save / Guardar", color = Color.Black, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Progress Bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Adventure Progress / Progreso:",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        Text(
                            text = "$completedCount of 7 Missions (${(progressFraction * 100).toInt()}%)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricCyan
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = ElectricCyan,
                        trackColor = SpaceBorder,
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Big Action Button: Continue / Start Adventure
                    Button(
                        onClick = { onNavigate(nextScreen) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("continue_adventure_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ElectricCyan
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (completedCount == 0) Icons.Default.PlayArrow else Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color(0xFF031E33),
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (completedCount == 0) "Start Adventure / Comenzar Aventura" else "Continue Mission / Continuar Misión",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF031E33)
                            )
                        }
                    }
                }
            }
        }

        // First Time User Guide (3 Steps)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(SpaceBorder, ElectricCyan.copy(alpha = 0.3f))))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🧭 How to Complete Missions / Guía de 3 Pasos",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = StarGold
                        )
                        Text(
                            text = if (showFirstTimeGuide) "Hide / Ocultar" else "Show / Ver",
                            fontSize = 11.sp,
                            color = ElectricCyan,
                            modifier = Modifier.clickable { showFirstTimeGuide = !showFirstTimeGuide }
                        )
                    }

                    AnimatedVisibility(visible = showFirstTimeGuide) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            GuideStepRow(
                                stepNumber = "1",
                                enTitle = "Explore & Listen",
                                esTitle = "Explora y escucha",
                                enDesc = "Learn the 10 routine actions and tap speaker icons for US English pronunciation.",
                                esDesc = "Aprende las 10 rutinas y toca el parlante para escuchar la pronunciación."
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            GuideStepRow(
                                stepNumber = "2",
                                enTitle = "Solve Interactive Missions",
                                esTitle = "Resuelve misiones interactivas",
                                enDesc = "Complete diagnostic, vocab lab, grammar rules and reading stories at your pace.",
                                esDesc = "Supera el diagnóstico, laboratorio, gramática y lecturas a tu propio ritmo."
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            GuideStepRow(
                                stepNumber = "3",
                                enTitle = "Write Wall & Present Orally",
                                esTitle = "Escribe tu rutina y presenta",
                                enDesc = "Publish 5-7 sentences on the Wall and record your presentation with the rubric.",
                                esDesc = "Publica 5-7 oraciones en el muro y graba tu presentación con la rúbrica."
                            )
                        }
                    }
                }
            }
        }

        // Section Title: Missions Roadmap
        item {
            Text(
                text = "MISSION FLIGHT PLAN / PLAN DE VUELO",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricCyan,
                letterSpacing = 1.sp
            )
        }

        // Missions List
        items(EducationalContentMissions) { mission ->
            val isDone = completedMissions.contains(mission.idKey)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(mission.screen) }
                    .testTag("mission_card_${mission.idKey}"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDone) SpaceCardNavy.copy(alpha = 0.85f) else SpaceCardNavy
                ),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(
                        if (isDone) listOf(SuccessGreen.copy(alpha = 0.6f), SpaceBorder)
                        else listOf(SpaceBorder, ElectricCyan.copy(alpha = 0.4f))
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isDone) SuccessGreen.copy(alpha = 0.15f) else ElectricCyan.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = mission.emoji, fontSize = 24.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = mission.tag,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isDone) SuccessGreen else ElectricCyan
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "+${mission.xpReward} XP",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = StarGold
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = mission.titleEn,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                        if (languageMode != LanguageMode.EN_ONLY) {
                            Text(
                                text = mission.titleEs,
                                fontSize = 12.sp,
                                color = SpanishHintColor
                            )
                        }
                    }

                    if (isDone) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SuccessGreen.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Done",
                                    tint = SuccessGreen,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Done",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SuccessGreen
                                )
                            }
                        }
                    } else {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Go",
                            tint = ElectricCyan,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GuideStepRow(
    stepNumber: String,
    enTitle: String,
    esTitle: String,
    enDesc: String,
    esDesc: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(ElectricCyan),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = "$enTitle / $esTitle",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.White
            )
            Text(
                text = "$enDesc\n$esDesc",
                fontSize = 11.sp,
                color = Color.LightGray,
                lineHeight = 15.sp
            )
        }
    }
}

data class MissionCardInfo(
    val idKey: String,
    val screen: AppScreen,
    val tag: String,
    val emoji: String,
    val titleEn: String,
    val titleEs: String,
    val xpReward: Int
)

val EducationalContentMissions = listOf(
    MissionCardInfo("m_zero", AppScreen.MISSION_ZERO, "MISSION 0", "🎯", "Mission Zero: Initial Diagnostic", "Diagnóstico Inicial (15 Preguntas)", 150),
    MissionCardInfo("m_routines", AppScreen.ACTIVATE_EXPLORE, "MISSION 1", "☀️", "Daily Routines Explorer", "Tarjetas de Rutinas y Pronunciación", 100),
    MissionCardInfo("m_vocab_lab", AppScreen.VOCABULARY_LAB, "MISSION 2", "🧪", "Vocabulary Lab & Pair Work", "Retos por Tiers y Trabajo en Parejas", 120),
    MissionCardInfo("m_grammar", AppScreen.GRAMMAR_CHALLENGE, "MISSION 3", "⚡", "Grammar Challenge: Present Simple", "Presente Simple y Adverbios de Frecuencia", 140),
    MissionCardInfo("m_reading", AppScreen.READING_MISSION, "MISSION 4", "📖", "Reading Mission: Teen Routines", "Lecturas de Rutinas de Adolescentes", 130),
    MissionCardInfo("m_wall", AppScreen.ROUTINE_WALL, "MISSION 5", "✍️", "My Daily Routine Wall", "Taller de Escritura y Publicación", 100),
    MissionCardInfo("m_final", AppScreen.FINAL_MISSION, "MISSION FINAL", "🎤", "Learn, Play & Communicate", "Presentación Oral, Audio y Rúbrica", 120)
)
