package com.arabnetwork.nft.ui.fragments.main.wallet.nft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentNftBinding
import com.arabnetwork.nft.models.network.NetworkModel
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.adapter.NftListRecAdapter
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.detail.NftDetailFragment.Companion.NFT_DETAIL_FRAGMENT_NFT_MODEL_KEY
import com.arabnetwork.nft.viewModels.NftViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NftFragment : Fragment(), View.OnClickListener {

    companion object {
        const val NFT_FRAGMENT_BUNDLE_COIN_SYMBOL_KEY = "coinSymbol"
    }

    /**
     * binding
     */
    private var _binding: FragmentNftBinding? = null
    private val mBinding get() = _binding

    /**
     * adapter
     */
    private lateinit var mNftListRecAdapter: NftListRecAdapter

    /**
     * variables
     */
    private lateinit var mNetworkModel: NetworkModel

    /**
     * viewModel
     */
    private val mNftViewModel: NftViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requireArguments().getParcelable<NetworkModel>(NFT_FRAGMENT_BUNDLE_COIN_SYMBOL_KEY)
                ?.let {
                    mNetworkModel = it
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate<FragmentNftBinding?>(
            inflater,
            R.layout.fragment_nft,
            container,
            false
        ).apply {
            this.lifecycleOwner = this@NftFragment
            this.networkModel = mNetworkModel
            this.nftViewModel = mNftViewModel
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        build()
    }

    private fun build() {
        setOnClickListener()

        initNftListRecAdapter()
        setupNftListRecView()

        requestGetNftList()
        observeNftList()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                findNavController().navigate(R.id.walletFragment)
            }
        }
    }

    private fun setOnClickListener() {
        mBinding?.toolbarNft?.toolbarIvBack?.setOnClickListener(this)
    }

    private fun initNftListRecAdapter() {
        mNftListRecAdapter = NftListRecAdapter().apply {
            onItemClicked.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.nftDetailFragment, Bundle().apply {
                    putParcelable(NFT_DETAIL_FRAGMENT_NFT_MODEL_KEY, it)
                })
            }
        }
    }


    private fun setupNftListRecView() {
        mBinding?.nftRec?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mNftListRecAdapter
        }
    }


    private fun requestGetNftList() {
        mNftViewModel.getNftList()
    }

    private fun observeNftList() {
        mNftViewModel.nftRes.observe(viewLifecycleOwner) {
            mNftListRecAdapter.setList(it.nftResultModelList)
        }
    }
}