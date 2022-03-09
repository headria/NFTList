package com.arabnetwork.nft.ui.fragments.main.wallet.nft

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentTradeBinding
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.adapter.TradeNftListRecAdapter
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.detail.NftDetailFragment.Companion.NFT_DETAIL_FRAGMENT_NFT_MODEL_KEY
import com.arabnetwork.nft.utils.StaticListConstants.Companion.trade_nft_list
import com.arabnetwork.nft.utils.StringConstants.Companion.NFT_LIST_TEXT

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


}