package com.arabnetwork.nft.ui.fragments.main.wallet.nft.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentNftBinding
import com.arabnetwork.nft.databinding.FragmentNftDetailBinding
import com.arabnetwork.nft.models.NftModel
import com.arabnetwork.nft.models.nft.NftResultModel
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.transfer.TransferFeeFragment.Companion.TRANSFER_FEE_FRAGMENT_NFT_MODEL_KEY
import com.arabnetwork.nft.util.ShareUtil
import com.arabnetwork.nft.util.fragments.BaseDialogFragment
import dagger.hilt.android.qualifiers.ApplicationContext

class NftDetailFragment : BaseDialogFragment(), View.OnClickListener {

    companion object {
        const val NFT_DETAIL_FRAGMENT_NFT_RESULT_MODEL_KEY = "nftResultModel"
    }

    /**
     * binding
     */
    private var _binding: FragmentNftDetailBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private lateinit var mNftResultModel: NftResultModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requireArguments().getParcelable<NftResultModel>(NFT_DETAIL_FRAGMENT_NFT_RESULT_MODEL_KEY)
                ?.let {
                    mNftResultModel = it
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate<FragmentNftDetailBinding?>(
            inflater,
            R.layout.fragment_nft_detail,
            container,
            false
        ).apply {
            this.lifecycleOwner = this@NftDetailFragment
            this.nftResultModel = mNftResultModel
            ShareUtil.fragment = this@NftDetailFragment
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
        mBinding?.nftDetailToolbar?.toolbarIvBack?.setOnClickListener(this)
        mBinding?.nftDetailBtnSend?.setOnClickListener(this)
        mBinding?.nftDetailIvNftShare?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                dismiss()
            }
            R.id.nft_detail_btn_send -> {
                findNavController().navigate(R.id.transferFeeFragment, Bundle().apply {
                    putParcelable(TRANSFER_FEE_FRAGMENT_NFT_MODEL_KEY, mNftResultModel)
                })
            }
            R.id.nft_detail_iv_nft_share -> {
                ShareUtil.shareTokenAddress(mNftResultModel.tokenAddress)
            }
        }
    }

}