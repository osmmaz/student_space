<?php
require "con.php";
$user_mat=$_POST["login_mat"];
$user_pass=$_POST["login_pass"];

//$user_mat="141030226";
//$user_pass="XD4E4sEf9c";

$sql_query="select nom,prenom from etudiant where matricule_id like'$user_mat' and mot_de_pass like '$user_pass';";
$result=mysqli_query($con,$sql_query);

if(mysqli_num_rows($result)>0)
{
/*$row=mysqli_fetch_assoc($result);
$name=$row["nom_etud_fr"];
$prename=$row["prenom_etud_fr"];
echo"Bienvenue ".$name." ".$prename;*/
echo "true";
}
else
{
/*echo"Votre Matricule ou Mot de Pass est incorrect ";
echo"Reessayer";*/
echo "false";
}
?>