package com.songbirdnest.employee_resolution


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.lang.Exception


class DeleteActivity : AppCompatActivity() {

    private lateinit var emp_no: EditText
    private lateinit var confirmationInfo: GridLayout
    private lateinit var first_name: TextView
    private lateinit var last_name: TextView
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        emp_no=findViewById(R.id.empToDelete)
        confirmationInfo=findViewById(R.id.confirmation_info)
        first_name=findViewById(R.id.first)
        last_name=findViewById(R.id.last)
    }

    fun confirm(view: View){
        // makes the confirmation grid visible
        val requestMan = RequestManager.getInstance(this)
        val url = "http://192.168.56.10/search_emp_no_json.php?emp_no=" + emp_no.text.toString()
        val req = StringRequest(
            Request.Method.GET,url,
            {response ->
                try{
                    val obj = JSONObject(response);
                    first_name.text = obj["first_name"].toString()
                    last_name.text = obj["last_name"].toString()
                    confirmationInfo.isVisible=true
                    Log.i("Request", "Recieved Request is: $obj")
                } catch (e: Exception){
                    Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"")
                }
            },null
        )
        // Add the request to the RequestQueue.
        requestMan.addToRequestQueue(req)
    }

    fun deleteData(view: View){
        val requestMan = RequestManager.getInstance(this)
        val url="http://192.168.56.10/deleteemployee.php?emp_no="+emp_no.text.toString()
        val req = StringRequest(Request.Method.GET,url,null,null)
        requestMan.addToRequestQueue(req)
        resetActivity(view)
        status.text=getString(R.string.delete_success)
        status.isVisible=true
    }
    fun resetActivity(view:View){
        emp_no.setText("")
        confirmationInfo.isVisible=false
        first_name.text=""
        last_name.text=""
        status.isVisible=false
    }
}