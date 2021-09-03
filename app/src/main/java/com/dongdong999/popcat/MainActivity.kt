package com.dongdong999.popcat

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.dongdong999.popcat.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var sound : SoundPool
    private lateinit var binding : ActivityMainBinding
    private var soundID by Delegates.notNull<Int>()


    var score : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load()
        sound=SoundPool.Builder().build()
        soundID=sound.load(this,R.raw.popsound,1)
        clickedCat()

    }

    fun playmusic(){


        sound.play(soundID,1.0f,1.0f,0,0,1.0f)

    }


    @SuppressLint("ClickableViewAccessibility")
    fun clickedCat(){
        binding.ivMainImageView.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action==MotionEvent.ACTION_DOWN){
                binding.ivMainImageView.isVisible=false
                binding.ivMainImageView2.isVisible=true
                score++
                playmusic()
                binding.tvScore.text=score.toString()


                Log.d("TAG","Score 오르는중 ${score}")

                return@setOnTouchListener true

            }else if(motionEvent.action==MotionEvent.ACTION_UP){
                binding.ivMainImageView.isVisible=true
                binding.ivMainImageView2.isVisible=false
                return@setOnTouchListener true
            }

            return@setOnTouchListener false
        }
    }

    fun load() {

        Glide.with(this).load(R.drawable.catimage).into(binding.ivMainImageView);
        Glide.with(this).load(R.drawable.popcatimage).into(binding.ivMainImageView2);
        val red = ContextCompat.getColor(this,R.color.start)
        val yellow = ContextCompat.getColor(this,R.color.end)
        val text = "POP CAT"
        val spannable = text.toSpannable()
        spannable[0..text.length] = LinearGradientSpan(text, text, red, yellow)
        binding.tvTitle.text = spannable
        binding.tvScore.text=score.toString()
    }




}