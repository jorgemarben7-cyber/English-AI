package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageMode
import com.example.data.RoutineCard as RoutineCardModel
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold

/**
 * Reusable Composable RoutineCard component that displays:
 * - English routine expression (e.g., "wake up")
 * - Spanish translation (e.g., "despertarse / levantarse")
 * - Illustrative icon / emoji
 * - Optional audio pronunciation button
 * - Simple example sentence in English (with optional Spanish translation)
 */
@Composable
fun RoutineCard(
    expression: String,
    spanishTranslation: String,
    iconEmoji: String,
    exampleSentenceEn: String,
    modifier: Modifier = Modifier,
    exampleSentenceEs: String? = null,
    timeCategory: String? = null,
    onAudioClick: (() -> Unit)? = null,
    onExampleAudioClick: (() -> Unit)? = null,
    showSpanish: Boolean = true
) {
    val cleanTag = expression.lowercase().replace(" ", "_").trim()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("routine_card_$cleanTag")
            .semantics {
                contentDescription = "Routine card for $expression. Meaning in Spanish: $spanishTranslation"
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = SolidColor(ElectricCyan.copy(alpha = 0.6f))
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Optional time category pill (e.g. MORNING, AFTERNOON, EVENING)
            if (!timeCategory.isNullOrBlank()) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SpaceCardElevated)
                        .border(1.dp, SpaceBorder, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "$timeCategory ROUTINE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = StarGold,
                        letterSpacing = 0.5.sp
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Illustrative Icon / Visual Emoji
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(SpaceCardElevated)
                    .border(1.5.dp, ElectricCyan.copy(alpha = 0.35f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = iconEmoji,
                    fontSize = 44.sp,
                    modifier = Modifier.semantics {
                        contentDescription = "Icon representing $expression"
                    }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Routine Expression & Optional Audio Pronunciation Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = expression,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                if (onAudioClick != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = onAudioClick,
                        modifier = Modifier
                            .size(48.dp) // Minimum 48dp touch target for accessibility
                            .clip(CircleShape)
                            .background(ElectricCyan.copy(alpha = 0.2f))
                            .border(1.dp, ElectricCyan.copy(alpha = 0.4f), CircleShape)
                            .testTag("audio_button_$cleanTag")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Listen to pronunciation of $expression",
                            tint = ElectricCyan,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // Spanish Translation
            if (showSpanish && spanishTranslation.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = spanishTranslation,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = SpanishHintColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Example Sentence Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SpaceCardElevated)
                    .border(1.dp, SpaceBorder, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Example Sentence / Oración de ejemplo:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = StarGold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = exampleSentenceEn,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            lineHeight = 20.sp
                        )
                        if (showSpanish && !exampleSentenceEs.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = exampleSentenceEs,
                                fontSize = 12.sp,
                                color = SpanishHintColor,
                                lineHeight = 17.sp
                            )
                        }
                    }

                    if (onExampleAudioClick != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = onExampleAudioClick,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(StarGold.copy(alpha = 0.15f))
                                .testTag("example_audio_button_$cleanTag")
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Listen to example sentence audio",
                                tint = StarGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Model-driven overload of RoutineCard accepting a [RoutineCardModel].
 */
@Composable
fun RoutineCard(
    card: RoutineCardModel,
    modifier: Modifier = Modifier,
    languageMode: LanguageMode = LanguageMode.BOTH,
    onAudioClick: (() -> Unit)? = null,
    onExampleAudioClick: (() -> Unit)? = null
) {
    RoutineCard(
        expression = card.expression,
        spanishTranslation = card.spanishMeaning,
        iconEmoji = card.iconEmoji,
        exampleSentenceEn = card.exampleEn,
        exampleSentenceEs = card.exampleEs,
        modifier = modifier,
        timeCategory = card.timeCategory.name,
        onAudioClick = onAudioClick,
        onExampleAudioClick = onExampleAudioClick,
        showSpanish = languageMode != LanguageMode.EN_ONLY
    )
}

@Preview(showBackground = true)
@Composable
fun RoutineCardPreview() {
    MaterialTheme {
        RoutineCard(
            expression = "wake up",
            spanishTranslation = "despertarse / levantarse",
            iconEmoji = "⏰",
            exampleSentenceEn = "I wake up at 6:00 AM every school day.",
            exampleSentenceEs = "Me despierto a las 6:00 AM todos los días de colegio.",
            timeCategory = "MORNING",
            onAudioClick = {},
            onExampleAudioClick = {}
        )
    }
}
