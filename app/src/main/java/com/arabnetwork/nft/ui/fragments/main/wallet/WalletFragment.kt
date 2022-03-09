package com.arabnetwork.nft.ui.fragments.main.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.arabnetwork.nft.databinding.FragmentWalletBinding
import com.arabnetwork.nft.ui.fragments.main.wallet.adapter.WalletSymbolListRecAdapter
import com.arabnetwork.nft.utils.StaticListConstants.Companion.wallet_symbol_list

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
    private lateinit var mWalletSymbolListRecAdapter: WalletSymbolListRecAdapter

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

        setupViews()
    }

    private fun setupViews() {

        setupWalletSymbolListRecView()
    }

    private fun setupWalletSymbolListRecView() {
        mWalletSymbolListRecAdapter = WalletSymbolListRecAdapter().apply {
            setList(wallet_symbol_list)
        }
        mBinding?.walletRecSymbolList?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mWalletSymbolListRecAdapter
        }
    }
}