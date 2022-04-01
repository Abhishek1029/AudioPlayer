package com.mvvm.audioplayer.ui.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.databinding.ActivityHomeBinding
import com.mvvm.audioplayer.ui.home.adapter.ViewPagerAdapter
import com.mvvm.audioplayer.ui.home.fragment.AlbumFragment
import com.mvvm.audioplayer.ui.home.fragment.ArtistFragment
import com.mvvm.audioplayer.ui.home.fragment.PlaylistFragment
import com.mvvm.audioplayer.ui.home.fragment.SongsFragment
import com.mvvm.audioplayer.ui.home.provider.SongsFactory
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import com.mvvm.audioplayer.ui.home.viewmodel.SongsViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var activityHomeBinding: ActivityHomeBinding
    var viewPagerAdapter: ViewPagerAdapter? = null
    var repo:SongsRepository = SongsRepository(this@HomeActivity)
    var songsViewModel:SongsViewModel ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        songsViewModel =ViewModelProvider(this, SongsFactory(repo)).get(SongsViewModel::class.java)

        viewPagerAdapter = ViewPagerAdapter(this@HomeActivity)
        activityHomeBinding.viewPager.adapter = viewPagerAdapter

        songsViewModel!!.getSongsList()

        TabLayoutMediator(activityHomeBinding.tab,activityHomeBinding.viewPager) { tab, position ->
             when(position)
                {
                0-> tab.text="Songs"
                1-> tab.text="Album"
                2-> tab.text="Artist"
                3-> tab.text="Playlist"

            }
        }.attach()
    }
}