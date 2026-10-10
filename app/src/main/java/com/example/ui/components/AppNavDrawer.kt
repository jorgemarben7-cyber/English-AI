package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageMode
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceDeepNight
import com.example.ui.theme.StarGold
import com.example.ui.theme.SuccessGreen

enum class AppScreen {
    HOME,
    MISSION_ZERO,
    ACTIVATE_EXPLORE,
    VOCABULARY_LAB,
    GRAMMAR_CHALLENGE,
    READING_MISSION,
    ROUTINE_WALL,
    FINAL_MISSION,
    PROGRESS,
    HELP_GLOSSARY,
    PRINTABLE_SHEET
}

data class NavItem(
    val screen: AppScreen,
    val missionCode: String,
    val titleEn: String,
    val titleEs: String,
    val iconEmoji: String,
    val missionIdKey: String? = null
)

val NAV_ITEMS = listOf(
    NavItem(AppScreen.HOME, "BASE", "Mission Control (Home)", "Centro de Mando (Inicio)", "🚀"),
    NavItem(AppScreen.MISSION_ZERO, "M0", "Mission Zero: Diagnostic", "Misión Cero: Diagnóstico", "🎯", "m_zero"),
    NavItem(AppScreen.ACTIVATE_EXPLORE, "M1", "Daily Routines Explorer", "Explorador de Rutinas", "☀️", "m_routines"),
    NavItem(AppScreen.VOCABULARY_LAB, "M2", "Vocabulary Lab", "Laboratorio de Vocabulario", "🧪", "m_vocab_lab"),
    NavItem(AppScreen.GRAMMAR_CHALLENGE, "M3", "Grammar Challenge", "Desafío Gramatical", "⚡", "m_grammar"),
    NavItem(AppScreen.READING_MISSION, "M4", "Reading Mission", "Misión de Lectura", "📖", "m_reading"),
    NavItem(AppScreen.ROUTINE_WALL, "M5", "My Daily Routine Wall", "Muro de Mi Rutina", "✍️", "m_wall"),
    NavItem(AppScreen.FINAL_MISSION, "M6", "Final Mission: Presentation", "Misión Final: Presentación", "🎤", "m_final"),
    NavItem(AppScreen.PROGRESS, "EXP", "Badges & Progress", "Insignias y Progreso", "🏆"),
    NavItem(AppScreen.HELP_GLOSSARY, "HELP", "Help & Routines Glossary", "Ayuda y Glosario", "💡"),
    NavItem(AppScreen.PRINTABLE_SHEET, "PRINT", "Printable Classroom Guide", "Guía Imprimible de Clase", "📄")
)

@Composable
fun AppNavDrawer(
    currentScreen: AppScreen,
    completedMissions: Set<String>,
    alias: String,
    avatar: String,
    xp: Int,
    languageMode: LanguageMode,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxHeight()
            .width(310.dp),
        color = SpaceDeepNight
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header Profile Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SpaceCardElevated)
                    .border(1.dp, SpaceBorder, RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(ElectricCyan.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = avatar, fontSize = 24.sp)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = alias,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Grade 7° • Grado 7°",
                            fontSize = 12.sp,
                            color = ElectricCyan
                        )
                        Text(
                            text = "⭐ $xp XP points",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StarGold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "EXPLORATION MISSIONS / MISIONES",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricCyan.copy(alpha = 0.8f),
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Navigation Items
            NAV_ITEMS.forEach { item ->
                val isSelected = currentScreen == item.screen
                val isCompleted = item.missionIdKey != null && completedMissions.contains(item.missionIdKey)

                val bgColor = if (isSelected) ElectricCyan.copy(alpha = 0.22f) else Color.Transparent
                val borderColor = if (isSelected) ElectricCyan.copy(alpha = 0.5f) else Color.Transparent

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(bgColor)
                        .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                        .clickable { onNavigate(item.screen) }
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                        .testTag("nav_item_${item.screen.name}"),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.iconEmoji, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.titleEn,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) ElectricCyan else Color.White
                        )
                        if (languageMode != LanguageMode.EN_ONLY) {
                            Text(
                                text = item.titleEs,
                                fontSize = 11.sp,
                                color = if (isSelected) StarGold else Color.Gray
                            )
                        }
                    }

                    if (isCompleted) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Completed",
                            tint = SuccessGreen,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = SpaceBorder)
            Spacer(modifier = Modifier.height(12.dp))

            // Footer note
            Text(
                text = "English Adventure • Grade 7th\nUTB Maestría TIC • Octubre 2026",
                fontSize = 10.sp,
                color = Color.Gray,
                lineHeight = 14.sp
            )
        }
    }
}
