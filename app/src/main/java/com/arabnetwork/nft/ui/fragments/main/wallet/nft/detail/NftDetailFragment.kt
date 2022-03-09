package com.arabnetwork.nft.ui.fragments.main.wallet.nft.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentNftDetailBinding
import com.arabnetwork.nft.models.NftModel
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.TradeFragment
import com.arabnetwork.nft.utils.fragments.BaseDialogFragment

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
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                dismiss()
            }
        }
    }

}