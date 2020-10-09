


<?php
require_once 'koneksi.php';
class usr{}
	class emp{}
	
	$image = addslashes(trim($_POST['image']));
	 $nik = addslashes(trim($_POST["nik"]));
	$nama = addslashes(trim($_POST["nama"]));
	$alamat = addslashes(trim($_POST["alamat"]));
	$password = addslashes(trim($_POST["password"]));
	$encrypted_mypassword=md5($password);


	//datenow
	 $dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
	$date 	= strtotime($dateNow."+ ".$staf." hours");
	$h    	= date('Y-m-d H:i:s',$date);
	$date2	= strtotime($h."+ ".$kadis." hours");
	$g    	= date('Y-m-d H:i:s',$date2);

	$random = random_word(20);
		$path = "images/user/".$random.".png";
		$actualpath = "https://newsikesal.jambikota.go.id/idn@droid/$path";
		$name = $random.".png";

		 if (!empty($nik) ){
		 	$num_rows = mysqli_num_rows(mysqli_query($konek, "SELECT * FROM user WHERE nik='".$nik."'"));
		 	if ($num_rows == 0){
		 		$query = mysqli_query($konek, "INSERT INTO `user` (`id_user`, `nik`, `username`, `password`, `nama_lengkap`, `email`, `id_jbt`, `id_kantor_dinas`, `id_lokasi_jabatan`,
				`no_hp`, `alamat`, `photo`, `token`, `tanggal_daftar`, `status`, `created_at`, `updated_at`, `uuid`)
				VALUES (0, '$nik', '', '$encrypted_mypassword', '$nama', '', '6', '0', '0', '', '$alamat', '$name', '', ' $dateNow', '1', '', '', '$random')");
		 		if ($query){
					file_put_contents($path,base64_decode($image));
		 			$response = new usr();
		 			$response->success = 1;
		 			$response->message = "Selamat anda telah berhasil Mendaftar.";
		 			die(json_encode($response));
		 		} else {
		 			$response = new usr();
		 			$response->success = 0;
		 			$response->message = "Username sudah ada";
		 			die(json_encode($response));
		 		}
		 	} else {
		 		$response = new usr();
		 		$response->success = 0;
		 		$response->message = "Username sudah ada";
		 		die(json_encode($response));
		 	}
		 }

		 // fungsi random string pada gambar untuk menghindari nama file yang sama
	function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';

		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word;
	}
?>
