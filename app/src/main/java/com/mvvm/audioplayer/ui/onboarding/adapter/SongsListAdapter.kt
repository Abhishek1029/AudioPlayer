package com.mvvm.audioplayer.ui.onboarding.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.databinding.SongsListAdapterItemBinding

class SongsListAdapter(var context: Context,var songsList:ArrayList<SongsModel>): RecyclerView.Adapter<SongsListAdapter.SongsHolder>() {

    inner class SongsHolder(var binding: SongsListAdapterItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder {
        val songsListAdapterItemBinding =SongsListAdapterItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return SongsHolder(songsListAdapterItemBinding)
    }

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {

        holder.binding.songTitle.text= songsList[position].songTitle
        holder.binding.artistName.text= songsList[position].artistName
        holder.binding.albumName.text= songsList[position].albumName

        holder.binding.menuIcon.setOnClickListener {
            val menu = PopupMenu(context,holder.binding.menuIcon)
            menu.inflate(R.menu.song_menu)
            menu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                when(it.itemId)
                {
                    R.id.playlist->{
                        Toast.makeText(context,"playlist ${holder.adapterPosition}",Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.delete->{
                        Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.favourite->{
                        Toast.makeText(context,"favourite",Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        true
                    }
                }
            })
            menu.show()

        }

    }

    override fun getItemCount(): Int {
      return  songsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notiydata(songsList: ArrayList<SongsModel>) {
        this.songsList =songsList
        notifyDataSetChanged()
    }
}