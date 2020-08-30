<?php
$db_name="scol2";
$user="";
$pass="";
$server_name="localhost";


$user_pass=$_POST["login_pass"];
$user_mat = $_POST["login_mat"];
//$user_mat="141030226";


$sql = "SELECT * FROM `effectuer`
		WHERE id_groupe like
		(SELECT id_groupe FROM `etudiant`
		WHERE matricule_et like '$user_mat') ";


$con=mysqli_connect($server_name,$user,$pass,$db_name);

$result=mysqli_query($con,$sql);


$response = array();

while ( $row = mysqli_fetch_array($result)) {
	

	array_push($response,array("type"=>$row[0],"jour"=>$row[1],"deb"=>$row[2],"salle"=>$row[4],"matier"=>$row[7]));


}

echo json_encode(array("server_response"=>$response));













?>
