package com.arabnetwork.nft.util

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

object StringUtil {

    private lateinit var mSearchTextWatcher: TextWatcher
    lateinit var currentFragment: Fragment

    private val textChanged: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    private val onTextChanged: LiveData<String> get() = textChanged

    var debouncedString: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val getDebouncedString: LiveData<String> get() = debouncedString

    fun createTextWatcher(): TextWatcher {
        mSearchTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textChanged.value = str.toString()
                createDebounce()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }

        return mSearchTextWatcher
    }

    @SuppressLint("CheckResult")
    fun createDebounce() {
        val textDebounce: Observable<String> = Observable
            .create(ObservableOnSubscribe<String> { emitter ->
                onTextChanged.observe(currentFragment.viewLifecycleOwner) {
                    if (!emitter.isDisposed) {
                        emitter.onNext(it)
                    }
                }
            })
            .debounce(600, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())


        textDebounce.subscribe {
            debouncedString.value = it
        }
    }

}