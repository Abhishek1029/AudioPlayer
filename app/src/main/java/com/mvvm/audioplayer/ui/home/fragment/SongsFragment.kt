package com.mvvm.audioplayer.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.databinding.FragmentSongsBinding
import com.mvvm.audioplayer.ui.home.provider.SongsFactory
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import com.mvvm.audioplayer.ui.home.viewmodel.SongsViewModel
import com.mvvm.audioplayer.ui.onboarding.adapter.SongsListAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SongsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var songsViewModel: SongsViewModel? = null
    var fragmentSongsBinding:FragmentSongsBinding?=null
    var songsListAdapter:SongsListAdapter?=null
    var songsList:ArrayList<SongsModel> = ArrayList()
    var repo: SongsRepository?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        repo = SongsRepository(requireActivity())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songsViewModel = ViewModelProvider(this, SongsFactory(repo!!)).get(SongsViewModel::class.java)
        songsListAdapter =SongsListAdapter(requireActivity(),songsList)

        fragmentSongsBinding!!.songRecycler.apply {
            this.setHasFixedSize(true)
            this.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            this.adapter =songsListAdapter
        }

        songsViewModel!!.getSongsList()
        observeSongData()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        fragmentSongsBinding = FragmentSongsBinding.inflate(inflater,container,false)
        return fragmentSongsBinding!!.root
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observeSongData()
    {
        songsViewModel!!.songslist.observe(viewLifecycleOwner, Observer {
            songsList.addAll(it)

            songsListAdapter!!.notiydata(songsList)
        })
    }

}