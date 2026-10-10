package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageMode
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.SpaceBorder
import com.example.ui.theme.SpaceCardNavy
import com.example.ui.theme.StarGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmicTopBar(
    title: String,
    xp: Int,
    languageMode: LanguageMode,
    soundEnabled: Boolean,
    onMenuClick: () -> Unit,
    onLanguageToggle: () -> Unit,
    onSoundToggle: () -> Unit,
    onTutorClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.testTag("nav_menu_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Open navigation menu",
                    tint = ElectricCyan
                )
            }
        },
        actions = {
            // XP Chip
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(StarGold.copy(alpha = 0.18f))
                    .border(1.dp, StarGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⭐", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "$xp XP",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = StarGold
                    )
                }
            }

            Spacer(modifier = Modifier.width(4.dp))

            // Language Mode Toggle Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                    .border(1.dp, SpaceBorder, RoundedCornerShape(8.dp))
                    .clickable { onLanguageToggle() }
                    .padding(horizontal = 6.dp, vertical = 5.dp)
                    .testTag("language_toggle_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Language toggle",
                        tint = ElectricCyan,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = when (languageMode) {
                            LanguageMode.BOTH -> "EN+ES"
                            LanguageMode.EN_ONLY -> "EN"
                            LanguageMode.ES_ONLY -> "ES"
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ElectricCyan
                    )
                }
            }

            // Sound Toggle
            IconButton(
                onClick = onSoundToggle,
                modifier = Modifier
                    .size(36.dp)
                    .testTag("sound_toggle_button")
            ) {
                Icon(
                    imageVector = if (soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeMute,
                    contentDescription = if (soundEnabled) "Sound on" else "Sound muted",
                    tint = if (soundEnabled) ElectricCyan else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Cosmic AI Tutor
            IconButton(
                onClick = onTutorClick,
                modifier = Modifier
                    .size(36.dp)
                    .testTag("ai_tutor_button")
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Cosmic AI Tutor",
                    tint = StarGold,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SpaceCardNavy,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
