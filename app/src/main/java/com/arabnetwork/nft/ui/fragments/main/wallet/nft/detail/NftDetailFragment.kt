package com.arabnetwork.nft.ui.fragments.main.wallet.nft.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentNftDetailBinding
import com.arabnetwork.nft.models.NftModel
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.transfer.TransferFeeFragment.Companion.TRANSFER_FEE_FRAGMENT_NFT_MODEL_KEY
import com.arabnetwork.nft.util.fragments.BaseDialogFragment

class NftDetailFragment : BaseDialogFragment(), View.OnClickListener {

    companion object {
        const val NFT_DETAIL_FRAGMENT_NFT_MODEL_KEY = "nftModel"
    }

    /**
     * binding
     */
    private var _binding: FragmentNftDetailBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private var mNftModel: NftModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requireArguments().getParcelable<NftModel>(NFT_DETAIL_FRAGMENT_NFT_MODEL_KEY)?.let {
                mNftModel = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNftDetailBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.nftModel = mNftModel

        build()
    }

    private fun build() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding?.nftDetailToolbar?.toolbarIvBack?.setOnClickListener(this)
        mBinding?.nftDetailBtnSend?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                dismiss()
            }
            R.id.nft_detail_btn_send -> {
                findNavController().navigate(R.id.transferFeeFragment, Bundle().apply {
                    putParcelable(TRANSFER_FEE_FRAGMENT_NFT_MODEL_KEY, mNftModel)
                })
            }
        }
    }

}