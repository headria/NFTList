package com.arabnetwork.nft.ui.fragments.main.wallet.nft.transfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentTransferFeeBinding
import com.arabnetwork.nft.models.ConfirmModel
import com.arabnetwork.nft.models.nft.NftResultModel
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.confirm.ConfirmFragment.Companion.CONFIRM_FRAGMENT_CONFIRM_MODEL_KEY
import com.arabnetwork.nft.util.fragments.BaseDialogFragment

class TransferFeeFragment : BaseDialogFragment(), View.OnClickListener {

    companion object {
        const val TRANSFER_FEE_FRAGMENT_NFT_RESULT_MODEL_KEY = "nftResultModel"
        const val TRANSFER_FEE_FRAGMENT_NFT_TOKEN_ADDRESS_KEY = "nftTokenAddress"

        private const val TAG = "TransferFeeFragment"
    }

    /**
     * binding
     */
    private var _binding: FragmentTransferFeeBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private lateinit var mNftResultModel: NftResultModel
    private var mPasteTokenAddressValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requireArguments().getParcelable<NftResultModel>(
                TRANSFER_FEE_FRAGMENT_NFT_RESULT_MODEL_KEY
            )?.let {
                mNftResultModel = it
            }
            requireArguments().getString(TRANSFER_FEE_FRAGMENT_NFT_TOKEN_ADDRESS_KEY)?.let {
                Log.d(TAG, "onCreate: mPasteTokenAddressValue $it")
                mPasteTokenAddressValue = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate<FragmentTransferFeeBinding?>(
            inflater,
            R.layout.fragment_transfer_fee,
            container,
            false
        ).apply {
            this.lifecycleOwner = this@TransferFeeFragment
            this.nftResultModel = mNftResultModel
        }

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
        mBinding?.transferFeeToolbar?.toolbarIvBack?.setOnClickListener(this)
        mBinding?.transferFeeBtnSend?.setOnClickListener(this)
        mBinding?.transferFeeCnsPaste?.setOnClickListener(this)
        mBinding?.transferFeeTvPasteTxt?.setOnClickListener(this)
        mBinding?.transferFeeIvPasteIcon?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                dismiss()
            }
            R.id.transfer_fee_cns_paste -> {
                handlePasteAction()
            }
            R.id.transfer_fee_tv_paste_txt -> {
                handlePasteAction()
            }
            R.id.transfer_fee_iv_paste_icon -> {
                handlePasteAction()
            }
            R.id.transfer_fee_btn_send -> {
                findNavController().navigate(R.id.confirmFragment, Bundle().apply {
                    putParcelable(CONFIRM_FRAGMENT_CONFIRM_MODEL_KEY, ConfirmModel().apply {
                        confirmFrom = "0845rftjhdytrnpsjfyrtbcge3218lprn"
                        confirmTo = "0845rftjhdytrnpsjfyrtbcge3218lprn"
                        confirmNetworkFee = "0845rftjhdytrnpsjfyrtbcge3218lprn"
                    })
                })
            }
        }
    }


    private fun handlePasteAction() {
        mPasteTokenAddressValue?.let {
            mBinding?.transferFeeTvPasteTxt?.visibility = GONE
            mBinding?.transferFeeIvPasteIcon?.visibility = GONE
            mBinding?.transferFeeEdtAddressInput?.setText(it)
        } ?: run {
            Toast.makeText(context, "No Value", Toast.LENGTH_SHORT).show()
        }
    }


}