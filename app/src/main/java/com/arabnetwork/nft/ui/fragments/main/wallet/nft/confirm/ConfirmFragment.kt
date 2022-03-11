package com.arabnetwork.nft.ui.fragments.main.wallet.nft.confirm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentConfirmBinding
import com.arabnetwork.nft.models.ConfirmModel
import com.arabnetwork.nft.utils.fragments.BaseDialogFragment

class ConfirmFragment : BaseDialogFragment(), View.OnClickListener{

    companion object {
        const val CONFIRM_FRAGMENT_CONFIRM_MODEL_KEY = "confirmModel"
    }

    /**
     * binding
     */
    private var _binding : FragmentConfirmBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private var mConfirmModel : ConfirmModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requireArguments().getParcelable<ConfirmModel>(CONFIRM_FRAGMENT_CONFIRM_MODEL_KEY)?.let {
                mConfirmModel = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConfirmBinding.inflate(inflater,container,false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.confirmModel = mConfirmModel

        build()
    }

    private fun build() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding?.toolbarConfirm?.toolbarIvBack?.setOnClickListener(this)
        mBinding?.confirmBtn?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.confirm_btn -> {

            }
            R.id.toolbar_iv_back -> {
                dismiss()
            }
        }
    }
}