package com.arabnetwork.nft.ui.fragments.main.wallet.nft

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentTradeBinding
import com.arabnetwork.nft.models.NftModel
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.adapter.TradeNftListRecAdapter
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.detail.NftDetailFragment.Companion.NFT_DETAIL_FRAGMENT_NFT_MODEL_KEY
import com.arabnetwork.nft.util.constants.StaticListConstants.Companion.WALLET_NETWORK_LIST
import com.arabnetwork.nft.util.constants.StaticListConstants.Companion.trade_nft_list
import com.arabnetwork.nft.util.constants.StringConstants.Companion.NFT_LIST_TEXT
import com.arabnetwork.nft.viewModels.NftViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TradeFragment : Fragment(), View.OnClickListener {

    companion object {
        const val TRADE_FRAGMENT_BUNDLE_COIN_SYMBOL_KEY = "coinSymbol"
    }

    /**
     * binding
     */
    private var _binding: FragmentTradeBinding? = null
    private val mBinding get() = _binding

    /**
     * adapter
     */
    private lateinit var mTradeNftListRecAdapter: TradeNftListRecAdapter

    /**
     * variables
     */
    private var mCoinSymbol: String? = ""

    /**
     * viewModel
     */
    private val mNftViewModel: NftViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requireArguments().getString(TRADE_FRAGMENT_BUNDLE_COIN_SYMBOL_KEY)?.let {
                mCoinSymbol = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTradeBinding.inflate(inflater, container, false)
        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        build()
    }

    private fun build() {
        setOnClickListener()
        setCoinSymbolTitle()

        initTradeNftListRecAdapter()
        setupTradeNftListRecView()

        //requestGetNftList()
        //observeNftList()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.toolbar_iv_back -> {
                findNavController().navigate(R.id.walletFragment)
            }
        }
    }

    private fun setOnClickListener() {
        mBinding?.toolbarTrade?.toolbarIvBack?.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setCoinSymbolTitle() {
        mBinding?.tradeTvCoinSymbolTitle?.text = "$mCoinSymbol - $NFT_LIST_TEXT"
    }

    private fun initTradeNftListRecAdapter() {
        mTradeNftListRecAdapter = TradeNftListRecAdapter().apply {
            setList(trade_nft_list)
            onItemClicked.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.nftDetailFragment, Bundle().apply {
                    putParcelable(NFT_DETAIL_FRAGMENT_NFT_MODEL_KEY,it)
                })
            }
        }
    }


    private fun setupTradeNftListRecView() {
        mBinding?.tradeNftListRec?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mTradeNftListRecAdapter
        }
    }


    private fun requestGetNftList() {
        mNftViewModel.getNftList()
    }

    private fun observeNftList() {
        mNftViewModel.nftRes.observe(viewLifecycleOwner) {
            //mTradeNftListRecAdapter.setList(it.nftResultModelList)
        }
    }
}