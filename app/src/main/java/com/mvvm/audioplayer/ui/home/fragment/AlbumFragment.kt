package com.mvvm.audioplayer.ui.home.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.Album
import com.mvvm.audioplayer.databinding.FragmentAlbumBinding
import com.mvvm.audioplayer.ui.home.adapter.AlbumAdapter
import com.mvvm.audioplayer.ui.home.viewmodel.AlbumViewModel
import com.mvvm.audioplayer.utils.helper.GridEqualSpacingItemDecorator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlbumFragment : Fragment() {
    private lateinit var mBinding:FragmentAlbumBinding
    private val mAlbumViewModel: AlbumViewModel by activityViewModels()
    private lateinit var albumAdapter: AlbumAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentAlbumBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumAdapter = AlbumAdapter()
        mBinding.albumRV.addItemDecoration(GridEqualSpacingItemDecorator(2,15))
        handleObservers()
        mAlbumViewModel.getAlbumList()

    }

    private fun handleObservers() {
        mAlbumViewModel.albumList.observe(viewLifecycleOwner, albumObserver)
    }

    // observer for list of Albums
    private val albumObserver =
        Observer<List<Album>> { listOfAlbums ->
            Log.e(
                TAG,
                "onChanged: ${listOfAlbums?.size ?: 0}"
            )
            mBinding.albumRV.adapter = albumAdapter
            albumAdapter.submitList(listOfAlbums)
        }

    companion object {
        private const val TAG = "AlbumFragment"
    }
}