package com.arabnetwork.nft.ui.fragments.main.wallet.nft.transactionFeeSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentTransactionFeeBinding
import com.arabnetwork.nft.databinding.FragmentTransactionFeeSettingBinding
import com.arabnetwork.nft.models.TransactionFeeModel
import com.arabnetwork.nft.utils.fragments.BaseDialogFragment

class TransactionFeeSettingFragment : BaseDialogFragment(), View.OnClickListener {

    companion object {
        const val TRANSACTION_FEE_FRAGMENT_TRANSACTION_FEE_MODEL_KEY = ""
    }

    /**
     * binding
     */
    private var _binding: FragmentTransactionFeeSettingBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private var mTransFeeModel: TransactionFeeModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTransactionFeeSettingBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        build()
    }

    private fun build() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding?.toolbarTransactionFee?.toolbarIvBack?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                dismiss()
            }
        }
    }
}