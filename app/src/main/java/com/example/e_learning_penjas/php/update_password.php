/* ========= KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI DI UNREMARK ======== */
	
	<?php

	include_once "koneksi.php";

	 class usr{}

	  $nik = $_POST["nik"];
	  $password = md5($_POST["password"]);
	  $tanggal_lahir = $_POST["tanggal_lahir"];

$num_rows = mysqli_num_rows(mysql_query("SELECT user.`nik` AS nik_Cek, pemohons.`tanggal_lahir`,pemohons.`nama` FROM USER,pemohons WHERE user.nik='$nik' AND pemohons.`tanggal_lahir`='$tanggal_lahir'  AND user.`nik`=pemohons.`nik`"));



	 	if ($num_rows == 0){
	 		$query = mysqli_query($con, "UPDATE `user` SET `password` = '$password' WHERE nik = '$nik'");

				if ($query){
					$response = new usr();
					$response->success = 1;
					$response->message = "Register berhasil, silahkan login.";
	 			    die(json_encode($response));

			   } else {
	 			$response = new usr();
					$response->success = 0;
					$response->message = "nik sudah salah xx";
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "nik sudah ada bb";
				die(json_encode($response));
			}
		
 

	mysqli_close($con);
	?>	