package com.example.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

class ProgressRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("english_adventure_prefs", Context.MODE_PRIVATE)

    private val _xpFlow = MutableStateFlow(prefs.getInt("xp", 0))
    val xpFlow: StateFlow<Int> = _xpFlow.asStateFlow()

    private val _aliasFlow = MutableStateFlow(prefs.getString("alias", "Space Cadet 7th") ?: "Space Cadet 7th")
    val aliasFlow: StateFlow<String> = _aliasFlow.asStateFlow()

    private val _avatarFlow = MutableStateFlow(prefs.getString("avatar", "🧑‍🚀") ?: "🧑‍🚀")
    val avatarFlow: StateFlow<String> = _avatarFlow.asStateFlow()

    private val _completedMissionsFlow = MutableStateFlow(
        prefs.getStringSet("completed_missions", emptySet())?.toSet() ?: emptySet()
    )
    val completedMissionsFlow: StateFlow<Set<String>> = _completedMissionsFlow.asStateFlow()

    private val _unlockedBadgesFlow = MutableStateFlow(
        prefs.getStringSet("unlocked_badges", emptySet())?.toSet() ?: emptySet()
    )
    val unlockedBadgesFlow: StateFlow<Set<String>> = _unlockedBadgesFlow.asStateFlow()

    private val _languageModeFlow = MutableStateFlow(
        try {
            LanguageMode.valueOf(prefs.getString("language_mode", LanguageMode.BOTH.name) ?: LanguageMode.BOTH.name)
        } catch (_: Exception) {
            LanguageMode.BOTH
        }
    )
    val languageModeFlow: StateFlow<LanguageMode> = _languageModeFlow.asStateFlow()

    private val _soundEnabledFlow = MutableStateFlow(prefs.getBoolean("sound_enabled", true))
    val soundEnabledFlow: StateFlow<Boolean> = _soundEnabledFlow.asStateFlow()

    private val _reducedMotionFlow = MutableStateFlow(prefs.getBoolean("reduced_motion", false))
    val reducedMotionFlow: StateFlow<Boolean> = _reducedMotionFlow.asStateFlow()

    private val _wallEntriesFlow = MutableStateFlow(loadWallEntries())
    val wallEntriesFlow: StateFlow<List<RoutineWallEntry>> = _wallEntriesFlow.asStateFlow()

    // Diagnostic Scores
    fun getDiagnosticScores(): Triple<Int, Int, Int> {
        val vocab = prefs.getInt("diag_vocab", 0)
        val grammar = prefs.getInt("diag_grammar", 0)
        val freq = prefs.getInt("diag_freq", 0)
        return Triple(vocab, grammar, freq)
    }

    fun isDiagnosticCompleted(): Boolean = prefs.getBoolean("diag_completed", false)

    fun saveDiagnosticResults(vocab: Int, grammar: Int, freq: Int) {
        val total = vocab + grammar + freq
        prefs.edit()
            .putBoolean("diag_completed", true)
            .putInt("diag_vocab", vocab)
            .putInt("diag_grammar", grammar)
            .putInt("diag_freq", freq)
            .putInt("diag_total", total)
            .apply()
        addXp(150)
        markMissionCompleted("m_zero")
    }

    fun addXp(amount: Int) {
        val newXp = prefs.getInt("xp", 0) + amount
        prefs.edit().putInt("xp", newXp).apply()
        _xpFlow.value = newXp
        checkBadgeProgression(newXp)
    }

    fun setAlias(alias: String) {
        prefs.edit().putString("alias", alias).apply()
        _aliasFlow.value = alias
    }

    fun setAvatar(avatar: String) {
        prefs.edit().putString("avatar", avatar).apply()
        _avatarFlow.value = avatar
    }

    fun setLanguageMode(mode: LanguageMode) {
        prefs.edit().putString("language_mode", mode.name).apply()
        _languageModeFlow.value = mode
    }

    fun toggleLanguageMode() {
        val next = when (_languageModeFlow.value) {
            LanguageMode.BOTH -> LanguageMode.EN_ONLY
            LanguageMode.EN_ONLY -> LanguageMode.ES_ONLY
            LanguageMode.ES_ONLY -> LanguageMode.BOTH
        }
        setLanguageMode(next)
    }

    fun toggleSound(): Boolean {
        val newVal = !_soundEnabledFlow.value
        prefs.edit().putBoolean("sound_enabled", newVal).apply()
        _soundEnabledFlow.value = newVal
        return newVal
    }

    fun toggleReducedMotion(): Boolean {
        val newVal = !_reducedMotionFlow.value
        prefs.edit().putBoolean("reduced_motion", newVal).apply()
        _reducedMotionFlow.value = newVal
        return newVal
    }

    fun markMissionCompleted(missionId: String) {
        val current = prefs.getStringSet("completed_missions", emptySet())?.toMutableSet() ?: mutableSetOf()
        current.add(missionId)
        prefs.edit().putStringSet("completed_missions", current).apply()
        _completedMissionsFlow.value = current.toSet()

        // Unlock badges based on mission completion
        when (missionId) {
            "m_routines" -> unlockBadge("badge_vocab")
            "m_vocab_lab" -> unlockBadge("badge_vocab")
            "m_grammar" -> unlockBadge("badge_grammar")
            "m_reading" -> unlockBadge("badge_reading")
            "m_wall" -> unlockBadge("badge_writing")
            "m_final" -> unlockBadge("badge_communication")
        }

        // If completed all 7 core missions
        val allRequired = setOf("m_zero", "m_routines", "m_vocab_lab", "m_grammar", "m_reading", "m_wall", "m_final")
        if (current.containsAll(allRequired)) {
            unlockBadge("badge_mission_master")
        }
    }

    fun unlockBadge(badgeId: String) {
        val current = prefs.getStringSet("unlocked_badges", emptySet())?.toMutableSet() ?: mutableSetOf()
        if (!current.contains(badgeId)) {
            current.add(badgeId)
            prefs.edit().putStringSet("unlocked_badges", current).apply()
            _unlockedBadgesFlow.value = current.toSet()
            addXp(50)
        }
    }

    private fun checkBadgeProgression(xp: Int) {
        // Additional XP milestones
    }

    // Routine Wall Storage
    private fun loadWallEntries(): List<RoutineWallEntry> {
        val raw = prefs.getString("wall_entries_json", null)
        if (raw.isNullOrBlank()) {
            // Default exemplary model entries for students to read
            return listOf(
                RoutineWallEntry(
                    id = "model_1",
                    authorAlias = "Captain Cosmo (Model)",
                    text = "First, I always wake up at 6:00 AM. Then, I usually take a warm shower and have breakfast with my crew. Next, we study star navigation at the space base. In the afternoon, I often do my homework and play cosmic tennis. Finally, I rarely stay awake late and I go to bed at 9:30 PM.",
                    sentenceCount = 6,
                    adverbsDetected = listOf("always", "usually", "often", "rarely"),
                    routinesDetected = listOf("wake up", "take a shower", "have breakfast", "study", "do homework", "play", "go to bed")
                ),
                RoutineWallEntry(
                    id = "model_2",
                    authorAlias = "Astronaut Sofia (7th)",
                    text = "Every weekday, I always wake up early at 6:30 AM. After that, I usually have breakfast and walk to school with friends. We study science in the morning. Later, I always do my homework before dinner. In the evening, my family and I sometimes play board games. Finally, I go to bed at 9:00 PM.",
                    sentenceCount = 6,
                    adverbsDetected = listOf("always", "usually", "sometimes"),
                    routinesDetected = listOf("wake up", "have breakfast", "study", "do homework", "play", "go to bed")
                )
            )
        }
        return try {
            val array = JSONArray(raw)
            val list = mutableListOf<RoutineWallEntry>()
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val adverbsArr = obj.optJSONArray("adverbs") ?: JSONArray()
                val adverbsList = mutableListOf<String>()
                for (j in 0 until adverbsArr.length()) adverbsList.add(adverbsArr.getString(j))

                val routinesArr = obj.optJSONArray("routines") ?: JSONArray()
                val routinesList = mutableListOf<String>()
                for (j in 0 until routinesArr.length()) routinesList.add(routinesArr.getString(j))

                list.add(
                    RoutineWallEntry(
                        id = obj.getString("id"),
                        authorAlias = obj.getString("author"),
                        text = obj.getString("text"),
                        sentenceCount = obj.getInt("sentences"),
                        adverbsDetected = adverbsList,
                        routinesDetected = routinesList,
                        timestampMillis = obj.optLong("time", System.currentTimeMillis())
                    )
                )
            }
            list
        } catch (_: Exception) {
            emptyList()
        }
    }

    fun addWallEntry(entry: RoutineWallEntry) {
        val currentList = _wallEntriesFlow.value.toMutableList()
        currentList.add(0, entry)
        _wallEntriesFlow.value = currentList
        saveWallEntries(currentList)
        addXp(100)
        markMissionCompleted("m_wall")
    }

    private fun saveWallEntries(list: List<RoutineWallEntry>) {
        val array = JSONArray()
        list.take(20).forEach { item ->
            val obj = JSONObject().apply {
                put("id", item.id)
                put("author", item.authorAlias)
                put("text", item.text)
                put("sentences", item.sentenceCount)
                put("time", item.timestampMillis)
                val adverbsArr = JSONArray()
                item.adverbsDetected.forEach { adverbsArr.put(it) }
                put("adverbs", adverbsArr)
                val routinesArr = JSONArray()
                item.routinesDetected.forEach { routinesArr.put(it) }
                put("routines", routinesArr)
            }
            array.put(obj)
        }
        prefs.edit().putString("wall_entries_json", array.toString()).apply()
    }

    // Oral Presentation Script & Rubric Scores
    fun getSavedOralScript(): String = prefs.getString("oral_script", "") ?: ""

    fun saveOralScript(script: String) {
        prefs.edit().putString("oral_script", script).apply()
    }

    fun getRubricScores(): Map<String, Int> {
        val keys = listOf("vocab", "grammar", "frequency", "communication", "collaboration")
        val map = mutableMapOf<String, Int>()
        keys.forEach { key ->
            map[key] = prefs.getInt("rubric_$key", 3)
        }
        return map
    }

    fun saveRubricScores(scores: Map<String, Int>) {
        val editor = prefs.edit()
        scores.forEach { (k, v) ->
            editor.putInt("rubric_$k", v)
        }
        editor.putBoolean("oral_eval_done", true)
        editor.apply()
        addXp(120)
        markMissionCompleted("m_final")
    }

    fun isOralEvaluationCompleted(): Boolean = prefs.getBoolean("oral_eval_done", false)

    // Complete Progress Reset with Confirmation
    fun resetAllProgress() {
        prefs.edit().clear().apply()
        _xpFlow.value = 0
        _aliasFlow.value = "Space Cadet 7th"
        _avatarFlow.value = "🧑‍🚀"
        _completedMissionsFlow.value = emptySet()
        _unlockedBadgesFlow.value = emptySet()
        _languageModeFlow.value = LanguageMode.BOTH
        _soundEnabledFlow.value = true
        _reducedMotionFlow.value = false
        _wallEntriesFlow.value = loadWallEntries()
    }
}
