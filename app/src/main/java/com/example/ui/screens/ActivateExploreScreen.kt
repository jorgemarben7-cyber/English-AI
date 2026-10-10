package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.ui.theme.*
import com.example.data.RoutineCard as RoutineCardModel
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
import com.example.ui.components.PedagogicalFeedbackCard
import com.example.ui.components.RoutineCard
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
fun ActivateExploreScreen(
    languageMode: LanguageMode,
    speechManager: SpeechManager,
    onCompleteMission: () -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val cards = EducationalContent.routineCards
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Explore Flashcards, 1: Match Game, 2: Quick Quiz
    var currentCardIndex by remember { mutableIntStateOf(0) }
    val currentCard = cards[currentCardIndex]

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
                    .background(ElectricCyan.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "☀️", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "MISSION 1: ACTIVATE & EXPLORE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = ElectricCyan
                )
                Text(
                    text = "Las 10 Rutinas Diarias Clave • Daily Routines",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tabs: Flashcards, Match Game, Quiz
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SpaceCardNavy,
            contentColor = ElectricCyan,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = ElectricCyan
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("1. Flashcards", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("2. Match Game", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("3. Quick Quiz", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> FlashcardExplorerView(
                cards = cards,
                currentIndex = currentCardIndex,
                onIndexChange = { currentCardIndex = it },
                languageMode = languageMode,
                speechManager = speechManager,
                onGoToMatch = { selectedTab = 1 }
            )
            1 -> RoutineMatchGameView(
                cards = cards,
                languageMode = languageMode,
                speechManager = speechManager,
                onGoToQuiz = { selectedTab = 2 }
            )
            2 -> RoutineQuickQuizView(
                cards = cards,
                languageMode = languageMode,
                onFinishMission = {
                    onCompleteMission()
                    onNavigate(AppScreen.VOCABULARY_LAB)
                }
            )
        }
    }
}

@Composable
private fun FlashcardExplorerView(
    cards: List<RoutineCardModel>,
    currentIndex: Int,
    onIndexChange: (Int) -> Unit,
    languageMode: LanguageMode,
    speechManager: SpeechManager,
    onGoToMatch: () -> Unit
) {
    val card = cards[currentIndex]

    BilingualInstructionCard(
        enInstruction = "Swipe or select cards to explore the 10 routines. Tap the speaker icon to listen to native English pronunciation.",
        esInstruction = "Explora las 10 tarjetas de rutinas. Toca el parlante para escuchar la pronunciación en inglés nativo.",
        languageMode = languageMode
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Horizontal chips selector for 10 routines
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(cards) { index, c ->
            val isSelected = index == currentIndex
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) ElectricCyan else SpaceCardElevated)
                    .border(1.dp, if (isSelected) ElectricCyan else SpaceBorder, RoundedCornerShape(12.dp))
                    .clickable { onIndexChange(index) }
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = c.iconEmoji, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = c.expression,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.Black else Color.White
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Reusable Composable RoutineCard
    RoutineCard(
        card = card,
        languageMode = languageMode,
        onAudioClick = { speechManager.speak(card.expression) },
        onExampleAudioClick = { speechManager.speak(card.exampleEn) }
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Bottom Navigation
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { if (currentIndex > 0) onIndexChange(currentIndex - 1) },
            enabled = currentIndex > 0,
            colors = ButtonDefaults.buttonColors(containerColor = SpaceCardElevated)
        ) {
            Text("Previous", color = Color.White)
        }

        if (currentIndex < cards.size - 1) {
            Button(
                onClick = { onIndexChange(currentIndex + 1) },
                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan)
            ) {
                Text("Next (${currentIndex + 2}/${cards.size})", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        } else {
            Button(
                onClick = onGoToMatch,
                colors = ButtonDefaults.buttonColors(containerColor = RocketOrange)
            ) {
                Text("Practice: Match Game ➔", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun RoutineMatchGameView(
    cards: List<RoutineCardModel>,
    languageMode: LanguageMode,
    speechManager: SpeechManager,
    onGoToQuiz: () -> Unit
) {
    // 5 random cards for matching with independent shuffled targets
    var gameKey by remember { mutableIntStateOf(0) }
    val sampleCards = remember(gameKey) { cards.shuffled().take(5) }
    val shuffledTargets = remember(gameKey) { sampleCards.shuffled() }
    var selectedWordId by remember { mutableStateOf<String?>(null) }
    var selectedImageId by remember { mutableStateOf<String?>(null) }
    val matchedIds = remember(gameKey) { mutableStateMapOf<String, Boolean>() }
    var feedbackMessage by remember(gameKey) { mutableStateOf<String?>(null) }

    BilingualInstructionCard(
        enInstruction = "Tap a word expression on the left, then tap its matching icon/meaning on the right. Immediate feedback on every tap!",
        esInstruction = "Toca una expresión en la izquierda y luego su ícono/significado en la derecha. ¡Retroalimentación inmediata en cada toque!",
        languageMode = languageMode
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Matching Area Header
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Matches: ${matchedIds.size} / 5",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = if (matchedIds.size == 5) SuccessGreen else StarGold
        )
        TextButton(onClick = { gameKey++ }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp), tint = ElectricCyan)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Shuffle Again / Reiniciar", fontSize = 11.sp, color = ElectricCyan)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))

    sampleCards.indices.forEach { index ->
        val wordCard = sampleCards[index]
        val targetCard = shuffledTargets[index]

        val isWordMatched = matchedIds[wordCard.id] == true
        val isTargetMatched = matchedIds[targetCard.id] == true
        val isSelectedWord = selectedWordId == wordCard.id
        val isSelectedImage = selectedImageId == targetCard.id

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Word Button (Left)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isWordMatched) SuccessGreen.copy(alpha = 0.25f)
                        else if (isSelectedWord) ElectricCyan.copy(alpha = 0.35f)
                        else SpaceCardNavy
                    )
                    .border(
                        1.dp,
                        if (isWordMatched) SuccessGreen else if (isSelectedWord) ElectricCyan else SpaceBorder,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable(enabled = !isWordMatched) {
                        selectedWordId = wordCard.id
                        speechManager.speak(wordCard.expression)
                        if (selectedImageId != null) {
                            if (selectedImageId == wordCard.id) {
                                matchedIds[wordCard.id] = true
                                feedbackMessage = "✓ Great match! / ¡Excelente pareja: ${wordCard.expression}!"
                            } else {
                                feedbackMessage = "✗ Almost! Try matching ${wordCard.expression} with its correct icon."
                            }
                            selectedWordId = null
                            selectedImageId = null
                        }
                    }
                    .padding(10.dp)
            ) {
                Text(
                    text = if (isWordMatched) "✓ ${wordCard.expression}" else wordCard.expression,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (isWordMatched) SuccessGreen else Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Match Target (Right: Emoji + Spanish meaning)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isTargetMatched) SuccessGreen.copy(alpha = 0.25f)
                        else if (isSelectedImage) StarGold.copy(alpha = 0.35f)
                        else SpaceCardElevated
                    )
                    .border(
                        1.dp,
                        if (isTargetMatched) SuccessGreen else if (isSelectedImage) StarGold else SpaceBorder,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable(enabled = !isTargetMatched) {
                        selectedImageId = targetCard.id
                        if (selectedWordId != null) {
                            if (selectedWordId == targetCard.id) {
                                matchedIds[targetCard.id] = true
                                feedbackMessage = "✓ Great match! / ¡Pareja correcta: ${targetCard.expression}!"
                            } else {
                                feedbackMessage = "✗ Almost! Keep exploring to find the right match."
                            }
                            selectedWordId = null
                            selectedImageId = null
                        }
                    }
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = targetCard.iconEmoji, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = targetCard.spanishMeaning,
                        fontSize = 11.sp,
                        color = if (isTargetMatched) SuccessGreen else SpanishHintColor
                    )
                }
            }
        }
    }

    if (feedbackMessage != null) {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(if (feedbackMessage!!.startsWith("✓")) SuccessGreen.copy(alpha = 0.2f) else StarGold.copy(alpha = 0.2f))
                .border(1.dp, if (feedbackMessage!!.startsWith("✓")) SuccessGreen else StarGold, RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Text(
                text = feedbackMessage!!,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (feedbackMessage!!.startsWith("✓")) SuccessGreen else StarGold
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    if (matchedIds.size == 5) {
        Button(
            onClick = onGoToQuiz,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
        ) {
            Text("All 5 Matched! Continue to Quick Quiz ➔", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun RoutineQuickQuizView(
    cards: List<RoutineCardModel>,
    languageMode: LanguageMode,
    onFinishMission: () -> Unit
) {
    val quizQuestions = remember {
        listOf(
            Triple("I use a toothbrush, shampoo and water at 6:15 AM.", "take a shower", listOf("take a shower", "do homework", "have dinner")),
            Triple("Students sit at their desks with notebooks and pencils to learn.", "study", listOf("study", "play", "go to bed")),
            Triple("I eat eggs, bread and drink orange juice before classes start.", "have breakfast", listOf("have breakfast", "have lunch", "have dinner")),
            Triple("I kick a soccer ball with my classmates on the school field.", "play", listOf("play", "wake up", "take a shower"))
        )
    }

    val answers = remember { mutableStateMapOf<Int, String>() }

    BilingualInstructionCard(
        enInstruction = "Read each clue and choose the correct daily routine expression. Instant feedback will appear immediately below!",
        esInstruction = "Lee cada pista y elige la rutina correcta. ¡La retroalimentación pedagógica aparecerá inmediatamente debajo!",
        languageMode = languageMode
    )

    Spacer(modifier = Modifier.height(14.dp))

    quizQuestions.forEachIndexed { qIdx, (clue, correct, options) ->
        val selected = answers[qIdx]
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(
                brush = androidx.compose.ui.graphics.SolidColor(
                    if (selected == null) SpaceBorder
                    else if (selected == correct) SuccessGreen
                    else ErrorRose
                )
            )
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "${qIdx + 1}. \"$clue\"",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    options.forEach { opt ->
                        val isChosen = selected == opt
                        val isRight = isChosen && opt == correct
                        val isWrong = isChosen && opt != correct

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (isRight) SuccessGreen.copy(alpha = 0.25f)
                                    else if (isWrong) ErrorRose.copy(alpha = 0.25f)
                                    else if (isChosen) ElectricCyan.copy(alpha = 0.2f)
                                    else SpaceCardElevated
                                )
                                .border(
                                    1.dp,
                                    if (isRight) SuccessGreen else if (isWrong) ErrorRose else SpaceBorder,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    answers[qIdx] = opt
                                }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = opt,
                                fontSize = 11.sp,
                                fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                                color = if (isRight) SuccessGreen else Color.White
                            )
                        }
                    }
                }

                // Immediate per-question feedback card
                if (selected != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    val isCorrect = selected == correct
                    PedagogicalFeedbackCard(
                        isCorrect = isCorrect,
                        enExplanation = if (isCorrect) "Excellent! '$correct' matches this daily activity perfectly."
                                       else "Notice: The correct expression is '$correct'. You selected '$selected'.",
                        esExplanation = if (isCorrect) "¡Excelente! '$correct' corresponde exactamente a esta actividad."
                                       else "Observa: La expresión correcta es '$correct'. Elegiste '$selected'.",
                        languageMode = languageMode
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    val correctCount = quizQuestions.indices.count { answers[it] == quizQuestions[it].second }
    if (answers.size == quizQuestions.size) {
        PedagogicalFeedbackCard(
            isCorrect = correctCount >= 3,
            enExplanation = "Overall result: You answered $correctCount of 4 correctly! Ready for Vocabulary Lab.",
            esExplanation = "Resultado global: ¡Respondiste $correctCount de 4 correctamente! Listo para el Laboratorio de Vocabulario.",
            languageMode = languageMode
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onFinishMission,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("finish_routines_mission_button"),
            colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan)
        ) {
            Text("Complete Mission & Claim +100 XP ➔", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
