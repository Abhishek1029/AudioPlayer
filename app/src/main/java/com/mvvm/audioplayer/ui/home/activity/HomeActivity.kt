package com.mvvm.audioplayer.ui.home.activity

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.material.tabs.TabLayoutMediator
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.databinding.ActivityHomeBinding
import com.mvvm.audioplayer.ui.home.adapter.ViewPagerAdapter
import com.mvvm.audioplayer.ui.player.activity.PlayerActivity
import com.mvvm.audioplayer.utils.helper.Constants
import com.mvvm.audioplayer.utils.service.AudioPlayerService
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

private const val TAG = "HomeActivity"

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var activityHomeBinding: ActivityHomeBinding
    var viewPagerAdapter: ViewPagerAdapter? = null
    lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        viewPagerAdapter = ViewPagerAdapter(this@HomeActivity)
        activityHomeBinding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(activityHomeBinding.tab, activityHomeBinding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.songs)
                1 -> tab.text = getString(R.string.album)
                2 -> tab.text = getString(R.string.artist)
                3 -> tab.text = getString(R.string.playlist)

            }
        }.attach()
        player = ExoPlayer.Builder(this).build()
        player.addListener(playerListener)
        activityHomeBinding.apply {
            homeSongTitleTV.isSelected = true
            homePrevIcon.setOnClickListener {
                playPrevSong()
            }

            homeNextIcon.setOnClickListener {
                playNextSong()
            }

            homePlayPauseIcon.setOnClickListener {
                playPausePlayer()
            }

            // homePlayerWrapper.setOnTouchListener(PlayerGestureDetector(this@HomeActivity))
            homePlayerWrapper.setOnClickListener {

                Intent(this@HomeActivity, PlayerActivity::class.java).apply {
                    putExtra(Constants.CURRENT_PLAYBACK_POSITION, player.currentPosition)
                    putExtra(Constants.SONG_DURATION, player.duration)
                    putExtra(Constants.SONG_TITLE, player.currentMediaItem?.mediaMetadata?.title)
                }.also {
                    startActivity(it)
                }
                //   overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
            }
        }
    }

    private fun playPausePlayer() {
        if (player.isPlaying) {
            player.pause()
            activityHomeBinding.homePlayPauseIcon.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_baseline_play_arrow_24,
                0,
                0,
                0,
            )
        } else {
            player.play()
            activityHomeBinding.homePlayPauseIcon.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_baseline_pause_24,
                0,
                0,
                0,
            )
        }
    }

    fun playPrevSong() {
        if (player.hasPreviousMediaItem()) {
            player.seekToPrevious()
        }
    }

    fun playNextSong() {
        if (player.hasNextMediaItem()) {
            player.seekToNext()
        }
    }

    private val playerListener = object : Player.Listener {
        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            super.onMediaItemTransition(mediaItem, reason)
            activityHomeBinding.apply {
                homeSongTitleTV.text = mediaItem?.mediaMetadata?.title
                homePlayPauseIcon.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_pause_24,
                    0,
                    0,
                    0,
                )
            }
            if (!player.isPlaying) {
                player.play()
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            if (playbackState == ExoPlayer.STATE_READY) {
                activityHomeBinding.apply {
                    homeSongTitleTV.text = player.currentMediaItem?.mediaMetadata?.title
                    homePlayPauseIcon.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_baseline_pause_24,
                        0,
                        0,
                        0,
                    )
                }
            } else {
                activityHomeBinding.homePlayPauseIcon.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_play_arrow_24,
                    0,
                    0,
                    0,
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player.isPlaying) {
            player.stop()
        }
        player.release()
    }

    class PlayerGestureDetector(val context: Context) : View.OnTouchListener {
        private var gestureController =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    /**
                     * abs(x) returns the absolute value of given x
                     */
                    if (abs(velocityX) > abs(velocityY)) {
                        if (abs(velocityX) < 0) {
                            (context as HomeActivity).playNextSong()
                        } else if (abs(velocityX) > 0) {
                            (context as HomeActivity).playPrevSong()
                        }
                    }
                    return false
                }
            })

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            return gestureController.onTouchEvent(p1)
        }

    }

    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            val binder: AudioPlayerService.AudioServiceBinder =
                binder as AudioPlayerService.AudioServiceBinder
            val service:AudioPlayerService = binder.getPlayerService()
            service.playSong("")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.e(TAG, "onServiceDisconnected: ")
        }

    }


}