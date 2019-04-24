<?php
$db_name = "test_db";
$mysql_username = "";
$mysql_password = "";
$server_name = "localhost";

$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

if ($conn->connect_error)
{
	die("Connection failed: " . $conn->connect_error);
}

?>