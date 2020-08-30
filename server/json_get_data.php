<?php

require "con.php";

$user_pass=$_POST["login_pass"];
$user_mat = $_POST["login_mat"];

$sql = "select * from etudiant where matricule_id like'$user_mat';";

//$sql = "select * from etudiant;";



$result = mysqli_query($con,$sql);

$response = array();

while ( $row = mysqli_fetch_array($result)) {
	

	array_push($response,array("n_inscription"=>$row[0],"pass_etud"=>$row[3],"nom_etud_fr" =>$row[1],
		"prenom_etud_fr" =>$row[2],"email" =>$row[5]));


}

echo json_encode(array("server_response"=>$response));






mysqli_close($con);


?>