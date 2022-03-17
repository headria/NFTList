package com.arabnetwork.nft.util

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment

object ShareUtil {

    private const val TAG = "ShareUtil"

    lateinit var fragment: Fragment

    fun shareTokenAddress(tokenAddress: String?) {
        try {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND;

            intent.type = "text/plain";
            intent.putExtra(Intent.EXTRA_TEXT, tokenAddress);
            fragment.startActivity(Intent.createChooser(intent, "Share"));
        } catch (e: Exception) {
            Log.d(TAG, "shareTokenAddress: ${e.message}")
        }
    }
}