package com.songbirdnest.employee_resolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.core.view.GestureDetectorCompat

class TitleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP){
            loadActionSelection()
        }
        return super.onTouchEvent(event)
    }
    fun loadActionSelection(){
        val intent = Intent(this,SelectActionActivity::class.java)
        startActivity(intent)
    }
}