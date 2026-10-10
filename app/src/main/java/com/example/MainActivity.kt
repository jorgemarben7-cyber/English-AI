package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.audio.SpeechManager
import com.example.audio.VoiceRecorderManager
import com.example.data.ProgressRepository
import com.example.ui.components.AppNavDrawer
import com.example.ui.components.AppScreen
import com.example.ui.components.CosmicTopBar
import com.example.ui.screens.ActivateExploreScreen
import com.example.ui.screens.CosmicTutorDialog
import com.example.ui.screens.FinalMissionScreen
import com.example.ui.screens.GrammarChallengeScreen
import com.example.ui.screens.HelpGlossaryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MissionZeroScreen
import com.example.ui.screens.PrintableSheetScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.ReadingMissionScreen
import com.example.ui.screens.RoutineWallScreen
import com.example.ui.screens.VocabularyLabScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SpaceDeepNight
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var progressRepository: ProgressRepository
    private lateinit var speechManager: SpeechManager
    private lateinit var voiceRecorder: VoiceRecorderManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        progressRepository = ProgressRepository(this)
        speechManager = SpeechManager(this)
        voiceRecorder = VoiceRecorderManager(this)

        setContent {
            MyApplicationTheme {
                EnglishAdventureApp(
                    repository = progressRepository,
                    speechManager = speechManager,
                    voiceRecorder = voiceRecorder
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechManager.destroy()
        voiceRecorder.cleanup()
    }
}

@Composable
fun EnglishAdventureApp(
    repository: ProgressRepository,
    speechManager: SpeechManager,
    voiceRecorder: VoiceRecorderManager
) {
    val xp by repository.xpFlow.collectAsState()
    val alias by repository.aliasFlow.collectAsState()
    val avatar by repository.avatarFlow.collectAsState()
    val completedMissions by repository.completedMissionsFlow.collectAsState()
    val unlockedBadges by repository.unlockedBadgesFlow.collectAsState()
    val languageMode by repository.languageModeFlow.collectAsState()
    val soundEnabled by repository.soundEnabledFlow.collectAsState()
    val wallEntries by repository.wallEntriesFlow.collectAsState()

    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }
    var showTutorDialog by remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Handle back button on sub-screens to return to Home
    if (currentScreen != AppScreen.HOME) {
        BackHandler {
            currentScreen = AppScreen.HOME
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = SpaceDeepNight
            ) {
                AppNavDrawer(
                    currentScreen = currentScreen,
                    completedMissions = completedMissions,
                    alias = alias,
                    avatar = avatar,
                    xp = xp,
                    languageMode = languageMode,
                    onNavigate = { screen ->
                        currentScreen = screen
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                CosmicTopBar(
                    title = when (currentScreen) {
                        AppScreen.HOME -> "English Adventure"
                        AppScreen.MISSION_ZERO -> "Mission Zero: Diagnostic"
                        AppScreen.ACTIVATE_EXPLORE -> "M1: Daily Routines"
                        AppScreen.VOCABULARY_LAB -> "M2: Vocabulary Lab"
                        AppScreen.GRAMMAR_CHALLENGE -> "M3: Grammar Challenge"
                        AppScreen.READING_MISSION -> "M4: Reading Mission"
                        AppScreen.ROUTINE_WALL -> "M5: Routine Wall"
                        AppScreen.FINAL_MISSION -> "M6: Oral Presentation"
                        AppScreen.PROGRESS -> "Badges & Progress"
                        AppScreen.HELP_GLOSSARY -> "Help & Glossary"
                        AppScreen.PRINTABLE_SHEET -> "Classroom Worksheet"
                    },
                    xp = xp,
                    languageMode = languageMode,
                    soundEnabled = soundEnabled,
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    },
                    onLanguageToggle = {
                        repository.toggleLanguageMode()
                    },
                    onSoundToggle = {
                        repository.toggleSound()
                    },
                    onTutorClick = {
                        showTutorDialog = true
                    }
                )
            },
            containerColor = SpaceDeepNight
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(SpaceDeepNight)
            ) {
                when (currentScreen) {
                    AppScreen.HOME -> HomeScreen(
                        xp = xp,
                        alias = alias,
                        avatar = avatar,
                        completedMissions = completedMissions,
                        languageMode = languageMode,
                        onNavigate = { currentScreen = it },
                        onUpdateAlias = { newAlias, newAvatar ->
                            repository.setAlias(newAlias)
                            repository.setAvatar(newAvatar)
                        }
                    )
                    AppScreen.MISSION_ZERO -> MissionZeroScreen(
                        languageMode = languageMode,
                        onSaveResults = { vocab, grammar, freq ->
                            repository.saveDiagnosticResults(vocab, grammar, freq)
                        },
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.ACTIVATE_EXPLORE -> ActivateExploreScreen(
                        languageMode = languageMode,
                        speechManager = speechManager,
                        onCompleteMission = {
                            repository.markMissionCompleted("m_routines")
                            repository.addXp(100)
                        },
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.VOCABULARY_LAB -> VocabularyLabScreen(
                        languageMode = languageMode,
                        onCompleteMission = {
                            repository.markMissionCompleted("m_vocab_lab")
                            repository.addXp(120)
                        },
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.GRAMMAR_CHALLENGE -> GrammarChallengeScreen(
                        languageMode = languageMode,
                        onCompleteMission = {
                            repository.markMissionCompleted("m_grammar")
                            repository.addXp(140)
                        },
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.READING_MISSION -> ReadingMissionScreen(
                        languageMode = languageMode,
                        speechManager = speechManager,
                        onCompleteMission = {
                            repository.markMissionCompleted("m_reading")
                            repository.addXp(130)
                        },
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.ROUTINE_WALL -> RoutineWallScreen(
                        alias = alias,
                        wallEntries = wallEntries,
                        languageMode = languageMode,
                        onSavePost = { entry ->
                            repository.addWallEntry(entry)
                        },
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.FINAL_MISSION -> FinalMissionScreen(
                        languageMode = languageMode,
                        savedScript = repository.getSavedOralScript(),
                        speechManager = speechManager,
                        voiceRecorder = voiceRecorder,
                        initialScores = repository.getRubricScores(),
                        onSaveEvaluation = { script, scores ->
                            repository.saveOralScript(script)
                            repository.saveRubricScores(scores)
                        },
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.PROGRESS -> ProgressScreen(
                        xp = xp,
                        alias = alias,
                        avatar = avatar,
                        completedMissions = completedMissions,
                        unlockedBadges = unlockedBadges,
                        diagnosticScores = repository.getDiagnosticScores(),
                        isDiagnosticDone = repository.isDiagnosticCompleted(),
                        rubricScores = repository.getRubricScores(),
                        languageMode = languageMode,
                        onResetProgress = { repository.resetAllProgress() }
                    )
                    AppScreen.HELP_GLOSSARY -> HelpGlossaryScreen(
                        languageMode = languageMode,
                        onNavigate = { currentScreen = it }
                    )
                    AppScreen.PRINTABLE_SHEET -> PrintableSheetScreen(
                        onNavigate = { currentScreen = it }
                    )
                }

                if (showTutorDialog) {
                    CosmicTutorDialog(
                        languageMode = languageMode,
                        onDismiss = { showTutorDialog = false }
                    )
                }
            }
        }
    }
}
