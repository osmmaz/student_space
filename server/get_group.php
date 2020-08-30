<?php
require "con.php";

$user_pass=$_POST["login_pass"];
$user_mat = $_POST["login_mat"];
//$user_mat="141030227";


$sql = "select * from groupe 
		where id_groupe in (

		select id_groupe from inscrit
		where nom_sp like (

		select nom_sp from inscrit
		where id_groupe like (

		select id_groupe from etudiant
		where matricule_id like'$user_mat'
		)))";

$result=mysqli_query($con,$sql);


$response = array();

while ( $row = mysqli_fetch_array($result)) {
	

	array_push($response,array("id_groupe"=>$row[0],"num_groupe"=>$row[1]));


}

echo json_encode(array("server_response"=>$response));













?>