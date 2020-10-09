<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $id_donor  = $_GET['id_donor'];
 
 require_once('koneksi.php');
 
 $sql ="SELECT *FROM donor order by id_donor";
 
 $result = mysqli_query($con,$sql); 
	
	//Adding results to an array 
	$res = array(); 

	while($row = mysqli_fetch_array($result)){
		array_push($res, array(
			"id_donor"=>$row['id_donor'],
			"nama"=>$row['nama'],
			"gol"=>$row['gol'],
			"rhesus"=>$row['rhesus'],
			"foto"=>$row['foto'],
			"status"=>$row['status'],
			"no_hp"=>$row['no_hp'],
			
			));
	}
	//Displaying the array in json format 
	
	echo json_encode(array("res"=>$res));
	
 }