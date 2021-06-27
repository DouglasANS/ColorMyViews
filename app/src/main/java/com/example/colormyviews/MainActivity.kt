package com.example.colormyviews

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.colormyviews.databinding.ActivityMainBinding
import java.util.jar.Manifest

open class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    open val sharedPreferences : SharedPreferences
        get() {
            return this.getSharedPreferences("colors", Context.MODE_PRIVATE)
        }

    var boxOneColor = 0
    var boxTwoColor = 0
    var boxThreeColor = 0
    var boxFourColor = 0
    var boxFiveColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linear = binding.viewid
        val button = binding.floatingActionButton


        fun sharedImage(bitmap: Bitmap){
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "image/png"

            val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "ColorMyView Image", null)

            val uri = Uri.parse(path)

            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, This is test Sharing")
            startActivity(Intent.createChooser(shareIntent, "Send your image"))

        }

        fun checkSharedPermission(bitmap: Bitmap){
            val SOLICITAR_PERMISSAO = 1
            val permissionCheck =
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    SOLICITAR_PERMISSAO
                )
            } else {
                sharedImage(bitmap)
            }
        }
        button.setOnClickListener {
            val bitconvert = screenShot(linear)
            bitconvert.let { it -> checkSharedPermission(it) }
        }

        val boxOne = binding.boxOneText
        val boxTwo  = binding.boxTwoText
        val boxThree= binding.boxThreeText
        val boxfour = binding.boxFourText
        val boxfive = binding.boxFiveText

        boxOneColor   = sharedPreferences.getInt("boxOne", R.color.grey)
        boxTwoColor   = sharedPreferences.getInt("boxTwo", R.color.grey)
        boxThreeColor = sharedPreferences.getInt("boxThree", R.color.grey)
        boxFourColor  = sharedPreferences.getInt("boxFour", R.color.grey)
        boxFiveColor  = sharedPreferences.getInt("boxFive", R.color.grey)

        boxOne.setBackgroundResource(boxOneColor)
        boxTwo.setBackgroundResource(boxTwoColor)
        boxThree.setBackgroundResource(boxThreeColor)
        boxfour.setBackgroundResource(boxFourColor)
        boxfive.setBackgroundResource(boxFiveColor)

        var changeColor = R.color.grey

        var redButton = binding.button
        var yellowButton = binding.button2
        var greenButton = binding.button3

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
