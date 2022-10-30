package com.songbirdnest.employee_resolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.lang.Exception

class UpdateActivity : AppCompatActivity() {

    private lateinit var empToRetrieve: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var hireDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        firstName=findViewById(R.id.edit_first)
        lastName=findViewById(R.id.edit_last)
        hireDate=findViewById(R.id.edit_hire)

    }
    fun  retrieveData(view: View){

        val requestMan = RequestManager.getInstance(this)

        val url = "http://192.168.56.10/search_emp_no_json.php?emp_no=" + empToRetrieve.text.toString()

        val req = StringRequest(
            Request.Method.GET,url,
            {response ->
                try{
                    val obj = JSONObject(response);
                    firstName.hint = obj["first_name"].toString()
                    lastName.hint = obj["last_name"].toString()
                    hireDate.hint = obj["hire_date"].toString()
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
        //create the json object to put the updated data into
        val obj = JSONObject()
        obj.put("emp_no",empToRetrieve.text.toString())
        obj.put("first_name",firstName.text.toString())
        obj.put("last_name",lastName.text.toString())
        obj.put("hire_date",hireDate.text.toString())

        val requestMan = RequestManager.getInstance(this)
        val url = "http://192.168.56.10/updateemployeeattrJSON.php"

        //post the udpated data to the server
        val req = JsonObjectRequest(Request.Method.POST,url,obj,
            {request ->
                Log.i("updateSuccess","Update success")
            },{error ->
                Log.e("updateRequest","Something went wrong updating the data")
            })

        requestMan.addToRequestQueue(req)
    }

}