package com.example

import com.example.data.EducationalContent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun testRoutineCardsCompleteness() {
    val cards = EducationalContent.routineCards
    assertEquals("Should have exactly 10 core routine cards", 10, cards.size)

    val expectedExpressions = listOf(
      "wake up", "take a shower", "have breakfast", "go to school",
      "study", "have lunch", "do homework", "play", "have dinner", "go to bed"
    )

    expectedExpressions.forEach { expr ->
      val card = cards.find { it.expression.equals(expr, ignoreCase = true) }
      assertNotNull("Card for '$expr' must exist", card)
      assertTrue("Card must have a valid Spanish meaning", card!!.spanishMeaning.isNotBlank())
      assertTrue("Card must have an illustrative icon/emoji", card.iconEmoji.isNotBlank())
      assertTrue("Card must have an English example sentence", card.exampleEn.isNotBlank())
      assertTrue("Card must have a Spanish translation of example", card.exampleEs.isNotBlank())
    }
  }

  @Test
  fun testDiagnosticQuestionsIntegrity() {
    val questions = EducationalContent.diagnosticQuestions
    assertEquals("Mission Zero must feature exactly 15 diagnostic questions", 15, questions.size)
    questions.forEach { q ->
      assertTrue("Question ID must be greater than 0", q.id > 0)
      assertTrue("English prompt must be present", q.promptEn.isNotBlank())
      assertTrue("Spanish prompt must be present", q.promptEs.isNotBlank())
      assertTrue("English options must contain at least 2 choices", q.optionsEn.size >= 2)
      assertTrue("Spanish options must contain at least 2 choices", q.optionsEs.size >= 2)
      assertTrue("Correct answer index must be in range", q.correctIndex in q.optionsEn.indices)
      assertTrue("Immediate feedback explanation must be provided", q.explanationEn.isNotBlank())
      assertTrue("Immediate feedback Spanish explanation must be provided", q.explanationEs.isNotBlank())
    }
  }

  @Test
  fun testVocabLabAndGrammarChallenges() {
    val vocabQuestions = EducationalContent.vocabLabItems
    assertTrue("Vocabulary Lab must have at least 12 questions", vocabQuestions.size >= 12)

    val grammarChallenges = EducationalContent.grammarChallenges
    assertEquals("Grammar challenge should have 15 structured items", 15, grammarChallenges.size)

    val readingStories = EducationalContent.readingStories
    assertEquals("Reading Mission must have 3 levels/stories", 3, readingStories.size)
  }
}
