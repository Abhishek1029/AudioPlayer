package com.mvvm.audioplayer.utils.service

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.util.NotificationUtil.IMPORTANCE_HIGH
import com.mvvm.audioplayer.R

class AudioPlayerService : Service() {
    private val audioServiceBinder: IBinder = AudioServiceBinder()
    private lateinit var playerNotificationManager: PlayerNotificationManager

    override fun onCreate() {
        super.onCreate()
        playerNotificationManager =
            PlayerNotificationManager.Builder(applicationContext, NOTIFICATION_ID, CHANNEL_ID)
                .setChannelImportance(IMPORTANCE_HIGH)
                .setChannelNameResourceId(R.string.app_name)
                .setSmallIconResourceId(R.drawable.ic_launcher_foreground)
                .build()
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

    companion object {
        const val CHANNEL_ID = "Audio Player Media Channel"
        const val NOTIFICATION_ID = 111
    }

}
