package com.example.ui.screens

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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.window.Dialog
import com.example.data.LanguageMode
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardElevated
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.SpaceDeepNight
import com.example.ui.theme.SpanishHintColor
import com.example.ui.theme.StarGold

data class ChatMessage(
    val sender: String, // "Captain Cosmo" or "You"
    val enText: String,
    val esText: String,
    val isUser: Boolean = false
)

@Composable
fun CosmicTutorDialog(
    languageMode: LanguageMode,
    onDismiss: () -> Unit
) {
    var queryText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                sender = "Captain Cosmo",
                enText = "Greetings cadet! I am Captain Cosmo, your English Adventure tutor. Ask me about Daily Routines, Present Simple rules, or Adverbs of Frequency!",
                esText = "¡Saludos cadete! Soy el Capitán Cosmo, tu tutor espacial. ¡Pregúntame sobre rutinas diarias, reglas del presente simple o adverbios de frecuencia!"
            )
        )
    }

    val quickQuestions = listOf(
        "Why 'she plays' with -s?",
        "Difference: have breakfast vs eat breakfast?",
        "Where does 'always' go?",
        "Why 'doesn't play' without -s?",
        "How to say 'rara vez' in English?"
    )

    fun answerQuery(query: String) {
        val q = query.lowercase().trim()
        val (enAns, esAns) = when {
            q.contains("she play") || q.contains("-s") || q.contains("third person") || q.contains("tercera persona") -> {
                "In Present Simple affirmative, with he, she, and it, we always add -s to the verb: 'She plays football'. With verbs ending in consonant + y (like study), change to -ies ('studies'). With -ch, -sh, -ss, -x, -o, add -es ('watches', 'goes')." to
                "En presente simple afirmativo, con he, she o it, siempre agregamos -s al verbo: 'She plays football'. Con consonante + y cambia a -ies ('studies'). Con -ch, -sh, -ss, -x, -o agrega -es ('watches', 'goes')."
            }
            q.contains("have breakfast") || q.contains("eat breakfast") || q.contains("comida") -> {
                "In English, both 'have breakfast' and 'eat breakfast' are understood, but 'have breakfast', 'have lunch', and 'have dinner' are the most natural native expressions for meals!" to
                "En inglés, ambas formas se entienden, pero 'have breakfast', 'have lunch' y 'have dinner' son las expresiones más naturales y frecuentes para las tres comidas."
            }
            q.contains("where") || q.contains("always") || q.contains("position") || q.contains("posicion") || q.contains("orden") -> {
                "Rule: Frequency adverbs go BEFORE action verbs ('I always have breakfast'), but AFTER the verb TO BE ('He is always on time')." to
                "Regla: Los adverbios van ANTES de los verbos de acción ('I always have breakfast'), pero DESPUÉS del verbo TO BE ('He is always on time')."
            }
            q.contains("doesn't") || q.contains("negative") || q.contains("negacion") || q.contains("don't") -> {
                "When you use 'doesn't' (he doesn't, she doesn't), the auxiliary 'does' already carries the 3rd person! The main verb must be in base form: 'He doesn't play' (never 'He doesn't plays')." to
                "Cuando usas 'doesn't', el auxiliar 'does' ya tiene la marca de tercera persona. El verbo principal queda en su forma base: 'He doesn't play' (nunca 'doesn't plays')."
            }
            q.contains("rara vez") || q.contains("rarely") || q.contains("seldom") -> {
                "'Rara vez' is translated as 'rarely' in English (representing about 20% frequency): 'I rarely watch TV on weekdays'." to
                "'Rara vez' se traduce como 'rarely' en inglés (representa 20% de frecuencia): 'I rarely watch TV on weekdays'."
            }
            else -> {
                "Great question! Remember that in 7th grade daily routines: 1. Use base verbs with I/You/We/They. 2. Add -s/-es/-ies with He/She/It. 3. Place adverbs (always, usually, sometimes, never) before the action verb." to
                "¡Gran pregunta! Recuerda para rutinas de 7°: 1. Verbo base con I/You/We/They. 2. Agrega -s/-es/-ies con He/She/It. 3. Coloca adverbios (always, usually, etc.) antes del verbo de acción."
            }
        }

        messages.add(ChatMessage("You", query, query, isUser = true))
        messages.add(ChatMessage("Captain Cosmo", enAns, esAns, isUser = false))
        queryText = ""
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(540.dp),
            shape = RoundedCornerShape(20.dp),
            color = SpaceDeepNight,
            border = androidx.compose.foundation.BorderStroke(1.5.dp, ElectricCyan)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🧑‍🚀", fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Captain Cosmo • AI Tutor",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = ElectricCyan
                            )
                            Text(
                                text = "Tutor de Rutinas y Gramática",
                                fontSize = 11.sp,
                                color = SpanishHintColor
                            )
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Quick Prompt Chips
                Text(
                    text = "Quick Questions / Preguntas Rápidas:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = StarGold
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(quickQuestions) { qq ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SpaceCardNavy)
                                .border(1.dp, SpaceBorder, RoundedCornerShape(8.dp))
                                .clickable { answerQuery(qq) }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(text = qq, fontSize = 10.sp, color = ElectricCyan)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Chat Messages Scroll
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SpaceCardNavy)
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    messages.forEach { msg ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            contentAlignment = if (msg.isUser) Alignment.CenterEnd else Alignment.CenterStart
                        ) {
                            Column(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (msg.isUser) ElectricCyan.copy(alpha = 0.25f) else SpaceCardElevated)
                                    .border(1.dp, if (msg.isUser) ElectricCyan else SpaceBorder, RoundedCornerShape(10.dp))
                                    .padding(8.dp)
                                    .fillMaxWidth(0.9f)
                            ) {
                                Text(
                                    text = msg.sender,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (msg.isUser) ElectricCyan else StarGold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = msg.enText,
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    lineHeight = 16.sp
                                )
                                if (!msg.isUser && languageMode != LanguageMode.EN_ONLY) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = msg.esText,
                                        fontSize = 11.sp,
                                        color = SpanishHintColor,
                                        lineHeight = 15.sp
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Input Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = queryText,
                        onValueChange = { queryText = it },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("tutor_input_field"),
                        placeholder = { Text("Ask about routines...", fontSize = 12.sp) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = ElectricCyan,
                            unfocusedBorderColor = SpaceBorder,
                            focusedContainerColor = SpaceCardNavy,
                            unfocusedContainerColor = SpaceCardNavy
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    IconButton(
                        onClick = {
                            if (queryText.isNotBlank()) {
                                answerQuery(queryText)
                            }
                        },
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(ElectricCyan)
                            .testTag("send_tutor_button")
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "Send", tint = Color.Black, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}
