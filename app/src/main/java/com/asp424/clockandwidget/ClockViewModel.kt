package com.asp424.clockandwidget

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asp424.clockandwidget.ui.theme.g
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ClockViewModel(context: Context) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private val con = context
    private val _y = MutableLiveData(t("ss") * 6 - 90)
    val y: LiveData<Int> = _y
     val playOff = MutableLiveData(true)
    private val _long = MutableLiveData(230)
    val long: LiveData<Int> = _long

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                _y.postValue(t("ss") * 6 - 90)
            }
        }
    }

    fun ass() {
        if (t("ss") % 5 == 0)
            viewModelScope.launch {
                if (playOff.value!!)
                playSound().start()
                g = t("ss")
                var v = 230
                while (v != 1500) {
                    v += 5
                    delay(20L)
                    _long.postValue(v)
                }
            }
    }
    private fun playSound(): MediaPlayer {
        return MediaPlayer.create(con, R.raw.a)
    }
}


