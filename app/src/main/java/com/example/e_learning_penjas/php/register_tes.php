<?php

	include_once "koneksi.php";

 class usr{}

     //$name = $_POST["name"];
     $nik = $_POST["nik"];
	 $password =md5($_POST["password"]);
     $confirm_password =md5($_POST["confirm_password"]);
	
	
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM pemohons WHERE nik='".$nik."'"));

		 	if ($num_rows == 0){
		 		$query = mysqli_query($con, "INSERT INTO user (id_user, nik, password) VALUES(0','$nik','$password')");

				if ($query){
					$response = new usr();	
                     $response->success = 1;					
					$response->message = "Register berhasil, silahkan login.";
	             	die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
	 		     	$response->message = "nik sva";
	 			die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "nik sudah ada";
				die(json_encode($response));
			}
	 
	

 mysqli_close($con);

?>	