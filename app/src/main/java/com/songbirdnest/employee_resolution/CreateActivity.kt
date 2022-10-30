package com.songbirdnest.employee_resolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class CreateActivity : AppCompatActivity() {

    private lateinit var emp_no:EditText
    private lateinit var first_name:EditText
    private lateinit var last_name:EditText
    private lateinit var gender:EditText
    private lateinit var birthday:EditText
    private lateinit var status:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        emp_no=findViewById(R.id.emp_no)
        first_name=findViewById(R.id.first)
        last_name=findViewById(R.id.last)
        gender=findViewById(R.id.sex)
        birthday=findViewById(R.id.birthday)
        status=findViewById(R.id.createStatus)
    }

    fun createData(){
        //get current date in mm-dd-yyyy format
        val dateformat = SimpleDateFormat("MM-dd-yyyy")
        val date = dateformat.format(Calendar.getInstance().time)

        //create json object to send the data with
        val obj = JSONObject()
        obj.put("emp_no",emp_no.text)
        obj.put("first_name",first_name.text)
        obj.put("last_name",last_name.text)
        obj.put("gender",gender.text)
        obj.put("birthday", birthday.text)
        obj.put("hire_date",date)

        //send the request
        val requestMan = RequestManager.getInstance(this)
        val url = "http://192.168.56.10/"

        val req = JsonObjectRequest(Request.Method.POST,url,obj,null,null)

        requestMan.addToRequestQueue(req)

        resetActivity()
        status.text=getString(R.string.create_success)
    }

    fun resetActivity(){
        first_name.setText("")
        last_name.setText("")
        gender.setText("")
        birthday.setText("")
        status.text=""
    }
}