package com.mvvm.audioplayer.utils.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.util.NotificationUtil.IMPORTANCE_HIGH
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.utils.helper.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "AudioPlayerService"

@AndroidEntryPoint
class AudioPlayerService : Service() {
    private val audioServiceBinder: IBinder = AudioServiceBinder()
    private lateinit var playerNotificationManager: PlayerNotificationManager

    @Inject
    lateinit var exoPlayer: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        playerNotificationManager =
            PlayerNotificationManager.Builder(applicationContext, NOTIFICATION_ID, CHANNEL_ID)
                .setChannelImportance(IMPORTANCE_HIGH)
                .setChannelNameResourceId(R.string.app_name)
                .setSmallIconResourceId(R.drawable.ic_launcher_foreground)
                .build()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: $exoPlayer")
        // creating Exo Player instance
        exoPlayer = ExoPlayer.Builder(applicationContext).build()
        val mediaItem =
            MediaItem.Builder()
                .setUri(intent?.getStringExtra(Constants.AUDIO_URI))
                .build()
        exoPlayer.apply {
            if (isPlaying) {
                stop()
            }
            setMediaItem(mediaItem)
            prepare()
            play()
        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return audioServiceBinder
    }

    // binder class
    class AudioServiceBinder : Binder() {
        fun getPlayerService(): AudioPlayerService {
            return AudioPlayerService()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    companion object {
        const val CHANNEL_ID = "Audio Player Media Channel"
        const val NOTIFICATION_ID = 111
    }

}
