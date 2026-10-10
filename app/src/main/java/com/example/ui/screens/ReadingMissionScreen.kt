package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.R
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.audio.SpeechManager
import com.example.data.EducationalContent
import com.example.data.LanguageMode
import com.example.data.ReadingStoryItem
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.components.PedagogicalFeedbackCard
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold
import com.example.ui.theme.SuccessGreen

@Composable
fun ReadingMissionScreen(
    languageMode: LanguageMode,
    speechManager: SpeechManager,
    onCompleteMission: () -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val stories = EducationalContent.readingStories
    var currentStoryIndex by remember { mutableIntStateOf(0) }
    var showSpanishTranslation by remember { mutableStateOf(false) }
    val userAnswers = remember { mutableStateMapOf<Int, Int>() } // questionId -> option
    val submittedQuestions = remember { mutableStateMapOf<Int, Boolean>() } // questionId -> submitted
    val story = stories[currentStoryIndex]

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
                    .background(NebulaTeal.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "📖", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "MISSION 4: READING MISSION",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = NebulaTeal
                )
                Text(
                    text = "Comprensión de Rutinas de Adolescentes",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Story Selector Tabs
        TabRow(
            selectedTabIndex = currentStoryIndex,
            containerColor = SpaceCardNavy,
            contentColor = NebulaTeal,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[currentStoryIndex]),
                    color = NebulaTeal
                )
            }
        ) {
            stories.forEachIndexed { index, s ->
                Tab(
                    selected = currentStoryIndex == index,
                    onClick = { currentStoryIndex = index },
                    text = {
                        Text(
                            text = "${s.flag} ${s.characterName}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        BilingualInstructionCard(
            enInstruction = "Read ${story.characterName}'s routine story. Listen to the narration, explore key vocabulary, and answer comprehension questions.",
            esInstruction = "Lee la rutina de ${story.characterName}. Escucha la narración, explora el vocabulario y responde las preguntas de comprensión.",
            languageMode = languageMode
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Story Reader Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("reading_story_card_${story.id}"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                val imageRes = when (story.imageResName) {
                    "reading_leo" -> R.drawable.reading_leo
                    "reading_sofia" -> R.drawable.reading_sofia
                    "reading_kenji" -> R.drawable.reading_kenji
                    else -> null
                }
                if (imageRes != null) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = story.titleEn,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Header with Flag, Location, and Audio Play
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${story.flag} ${story.titleEn}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "Location: ${story.location}",
                            fontSize = 12.sp,
                            color = StarGold
                        )
                    }
                    Row {
                        IconButton(
                            onClick = { speechManager.speak(story.textEn) },
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(NebulaTeal.copy(alpha = 0.2f))
                                .testTag("tts_story_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Read aloud",
                                tint = NebulaTeal,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        IconButton(
                            onClick = { showSpanishTranslation = !showSpanishTranslation },
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(ElectricCyan.copy(alpha = 0.2f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Translate,
                                contentDescription = "Toggle translation",
                                tint = ElectricCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // English Passage
                Text(
                    text = story.textEn,
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 22.sp
                )

                // Optional Spanish Translation
                AnimatedVisibility(visible = showSpanishTranslation) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(SpaceCardElevated)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Traducción de apoyo al español:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricCyan
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = story.textEs,
                            fontSize = 13.sp,
                            color = SpanishHintColor,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Key Vocabulary Pills
                Text(
                    text = "Key Vocabulary / Vocabulario Clave (Tap for audio):",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = StarGold
                )
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    itemsIndexed(story.keyVocab) { _, (enVocab, esVocab) ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SpaceCardElevated)
                                .border(1.dp, SpaceBorder, RoundedCornerShape(8.dp))
                                .clickable { speechManager.speak(enVocab) }
                                .padding(horizontal = 8.dp, vertical = 5.dp)
                        ) {
                            Column {
                                Text(text = enVocab, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = NebulaTeal)
                                Text(text = esVocab, fontSize = 10.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Comprehension Questions
        Text(
            text = "COMPREHENSION QUESTIONS / PREGUNTAS:",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        story.questions.forEachIndexed { qIdx, question ->
            val userChoice = userAnswers[question.id]
            val isQuestionSubmitted = submittedQuestions[question.id] == true
            val isCorrect = userChoice == question.correctIndex

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .testTag("reading_q_${question.id}"),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(
                        if (isQuestionSubmitted) {
                            if (isCorrect) SuccessGreen else ErrorRose
                        } else SpaceBorder
                    )
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    BilingualText(
                        en = "${qIdx + 1}. ${question.promptEn}",
                        es = "${qIdx + 1}. ${question.promptEs}",
                        languageMode = languageMode,
                        enSize = 14.sp,
                        esSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    question.optionsEn.forEachIndexed { optIdx, optEn ->
                        val optEs = question.optionsEs.getOrElse(optIdx) { "" }
                        val isSelected = userChoice == optIdx

                        val bg = when {
                            isQuestionSubmitted && optIdx == question.correctIndex -> SuccessGreen.copy(alpha = 0.25f)
                            isQuestionSubmitted && isSelected && !isCorrect -> ErrorRose.copy(alpha = 0.25f)
                            isSelected -> NebulaTeal.copy(alpha = 0.25f)
                            else -> SpaceCardElevated
                        }

                        val border = when {
                            isQuestionSubmitted && optIdx == question.correctIndex -> SuccessGreen
                            isQuestionSubmitted && isSelected && !isCorrect -> ErrorRose
                            isSelected -> NebulaTeal
                            else -> SpaceBorder
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(bg)
                                .border(1.dp, border, RoundedCornerShape(8.dp))
                                .clickable(enabled = !isQuestionSubmitted) {
                                    userAnswers[question.id] = optIdx
                                }
                                .padding(10.dp)
                                .testTag("reading_opt_${question.id}_$optIdx"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${('A' + optIdx)}. ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isSelected) NebulaTeal else Color.White
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = optEn,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = Color.White
                                )
                                if (languageMode != LanguageMode.EN_ONLY && optEs.isNotBlank() && optEs != optEn) {
                                    Text(text = optEs, fontSize = 11.sp, color = SpanishHintColor)
                                }
                            }
                        }
                    }

                    if (!isQuestionSubmitted && userChoice != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { submittedQuestions[question.id] = true },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal)
                        ) {
                            Text("Confirm Answer / Confirmar", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }

                    if (isQuestionSubmitted) {
                        Spacer(modifier = Modifier.height(10.dp))
                        PedagogicalFeedbackCard(
                            isCorrect = isCorrect,
                            enExplanation = question.explanationEn,
                            esExplanation = question.explanationEs,
                            languageMode = languageMode
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Next Story or Finish Mission
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if (currentStoryIndex < stories.size - 1) {
                Button(
                    onClick = {
                        currentStoryIndex++
                        showSpanishTranslation = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan)
                ) {
                    Text("Next Story (${currentStoryIndex + 2}/${stories.size}) ➔", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            } else {
                Button(
                    onClick = {
                        onCompleteMission()
                        onNavigate(AppScreen.ROUTINE_WALL)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                    modifier = Modifier.testTag("finish_reading_mission_button")
                ) {
                    Text("Complete Reading Mission & Claim +130 XP ➔", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
