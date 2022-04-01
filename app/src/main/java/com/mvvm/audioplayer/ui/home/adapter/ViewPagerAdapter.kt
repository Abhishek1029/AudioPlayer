package com.mvvm.audioplayer.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mvvm.audioplayer.ui.home.fragment.AlbumFragment
import com.mvvm.audioplayer.ui.home.fragment.ArtistFragment
import com.mvvm.audioplayer.ui.home.fragment.PlaylistFragment
import com.mvvm.audioplayer.ui.home.fragment.SongsFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SongsFragment()
            1 -> AlbumFragment()
            2 -> ArtistFragment()
            3 -> PlaylistFragment()
            else -> AlbumFragment()
        }
    }
}