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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
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
import com.example.data.GrammarChallengeItem
import com.example.data.LanguageMode
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.components.PedagogicalFeedbackCard
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
fun GrammarChallengeScreen(
    languageMode: LanguageMode,
    onCompleteMission: () -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val challenges = EducationalContent.grammarChallenges
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Interactive Guide, 1: Challenges
    var currentChallengeIndex by remember { mutableIntStateOf(0) }
    val userAnswers = remember { mutableStateMapOf<Int, Int>() } // id -> choice
    val submittedMap = remember { mutableStateMapOf<Int, Boolean>() } // id -> Boolean

    val activeChallenge = challenges[currentChallengeIndex]
    val chosenOption = userAnswers[activeChallenge.id]
    val isSubmitted = submittedMap[activeChallenge.id] == true

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
                    .background(StarGold.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "⚡", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "MISSION 3: GRAMMAR CHALLENGE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = StarGold
                )
                Text(
                    text = "Present Simple & Adverbs of Frequency • Reglas y Retos",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tabs: Guide vs Challenges
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SpaceCardNavy,
            contentColor = StarGold,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = StarGold
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("📖 Mini Grammar Guide", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("🎯 Interactive Challenges", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (selectedTab == 0) {
            // MINI GRAMMAR GUIDE WITH MODELS & VISUAL RULES
            MiniGrammarGuideView(languageMode = languageMode, onStartChallenges = { selectedTab = 1 })
        } else {
            // INTERACTIVE CHALLENGES VIEW
            BilingualInstructionCard(
                enInstruction = "Complete each grammar challenge: 3rd person singular, negatives with doesn't, questions and adverb order.",
                esInstruction = "Completa cada reto gramatical: 3ª persona del singular, negaciones con doesn't, preguntas y orden de adverbios.",
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
                    text = "Challenge ${currentChallengeIndex + 1} of ${challenges.size}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Category: ${activeChallenge.ruleCategory}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = StarGold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { (currentChallengeIndex + 1).toFloat() / challenges.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = StarGold,
                trackColor = SpaceBorder
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Challenge Question Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("grammar_card_${activeChallenge.id}"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Rule Tip Badge
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SpaceCardElevated)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Column {
                            Text(
                                text = "Rule Tip / Pista de regla:",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = StarGold
                            )
                            Text(
                                text = "${activeChallenge.ruleTipEn}\n${activeChallenge.ruleTipEs}",
                                fontSize = 11.sp,
                                color = SpanishHintColor,
                                lineHeight = 15.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    BilingualText(
                        en = activeChallenge.promptEn,
                        es = activeChallenge.promptEs,
                        languageMode = languageMode,
                        enSize = 16.sp,
                        esSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Options
                    activeChallenge.optionsEn.forEachIndexed { optIndex, optionEn ->
                        val optionEs = activeChallenge.optionsEs.getOrElse(optIndex) { "" }
                        val isSelected = chosenOption == optIndex
                        val isCorrect = optIndex == activeChallenge.correctIndex

                        val bg = when {
                            isSubmitted && isCorrect -> SuccessGreen.copy(alpha = 0.25f)
                            isSubmitted && isSelected && !isCorrect -> ErrorRose.copy(alpha = 0.25f)
                            isSelected -> StarGold.copy(alpha = 0.25f)
                            else -> SpaceCardElevated
                        }

                        val border = when {
                            isSubmitted && isCorrect -> SuccessGreen
                            isSubmitted && isSelected && !isCorrect -> ErrorRose
                            isSelected -> StarGold
                            else -> SpaceBorder
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(bg)
                                .border(1.5.dp, border, RoundedCornerShape(10.dp))
                                .clickable(enabled = !isSubmitted) {
                                    userAnswers[activeChallenge.id] = optIndex
                                }
                                .padding(12.dp)
                                .testTag("grammar_opt_${activeChallenge.id}_$optIndex"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${('A' + optIndex)}. ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = if (isSelected) StarGold else Color.White
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = optionEn,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = Color.White
                                )
                                if (languageMode != LanguageMode.EN_ONLY && optionEs.isNotBlank() && optionEs != optionEn) {
                                    Text(
                                        text = optionEs,
                                        fontSize = 11.sp,
                                        color = SpanishHintColor
                                    )
                                }
                            }
                        }
                    }

                    // Check Answer Button
                    if (!isSubmitted && chosenOption != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { submittedMap[activeChallenge.id] = true },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = StarGold)
                        ) {
                            Text("Check Grammar / Comprobar Regla", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Pedagogical bilingual explanation
                    if (isSubmitted) {
                        Spacer(modifier = Modifier.height(14.dp))
                        val isCorrect = chosenOption == activeChallenge.correctIndex
                        PedagogicalFeedbackCard(
                            isCorrect = isCorrect,
                            enExplanation = activeChallenge.explanationEn,
                            esExplanation = activeChallenge.explanationEs,
                            languageMode = languageMode
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            if (!isCorrect) {
                                OutlinedButton(
                                    onClick = {
                                        submittedMap[activeChallenge.id] = false
                                        userAnswers.remove(activeChallenge.id)
                                    },
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Try Again / Reintentar")
                                    }
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                            }

                            if (currentChallengeIndex < challenges.size - 1) {
                                Button(
                                    onClick = { currentChallengeIndex++ },
                                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan)
                                ) {
                                    Text("Next Challenge ➔", color = Color.Black, fontWeight = FontWeight.Bold)
                                }
                            } else {
                                Button(
                                    onClick = {
                                        onCompleteMission()
                                        onNavigate(AppScreen.READING_MISSION)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                                ) {
                                    Text("Mission Conquered! Claim +140 XP ➔", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MiniGrammarGuideView(
    languageMode: LanguageMode,
    onStartChallenges: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Rule Card 1: Affirmative & 3rd Person
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "1. PRESENT SIMPLE: 3RD PERSON SINGULAR (-S, -ES, -IES)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = ElectricCyan
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "With he, she, it, we add an ending to the affirmative verb:",
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(8.dp))

                GrammarModelRow("General (+s):", "I play football. ➔ She plays football.", "Juego fútbol. ➔ Ella juega fútbol.")
                GrammarModelRow("-ch, -sh, -ss, -x, -o (+es):", "watch ➔ watches | go ➔ goes", "mirar ➔ mira | ir ➔ va")
                GrammarModelRow("Consonant + y (+ies):", "study ➔ studies | cry ➔ cries", "estudiar ➔ estudia")
                GrammarModelRow("Vowel + y (+s):", "play ➔ plays (a + y) | enjoy ➔ enjoys", "jugar ➔ juega")
                GrammarModelRow("Irregular 'have':", "I have lunch. ➔ He HAS lunch.", "Almuerzo. ➔ Él almuerza.")
            }
        }

        // Rule Card 2: Negatives & Questions
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "2. NEGATIVES (DON'T / DOESN'T) & QUESTIONS (DO / DOES)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = StarGold
                )
                Spacer(modifier = Modifier.height(8.dp))
                GrammarModelRow("I / You / We / They:", "Negative: don't + verb (I don't play)\nQuestion: Do you play...?", "No juego / ¿Juegas tú...?")
                GrammarModelRow("He / She / It:", "Negative: doesn't + base verb (He doesn't play)\nQuestion: Does he play...?", "Él no juega / ¿Juega él...?")
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "⚠️ Notice: Never say 'He doesn't plays'. The 'does' already carries the 3rd person!",
                    fontSize = 11.sp,
                    color = RocketOrange,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Rule Card 3: Adverbs of Frequency & Position
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "3. ADVERBS OF FREQUENCY & POSITION",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = SuccessGreen
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Hierarchy: always (100%) > usually (80%) > often (70%) > sometimes (50%) > rarely (20%) > never (0%)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))
                GrammarModelRow("Before regular action verbs:", "I always have breakfast. / She usually studies.", "Sujeto + Adverbio + Verbo Principal")
                GrammarModelRow("After verb TO BE (am/is/are):", "He is always happy. / They are never late.", "Sujeto + Verb TO BE + Adverbio")
            }
        }

        // Button to jump to challenges
        Button(
            onClick = onStartChallenges,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = StarGold)
        ) {
            Text("Ready! Start Challenges / Comenzar Retos ➔", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun GrammarModelRow(label: String, exampleEn: String, exampleEs: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(SpaceCardElevated)
            .padding(8.dp)
    ) {
        Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = StarGold)
        Text(text = exampleEn, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        Text(text = exampleEs, fontSize = 11.sp, color = SpanishHintColor)
    }
}
