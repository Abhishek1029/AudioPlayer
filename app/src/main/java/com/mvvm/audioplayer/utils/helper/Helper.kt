package com.mvvm.audioplayer.utils.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.text.Spanned
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel


class Helper {
    companion object {
        fun showDialogBox(activity: Activity, songsModel: SongsModel) {
            val alertDialog = AlertDialog.Builder(activity, R.style.AppBottomSheetDialogTheme)
            val inflater: LayoutInflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView = inflater.inflate(R.layout.item_dialog_view, null)
            alertDialog.setView(dialogView)
            var recyclerView = dialogView.findViewById<RecyclerView>(R.id.dialog_recyclerView)
            val createPlaylist = dialogView.findViewById<TextView>(R.id.create_playlist)
            alertDialog.setCancelable(false)
            val dismiss = alertDialog.show()
            createPlaylist.setOnClickListener {
                dismiss.dismiss()
                insert_Playlist(activity, songsModel)
            }


            // alertDialog.show()
        }

        fun insert_Playlist(activity: Activity, model: SongsModel) {
            val alertDialog = AlertDialog.Builder(activity, R.style.AppBottomSheetDialogTheme)
            val inflater: LayoutInflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView = inflater.inflate(R.layout.song_bottom_dialog, null)
            alertDialog.setView(dialogView)
            var dismiss = alertDialog.show()

        }
    }
}