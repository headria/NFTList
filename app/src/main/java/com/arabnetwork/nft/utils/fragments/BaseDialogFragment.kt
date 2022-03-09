package com.arabnetwork.nft.utils.fragments

import android.os.Bundle
import android.view.View
import com.arabnetwork.nft.R

open class BaseDialogFragment : BaseDialog() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // for opening the fragment as dialog fragment
        setStyle(
            STYLE_NORMAL,
            R.style.FullScreenDialogStyle
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // for setting the animation when opening the fragment
        dialog?.window?.attributes?.windowAnimations = R.style.SymbolDetailsTransition
    }
}