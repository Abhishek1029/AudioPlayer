package com.mvvm.audioplayer.utils.notification

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.utils.helper.Constants.NOTIFICATION_CHANNEL_ID
import com.mvvm.audioplayer.utils.helper.Constants.NOTIFICATION_ID

class AudioNotificationManager(
    context: Context,
    notificationListener: PlayerNotificationManager.NotificationListener,
    sessionToken: MediaSessionCompat.Token
) {
    private val playerNotificationManager: PlayerNotificationManager

    init {
        val mediaController = MediaControllerCompat(context, sessionToken)
        playerNotificationManager = PlayerNotificationManager.Builder(
            context,
            NOTIFICATION_ID,
            NOTIFICATION_CHANNEL_ID
        ).setChannelNameResourceId(R.string.app_name)
            .setChannelDescriptionResourceId(R.string.channel_description)
            .setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
            .setNotificationListener(notificationListener)
            .setSmallIconResourceId(R.drawable.ic_launcher_foreground)
            .build().also {
                // media session token gives our NotificationManager access to our
                // current media session in music service so that it just also sees changes
                // in our music service
                it.setMediaSessionToken(sessionToken)
            }
    }

    fun showNotification(exoPlayer: ExoPlayer?) {
        playerNotificationManager.setPlayer(exoPlayer)
    }

    private inner class DescriptionAdapter(
        val mediaController: MediaControllerCompat
    ) : PlayerNotificationManager.MediaDescriptionAdapter {
        override fun getCurrentContentTitle(player: Player): CharSequence {
            return mediaController.metadata.description.title.toString()
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            return mediaController.sessionActivity
        }

        override fun getCurrentContentText(player: Player): CharSequence? {
            return mediaController.metadata.description.subtitle.toString()
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            return null
        }

    }
}