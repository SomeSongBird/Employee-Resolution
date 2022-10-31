package com.songbirdnest.employee_resolution

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
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

    fun createData(view:View){
        //get current date in mm-dd-yyyy format
        val dateformat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateformat.format(Calendar.getInstance().time)

        //send the request
        val requestMan = RequestManager.getInstance(this)
        val url = "http://192.168.56.10/addemployeejson.php"

        //note: for code like this I looked it up and added my own stuff | source: https://stackoverflow.com/questions/50344562/how-to-get-string-response-from-php-using-android-volley-jsonobjectrequest
        val req = object: StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                Log.i("response",response.toString())
                status.text="Record successfully created"
            }, Response.ErrorListener { error ->
                Log.e("response",error.toString())
                status.text="Something went wrong creating the employee record"
            }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
            override fun getBody(): ByteArray {
                try {
                    //construction of json to send
                    val obj = JSONObject()
                    obj.put("emp_no",emp_no.text)
                    obj.put("first_name",first_name.text)
                    obj.put("last_name",last_name.text)
                    obj.put("gender",gender.text)
                    obj.put("birth_date", birthday.text)
                    obj.put("hire_date",date)

                    return obj.toString().toByteArray(charset("utf-8"))
                } catch (e: Exception) {
                }
                return ByteArray(0) //empty ByteArray
            }
        }

        requestMan.addToRequestQueue(req)

        //resetActivity(view)
        status.text=getString(R.string.create_success)
    }

    fun resetActivity(view: View){
        emp_no.setText("")
        first_name.setText("")
        last_name.setText("")
        gender.setText("")
        birthday.setText("")
        status.text=""
    }
}