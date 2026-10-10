package com.example.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class SpeechManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isReady = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w("SpeechManager", "US English TTS not supported, trying default English")
                tts?.setLanguage(Locale.ENGLISH)
            }
            tts?.setSpeechRate(0.9f) // Slightly slower for language learners
            tts?.setPitch(1.0f)
            isReady = true
        } else {
            Log.e("SpeechManager", "TTS initialization failed: $status")
        }
    }

    fun speak(text: String) {
        if (isReady && tts != null) {
            tts?.stop()
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "Utterance_${System.currentTimeMillis()}")
        }
    }

    fun stop() {
        if (isReady) {
            tts?.stop()
        }
    }

    fun destroy() {
        try {
            tts?.stop()
            tts?.shutdown()
            tts = null
            isReady = false
        } catch (e: Exception) {
            Log.e("SpeechManager", "Error shutting down TTS", e)
        }
    }
}
