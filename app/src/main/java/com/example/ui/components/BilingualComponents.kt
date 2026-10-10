package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageMode
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.SuccessGreen

@Composable
fun BilingualText(
    en: String,
    es: String,
    languageMode: LanguageMode = LanguageMode.BOTH,
    modifier: Modifier = Modifier,
    enSize: TextUnit = 16.sp,
    esSize: TextUnit = 13.sp,
    enColor: Color = MaterialTheme.colorScheme.onSurface,
    esColor: Color = SpanishHintColor,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Column(modifier = modifier) {
        when (languageMode) {
            LanguageMode.BOTH -> {
                Text(
                    text = en,
                    fontSize = enSize,
                    fontWeight = fontWeight,
                    color = enColor,
                    lineHeight = (enSize.value * 1.3f).sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = es,
                    fontSize = esSize,
                    fontWeight = FontWeight.Normal,
                    color = esColor,
                    lineHeight = (esSize.value * 1.3f).sp
                )
            }
            LanguageMode.EN_ONLY -> {
                Text(
                    text = en,
                    fontSize = enSize,
                    fontWeight = fontWeight,
                    color = enColor,
                    lineHeight = (enSize.value * 1.3f).sp
                )
            }
            LanguageMode.ES_ONLY -> {
                Text(
                    text = es,
                    fontSize = enSize,
                    fontWeight = fontWeight,
                    color = enColor,
                    lineHeight = (enSize.value * 1.3f).sp
                )
            }
        }
    }
}

@Composable
fun BilingualInstructionCard(
    enInstruction: String,
    esInstruction: String,
    languageMode: LanguageMode,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f))
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            if (icon != null) {
                Box(modifier = Modifier.padding(end = 12.dp)) {
                    icon()
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Instruction",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 6.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            BilingualText(
                en = enInstruction,
                es = esInstruction,
                languageMode = languageMode,
                enSize = 14.sp,
                esSize = 12.sp,
                enColor = MaterialTheme.colorScheme.onSurface,
                esColor = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PedagogicalFeedbackCard(
    isCorrect: Boolean,
    enExplanation: String,
    esExplanation: String,
    languageMode: LanguageMode,
    modifier: Modifier = Modifier
) {
    val containerColor = if (isCorrect) SuccessGreen.copy(alpha = 0.15f) else ErrorRose.copy(alpha = 0.15f)
    val borderColor = if (isCorrect) SuccessGreen else ErrorRose
    val icon = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Error
    val iconTint = if (isCorrect) SuccessGreen else ErrorRose

    val headerEn = if (isCorrect) "Great job! / ¡Excelente!" else "Almost! / ¡Casi!"
    val headerEs = if (isCorrect) "Well understood!" else "Let's review the rule:"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .testTag("pedagogical_feedback_card")
            .clip(RoundedCornerShape(14.dp))
            .background(containerColor)
            .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = icon,
                contentDescription = if (isCorrect) "Correct" else "Needs practice",
                tint = iconTint,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = headerEn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = iconTint
                )
                Spacer(modifier = Modifier.height(4.dp))
                BilingualText(
                    en = enExplanation,
                    es = esExplanation,
                    languageMode = languageMode,
                    enSize = 14.sp,
                    esSize = 12.sp,
                    enColor = MaterialTheme.colorScheme.onSurface,
                    esColor = SpanishHintColor
                )
            }
        }
    }
}
