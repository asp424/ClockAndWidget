package com.asp424.clockandwidget

import android.app.Activity
import android.graphics.Point
import android.net.Uri
import android.provider.OpenableColumns
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

fun t(value: String): Int {
    return SimpleDateFormat(value, Locale.getDefault()).format(Date()).toInt()
}

@Composable
fun Tt(m: String, s: String) {
    Column(Modifier.background(Green)) {
        Text(
            text =
            t("HH").toString() + ":" + m + t("mm").toString() + ":" + s + t("ss").toString(),
            Modifier
                .padding(6.dp), style = TextStyle(fontSize = 12.sp), color = Black
        )
    }

}

fun l(r: Int = 200, d: Int = 0, activity: Activity, c: Int = 1): Offset {
    val size = Point()
    activity.windowManager.defaultDisplay.getSize(size)
    val x = size.x.toFloat() / 2
    val y = size.y.toFloat() / 2
    return when (c) {
        0 -> Offset(
            r * cos(d * 0.0174444444).toFloat(),
            r * sin(d * 0.0174444444).toFloat()
        )
        1 -> Offset(
            x + r * cos(d * 0.0174444444).toFloat(),
            y + r * sin(d * 0.0174444444).toFloat()
        )
        2 -> Offset(x, y)
        else -> Offset(0f, 0f)
    }
}

fun Modifier.drawBehindClock(
    d: Int,
    activity: Activity

) = this.then(
    Cl(
        onDraw = {
            drawLine(
                color = it,
                start = l(activity = activity, c = 2),
                end =
                l(r = 170, d = d, activity = activity),
                strokeWidth = 2.dp.toPx()
            )
            drawLine(
                color = it,
                start = l(c = 2, activity = activity),
                end = l(activity = activity, r = 130,
                    d = t("hh") * 30 + t("mm")/2 - 90),
                strokeWidth = 6.dp.toPx()
            )
            drawLine(
                color = it,
                start = l(activity = activity, c = 2),
                end = l(activity = activity, d = t("mm") * 6 - 90)
                , strokeWidth = 4.dp.toPx()
            )
            repeat(12) {dot ->
                drawCircle(
                    color = it,
                    center = l(activity = activity, r = 230, d = 30 * dot)
                    , radius = 10f
                )

            }
        }
    )
)

private class Cl(
    val onDraw: DrawScope.(color: Color) -> Unit
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        onDraw(Blue)
        drawContent()
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cl) return false

        return onDraw == other.onDraw
    }
    override fun hashCode(): Int {
        return onDraw.hashCode()
    }
}
@Composable
fun DickDraw(activity: Activity, r: Int, k: Int, ia: Int, c: Int = 1){
    Canvas(modifier = Modifier , onDraw = {
        drawCircle(
            color = Blue,
            center =
            l(c = 1, activity = activity, r = r, d = k + 6 * ia - 90)
            , radius =  30f
        )
    })
}


