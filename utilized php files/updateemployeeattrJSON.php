{ response->
                updateStatus.text = getString(R.string.updateComplete)
            }<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Find Employee</title>
</head>

<body>
        <h2>Employee Updated</h2>
        <hr>
<?php

                //access credentials fils
                include 'credentials.php';

                //this is the php object oriented style of creating a mysql connection
                $conn = new mysqli($servername, $username, $password, $dbname);

                //check for connection success
                if ($conn->connect_error) {
                        die("MySQL Connection Failed: " . $conn->connect_error);
                }
                echo "MySQL Connection Succeeded<br><br>";

                $json = file_get_contents('php://input');
                $data = json_decode($json);

                $emp_no = $data->emp_no;
                $first_name = $data->first_name; // "birth_date"
                $last_name = $data->last_name;   // "1979-05-12"
                $hire_date = $data->hire_date;

                //echo $emp_attr;
                //echo $new_val;
                //echo $emp_no;

                echo "Updating: " . $emp_attr . " to " . $new_val;

                echo "<br><br>";

                //create the SQL select statement, notice the funky string concat at the end to varia>
                //based on using the GET attribute
                $sql = "UPDATE employees SET first_name='" .$first_name. "',last_name='" .$last_name.>

                //run the update
                if ($conn->query($sql) === TRUE){

                    echo "Update Completed";

            } else {

                    echo "Error: " . $sql . "<br>" . $conn->error;

            }

            //always close the DB connections, don't leave open
            $conn->close();

?>
<br><br>
<a href="index.html" title="Home" target="_parent">Home Page</a>
</body>
</html>