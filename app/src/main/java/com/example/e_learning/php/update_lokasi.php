<?php
	include_once "koneksi.php";
	
	class usr{}
	
	
	$lat = $_POST['lat'];
	$lng = $_POST['lng'];
	$id_user = $_POST['id_user'];
	
	 if(empty($lat)) {
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty alamat."; 
		die(json_encode($response));
	}else if (empty($lng)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty no_hp_telpon."; 
		die(json_encode($response));
	} else {
	
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM donor WHERE id_user='".$id_user."'"));
		
		if ($num_rows == 1){
		 		$query2 = mysqli_query($con, "UPDATE donor SET lat='$lat',lng='$lng' WHERE id_user='$id_user'");

				if ($query2){
					$response = new usr();		
					$response->message = "UPDATE berhasil, silahkan login.";
	             	die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
	 		     	$response->message = "up sudah ada";
	 			die(json_encode($response));
				}
			} else {
				$response = new usr();
				//$response->success = 0;
				$response->message = "xxxxxxxx";
				die(json_encode($response));
			}
	}	
	

	mysqli_close($con);
	
?>	