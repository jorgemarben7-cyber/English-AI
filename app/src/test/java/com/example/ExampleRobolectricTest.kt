package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.EducationalContent
import com.example.data.ProgressRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("English Adventure", appName)
  }

  @Test
  fun `verify educational content structure`() {
    // 10 routine cards with expressions, spanish translation, icons, and examples
    assertEquals(10, EducationalContent.routineCards.size)
    EducationalContent.routineCards.forEach { card ->
      assertTrue(card.expression.isNotBlank())
      assertTrue(card.spanishMeaning.isNotBlank())
      assertTrue(card.iconEmoji.isNotBlank())
      assertTrue(card.exampleEn.isNotBlank())
    }
    // 15 diagnostic questions (5 vocab, 5 grammar, 5 frequency)
    assertEquals(15, EducationalContent.diagnosticQuestions.size)
    // 15 grammar challenge items
    assertEquals(15, EducationalContent.grammarChallenges.size)
    // 3 reading stories
    assertEquals(3, EducationalContent.readingStories.size)
    // 5 rubric criteria
    assertEquals(5, EducationalContent.rubricCriteria.size)
    // 6 badges
    assertEquals(6, EducationalContent.badges.size)
  }

  @Test
  fun `verify progress repository persistence`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val repo = ProgressRepository(context)

    repo.setAlias("StarCadet")
    assertEquals("StarCadet", repo.aliasFlow.value)

    repo.addXp(100)
    assertTrue(repo.xpFlow.value >= 100)

    repo.markMissionCompleted("m_zero")
    assertTrue(repo.completedMissionsFlow.value.contains("m_zero"))
  }
}
