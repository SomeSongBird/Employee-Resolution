<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Add Employee</title>
</head>

<body>
        <h2>Add an Employee Record</h2>
        <br><br>
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

                //pull the attribute that was passed with the html form GET request and put into a lo>

                $json = file_get_contents('php://input');
                $data = json_decode($json);

                $last_name = $data->last_name;
                $first_name = $data->first_name;
                $emp_no = $data->emp_no;
                $gender = $data->gender;
                $hire_date = $data->hire_date;
                $birth_date = $data->birth_date;

                echo "Adding record for: " . $firstname . " " . $lastname;

                echo "<br><br>";

                //create the SQL insert statement, notice the funky string concat at the end to varia>
                //based on using the GET attribute
                //this statement needs to be variablized to put in the data passed from the form
                //right now it is hardcoded.
                $sql = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hir>
                //run the query and check for errors
                if ($conn->query($sql) === TRUE){<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Add Employee</title>
</head>

<body>
        <h2>Add an Employee Record</h2>
        <br><br>
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

                //pull the attribute that was passed with the html form GET request and put into a lo>

                $json = file_get_contents('php://input');
                $data = json_decode($json);

                $last_name = $data->last_name;
                $first_name = $data->first_name;
                $emp_no = $data->emp_no;
                $gender = $data->gender;
                $hire_date = $data->hire_date;
                $birth_date = $data->birth_date;

                echo "Adding record for: " . $firstname . " " . $lastname;

                echo "<br><br>";

                //create the SQL insert statement, notice the funky string concat at the end to varia>
                //based on using the GET attribute
                //this statement needs to be variablized to put in the data passed from the form
                //right now it is hardcoded.
                $sql = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hir>
                //run the query and check for errors
                if ($conn->query($sql) === TRUE){

                    echo "New Employee Created Successfully";

            } else {

                    echo "Error: " . $sql . "<br>" . $conn->error;

            }

            //always close the DB connections, don't leave 'em hanging
            $conn->close();

?>
<br><br>
<a href="index.html" title="Home" target="_parent">Home Page</a>
</body>
</html>