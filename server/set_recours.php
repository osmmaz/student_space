<?php
require "con.php";
$recours=$_POST["recours"];
$user_mat=$_POST["login_mat"];
$note=$_POST["note"];
$matier=$_POST["matier"];
$sql_query="INSERT INTO `faire_un_recours` (`type_note`,`cause`,`nom_matiere`,`matricule_id`) VALUES ('$note', '$recours','$matier','$user_mat');";


$result=mysqli_query($con,$sql_query);

if($result)
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

mysqli_close($con);
?>
