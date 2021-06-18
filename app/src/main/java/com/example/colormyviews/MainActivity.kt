package com.example.colormyviews

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var boxOne : TextView
    lateinit var boxTwo : TextView
    lateinit var boxThree : TextView
    lateinit var boxfour : TextView
    lateinit var boxfive : TextView

    var boxOneColor = 0
    var boxTwoColor = 0
    var boxThreeColor = 0
    var boxFourColor = 0
    var boxFiveColor = 0

    val sharedPreferences : SharedPreferences
        get() {
            return this.getSharedPreferences("colors", Context.MODE_PRIVATE)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         boxOne  = findViewById<TextView>(R.id.box_one_text)
         boxTwo = findViewById<TextView>(R.id.box_two_text)
         boxThree= findViewById<TextView>(R.id.box_three_text)
         boxfour = findViewById<TextView>(R.id.box_four_text)
         boxfive = findViewById<TextView>(R.id.box_five_text)

        boxOneColor = sharedPreferences.getInt("boxOne", R.color.grey)
        boxTwoColor = sharedPreferences.getInt("boxTwo", R.color.grey)
        boxThreeColor = sharedPreferences.getInt("boxThree", R.color.grey)
        boxFourColor = sharedPreferences.getInt("boxFour", R.color.grey)
        boxFiveColor = sharedPreferences.getInt("boxFive", R.color.grey)

        boxOne.setBackgroundResource(boxOneColor)
        boxTwo.setBackgroundResource(boxTwoColor)
        boxThree.setBackgroundResource(boxThreeColor)
        boxfour.setBackgroundResource(boxFourColor)
        boxfive.setBackgroundResource(boxFiveColor)

        var changeColor = R.color.grey

        var redButton = findViewById<Button>(R.id.button)
        var yellowButton = findViewById<Button>(R.id.button2)
        var greenButton = findViewById<Button>(R.id.button3)

        redButton.setOnClickListener{
            changeColor = R.color.red
        }

        yellowButton.setOnClickListener{
            changeColor = R.color.yellow
        }

        greenButton.setOnClickListener{
            changeColor = R.color.green
        }

        boxOne.setOnClickListener{
            boxOne.setBackgroundResource(changeColor)
            boxOneColor = changeColor
        }
        boxTwo.setOnClickListener{
            boxTwo.setBackgroundResource(changeColor)
            boxTwoColor = changeColor
        }

        boxThree.setOnClickListener{
            boxThree.setBackgroundResource(changeColor)
            boxThreeColor = changeColor
        }
        boxfour.setOnClickListener{
            boxfour.setBackgroundResource(changeColor)
            boxFourColor = changeColor
        }

        boxfive.setOnClickListener{
            boxfive.setBackgroundResource(changeColor)
            boxFiveColor = changeColor
        }

    }

    override fun onStop() {
        super.onStop()


        val sharedPreferences = getSharedPreferences("colors", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        editor.putInt("boxOne", boxOneColor)
        editor.putInt("boxTwo", boxTwoColor)
        editor.putInt("boxThree", boxThreeColor)
        editor.putInt("boxFour", boxFourColor)
        editor.putInt("boxFive", boxFiveColor)


        editor.commit()

    }
}