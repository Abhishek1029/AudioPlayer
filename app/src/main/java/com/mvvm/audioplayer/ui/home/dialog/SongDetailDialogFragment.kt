package com.mvvm.audioplayer.ui.home.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.databinding.DialogSongDetailsBinding
import com.mvvm.audioplayer.utils.extensions.materialDialog
import com.mvvm.audioplayer.utils.helper.Constants

class SongDetailDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mBinding = DialogSongDetailsBinding.inflate(layoutInflater)
        val dialogView = mBinding.root
        val song = arguments?.getParcelable<SongsModel>(Constants.SONG)
        mBinding.songsData = song
        return materialDialog(requireActivity().resources.getString(R.string.app_name))
            .setView(dialogView)
            .create()
    }

    companion object {
        fun createDialog(songsModel: SongsModel?): SongDetailDialogFragment {
            return SongDetailDialogFragment().apply {
                arguments = bundleOf(Constants.SONG to songsModel)
            }
        }
    }
}