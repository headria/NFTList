package com.arabnetwork.nft.ui.fragments.main.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentWalletBinding
import com.arabnetwork.nft.ui.fragments.main.wallet.adapter.WalletNetworkListRecAdapter
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.NftFragment.Companion.NFT_FRAGMENT_BUNDLE_COIN_SYMBOL_KEY
import com.arabnetwork.nft.util.constants.StaticListConstants.Companion.WALLET_NETWORK_LIST

class WalletFragment : Fragment() {

    companion object {

    }

    /**
     * binding
     */
    private var _binding: FragmentWalletBinding? = null
    private val mBinding get() = _binding

    /**
     * adapters
     */
    private lateinit var mWalletNetworkListRecAdapter: WalletNetworkListRecAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        build()
    }

    private fun build() {
        initWalletNetworkListRecAdapter()
        setupWalletNetworkListRecView()
    }

    private fun initWalletNetworkListRecAdapter() {
        mWalletNetworkListRecAdapter = WalletNetworkListRecAdapter().apply {
            setList(WALLET_NETWORK_LIST)

            onItemClicked.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.nftFragment, Bundle().apply {
                    putParcelable(NFT_FRAGMENT_BUNDLE_COIN_SYMBOL_KEY,it)
                })
            }
        }
    }


    private fun setupWalletNetworkListRecView() {
        mBinding?.walletRecSymbolList?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mWalletNetworkListRecAdapter
        }
    }
}