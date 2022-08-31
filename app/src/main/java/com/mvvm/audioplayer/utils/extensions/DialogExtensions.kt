package com.mvvm.audioplayer.utils.extensions

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mvvm.audioplayer.R

fun DialogFragment.materialDialog(title: String): AlertDialog.Builder {
    return AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setPositiveButton(android.R.string.ok, null)
}