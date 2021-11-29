package com.asp424.clockandwidget


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asp424.clockandwidget.ui.theme.Clock
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: ClockViewModel by viewModels{
            ClockViewModelFactory(this)
        }
        setContent {Clock(viewModel = vm, activity = this)}
    }
    @Composable
    fun Plus(vm: VM) {
        val number: Int? by vm.number.observeAsState()
        Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Text(text = number.toString())
            Button(onClick = {vm.add()}) {
                Text(text = "+")
            }
        }
    }
}
class VM: ViewModel(){
    private val scope = viewModelScope
    val number = MutableLiveData(0)
    fun add() = scope.launch{
        number.value = number.value!! + 1
    }
}






/*

class ExampleAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val views = RemoteViews(context.packageName, R.layout.compose)
            views.setOnClickPendingIntent(R.id.compose_view, pendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}



*/


