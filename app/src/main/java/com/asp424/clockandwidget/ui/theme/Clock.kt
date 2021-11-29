package com.asp424.clockandwidget.ui.theme

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.asp424.clockandwidget.*
var g: Int = 1000000
@ExperimentalAnimationApi
@Composable
fun Clock(viewModel: ClockViewModel, activity: Activity) {
    val d: Int? by viewModel.y.observeAsState()
    var icon by rememberSaveable { mutableStateOf(R.drawable.outline_music_note_24) }
    viewModel.ass()
    Fly(mainViewModel = viewModel, activity = activity)
    Column(modifier = Modifier.fillMaxSize().drawBehindClock(d!!, activity)
            .padding(end = 40.dp, top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) { Icon(painter = painterResource(id = icon), contentDescription = null,
            Modifier.clickable {
                icon = if (icon == R.drawable.outline_music_off_24) R.drawable.outline_music_note_24
                else R.drawable.outline_music_off_24
                viewModel.playOff.value = !viewModel.playOff.value!!
            })} }

@Composable
fun Fly(mainViewModel: ClockViewModel, activity: Activity) {
    val long: Int? by mainViewModel.long.observeAsState()
    if (g != 1000000) {
        DickDraw(activity = activity, k = 5, r = long!!, ia = g)
        DickDraw(activity = activity, k = -5, r = long!!, ia = g)
        var dickLong = 10
        while (dickLong != 140) {
            dickLong += 10
            DickDraw(activity = activity, k = 0, r = long!! + dickLong, ia = g)
        } } }
