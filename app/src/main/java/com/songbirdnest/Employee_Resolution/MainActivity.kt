package com.songbirdnest.Employee_Resolution

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest


class MainActivity : AppCompatActivity() {

    // set up so that data in the app's fields can be used here
    // the employee number to search on
    private lateinit var empToRetrieve: EditText
    // the employee data to be returned and displayed
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var hireDate: TextView
    private lateinit var errorLine:TextView
    private  lateinit var table: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the data from the User Interface by element ID
        empToRetrieve = findViewById(R.id.empToRetrieve)
        // here is the var used for the returned query that will display
        // to the UI
        firstName = findViewById(R.id.firstNameData)
        lastName = findViewById(R.id.lastNameData)
        hireDate = findViewById(R.id.hireDateData)
        errorLine = findViewById(R.id.Error)
        table = findViewById(R.id.table)
    }

    // function to retrieve the data based on employee ID
    // this is tied to the "retrieveData" button
    fun  retrieveData(view: View){
        table.isVisible = false
        errorLine.isVisible = false
        // local function var that contains the employee to retrieve
        val employeeId = empToRetrieve.text.toString()

        // The next three variable settings are for setting up the HTTP request
        // to the PHP app server to invoke the API and retrieve the data
        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

        // need to set up a request queue to hold the data that is being retrieved
        // asynchronously
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }

        // you will need to change the IP address to whatever your LAMP server's IP is
        // uncomment the url you'd like to use
        // this url returns plain text
        val url = "http://192.168.56.10/search_emp_no.php?emp_no=" + employeeId
        // this url returns json data
        //val url = "http://172.16.141.133/search_emp_no_json.php?emp_no=" + employeeId

        // Formulate the request and handle the response.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val splitResponse = response.split(' ')
                val firstN = splitResponse[2]
                val lastN = splitResponse[5]
                val hireD = splitResponse[8]
                firstName.text = firstN// Do something with the response
                lastName.text = lastN
                hireDate.text = hireD
                table.isVisible = true
            },
            { error ->
                // Handle error
                errorLine.text = "ERROR: %s".format(error.toString())
                errorLine.isVisible=true
            })

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest)

    }
}