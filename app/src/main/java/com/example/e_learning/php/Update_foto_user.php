<?php 

 require_once 'koneksi.php';
class emp{}

 	$id_user = $_POST['id_user'];
	$image = $_POST['image'];


		$random = random_word(20);
		$path = "images/user/".$random.".png";
		$name = $random.".png";
		$actualpath = "https://newsikesal.jambikota.go.id/sikesal/$path";


$query = mysqli_query($konek, "UPDATE `user` SET `photo` = '$name' WHERE `user`.`id_user` = '$id_user';

");


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
