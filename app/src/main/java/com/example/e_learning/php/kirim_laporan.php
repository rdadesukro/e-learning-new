<?php

	require_once 'koneksi.php';

	class emp{}

	
		
		$image = addslashes(trim($_POST['image']));
		$name = addslashes(trim($_POST['name']));
		$judul = addslashes(trim($_POST['judul']));
		$tanggapan_dinas = addslashes(trim($_POST['tanggapan_dinas']));
		$alamat = addslashes(trim($_POST['alamat']));
 		$id_lokasi = addslashes(trim($_POST['id_lokasi']));
 		$id_user = addslashes(trim($_POST['id_user']));
 		$id_masalah = addslashes(trim($_POST['id_masalah']));
 		$id_kantor_dinas = addslashes(trim($_POST['id_kantor_dinas']));
 		$isi_laporan =addslashes(trim( $_POST['isi_laporan']));
 		$saran_laporan = addslashes(trim($_POST['saran_laporan']));
 		$lng = addslashes(trim($_POST['lng']));
 		$lat = addslashes(trim($_POST['lat']));
 		$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
		$date = strtotime($dateNow."+ ".$staf." hours");
		 
		$h    = date('Y-m-d H:i:s',$date);
		$date2 = strtotime($h."+ ".$kadis." hours");
		$g    = date('Y-m-d H:i:s',$date2);

		if (empty($judul)) {
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty Name.";
		die(json_encode($response));
		} else {
		$random = random_word(20);
		$uuid = random_word(30);
		$path = "images/before/".$random.".png";
		$name = $random.".png";

		$actualpath = "https://newsikesal.jambikota.go.id/idn@droid/$path";

		$query = mysqli_query($konek, "INSERT INTO laporan (
		id_laporan,
		id_user,
		id_masalah,
		id_lokasi,
		pelapor,
		judul,
		isi_laporan,
		saran_laporan,
		tanggapan_dinas,
		jenis_pengiriman,
		telp_pelapor,
		tanggal_lapor,
		tanggal_selesai,
		lng,
		lat,
		alamat,
		foto_laporan,
		foto_selesai,
		status_laporan,
		rating,
		uuid,view,timer)
		VALUES (
		NULL,'$id_user','$id_masalah','$id_lokasi','0','$judul','$isi_laporan','$saran_laporan','$tanggapan_dinas','sikesal',
		'0','$dateNow',NULL,'$lng','$lat','$alamat','$name',NULL,'baru',NULL,'$uuid','1','0')");

		if ($query){
			file_put_contents($path,base64_decode($image));

			$response = new emp();
			$response->success = 1;
			$response->message = "Successfully Uploaded";
			die(json_encode($response));
		} else{
			$response = new emp();
			$response->success = 0;
			$response->message = "Gagal Mengirim Laporan , periksa jaringan anda ...";
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

//	mysqli_close($con);

?>
