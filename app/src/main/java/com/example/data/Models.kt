package com.example.data

enum class LanguageMode {
    BOTH,
    EN_ONLY,
    ES_ONLY
}

data class Bilingual(
    val en: String,
    val es: String
)

enum class QuestionCategory {
    VOCABULARY,
    GRAMMAR,
    FREQUENCY
}

data class DiagnosticQuestion(
    val id: Int,
    val category: QuestionCategory,
    val promptEn: String,
    val promptEs: String,
    val optionsEn: List<String>,
    val optionsEs: List<String>,
    val correctIndex: Int,
    val explanationEn: String,
    val explanationEs: String
)

enum class RoutineTimeCategory {
    MORNING,
    AFTERNOON,
    EVENING
}

data class RoutineCard(
    val id: String,
    val expression: String,
    val spanishMeaning: String,
    val exampleEn: String,
    val exampleEs: String,
    val timeCategory: RoutineTimeCategory,
    val iconEmoji: String
)

enum class LabTier {
    TIER_1_CADET,
    TIER_2_NAVIGATOR,
    TIER_3_MASTER
}

enum class VocabQuestionType {
    ASSOCIATION,
    TIME_CLASSIFICATION,
    UNSCRAMBLE,
    TRUE_FALSE
}

data class VocabLabItem(
    val id: Int,
    val tier: LabTier,
    val type: VocabQuestionType,
    val promptEn: String,
    val promptEs: String,
    val optionsEn: List<String>,
    val optionsEs: List<String>,
    val correctIndex: Int,
    val explanationEn: String,
    val explanationEs: String,
    val clueEn: String = "",
    val clueEs: String = ""
)

enum class GrammarChallengeType {
    SELECT_FORM,
    SPOT_AND_FIX,
    WORD_ORDER,
    FILL_IN_BLANK
}

data class GrammarChallengeItem(
    val id: Int,
    val type: GrammarChallengeType,
    val ruleCategory: String, // "3rd_person", "negative", "frequency_position", "questions"
    val promptEn: String,
    val promptEs: String,
    val optionsEn: List<String>,
    val optionsEs: List<String>,
    val correctIndex: Int,
    val explanationEn: String,
    val explanationEs: String,
    val ruleTipEn: String,
    val ruleTipEs: String
)

data class ReadingStoryItem(
    val id: String,
    val titleEn: String,
    val titleEs: String,
    val characterName: String,
    val location: String,
    val flag: String,
    val textEn: String,
    val textEs: String,
    val keyVocab: List<Pair<String, String>>, // English to Spanish
    val questions: List<DiagnosticQuestion>,
    val imageResName: String = ""
)

data class RoutineWallEntry(
    val id: String,
    val authorAlias: String,
    val text: String,
    val sentenceCount: Int,
    val adverbsDetected: List<String>,
    val routinesDetected: List<String>,
    val timestampMillis: Long = System.currentTimeMillis()
)

data class RubricItem(
    val id: String,
    val titleEn: String,
    val titleEs: String,
    val weightMax: Int = 4,
    val level4En: String,
    val level4Es: String,
    val level3En: String,
    val level3Es: String,
    val level2En: String,
    val level2Es: String,
    val level1En: String,
    val level1Es: String
)

data class BadgeItem(
    val id: String,
    val titleEn: String,
    val titleEs: String,
    val descriptionEn: String,
    val descriptionEs: String,
    val iconEmoji: String,
    val isUnlocked: Boolean = false
)

data class DiagnosticReport(
    val totalScore: Int,
    val totalQuestions: Int,
    val vocabScore: Int,
    val grammarScore: Int,
    val frequencyScore: Int,
    val strengthsEn: List<String>,
    val strengthsEs: List<String>,
    val recommendationsEn: List<String>,
    val recommendationsEs: List<String>
)
