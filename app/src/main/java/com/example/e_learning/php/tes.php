<?php
	include_once "koneksi.php";
	
	class emp{}
	
	$image = $_POST['image'];
	
	
		$random = random_word(20);
		
		$path = "images/".$random.".png";
		
		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "http://192.168.10.177/android/upload_image/$path";
		
		 $sql =  "INSERT INTO registrasi_syarats (id,registrasi_id,syarat,image,status,created_at,created_by,updated_at,updated_by,uuid)
	 VALUES  (NULL,'1','1','$path','1','','','','','1')";
		
		if (mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			
			$response = new emp();
			$response->success = 1;
			$response->message = "Successfully Uploaded";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Upload image";
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

	mysqli_close($con);
	
?>	