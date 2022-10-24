package com.songbirdnest.employee_resolution

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SelectActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_action)
    }

    fun loadNewActivity(view: View){
        val activity:Activity = when (view.id){
            R.id.search_button -> SearchActivity()
            R.id.update_button -> UpdateActivity()
            R.id.create_button -> CreateActivity()
            R.id.delete_button -> DeleteActivity()
            R.id.about_button -> AboutActivity()
            else -> SelectActionActivity()
        }
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

}