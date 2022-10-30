package com.songbirdnest.employee_resolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.lang.Exception

class UpdateActivity : AppCompatActivity() {

    private lateinit var empToRetrieve: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var hireDate: EditText
    private lateinit var updateStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        empToRetrieve=findViewById(R.id.empToRetrieve)
        firstName=findViewById(R.id.edit_first)
        lastName=findViewById(R.id.edit_last)
        hireDate=findViewById(R.id.edit_hire)
        updateStatus=findViewById(R.id.updateStatus)

    }
    fun  retrieveData(view: View){

        val requestMan = RequestManager.getInstance(this)

        val url = "http://192.168.56.10/search_emp_no_json.php?emp_no=" + empToRetrieve.text.toString()

        val req = StringRequest(
            Request.Method.GET,url,
            {response ->
                try{
                    val obj = JSONObject(response);
                    firstName.setText(obj["first_name"].toString())
                    lastName.setText(obj["last_name"].toString())
                    hireDate.setText(obj["hire_date"].toString())
                    Log.i("Request", "Recieved Request is: $obj")
                } catch (e: Exception){
                    Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"")
                }
                //firstName.text = response
            },{error ->
                Log.e("responseError",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        requestMan.addToRequestQueue(req)
    }
    fun updateData(view:View){
        val requestMan = RequestManager.getInstance(this)
        val url = "http://192.168.56.10/updateemployeeattrJSON.php"

        //post the udpated data to the server

        //note: for code like this I looked it up and added my own stuff | source: https://stackoverflow.com/questions/50344562/how-to-get-string-response-from-php-using-android-volley-jsonobjectrequest
        val req = object: StringRequest(Request.Method.POST, url,
            Response.Listener { }, Response.ErrorListener { }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
            override fun getBody(): ByteArray {
                try {
                    //construction of json to send
                    val obj = JSONObject()
                    obj.put("emp_no",empToRetrieve.text.toString())
                    obj.put("first_name",firstName.text.toString())
                    obj.put("last_name",lastName.text.toString())
                    obj.put("hire_date",hireDate.text.toString())

                    return obj.toString().toByteArray(charset("utf-8"))
                } catch (e: Exception) {
                }
                return ByteArray(0) //empty ByteArray
            }
        }

        requestMan.addToRequestQueue(req)
    }

}