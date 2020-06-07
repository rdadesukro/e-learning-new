/* ========= KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI DI UNREMARK ======== */
	
	<?php

	include_once "koneksi.php";

	class emp{}
	
	$image = $_POST['image'];
	$id_user = $_POST['id_user'];
	$name = $_POST['name'];
	$gol = $_POST['gol'];
	$rhesus = $_POST['rhesus'];
	$alamat = $_POST['alamat'];
	$no_hp = $_POST['no_hp'];
	$lat = $_POST['lat'];
	$lng = $_POST['lng'];
	
	if (empty($id_user)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty id_user."; 
		die(json_encode($response));
	} else if (empty($name)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty Name."; 
		die(json_encode($response));
	}  else if (empty($gol)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty gol."; 
		die(json_encode($response));
	} else if (empty($rhesus)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty rhesus."; 
		die(json_encode($response));
	} else if(empty($alamat)) {
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty alamat."; 
		die(json_encode($response));
	}else if (empty($no_hp)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty no_hp_telpon."; 
		die(json_encode($response));
	} else {
		$random = random_word(20);
		
		$path = "images/".$random.".png";
		
		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		if (!empty($email) && $encrypted_password == $confirm_encrypted_password){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM users WHERE id_user='".$id_user."'"));

	 	if ($num_rows == 0){
	 		$query = mysqli_query($con, "INSERT INTO donor (foto,nama,gol,rhesus,alamat,no_hp,lat,lng,id_user) VALUES ('$actualpath','$name','$gol','$rhesus','$alamat','$no_hp','$lat','$lng','$id_user')");

				if ($query){
					$response = new usr();
					$response->success = 1;
					$response->message = "Register berhasil, silahkan login.";
	 			die(json_encode($response));

			} else {
	 			$response = new usr();
					$response->success = 0;
					$response->message = "email sudah salah";
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "email sudah ada";
				die(json_encode($response));
			}
		}
 }

	mysqli_close($con);
	?>	