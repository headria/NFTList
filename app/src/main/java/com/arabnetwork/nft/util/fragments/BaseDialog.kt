package com.arabnetwork.nft.util.fragments

import android.content.DialogInterface
import androidx.fragment.app.DialogFragment

open class BaseDialog : DialogFragment() {

    override fun onPause() {
        super.onPause()
        // for disabling the animation onResume of the fragment
        dialog?.window?.setWindowAnimations(-1)
    }

    override fun onDismiss(dialogInterface: DialogInterface) {
        super.onDismiss(dialogInterface)
    }

}