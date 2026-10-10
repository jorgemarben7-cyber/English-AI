package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.data.LanguageMode
import com.example.data.RoutineWallEntry
import com.example.ui.components.AppScreen
import com.example.ui.components.BilingualInstructionCard
import com.example.ui.components.BilingualText
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
fun RoutineWallScreen(
    alias: String,
    wallEntries: List<RoutineWallEntry>,
    languageMode: LanguageMode,
    onSavePost: (RoutineWallEntry) -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var textInput by remember {
        mutableStateOf(
            "First, I always wake up at 6:00 AM.\n" +
            "Then, I usually take a shower and have breakfast.\n" +
            "At 7:00 AM, I go to school with my friends.\n" +
            "In the afternoon, I often do my homework and study.\n" +
            "Later, I sometimes play soccer in the park.\n" +
            "Finally, I rarely stay awake late and I go to bed at 9:30 PM."
        )
    }

    // Dynamic checks
    val sentences = remember(textInput) {
        textInput.split(".", "\n")
            .map { it.trim() }
            .filter { it.length > 5 }
    }
    val sentenceCount = sentences.size

    val recognizedAdverbs = remember(textInput) {
        val lower = textInput.lowercase()
        listOf("always", "usually", "often", "sometimes", "rarely", "never").filter { lower.contains(it) }
    }

    val recognizedRoutines = remember(textInput) {
        val lower = textInput.lowercase()
        listOf("wake up", "take a shower", "have breakfast", "go to school", "study", "have lunch", "do homework", "play", "have dinner", "go to bed")
            .filter { lower.contains(it) }
    }

    val isSentenceCountValid = sentenceCount in 5..7
    val hasFourAdverbs = recognizedAdverbs.size >= 4
    val hasRoutines = recognizedRoutines.size >= 3

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
                    .background(ElectricBlue.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✍️", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "MISSION 5: MY DAILY ROUTINE WALL",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = ElectricCyan
                )
                Text(
                    text = "Taller de Escritura • 5 a 7 Oraciones",
                    fontSize = 12.sp,
                    color = SpanishHintColor
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        BilingualInstructionCard(
            enInstruction = "Write 5 to 7 sentences about your daily routine. Include at least 4 frequency adverbs (always, usually, etc.) and connect your ideas.",
            esInstruction = "Escribe de 5 a 7 oraciones sobre tu rutina diaria. Incluye al menos 4 adverbios de frecuencia y conecta tus ideas.",
            languageMode = languageMode
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Honest Note about Padlet / External Publishing
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(SpaceCardElevated)
                .border(1.dp, SpaceBorder, RoundedCornerShape(10.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = StarGold, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Digital Wall Notice: External Padlet cloud sync is not active. Your post will be stored in this device's classroom wall! You can copy your text to paste into your school board.\n\nAviso del Muro: La sincronización externa con Padlet no está habilitada. Tu publicación se guarda en el muro local de este dispositivo. ¡Puedes copiar el texto para pegarlo en tu mural escolar!",
                    fontSize = 11.sp,
                    color = Color.LightGray,
                    lineHeight = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Connectors & Adverbs Quick-Insert Bank
        Text(
            text = "Quick Connectors Bank (Tap to insert):",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = StarGold
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val connectors = listOf("First, ", "Then, ", "After that, ", "Next, ", "In the afternoon, ", "Later, ", "Finally, ")
            items(connectors) { conn ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SpaceCardNavy)
                        .border(1.dp, SpaceBorder, RoundedCornerShape(8.dp))
                        .clickable { textInput += " $conn" }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = conn, fontSize = 11.sp, color = ElectricCyan, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Adverbs of Frequency Bank:",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = StarGold
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val adverbs = listOf("always", "usually", "often", "sometimes", "rarely", "never")
            items(adverbs) { adv ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SpaceCardElevated)
                        .border(1.dp, SpaceBorder, RoundedCornerShape(8.dp))
                        .clickable { textInput += " $adv " }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = adv, fontSize = 11.sp, color = StarGold, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Text Input Field
        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .testTag("routine_wall_input"),
            label = { Text("Your Daily Routine (5–7 sentences in English)") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = ElectricCyan,
                unfocusedBorderColor = SpaceBorder,
                focusedContainerColor = SpaceCardNavy,
                unfocusedContainerColor = SpaceCardNavy
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Live Checklist Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Writing Checklist / Criterios de Escritura:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))

                ChecklistItemRow(
                    label = "Sentence Count: $sentenceCount (Target: 5–7 sentences)",
                    isValid = isSentenceCountValid
                )
                ChecklistItemRow(
                    label = "Frequency Adverbs: ${recognizedAdverbs.size} / 4 minimum (${recognizedAdverbs.joinToString()})",
                    isValid = hasFourAdverbs
                )
                ChecklistItemRow(
                    label = "Routine Actions: ${recognizedRoutines.size} detected (${recognizedRoutines.take(3).joinToString()}...)",
                    isValid = hasRoutines
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Action Buttons: Copy Text & Publish to Wall
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("Daily Routine", textInput))
                    Toast.makeText(context, "Copied to clipboard! / ¡Texto copiado!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag("copy_routine_button"),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Copy Text", fontSize = 12.sp)
            }

            Button(
                onClick = {
                    val entry = RoutineWallEntry(
                        id = "user_${System.currentTimeMillis()}",
                        authorAlias = alias,
                        text = textInput.trim(),
                        sentenceCount = sentenceCount,
                        adverbsDetected = recognizedAdverbs,
                        routinesDetected = recognizedRoutines
                    )
                    onSavePost(entry)
                    Toast.makeText(context, "Published to Routine Wall! / ¡Publicado en el muro!", Toast.LENGTH_SHORT).show()
                },
                enabled = textInput.length > 20,
                modifier = Modifier
                    .weight(1f)
                    .testTag("publish_routine_button"),
                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(imageVector = Icons.Default.PostAdd, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Publish to Wall", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Published Wall Entries List
        Text(
            text = "CLASSROOM ROUTINE WALL / MURO DE RUTINAS:",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        wallEntries.forEach { entry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCardNavy),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SpaceBorder))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🧑‍🚀 ${entry.authorAlias}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = ElectricCyan
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SpaceCardElevated)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${entry.sentenceCount} sentences",
                                fontSize = 10.sp,
                                color = StarGold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = entry.text,
                        fontSize = 13.sp,
                        color = Color.White,
                        lineHeight = 18.sp
                    )
                    if (entry.adverbsDetected.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Adverbs: ${entry.adverbsDetected.joinToString()}",
                            fontSize = 10.sp,
                            color = SpanishHintColor
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = { onNavigate(AppScreen.FINAL_MISSION) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("continue_to_final_mission_button"),
            colors = ButtonDefaults.buttonColors(containerColor = RocketOrange),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Continue to Final Mission: Presentation ➔", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun ChecklistItemRow(label: String, isValid: Boolean) {
    Row(
        modifier = Modifier.padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isValid) Icons.Default.CheckCircle else Icons.Default.Info,
            contentDescription = null,
            tint = if (isValid) SuccessGreen else StarGold,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = if (isValid) SuccessGreen else Color.LightGray
        )
    }
}
