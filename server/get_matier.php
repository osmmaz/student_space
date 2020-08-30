<?php
//online
/*
$host = "oussamacom.000webhostapp.com";
$user = "id1164143_root";
$pass = "osmmaz1995";
$db = "id1164143_uscol_db";
$sql = "select * from new_etudiants;";
*/
//offline
$host = "localhost";
$user = "";
$pass = "";
$db = "";

$user_pass=$_POST["login_pass"];
$user_mat = $_POST["login_mat"];
//$user_mat ='141030226' ;

$sql = "select * from enseigne where matricule_id like'$user_mat';";



$con = mysqli_connect(
	$host,
	$user,
	$pass,
	$db);



$result = mysqli_query($con,$sql);

$response = array();

while ( $row = mysqli_fetch_array($result)) {
	

	array_push($response,array("note_cc"=>$row[0],"note_exam" =>$row[1],"note_ratt"=>$row[2],"moy_mat"=>$row[3],"nom_matier"=>$row[4]));


}

echo json_encode(array("server_response"=>$response));









?>
