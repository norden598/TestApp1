<?php
require "conn.php";

$number = urldecode($_POST["number"]);
$first_name = urldecode($_POST["first_name"]);
$last_name = urldecode($_POST["last_name"]);
$position = urldecode($_POST["position"]);

// single quotes inside the double quotes
$sql = "INSERT INTO roster VALUES ('$number', '$first_name', '$last_name', '$position')";

if ($conn->query($sql) === TRUE)
{
	echo "New record created successfully";
}
else
{
	echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();

?>