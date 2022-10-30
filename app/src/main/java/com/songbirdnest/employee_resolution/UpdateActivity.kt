package com.songbirdnest.employee_resolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView

class UpdateActivity : AppCompatActivity() {

    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var hireDate: TextView
    private lateinit var errorLine: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
    }
}