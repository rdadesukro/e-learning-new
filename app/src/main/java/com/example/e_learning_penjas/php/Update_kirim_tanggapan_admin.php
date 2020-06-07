<?php
// 	include_once "koneksi.php";	//
require_once 'koneksi.php';

//	$server 	= "localhost";
//	$username 	= "newsikesaljambik";
//	$password	= "newsikesaljambik_db";
//	$database 	= "LangsungGanti123";//


//	$con = mysqli_connect($server, $username, $password, $database);
	class emp{}

	$image = $_POST['image'];
	$id_laporan = $_POST['id_laporan'];
	$tanggapan_dinas = $_POST['tanggapan_dinas'];
		$jenis_pengiriman = $_POST['jenis_pengiriman'];



$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
$date = strtotime($dateNow."+ ".$staf." hours");
$h    = date('Y-m-d H:i:s',$date);
$date2 = strtotime($h."+ ".$kadis." hours");
$g    = date('Y-m-d H:i:s',$date2);

		$random = random_word(20);
		$path = "images/after/".$random.".png";
		$name = $random.".png";

$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
 							$date = strtotime($dateNow."+ ".$staf." hours");
$h    = date('Y-m-d H:i:s',$date);

$date2 = strtotime($h."+ ".$kadis." hours");
$g    = date('Y-m-d H:i:s',$date2);

		$actualpath = "https://newsikesal.jambikota.go.id/sikesal/$path";
		if($jenis_pengiriman=='sikesal'){
			$query = mysqli_query($konek, "UPDATE laporan SET tanggal_selesai='$dateNow',view='$dateNow',status_laporan='tanggapan',tanggapan_dinas='$tanggapan_dinas',foto_selesai='$name'WHERE id_laporan='$id_laporan'");

			}
		else {
			$query = mysqli_query($konek, "UPDATE laporan SET tanggal_selesai='$dateNow',view='$dateNow', status_laporan='selesai',tanggapan_dinas='$tanggapan_dinas',foto_selesai='$name'WHERE id_laporan='$id_laporan'");
		}


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
