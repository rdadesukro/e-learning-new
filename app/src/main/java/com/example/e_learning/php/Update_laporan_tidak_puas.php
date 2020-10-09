<?php 

 require_once 'koneksi.php';
class emp{}

 	$id_laporan = $_POST['id_laporan'];
	$saran_laporan = $_POST['saran_laporan'];
	$image = $_POST['image'];
	
	
		$random = random_word(20);
		$path = "images/before/".$random.".png";
		$name = $random.".png";
		$actualpath = "http://180.250.53.107/sikesal/$path";


$query = mysqli_query($konek, "UPDATE `laporan` SET `saran_laporan` = '$saran_laporan',`foto_laporan` = '$name',status_laporan='baru' WHERE `laporan`.`id_laporan` = '$id_laporan'");


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
	


		
		