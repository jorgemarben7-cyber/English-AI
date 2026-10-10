package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageMode
import com.example.ui.components.AppScreen
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.SpaceCardNavy

@Composable
fun PrintableSheetScreen(
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val worksheetText = """
ENGLISH ADVENTURE: DAILY ROUTINES & PRESENT SIMPLE
INSTITUCIÓN EDUCATIVA / GRADO SÉPTIMO (7°)
Estudiante: ___________________________ Fecha: ______________
Equipo / Pareja: _______________________ Rol: _______________

-----------------------------------------------------------------
PARTE 1: VOCABULARIO DE RUTINAS (Daily Routines)
Escribe la rutina correspondiente para cada acción:
1. Me despierto en la mañana: __________________________________
2. Tomo una ducha con agua y jabón: ____________________________
3. Desayuno antes del colegio: _________________________________
4. Voy a la escuela: __________________________________________
5. Almuerzo al mediodía: ______________________________________
6. Hago la tarea escolar en casa: ______________________________
7. Me acuesto a dormir en la noche: ____________________________

-----------------------------------------------------------------
PARTE 2: PRESENTE SIMPLE Y TERCERA PERSONA (-s, -es, -ies)
Completa con la forma adecuada del verbo entre paréntesis:
1. She _________________ (wake up) at 6:15 AM every weekday.
2. Carlos _________________ (study) English and math in the morning.
3. Diego _________________ (not / play) soccer on Monday. [doesn't play]
4. My sister _________________ (go) to bed at 9:00 PM.
5. They _________________ (have) lunch in the school yard.

-----------------------------------------------------------------
PARTE 3: ADVERBIOS DE FRECUENCIA (always, usually, sometimes, never)
Ordena las palabras para formar una oración correcta:
1. [walks / usually / to school / Maria]
   ➔ ___________________________________________________________
2. [happy / is / always / Daniel]
   ➔ ___________________________________________________________

-----------------------------------------------------------------
PARTE 4: TALLER DE ESCRITURA PERSONAL (5 a 7 oraciones)
Escribe tu rutina usando conectores (First, Then, Finally) y al menos
cuatro adverbios de frecuencia (always, usually, often, sometimes, rarely, never):
1. _____________________________________________________________
2. _____________________________________________________________
3. _____________________________________________________________
4. _____________________________________________________________
5. _____________________________________________________________
6. _____________________________________________________________

-----------------------------------------------------------------
PARTE 5: RÚBRICA DE AUTOEVALUACIÓN (Máx 20 pts)
[ ] Vocabulario de rutinas (1-4 pts)
[ ] Estructura presente simple (1-4 pts)
[ ] Adverbios de frecuencia (1-4 pts)
[ ] Comunicación comprensible (1-4 pts)
[ ] Trabajo colaborativo y cumplimiento de rol (1-4 pts)
PUNTAJE TOTAL: _____ / 20 pts (18-20: Superior, 15-17: Alto, 11-14: Básico)
    """.trimIndent()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Back Button & Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { onNavigate(AppScreen.HELP_GLOSSARY) },
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Back to Help", fontSize = 12.sp)
            }

            Row {
                Button(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Classroom Worksheet", worksheetText))
                        Toast.makeText(context, "Worksheet copied! / ¡Ficha copiada!", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Copy", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(6.dp))

                Button(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, "English Adventure - Grade 7 Worksheet")
                            putExtra(Intent.EXTRA_TEXT, worksheetText)
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share / Print Worksheet"))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Share", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Printable Paper Preview Card (Clean, high contrast)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color.LightGray))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = worksheetText,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Black,
                    lineHeight = 17.sp
                )
            }
        }
    }
}
