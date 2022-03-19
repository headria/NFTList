package com.arabnetwork.nft.ui.fragments.main.wallet

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.FragmentWalletBinding
import com.arabnetwork.nft.ui.fragments.main.wallet.adapter.WalletNetworkListRecAdapter
import com.arabnetwork.nft.ui.fragments.main.wallet.nft.NftFragment.Companion.NFT_FRAGMENT_BUNDLE_COIN_SYMBOL_KEY
import com.arabnetwork.nft.util.StringUtil
import com.arabnetwork.nft.util.constants.StaticListConstants.Companion.WALLET_NETWORK_LIST

class WalletFragment : Fragment() {

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

        _binding = DataBindingUtil.inflate<FragmentWalletBinding?>(
            inflater,
            R.layout.fragment_wallet,
            container,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
            StringUtil.currentFragment = this@WalletFragment
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        build()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
    }

    private fun build() {
        initWalletNetworkListRecAdapter()
        setupWalletNetworkListRecView()
        makeSearchTextWatcher()
        setDebounceOnSearch()
        requestLocalSearchOnNetworkList(StringUtil.debouncedString)
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

    private fun makeSearchTextWatcher() {
        mBinding?.toolbarWallet?.searchInputEdt?.addTextChangedListener(StringUtil.createTextWatcher())
    }

    @SuppressLint("CheckResult")
    private fun setDebounceOnSearch() {
        StringUtil.createDebounce()
    }

    private fun requestLocalSearchOnNetworkList(searchChar: String) {
        mWalletNetworkListRecAdapter.searchOnNetworkList(searchChar = searchChar)
    }
}