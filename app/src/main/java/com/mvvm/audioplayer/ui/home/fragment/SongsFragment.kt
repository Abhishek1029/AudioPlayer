package com.mvvm.audioplayer.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.gson.Gson
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.databinding.FragmentSongsBinding
import com.mvvm.audioplayer.di.CustomAnnotation.CustomeAnotation1
import com.mvvm.audioplayer.roomdb.AudioPlayerDB
import com.mvvm.audioplayer.roomdb.PlaylistEntity
import com.mvvm.audioplayer.ui.home.activity.HomeActivity
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import com.mvvm.audioplayer.ui.home.viewmodel.SongsViewModel
import com.mvvm.audioplayer.ui.home.adapter.SongsListAdapter
import com.mvvm.audioplayer.ui.home.provider.PlaylistFactory
import com.mvvm.audioplayer.ui.home.repository.PlayListRepository
import com.mvvm.audioplayer.ui.home.viewmodel.PlayListViewModel
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SongsFragment : Fragment() {
    @Inject
    lateinit var glide: RequestManager
    var songsViewModel: SongsViewModel? = null
    var fragmentSongsBinding: FragmentSongsBinding? = null
    var songsListAdapter: SongsListAdapter? = null
    var songsList: ArrayList<SongsModel> = ArrayList()
    var repo: SongsRepository? = null
    var playListRepository: PlayListRepository? = null
    var list: ArrayList<PlaylistEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repo = SongsRepository(requireActivity())
        playListRepository =
            PlayListRepository(requireActivity(), AudioPlayerDB.getDatabase(requireActivity()))

        if (!AudioPlayerDB.getDatabase(requireActivity()).isOpen) {
            AudioPlayerDB.getDatabase(requireActivity()).openHelper.readableDatabase
            AudioPlayerDB.getDatabase(requireActivity()).openHelper.writableDatabase
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songsViewModel = ViewModelProvider(this)[SongsViewModel::class.java]
        //songsListAdapter = SongsListAdapter(requireActivity(), songsList)

        fragmentSongsBinding!!.songRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            //adapter = songsListAdapter

        }

        lifecycleScope.launch(Dispatchers.IO)
        {
            songsViewModel!!.getSongsList()

        }
        observeSongData()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSongsBinding = FragmentSongsBinding.inflate(inflater, container, false)
        return fragmentSongsBinding!!.root
    }

    private fun observeSongData() {
        songsViewModel!!.songslist.observe(viewLifecycleOwner) {
            Log.e(TAG, "observeSongData: ${it.size}" )
            songsList.addAll(it)
            songsListAdapter = SongsListAdapter(requireActivity(), songsList,(activity as HomeActivity).player)
            fragmentSongsBinding?.songRecycler?.adapter = songsListAdapter
        }
    }

    companion object{
        private const val TAG = "SongsFragment"
    }
}