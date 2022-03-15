package com.arabnetwork.nft.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arabnetwork.nft.data.repository.NftRepository
import com.arabnetwork.nft.models.nft.NftResponseModel
import com.arabnetwork.nft.models.nft.NftResultModel
import com.arabnetwork.nft.util.network.ApiResponse
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

    private val _nftRes: LiveEvent<NftResponseModel> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val nftRes: LiveData<NftResponseModel> get() = _nftRes

    private fun setProgressBarVisibility(isVisible: Boolean) {
        _isProgressEnable.value = isVisible
    }

    private fun showToastErrorMsg(errorMsg: String) {
        Toast.makeText(getApplication(), errorMsg, Toast.LENGTH_SHORT).show()
    }

    fun getNftList() {
        getNftListSafeCall()
    }

    private fun getNftListSafeCall() {
        viewModelScope.launch {
            flowResponse { nftRepository.getNftList() }.collect {
                when (it) {
                    is ApiResponse.Loading -> {
                        setProgressBarVisibility(true)
                        Log.d(TAG, "getNftListSafeCall: loading")
                    }
                    is ApiResponse.Success -> {
                        setProgressBarVisibility(false)
                        Log.d(TAG, "getNftListSafeCall success: ${it.data.nftResultModelList}")
                        _nftRes.value = it.data!!
                    }
                    is ApiResponse.Failure -> {
                        setProgressBarVisibility(false)
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


}