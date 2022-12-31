package com.example.kotlinday2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.Math.abs
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var timerTask : Timer? = null
        val timeText = findViewById<TextView>(R.id.tv_random)
        val tvTimer = findViewById<TextView>(R.id.tv_timer)
        val stopButton = findViewById<Button>(R.id.button)
        val textPoint = findViewById<TextView>(R.id.tv_point)
        var second : Int = 0
        var isRunning = false
        var num : Int = 0
        fun getRandom(){
            val random = Random()
            num = random.nextInt(1001)
            timeText.text = ((num.toFloat())/100).toString()
        }
        getRandom()


        stopButton.setOnClickListener {
            isRunning = !isRunning
            if(isRunning == true){
                timerTask = timer(period = 10){
                    second ++
                    runOnUiThread{
                        tvTimer.text = (second.toFloat()/100).toString()
                    }
                }
            }else{
                timerTask!!.cancel()
                val point = abs(second - num).toDouble()/100
                textPoint.text = "점수 :" + point.toString()
                println(point)
            }
        }

    }
}