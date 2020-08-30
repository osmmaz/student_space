<?php
require "con.php";

$demande=$_POST["demande"];
$group=$_POST["group"];
$user_mat=$_POST["login_mat"];

//$demande="hhhhhh yah its me";
//$group="01";
//$user_mat="141030226";
//$user_pass="XD4E4sEf9c";

$sql_query="INSERT INTO `demande_de_changer`
			(`cause`,`id_groupe`,`matricule_id`) 
			VALUES ('$demande', '$group','$user_mat');";


$result=mysqli_query($con,$sql_query);

if($result)
{

echo "true";
}
else
{

echo "false";
}

mysqli_close($con);

?>
