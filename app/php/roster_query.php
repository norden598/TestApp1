<?php
require "conn.php"

$sql = "SELECT number, first_name, last_name FROM roster";
$result = $conn->query($sql);

if ($result->num_rows > 0) 
{
	// output data of each row
	while ($row = $result->fetch_assoc())
	{
		echo "number: " . $row["number"]. " - Name: " . $row["first_name"]. " " . $row["last_name"]. "<br>";		
	}
}
else
{
	echo "0 results";
}

$conn->close();

?>