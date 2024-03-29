package com.mvvm.audioplayer.ui.home.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.databinding.SongsListAdapterItemBinding
import com.mvvm.audioplayer.ui.home.activity.HomeActivity
import com.mvvm.audioplayer.ui.home.fragment.BottomDialogFragment
import com.mvvm.audioplayer.utils.helper.Helper

private const val TAG = "SongsListAdapter"

class SongsListAdapter(
    var context: Context,
    var songsList: ArrayList<SongsModel>,
    private val player: ExoPlayer
) :
    RecyclerView.Adapter<SongsListAdapter.SongsHolder>() {

    inner class SongsHolder(var binding: SongsListAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder {
        val songsListAdapterItemBinding =
            SongsListAdapterItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SongsHolder(songsListAdapterItemBinding)
    }

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {
        bindData(holder.binding, songsList[position], position)
        holder.binding.menuIcon.setOnClickListener {
            val menu = PopupMenu(context, holder.binding.menuIcon)
            menu.inflate(R.menu.song_menu)
            menu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.playlist -> {
                        Helper.showDialogBox(context as Activity, songsList[holder.adapterPosition])

                        /*
   */
                        true
                    }
                    R.id.delete -> {
                        Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.favourite -> {
                        Toast.makeText(context, "favourite", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
            menu.show()
        }
    }

    private fun bindData(
        binding: SongsListAdapterItemBinding,
        songsModel: SongsModel,
        position: Int
    ) {
        binding.songsData = songsModel
        /**
         * Play song on item click
         */
        binding.parent.setOnClickListener {
            player.apply {
                if (!isPlaying) {
                    setMediaItems(getMediaItems(), position, 0)
                } else {
                    pause()
                    seekTo(position, 0)
                }
                prepare()
                play()
            }
        }

        binding.parent.setOnLongClickListener {
            BottomDialogFragment.createBottomDialog(songsModel).show(
                (context as HomeActivity).supportFragmentManager,
                "MusicBottomDialog"
            )
            /**
             * returning true means that the event is handled by longPress click and no further handling is required
             * returning false means that event has not handled so other events from event tree will process
             * like if long click returns false the the other event onClick will process the execution.
             */
            true
        }
    }

    private fun getMediaItems(): List<MediaItem> {
        /**
         * Define a list of MediaItem
         */
        val mediaItemList = ArrayList<MediaItem>()
        for (song in songsList) {
            val mediaItem = MediaItem.Builder()
                .setUri(song.path)
                .setMediaMetadata(getMediaMetaData(song))
                .build()
            mediaItemList.add(mediaItem)
        }
        return mediaItemList
    }

    private fun getMediaMetaData(song: SongsModel): MediaMetadata {
        return MediaMetadata.Builder()
            .setTitle(song.songTitle)
            .build()
    }

    override fun getItemCount(): Int {
        return songsList.size
    }
}