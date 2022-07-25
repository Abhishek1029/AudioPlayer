package com.mvvm.audioplayer.ui.home.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.databinding.SongsListAdapterItemBinding
import com.mvvm.audioplayer.utils.helper.Helper

class SongsListAdapter(var context: Context, var songsList: ArrayList<SongsModel>) :
    RecyclerView.Adapter<SongsListAdapter.SongsHolder>() {

    inner class SongsHolder(var binding: SongsListAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder {
        val songsListAdapterItemBinding =
            SongsListAdapterItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SongsHolder(songsListAdapterItemBinding)
    }

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {
        bindData(holder.binding,songsList[position])
        holder.binding.menuIcon.setOnClickListener {
            val menu = PopupMenu(context, holder.binding.menuIcon)
            menu.inflate(R.menu.song_menu)
            menu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.playlist -> {
                        Helper.showDialogBox(context as Activity,songsList[holder.adapterPosition])

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

    private fun bindData(binding: SongsListAdapterItemBinding, songsModel: SongsModel) {
        binding.songsData = songsModel
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notiydata(songsList: ArrayList<SongsModel>) {
        this.songsList = songsList
        notifyDataSetChanged()
    }

    fun coverpicture(path: String?): Bitmap? {
        val mr = MediaMetadataRetriever()
        mr.setDataSource(path)
        val byte1 = mr.embeddedPicture
        mr.release()
        return if (byte1 != null) BitmapFactory.decodeByteArray(byte1, 0, byte1.size) else null
    }
}