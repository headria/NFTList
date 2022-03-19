package com.arabnetwork.nft.ui.fragments.main.wallet.nft.transactionFee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentTransactionFeeBinding
import com.arabnetwork.nft.models.TransactionFeeModel
import com.arabnetwork.nft.util.fragments.BaseDialogFragment

class TransactionFeeFragment : BaseDialogFragment(), View.OnClickListener {

    companion object {
        const val TRANSACTION_FEE_FRAGMENT_TRANSACTION_FEE_MODEL_KEY = "transactionFeeModel"
    }

    /**
     * binding
     */
    private var _binding: FragmentTransactionFeeBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private var mTransFeeModel: TransactionFeeModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requireArguments().getParcelable<TransactionFeeModel>(
                TRANSACTION_FEE_FRAGMENT_TRANSACTION_FEE_MODEL_KEY
            )?.let {
                mTransFeeModel = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTransactionFeeBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.transFeeModel = mTransFeeModel

        build()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                dismiss()
            }
            R.id.toolbar_iv_setting -> {
                findNavController().navigate(R.id.transactionFeeSettingFragment)
            }
        }
    }

    private fun build() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding?.toolbarTransactionFee?.toolbarIvBack?.setOnClickListener(this)
        mBinding?.toolbarTransactionFee?.toolbarIvSetting?.setOnClickListener(this)
    }

}