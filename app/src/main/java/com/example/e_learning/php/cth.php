<?php 

	/*
	* Created by Belal Khan
	* website: www.simplifiedcoding.net 
	* Retrieve Data From MySQL Database in Android_donor
	*/
	
	//database constants
	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'blood_skripsi');
	
	//connecting to database and getting the connection object
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	//creating a query
	$stmt = $conn->prepare("SELECT id_donor_user, nama, gol, rhesus, alamat, gol FROM donor;");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id_donor, $nama, $gol, $rhesus, $alamat, $foto);
	
	$donor = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id_donor'] = $id_donor; 
		$temp['nama'] = $nama; 
		$temp['gol'] = $gol; 
		$temp['rhesus'] = $rhesus; 
		$temp['alamat'] = $alamat; 
		$temp['foto'] = $foto; 
		array_push($donor, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);