package com.example.ui.screens

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
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.EducationalContent
import com.example.data.LanguageMode
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.RocketOrange
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold
import com.example.ui.theme.SuccessGreen

@Composable
fun HelpGlossaryScreen(
    languageMode: LanguageMode,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
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
                    .background(ElectricCyan.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "💡", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "HELP & ROUTINES GLOSSARY",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = ElectricCyan
                )
                Text(
                    text = "Centro de Ayuda, Glosario y Consejos de Aprendizaje",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Quick Navigation Tips
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "🚀 How to Navigate / Cómo Navegar:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = StarGold
                )
                Spacer(modifier = Modifier.height(6.dp))
                HelpBullet("Top Menu (☰):", "Access any of the 7 exploration missions at any time.", "Accede a cualquiera de las 7 misiones cuando lo desees.")
                HelpBullet("Language Toggle (EN/ES):", "Toggle English only, Spanish only, or bilingual mode.", "Alterna entre solo inglés, solo español o modo bilingüe.")
                HelpBullet("Speaker Icons (🔊):", "Listen to correct US English audio pronunciation.", "Escucha la pronunciación correcta en inglés estadounidense.")
                HelpBullet("Retry Buttons (🔄):", "You can retry questions anytime without severe penalties.", "Puedes reintentar preguntas para aprender del error sin penalización.")
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Frequency Adverbs Scale Ladder
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(StarGold.copy(alpha = 0.4f)))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "⏳ Adverbs of Frequency Ladder / Escala de Frecuencia:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = StarGold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FrequencyLadderRow("ALWAYS", "100%", "Siempre (All the time)", SuccessGreen)
                FrequencyLadderRow("USUALLY", "80%", "Usualmente / Normalmente", ElectricCyan)
                FrequencyLadderRow("OFTEN", "70%", "A menudo / Frecuentemente", ElectricCyan)
                FrequencyLadderRow("SOMETIMES", "50%", "A veces (Half the time)", StarGold)
                FrequencyLadderRow("RARELY", "20%", "Rara vez (Seldom)", RocketOrange)
                FrequencyLadderRow("NEVER", "0%", "Nunca (At no time)", Color.Red)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Daily Routines Vocabulary Glossary
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "📚 Daily Routines Glossary / Glosario Completo:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = ElectricCyan
                )
                Spacer(modifier = Modifier.height(8.dp))

                EducationalContent.routineCards.forEach { card ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = card.iconEmoji, fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = card.expression, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
                            Text(text = card.spanishMeaning, fontSize = 11.sp, color = SpanishHintColor)
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SpaceCardElevated)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(text = card.timeCategory.name, fontSize = 9.sp, color = StarGold)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Classroom Contingencies & Offline Notice
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardElevated),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Offline Readiness & Contingency / Modo Offline y Contingencias:", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = SuccessGreen)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "• All 7 missions and quizzes work 100% offline on this device!\n" +
                           "• Single Device per Pair: Use Pair Work mode with Reader, Solver, and Checker roles.\n" +
                           "• Low Connectivity: All resources are bundled within the app. No external API keys are required for the core educational curriculum.",
                    fontSize = 11.sp,
                    color = Color.LightGray,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Printable Classroom Guide Button
        Button(
            onClick = { onNavigate(AppScreen.PRINTABLE_SHEET) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Print, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Open Printable Classroom Worksheet / Ficha de Clase", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Pedagogical Alignment Matrix Card (Anexo 1 - UTB)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ElectricCyan.copy(alpha = 0.5f)))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "🎯 MATRIZ DE ALINEACIÓN PEDAGÓGICA (ANEXO 1 - UTB):",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = ElectricCyan,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                MatrixItemRow(
                    target = "1. Vocabulario de rutinas (10 acciones)",
                    strategies = "Estrategias 1, 2 y 3 (Flashcards, Match, Vocab Lab)",
                    evidence = "Asociaciones, clasificación y producción oral/escrita",
                    criteria = "Al menos 80% de aciertos en vocabulario clave"
                )
                MatrixItemRow(
                    target = "2. Presente simple (3ª persona -s, -es, -ies y negativa)",
                    strategies = "Estrategias 1 y 4 (Mission Zero & Grammar Challenge)",
                    evidence = "Selección, corrección de errores y construcción de oraciones",
                    criteria = "Al menos 75% de precisión en ejercicios estructurados"
                )
                MatrixItemRow(
                    target = "3. Adverbios de frecuencia (always, usually, etc.)",
                    strategies = "Estrategias 2, 4 y 6 (Gramática, Muro y Misión Final)",
                    evidence = "Ubicación sintáctica y producción propia de oraciones",
                    criteria = "Usa correctamente al menos 4 adverbios en producción propia"
                )
                MatrixItemRow(
                    target = "4. Comprensión de textos y audios de rutinas",
                    strategies = "Estrategias 1, 2 y 5 (Diagnóstico y Reading Mission)",
                    evidence = "Identificación de información explícita y secuencia cronológica",
                    criteria = "Responde correctamente al menos 75% de preguntas"
                )
                MatrixItemRow(
                    target = "5. Producción escrita (Rutina diaria)",
                    strategies = "Estrategias 5 y 6 (My Daily Routine Wall)",
                    evidence = "Texto de 5–7 oraciones comprensibles con conectores",
                    criteria = "Incluye vocabulario, presente simple y adverbios con coherencia"
                )
                MatrixItemRow(
                    target = "6. Producción oral (Presentación de rutina)",
                    strategies = "Estrategias 5 y 6 (Final Mission: Studio & Audio)",
                    evidence = "Exposición de 5–7 oraciones con apoyo visual y guion",
                    criteria = "Mensaje comprensible y participación activa (Rúbrica 20 pts)"
                )
                MatrixItemRow(
                    target = "7. Competencia digital y trabajo colaborativo",
                    strategies = "Estrategias 2–6 (Pair Work: Reader, Solver, Checker)",
                    evidence = "Navegación responsable, publicación en muro y roles",
                    criteria = "Cumple normas, aporta al equipo y usa herramientas con autonomía"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // References & Pedagogical Authors Credit
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardElevated),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(text = "📚 REFERENCIAS CURRICULARES Y BIBLIOGRÁFICAS:", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = StarGold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "• Ministerio de Educación Nacional de Colombia (MEN). (2016). Derechos Básicos de Aprendizaje (DBA) de Inglés: Grado 6° y 7°. Bogotá, Colombia.\n" +
                           "• Consejo de Europa. (2020). Marco Común Europeo de Referencia para las Lenguas (MCER): Aprendizaje, enseñanza, evaluación (Nivel A1–A2).\n" +
                           "• Propuesta pedagógica: \"Diseño de Estrategias Didácticas Digitales para la Enseñanza del Inglés en Grado Séptimo: English Adventure – Learn, Play and Communicate\".\n" +
                           "• Autores: Heimy Lemos Rentería, Lizbeth Martín Méndez, Jorge Martínez Benavides, Alex David Rojas Caro.\n" +
                           "• Docente asesora: Liliana Andrea Castaño Lotero.\n" +
                           "• Universidad Tecnológica de Bolívar • Maestría en Educación Mediada por las TIC • Octubre 2026.",
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

@Composable
private fun MatrixItemRow(target: String, strategies: String, evidence: String, criteria: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(SpaceCardElevated)
            .padding(8.dp)
    ) {
        Text(text = target, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = StarGold)
        Text(text = "• Estrategias: $strategies", fontSize = 10.sp, color = Color.White)
        Text(text = "• Evidencia: $evidence", fontSize = 10.sp, color = SpanishHintColor)
        Text(text = "• Criterio de logro: $criteria", fontSize = 10.sp, color = SuccessGreen, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun HelpBullet(boldTitle: String, enDesc: String, esDesc: String) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(text = boldTitle, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ElectricCyan)
        Text(text = "$enDesc\n$esDesc", fontSize = 11.sp, color = Color.LightGray, lineHeight = 14.sp)
    }
}

@Composable
private fun FrequencyLadderRow(adverb: String, percentage: String, meaning: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(color.copy(alpha = 0.2f))
                    .border(1.dp, color, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(text = percentage, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = adverb, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
        }
        Text(text = meaning, fontSize = 11.sp, color = SpanishHintColor)
    }
}
