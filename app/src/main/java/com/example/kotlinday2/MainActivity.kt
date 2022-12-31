package com.example.kotlinday2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {
    var pNum = 1 //참가자 수
    var k = 1 //참가자 번호
    val pointList = mutableListOf<Double>()

    fun startPage(){
        setContentView(R.layout.activity_start)
        val tvPnum = findViewById<TextView>(R.id.tvPnum)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val startBtn = findViewById<Button>(R.id.startBtn)
        tvPnum.text = pNum.toString() //인원수 초기화
        btnMinus.setOnClickListener {
            pNum -=1
            if(pNum == -1){
                pNum = 0
            }
            tvPnum.text = pNum.toString()
        }
        btnPlus.setOnClickListener {
             pNum += 1
            tvPnum.text = pNum.toString()
        }
        startBtn.setOnClickListener {
            main()
        }
    }

    fun main() {
        setContentView(R.layout.activity_main)
        var timerTask: Timer? = null
        val timeText = findViewById<TextView>(R.id.tv_random)
        val tvTimer = findViewById<TextView>(R.id.tv_timer)
        val stopButton = findViewById<Button>(R.id.startBtn)
        val textPoint = findViewById<TextView>(R.id.tv_point)
        val textPeople = findViewById<TextView>(R.id.tvPeople)
        var second: Int = 0
        var stage = 1
        var num: Int = 0
        fun getRandom() {
            val random = Random()
            num = random.nextInt(1001)
            timeText.text = ((num.toFloat()) / 100).toString()
            stopButton.text = "시작"
            textPeople.text = "참가자$k"
        }
        getRandom()


        stopButton.setOnClickListener {
            stage ++

            if (stage == 2) {
                timerTask = timer(period = 10) {
                    second++
                    runOnUiThread {
                        tvTimer.text = (second.toFloat() / 100).toString()
                    }
                }
                stopButton.text = "정지"
            }else if(stage == 3){
                timerTask!!.cancel()
                val point = abs(second - num).toDouble() / 100
                pointList.add(point)
                textPoint.text = "점수 :" + point.toString()
                stopButton.text = "다음"
                stage = 0
            } else if(stage == 1){
                if(k <= pNum){
                    k++
                    main()
                }else{
                    println(pointList)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startPage()
        //main()

    }
}