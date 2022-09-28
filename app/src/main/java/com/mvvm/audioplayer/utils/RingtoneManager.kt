package com.mvvm.audioplayer.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.BaseColumns
import android.provider.MediaStore
import android.provider.Settings
import android.view.contentcapture.ContentCaptureContext
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel

class RingtoneManager(val context: Context) {

    fun setRingtone(songsModel: SongsModel?) {
        val contentResolver = context.contentResolver
        val uri = AudioUtils.getSongUri(songsModel?.id)
        try {
            val cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.MediaColumns.TITLE),
                BaseColumns._ID + "=?",
                arrayOf(songsModel?.id.toString()), null
            )
            cursor.use {
                if (it != null && it.count == 1) {
                    it.moveToFirst()
                    Settings.System.putString(
                        contentResolver,
                        Settings.System.RINGTONE,
                        uri?.toString()
                    )
                    val message =
                        context.getString(R.string.song_has_been_set_as_ringtone, it.getString(0))
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        } catch (se: SecurityException) {

        }
    }

    companion object {
        fun isDialogNeeded(context: Context): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.System.canWrite(context)) {
                    return true
                }
            }
            return false
        }

        fun openDialog(context: Context) {
            return AlertDialog.Builder(context)
                .setTitle(R.string.set_ringtone)
                .setMessage(R.string.message_set_ringtone)
                .setPositiveButton(R.string.ok) { _, _ ->
                    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                    intent.data = Uri.parse("package:" + context.applicationContext.packageName)
                    context.startActivity(intent)
                }
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show()
        }
    }
}