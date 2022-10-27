package com.songbirdnest.employee_resolution

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject


class SearchActivity : AppCompatActivity() {

    // set up so that data in the app's fields can be used here
    // the employee number to search on
    private lateinit var empToRetrieve: EditText
    // the employee data to be returned and displayed
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var birthDate: TextView
    private lateinit var hireDate: TextView
    private lateinit var errorLine:TextView
    private  lateinit var table: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // get the data from the User Interface by element ID
        empToRetrieve = findViewById(R.id.empToRetrieve)
        // here is the var used for the returned query that will display
        // to the UI
        firstName = findViewById(R.id.firstNameData)
        lastName = findViewById(R.id.lastNameData)
        birthDate = findViewById(R.id.birthData)
        hireDate = findViewById(R.id.hireDateData)
        errorLine = findViewById(R.id.Error)
        table = findViewById(R.id.table)
    }

    // function to retrieve the data based on employee ID
    // this is tied to the "retrieveData" button
    fun  retrieveData(view: View){
        table.isVisible = false
        errorLine.isVisible = false

        val requestMan = RequestManager.getInstance(this)

        val url = "http://192.168.56.10/search_emp_no_json.php?emp_no=" + empToRetrieve.text.toString()

        val req = StringRequest(Request.Method.GET,url,
            {response ->
                try{
                    val obj = JSONObject(response);
                    firstName.text = obj["first_name"].toString()
                    lastName.text = obj["last_name"].toString()
                    birthDate.text = obj["birth_date"].toString()
                    hireDate.text = obj["hire_date"].toString()
                    Log.i("Request", "Recieved Request is: $obj")
                    table.isVisible = true
                    errorLine.isVisible = false
                } catch (e:java.lang.Exception){
                    table.isVisible = false
                    errorLine.isVisible = true
                    errorLine.text = e.toString()
                    Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"")
                }
                //firstName.text = response
            },{error ->
                table.isVisible = false
                errorLine.isVisible = true
                errorLine.text = error.toString()}
        )
        // Add the request to the RequestQueue.
        requestMan.addToRequestQueue(req)
    }
}