<?php
$host="localhost"; //replace with database hostname 
$username=""; //replace with database username 
$password=""; //replace with database password 
$db_name=""; //replace with database name

$con=mysqli_connect($host,$username,$password)or die("cannot connect"); 
mysqli_select_db($con,$db_name)or die("cannot select DB");
$sql = "select * from member"; 

$result = mysqli_query($con,$sql);
$json = array();


if(mysqli_num_rows($result)){
while($row=mysqli_fetch_assoc($result)){
$json['member'][]=$row;

}

}

mysqli_close($con);
echo json_encode($json); 
?> 
