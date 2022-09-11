package com.mvvm.audioplayer.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.audioplayer.data.Album
import com.mvvm.audioplayer.databinding.SingleItemAlbumBinding

class AlbumAdapter : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(AlbumItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val mBinding =
            SingleItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AlbumViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        bindData(holder.mBinding,album)
    }

    private fun bindData(mBinding: SingleItemAlbumBinding, album: Album?) {
        mBinding.album = album
    }

    inner class AlbumViewHolder(val mBinding: SingleItemAlbumBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

    }

}

class AlbumItemCallBack : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }

}