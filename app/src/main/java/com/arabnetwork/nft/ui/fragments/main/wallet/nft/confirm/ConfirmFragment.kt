package com.arabnetwork.nft.ui.fragments.main.wallet.nft.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentConfirmBinding
import com.arabnetwork.nft.models.ConfirmModel
import com.arabnetwork.nft.models.TransactionFeeModel
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.transactionFee.TransactionFeeFragment.Companion.TRANSACTION_FEE_FRAGMENT_TRANSACTION_FEE_MODEL_KEY
import com.arabnetwork.nft.util.fragments.BaseDialogFragment

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.confirm_btn -> {

            }
            R.id.toolbar_iv_back -> {
                dismiss()
            }
            R.id.toolbar_iv_confirm_setting -> {
                findNavController().navigate(R.id.transactionFeeFragment, Bundle().apply {
                    putParcelable(
                        TRANSACTION_FEE_FRAGMENT_TRANSACTION_FEE_MODEL_KEY,
                        TransactionFeeModel().apply {
                            transFeeTotalFee = "7.13385"
                            transFeeCurrentGWEI = "0.0024"
                            transFeeCoinSymbol = "BUSD"
                            transFeeSlowGWEI = "90 GWEI"
                            transFeeSlowMin = "3 Min"
                            transFeeMediumGWEI = "110 GWEI"
                            transFeeMediumMin = "2 Min"
                            transFeeFastGWEI = "120 GWEI"
                            transFeeFastMin = "1 Min"
                        })
                })
            }

        }
    }

    private fun build() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding?.toolbarConfirm?.toolbarIvBack?.setOnClickListener(this)
        mBinding?.toolbarConfirm?.toolbarIvConfirmSetting?.setOnClickListener(this)
        mBinding?.confirmBtn?.setOnClickListener(this)

    }
}