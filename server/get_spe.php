<?php

require "con.php";

$user_pass=$_POST["login_pass"];
$user_mat = $_POST["login_mat"];

//$user_mat = "141030226";

$sql1= "select * from etudiant
		where matricule_id like'$user_mat';";

$sql2= "select nom_sp from inscrit
		where id_groupe like (
		select id_groupe from etudiant
		where matricule_id like'$user_mat'
		);";
$sql3= "select num_section from section
		where section_id like(
		select section_id from groupe
		where id_groupe like (
		select id_groupe from etudiant
		where matricule_id like'$user_mat'
		));";
//$sql = "select * from etudiant;";



$result1= mysqli_query($con,$sql1);

$result2= mysqli_query($con,$sql2);
$result3= mysqli_query($con,$sql3);

$row1= mysqli_fetch_array($result1);

$row2= mysqli_fetch_array($result2);

$row3= mysqli_fetch_array($result3);

$response = array();
	array_push($response,array("nom"=>$row1[1],"prenom"=>$row1[2],"groupe"=>$row1[4],"specialite"=>$row2[0],"section"=>$row3[0]));

echo json_encode(array("server_response"=>$response));



mysqli_close($con);


?>