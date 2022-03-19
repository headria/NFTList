package com.arabnetwork.nft.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arabnetwork.nft.data.repository.NftRepository
import com.arabnetwork.nft.models.nft.NftResultModel
import com.arabnetwork.nft.util.NftUtil
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_LIST_IS_EMPTY
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_LIST_IS_NOT_EMPTY
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_TRADE_IS_NOT_AVAILABLE
import com.arabnetwork.nft.util.network.ApiResponse
import com.arabnetwork.nft.util.network.GenericResponseModel
import com.arabnetwork.nft.util.network.NetworkUtil.flowResponse
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NftViewModel @Inject constructor(
    private val nftRepository: NftRepository,
    application: Application
) : AndroidViewModel(application) {

    companion object {
        const val TAG = "NftViewModel"
    }

    private val _isProgressEnable: MutableLiveData<Boolean> = MutableLiveData()
    val isProgressEnable: LiveData<Boolean> get() = _isProgressEnable

    private val _isNftRecVisible: MutableLiveData<Boolean> = MutableLiveData()
    val isNftRecVisible: LiveData<Boolean> get() = _isNftRecVisible

    private val _isNftListEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val isNftListEmpty: LiveData<Boolean> get() = _isNftListEmpty

    private val _textViewMsg: MutableLiveData<String> = MutableLiveData()
    val textViewMsg: LiveData<String> get() = _textViewMsg

    private val _nftTradePriceFromWei: MutableLiveData<String> = MutableLiveData()
    val nftTradePriceFromWei: LiveData<String> get() = _nftTradePriceFromWei

    private val _nftRes: LiveEvent<GenericResponseModel<NftResultModel>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val nftRes: LiveData<GenericResponseModel<NftResultModel>> get() = _nftRes

    private fun showToastErrorMsg(errorMsg: String) {
        Toast.makeText(getApplication(), errorMsg, Toast.LENGTH_SHORT).show()
    }

    private fun setProgressBarVisibility(isVisible: Boolean) {
        _isProgressEnable.value = isVisible
    }

    private fun setNftRecViewVisibility(isAvailable: Boolean) {
        _isNftRecVisible.value = isAvailable
    }

    private fun setNftListEmptiness(isEmpty: Boolean) {
        _isNftListEmpty.value = isEmpty
    }

    private fun setTextViewMessage(msg: String) {
        _textViewMsg.value = msg
    }

    fun getNftList() {
        getNftListSafeCall()
    }

    fun getNftTrade(tokenAddress: String) {
        getNftTradeSafeCall(tokenAddress = tokenAddress)
    }

    private fun getNftListSafeCall() {
        viewModelScope.launch {
            flowResponse { nftRepository.getNftList() }.collect {
                when (it) {
                    is ApiResponse.Loading -> {
                        Log.d(TAG, "getNftListSafeCall: loading")
                        setProgressBarVisibility(true)
                        setNftRecViewVisibility(false)
                    }
                    is ApiResponse.Success -> {
                        Log.d(TAG, "getNftListSafeCall success: ${it.data.results}")
                        setProgressBarVisibility(false)
                        if (!it.data.results.isNullOrEmpty()) {
                            setNftRecViewVisibility(true)
                            setNftListEmptiness(false)
                            setTextViewMessage(NFT_LIST_IS_NOT_EMPTY)
                            it.data.let { nftResModel ->
                                _nftRes.value = nftResModel
                            }
                        } else {
                            setNftListEmptiness(true)
                            setTextViewMessage(NFT_LIST_IS_EMPTY)
                        }
                    }
                    is ApiResponse.Failure -> {
                        Log.d(TAG, "getNftListSafeCall: failure")
                        setProgressBarVisibility(false)
                        setNftRecViewVisibility(false)
                        showToastErrorMsg(it.error)
                    }
                    is ApiResponse.Complete -> {
                        Log.d(TAG, "getNftListSafeCall: complete")
                        setProgressBarVisibility(false)

                    }
                }
            }
        }
    }

    private fun getNftTradeSafeCall(tokenAddress: String) {
        viewModelScope.launch {
            flowResponse {
                nftRepository.getNftTrade(tokenAddress = tokenAddress)
            }.collect {
                when (it) {
                    is ApiResponse.Loading -> {
                        Log.d(TAG, "getNftTradeSafeCall: loading")
                        setProgressBarVisibility(true)
                    }
                    is ApiResponse.Success -> {
                        Log.d(TAG, "getNftTradeSafeCall: success ${it.data.results}")
                        setProgressBarVisibility(false)
                        if (!it.data.results.isNullOrEmpty()) {
                            it.data.results[0].price?.let {
                                withContext(Dispatchers.IO) {
                                    _nftTradePriceFromWei.postValue(
                                        NftUtil.convertNftTradePriceFromWei(
                                            bigDecimalNum = it
                                        )
                                    )
                                }
                            }

                        } else {
                            showToastErrorMsg(errorMsg = NFT_TRADE_IS_NOT_AVAILABLE)
                        }

                    }
                    is ApiResponse.Failure -> {
                        Log.d(TAG, "getNftTradeSafeCall: failure")
                        setProgressBarVisibility(false)
                        showToastErrorMsg(it.error)

                    }
                    is ApiResponse.Complete -> {
                        Log.d(TAG, "getNftTradeSafeCall: complete")
                        setProgressBarVisibility(false)
                    }


                }
            }
        }
    }

}