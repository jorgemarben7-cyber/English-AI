package com.example.audio

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import java.io.File

class VoiceRecorderManager(private val context: Context) {

    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private val outputFile: File = File(context.cacheDir, "student_routine_speech.m4a")

    var isRecording: Boolean = false
        private set
    var isPlaying: Boolean = false
        private set

    fun hasRecording(): Boolean = outputFile.exists() && outputFile.length() > 0

    fun startRecording(): Boolean {
        return try {
            stopPlayback()
            mediaRecorder?.release()

            mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(context)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(outputFile.absolutePath)
                prepare()
                start()
            }
            isRecording = true
            true
        } catch (e: Exception) {
            Log.e("VoiceRecorderManager", "Error starting recording", e)
            isRecording = false
            false
        }
    }

    fun stopRecording(): Boolean {
        return try {
            if (isRecording && mediaRecorder != null) {
                mediaRecorder?.stop()
                mediaRecorder?.release()
                mediaRecorder = null
                isRecording = false
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e("VoiceRecorderManager", "Error stopping recording", e)
            isRecording = false
            false
        }
    }

    fun startPlayback(onCompletion: () -> Unit = {}): Boolean {
        if (!hasRecording()) return false
        return try {
            stopPlayback()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(outputFile.absolutePath)
                prepare()
                setOnCompletionListener {
                    this@VoiceRecorderManager.isPlaying = false
                    onCompletion()
                }
                start()
            }
            isPlaying = true
            true
        } catch (e: Exception) {
            Log.e("VoiceRecorderManager", "Error playing recording", e)
            isPlaying = false
            false
        }
    }

    fun stopPlayback() {
        try {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.stop()
            }
            mediaPlayer?.release()
            mediaPlayer = null
            isPlaying = false
        } catch (e: Exception) {
            Log.e("VoiceRecorderManager", "Error stopping playback", e)
        }
    }

    fun deleteRecording() {
        stopPlayback()
        if (outputFile.exists()) {
            outputFile.delete()
        }
    }

    fun cleanup() {
        try {
            if (isRecording) {
                mediaRecorder?.stop()
            }
            mediaRecorder?.release()
            mediaRecorder = null
            isRecording = false

            stopPlayback()
        } catch (e: Exception) {
            Log.e("VoiceRecorderManager", "Cleanup error", e)
        }
    }
}
