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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import com.example.data.EducationalContent
import com.example.data.LabTier
import com.example.data.LanguageMode
import com.example.data.VocabLabItem
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.components.PedagogicalFeedbackCard
import com.example.ui.theme.CosmicViolet
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold
import com.example.ui.theme.SuccessGreen

@Composable
fun VocabularyLabScreen(
    languageMode: LanguageMode,
    onCompleteMission: () -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val allItems = EducationalContent.vocabLabItems
    var selectedTierIndex by remember { mutableIntStateOf(0) } // 0: Tier 1, 1: Tier 2, 2: Tier 3
    var showPairWorkModal by remember { mutableStateOf(false) }
    var currentPairRole by remember { mutableStateOf("READER") } // READER, SOLVER, CHECKER

    val currentTier = when (selectedTierIndex) {
        0 -> LabTier.TIER_1_CADET
        1 -> LabTier.TIER_2_NAVIGATOR
        else -> LabTier.TIER_3_MASTER
    }

    val tierItems = allItems.filter { it.tier == currentTier }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    val userAnswers = remember { mutableStateMapOf<Int, Int>() } // questionId -> selectedIndex
    val answeredSubmitted = remember { mutableStateMapOf<Int, Boolean>() } // questionId -> submitted

    val activeItem = tierItems.getOrNull(currentQuestionIndex) ?: tierItems.first()
    val chosenOption = userAnswers[activeItem.id]
    val isItemSubmitted = answeredSubmitted[activeItem.id] == true

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
                    .background(CosmicViolet.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🧪", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "MISSION 2: VOCABULARY LAB",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = CosmicViolet
                )
                Text(
                    text = "Laboratorio de Vocabulario • 3 Niveles",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }

            // Pair Work Mode Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(SpaceCardElevated)
                    .border(1.dp, SpaceBorder, RoundedCornerShape(8.dp))
                    .clickable { showPairWorkModal = !showPairWorkModal }
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Group, contentDescription = null, tint = StarGold, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Pair Work", fontSize = 11.sp, color = StarGold, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Pair Work Banner / Role Indicator
        AnimatedVisibility(visible = showPairWorkModal) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardElevated),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(StarGold))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "👥 Pair Work Mode / Trabajo en Parejas",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = StarGold
                    )
                    Text(
                        text = "Rotate roles for each question when sharing one device / Rota los roles por pregunta:",
                        fontSize = 11.sp,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PairRoleChip(
                            title = "📖 Reader / Lector",
                            roleKey = "READER",
                            isSelected = currentPairRole == "READER",
                            onClick = { currentPairRole = "READER" },
                            modifier = Modifier.weight(1f)
                        )
                        PairRoleChip(
                            title = "💡 Solver / Solucionador",
                            roleKey = "SOLVER",
                            isSelected = currentPairRole == "SOLVER",
                            onClick = { currentPairRole = "SOLVER" },
                            modifier = Modifier.weight(1f)
                        )
                        PairRoleChip(
                            title = "🔍 Checker / Verificador",
                            roleKey = "CHECKER",
                            isSelected = currentPairRole == "CHECKER",
                            onClick = { currentPairRole = "CHECKER" },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Tiers Tab Row
        TabRow(
            selectedTabIndex = selectedTierIndex,
            containerColor = SpaceCardNavy,
            contentColor = CosmicViolet,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTierIndex]),
                    color = CosmicViolet
                )
            }
        ) {
            Tab(
                selected = selectedTierIndex == 0,
                onClick = { selectedTierIndex = 0; currentQuestionIndex = 0 },
                text = { Text("Tier 1: Cadet", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTierIndex == 1,
                onClick = { selectedTierIndex = 1; currentQuestionIndex = 0 },
                text = { Text("Tier 2: Navigator", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTierIndex == 2,
                onClick = { selectedTierIndex = 2; currentQuestionIndex = 0 },
                text = { Text("Tier 3: Master", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Instruction card
        BilingualInstructionCard(
            enInstruction = "Solve association, classification, and sequencing challenges. Read explanations to master daily routine vocabulary.",
            esInstruction = "Resuelve retos de asociación, clasificación y orden. Lee las explicaciones para dominar el vocabulario de rutinas.",
            languageMode = languageMode
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Progress
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Challenge ${currentQuestionIndex + 1} of ${tierItems.size}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Type: ${activeItem.type.name.replace('_', ' ')}",
                fontSize = 11.sp,
                color = ElectricCyan,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
            progress = { (currentQuestionIndex + 1).toFloat() / tierItems.size.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = CosmicViolet,
            trackColor = SpaceBorder
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Active Challenge Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("vocab_lab_card_${activeItem.id}"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                BilingualText(
                    en = activeItem.promptEn,
                    es = activeItem.promptEs,
                    languageMode = languageMode,
                    enSize = 15.sp,
                    esSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )

                if (activeItem.clueEn.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(StarGold.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "💡 Clue / Pista: ${activeItem.clueEn} (${activeItem.clueEs})",
                            fontSize = 11.sp,
                            color = StarGold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Options
                activeItem.optionsEn.forEachIndexed { optIndex, optionEn ->
                    val optionEs = activeItem.optionsEs.getOrElse(optIndex) { "" }
                    val isSelected = chosenOption == optIndex
                    val isCorrect = optIndex == activeItem.correctIndex

                    val bg = when {
                        isItemSubmitted && isCorrect -> SuccessGreen.copy(alpha = 0.25f)
                        isItemSubmitted && isSelected && !isCorrect -> ErrorRose.copy(alpha = 0.25f)
                        isSelected -> CosmicViolet.copy(alpha = 0.25f)
                        else -> SpaceCardElevated
                    }

                    val border = when {
                        isItemSubmitted && isCorrect -> SuccessGreen
                        isItemSubmitted && isSelected && !isCorrect -> ErrorRose
                        isSelected -> CosmicViolet
                        else -> SpaceBorder
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(bg)
                            .border(1.5.dp, border, RoundedCornerShape(10.dp))
                            .clickable(enabled = !isItemSubmitted) {
                                userAnswers[activeItem.id] = optIndex
                            }
                            .padding(12.dp)
                            .testTag("vocab_opt_${activeItem.id}_$optIndex"),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${('A' + optIndex)}. ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (isSelected) CosmicViolet else Color.White
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = optionEn,
                                fontSize = 13.sp,
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

                // Check / Submit Button
                if (!isItemSubmitted && chosenOption != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { answeredSubmitted[activeItem.id] = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = CosmicViolet)
                    ) {
                        Text("Check Answer / Comprobar", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

                // Instant Pedagogical Feedback
                if (isItemSubmitted) {
                    Spacer(modifier = Modifier.height(14.dp))
                    val isCorrect = chosenOption == activeItem.correctIndex
                    PedagogicalFeedbackCard(
                        isCorrect = isCorrect,
                        enExplanation = activeItem.explanationEn,
                        esExplanation = activeItem.explanationEs,
                        languageMode = languageMode
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (!isCorrect) {
                            OutlinedButton(
                                onClick = {
                                    answeredSubmitted[activeItem.id] = false
                                    userAnswers.remove(activeItem.id)
                                },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Retry / Reintentar")
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        if (currentQuestionIndex < tierItems.size - 1) {
                            Button(
                                onClick = { currentQuestionIndex++ },
                                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan)
                            ) {
                                Text("Next Challenge ➔", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                        } else if (selectedTierIndex < 2) {
                            Button(
                                onClick = {
                                    selectedTierIndex++
                                    currentQuestionIndex = 0
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = StarGold)
                            ) {
                                Text("Advance to Next Tier! ➔", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Button(
                                onClick = {
                                    onCompleteMission()
                                    onNavigate(AppScreen.GRAMMAR_CHALLENGE)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                            ) {
                                Text("Complete Lab & Go to Grammar! ➔", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PairRoleChip(
    title: String,
    roleKey: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) StarGold else SpaceCardNavy)
            .border(1.dp, if (isSelected) StarGold else SpaceBorder, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 6.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.Black else Color.White
        )
    }
}
