package com.mvvm.audioplayer.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.databinding.SongBottomDialogBinding
import com.mvvm.audioplayer.ui.home.dialog.SongDetailDialogFragment
import com.mvvm.audioplayer.utils.RingtoneManager
import com.mvvm.audioplayer.utils.helper.Constants

class BottomDialogFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mBinding = SongBottomDialogBinding.inflate(inflater, container, false)
        val songsModel = arguments?.getParcelable<SongsModel>(Constants.SONG)
        mBinding.apply {
            detailsTV.setOnClickListener {
                SongDetailDialogFragment.createDialog(songsModel = songsModel)
                    .show(requireActivity().supportFragmentManager, "SongDialog")
                dismiss()
            }
            ringtoneTV.setOnClickListener {
                if (RingtoneManager.isDialogNeeded(requireContext())) {
                    RingtoneManager.openDialog(requireContext())
                } else {
                    val ringtoneManager = RingtoneManager(requireContext())
                    ringtoneManager.setRingtone(songsModel)
                }
            }
        }
        return mBinding.root
    }

    companion object {
        fun createBottomDialog(songsModel: SongsModel): BottomDialogFragment {
            return BottomDialogFragment().apply {
                val bundle = Bundle().apply {
                    putParcelable(Constants.SONG, songsModel)
                }
                arguments = bundle
            }
        }
    }
}