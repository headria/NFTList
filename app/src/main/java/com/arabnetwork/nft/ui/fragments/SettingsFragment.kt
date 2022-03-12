package com.arabnetwork.nft.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    companion object {

    }

    /**
     * binding
     */
    private var _binding: FragmentSettingsBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private lateinit var mSwitchBtn: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        build()
    }

    private fun build() {
        mBinding?.switchBtn?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                goToDarkMode()
            else
                goToLightMode()
        }
    }

    private fun goToLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun goToDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }


}