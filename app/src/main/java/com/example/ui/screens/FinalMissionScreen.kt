package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.audio.SpeechManager
import com.example.audio.VoiceRecorderManager
import com.example.data.EducationalContent
import com.example.data.LanguageMode
import com.example.data.RubricItem
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
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
import kotlinx.coroutines.delay

@Composable
fun FinalMissionScreen(
    languageMode: LanguageMode,
    savedScript: String,
    speechManager: SpeechManager,
    voiceRecorder: VoiceRecorderManager,
    initialScores: Map<String, Int>,
    onSaveEvaluation: (script: String, scores: Map<String, Int>) -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Script & Audio, 1: Rubric & Self-Evaluation

    // Script state
    var scriptText by remember {
        mutableStateOf(
            if (savedScript.isNotBlank()) savedScript
            else "Good morning everyone! My name is Explorer Cadet.\n" +
                 "Today I want to share my daily routine.\n" +
                 "First, I always wake up at 6:00 AM and take a shower.\n" +
                 "Then, I usually have breakfast with my family.\n" +
                 "At 7:00 AM, I go to school and study English.\n" +
                 "In the afternoon, I often do my homework and play soccer.\n" +
                 "Finally, I rarely go to bed late; I sleep at 9:30 PM. Thank you!"
        )
    }

    // Audio recording state
    var isRecordingAudio by remember { mutableStateOf(false) }
    var isPlayingAudio by remember { mutableStateOf(false) }
    var hasAudioFile by remember { mutableStateOf(voiceRecorder.hasRecording()) }
    var recordingTimerSeconds by remember { mutableIntStateOf(0) }

    // Practice speech timer
    var practiceTimerRunning by remember { mutableStateOf(false) }
    var practiceSecondsRemaining by remember { mutableIntStateOf(60) }

    // Permission launcher for microphone
    val micPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val started = voiceRecorder.startRecording()
            if (started) {
                isRecordingAudio = true
                recordingTimerSeconds = 0
            }
        } else {
            Toast.makeText(context, "Microphone permission needed to record audio / Permiso de micrófono requerido", Toast.LENGTH_SHORT).show()
        }
    }

    // Recording timer tick
    LaunchedEffect(isRecordingAudio) {
        if (isRecordingAudio) {
            while (isRecordingAudio) {
                delay(1000L)
                recordingTimerSeconds++
            }
        }
    }

    // Practice timer tick
    LaunchedEffect(practiceTimerRunning) {
        if (practiceTimerRunning) {
            while (practiceTimerRunning && practiceSecondsRemaining > 0) {
                delay(1000L)
                practiceSecondsRemaining--
            }
            if (practiceSecondsRemaining <= 0) {
                practiceTimerRunning = false
            }
        }
    }

    // Rubric scores (1 to 4 for each of the 5 criteria)
    val rubricScores = remember {
        mutableStateMapOf<String, Int>().apply {
            putAll(initialScores)
        }
    }

    val totalRubricScore = rubricScores.values.sum()

    DisposableEffect(Unit) {
        onDispose {
            voiceRecorder.stopPlayback()
            if (voiceRecorder.isRecording) {
                voiceRecorder.stopRecording()
            }
        }
    }

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
                Text(text = "🎤", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "FINAL MISSION: LEARN, PLAY & COMMUNICATE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = RocketOrange
                )
                Text(
                    text = "Presentación Oral de la Rutina • Rúbrica de 20 Puntos",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SpaceCardNavy,
            contentColor = RocketOrange,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = RocketOrange
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("1. Script & Audio Studio", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("2. Rubric & Self-Evaluation", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (selectedTab == 0) {
            // SCRIPT & AUDIO STUDIO
            BilingualInstructionCard(
                enInstruction = "Prepare your 5–7 sentence oral presentation script. Practice with the timer and record your speech with permission.",
                esInstruction = "Prepara tu guion de 5 a 7 oraciones. Practica con el temporizador y graba tu presentación con permiso de micrófono.",
                languageMode = languageMode
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Practice Speech Timer Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Timer, contentDescription = null, tint = StarGold, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Practice Timer: ${practiceSecondsRemaining}s", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
                            Text(text = "Ideal oral delivery: 60 to 90 seconds", fontSize = 11.sp, color = SpanishHintColor)
                        }
                    }
                    Button(
                        onClick = {
                            if (practiceTimerRunning) {
                                practiceTimerRunning = false
                            } else {
                                if (practiceSecondsRemaining <= 0) practiceSecondsRemaining = 60
                                practiceTimerRunning = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (practiceTimerRunning) RocketOrange else ElectricCyan
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(if (practiceTimerRunning) "Stop" else "Start 60s", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Script Text Editor
            Text(
                text = "Oral Presentation Script / Guion de Presentación:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = scriptText,
                onValueChange = { scriptText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .testTag("oral_script_input"),
                label = { Text("My Oral Script (5–7 sentences)") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = RocketOrange,
                    unfocusedBorderColor = SpaceBorder,
                    focusedContainerColor = SpaceCardNavy,
                    unfocusedContainerColor = SpaceCardNavy
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // TTS Read Aloud Script Helper
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { speechManager.speak(scriptText) },
                    colors = ButtonDefaults.buttonColors(containerColor = SpaceCardElevated),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, tint = ElectricCyan, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Listen to Script Audio (TTS)", color = ElectricCyan, fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Audio Recording Studio Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(RocketOrange.copy(alpha = 0.5f)))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "🎙️ Voice Recording Studio / Grabación de Voz",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = RocketOrange
                    )
                    Text(
                        text = "Record your oral presentation using the microphone (permission required). / Graba tu voz con el micrófono:",
                        fontSize = 11.sp,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (isRecordingAudio) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(ErrorRose.copy(alpha = 0.2f))
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(ErrorRose)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Recording in progress... (${recordingTimerSeconds}s)",
                                color = ErrorRose,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    // Recording Controls
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (!isRecordingAudio) {
                            Button(
                                onClick = {
                                    val hasPermission = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.RECORD_AUDIO
                                    ) == PackageManager.PERMISSION_GRANTED

                                    if (hasPermission) {
                                        val ok = voiceRecorder.startRecording()
                                        if (ok) {
                                            isRecordingAudio = true
                                            recordingTimerSeconds = 0
                                        }
                                    } else {
                                        micPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("record_audio_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = RocketOrange),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Mic, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Record Voice", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        } else {
                            Button(
                                onClick = {
                                    voiceRecorder.stopRecording()
                                    isRecordingAudio = false
                                    hasAudioFile = voiceRecorder.hasRecording()
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("stop_record_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = ErrorRose),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Stop, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Stop Recording", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }

                        // Playback Button
                        Button(
                            onClick = {
                                if (isPlayingAudio) {
                                    voiceRecorder.stopPlayback()
                                    isPlayingAudio = false
                                } else {
                                    isPlayingAudio = voiceRecorder.startPlayback {
                                        isPlayingAudio = false
                                    }
                                }
                            },
                            enabled = hasAudioFile && !isRecordingAudio,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("play_audio_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = if (isPlayingAudio) Icons.Default.Stop else Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (isPlayingAudio) "Stop Play" else "Play Recording", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                    if (hasAudioFile) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "✓ Audio recorded successfully! You can listen or re-record anytime.",
                            fontSize = 11.sp,
                            color = SuccessGreen,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = { selectedTab = 1 },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Proceed to Rubric & Self-Evaluation ➔", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        } else {
            // RUBRIC & SELF-EVALUATION
            BilingualInstructionCard(
                enInstruction = "Evaluate your presentation using the 5 pedagogical criteria (1 to 4 points each, maximum 20). Be honest with your self-assessment!",
                esInstruction = "Evalúa tu presentación con los 5 criterios pedagógicos (1 a 4 puntos cada uno, máximo 20). ¡Diferencia tu autoevaluación del juicio docente!",
                languageMode = languageMode
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Score Banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(RocketOrange))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "Total Evaluation Score:", fontSize = 12.sp, color = Color.Gray)
                            Text(
                                text = "$totalRubricScore / 20 Points",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (totalRubricScore >= 15) SuccessGreen else StarGold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(RocketOrange.copy(alpha = 0.2f))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = when {
                                    totalRubricScore in 18..20 -> "Superior (18-20)"
                                    totalRubricScore in 15..17 -> "Alto (15-17)"
                                    totalRubricScore in 11..14 -> "Básico (11-14)"
                                    else -> "Bajo (1-10)"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = RocketOrange
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { totalRubricScore.toFloat() / 20f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = RocketOrange,
                        trackColor = SpaceBorder
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5 Criteria
            EducationalContent.rubricCriteria.forEach { item ->
                val currentScore = rubricScores[item.id] ?: 3
                RubricItemCard(
                    item = item,
                    currentScore = currentScore,
                    onScoreSelected = { newScore ->
                        rubricScores[item.id] = newScore
                    },
                    languageMode = languageMode
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Evaluation Button
            Button(
                onClick = {
                    onSaveEvaluation(scriptText, rubricScores)
                    Toast.makeText(context, "Evaluation saved! +120 XP / ¡Autoevaluación guardada!", Toast.LENGTH_SHORT).show()
                    onNavigate(AppScreen.PROGRESS)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("submit_final_evaluation_button"),
                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Evaluation & Finish Mission ➔", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun RubricItemCard(
    item: RubricItem,
    currentScore: Int,
    onScoreSelected: (Int) -> Unit,
    languageMode: LanguageMode
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.titleEn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color.White
                )
                Text(
                    text = "$currentScore / 4 pts",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = StarGold
                )
            }
            if (languageMode != LanguageMode.EN_ONLY) {
                Text(text = item.titleEs, fontSize = 11.sp, color = SpanishHintColor)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Selected Level Description
            val (descEn, descEs) = when (currentScore) {
                4 -> item.level4En to item.level4Es
                3 -> item.level3En to item.level3Es
                2 -> item.level2En to item.level2Es
                else -> item.level1En to item.level1Es
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(SpaceCardElevated)
                    .padding(10.dp)
            ) {
                Column {
                    Text(
                        text = "Descriptor (${when (currentScore) { 4 -> "Excelente"; 3 -> "Satisfactorio"; 2 -> "En proceso"; else -> "Inicial" }}):",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = StarGold
                    )
                    Text(text = descEn, fontSize = 11.sp, color = Color.White)
                    if (languageMode != LanguageMode.EN_ONLY) {
                        Text(text = descEs, fontSize = 10.sp, color = SpanishHintColor)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Score Selector Chips (1, 2, 3, 4)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf(
                    1 to "1 (Inicial)",
                    2 to "2 (En proceso)",
                    3 to "3 (Satisfactorio)",
                    4 to "4 (Excelente)"
                ).forEach { (score, label) ->
                    val isChosen = currentScore == score
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isChosen) RocketOrange else SpaceCardElevated)
                            .border(1.dp, if (isChosen) RocketOrange else SpaceBorder, RoundedCornerShape(8.dp))
                            .clickable { onScoreSelected(score) }
                            .padding(vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$score pt",
                            fontSize = 11.sp,
                            fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                            color = if (isChosen) Color.White else Color.LightGray
                        )
                    }
                }
            }
        }
    }
}
