<?php
	include_once "koneksi.php";
	
	class emp{}
	
	//$image = $_POST['image'];
	$id_user = $_POST['id_user'];
	$name = $_POST['name'];
	$gol = $_POST['gol'];
	$jns_kel = $_POST['jns_kel'];
	$tgl_lahir = $_POST['tgl_lahir'];
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
    }	else if (empty($jns_kel)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty jenis kelamin."; 
		die(json_encode($response));
	} else if (empty($name)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty Name."; 
		die(json_encode($response));
	} else if (empty($tgl_lahir)){
	    $response = new emp();
		$response->success = 0;
		$response->message = "Please dont Tanggal Lahir."; 
		die(json_encode($response));
	} 	else if (empty($gol)){
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
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM donor WHERE id_user='".$id_user."'"));
		
		
	       if ($num_rows == 0){
		 	    $query = mysqli_query($con, "INSERT INTO donor (foto,nama,gol,rhesus,alamat,no_hp,lat,lng,id_user,tgl_lahir,jns_kel) VALUES ('$actualpath','$name','$gol','$rhesus','$alamat','$no_hp','$lat','$lng','$id_user','$tgl_lahir','$jns_kel')");
				if ($query){
					//file_put_contents($path,base64_decode($image));
			
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
				}
			} else  {
				$query2 = mysqli_query($con, "UPDATE donor SET foto='$actualpath',gol='$gol',rhesus='$rhesus',nama='$name',alamat='$alamat',no_hp='$no_hp',lat='$lat',lng='$lng',tgl_lahir='$tgl_lahir',jns_kel='$jns_kel' WHERE id_user='$id_user'");

				if ($query2){
					//file_put_contents($path,base64_decode($image));
					$response = new emp();	
                    $response->success = 1;					
					$response->message = "Successfully Uploaded";
		            die(json_encode($response));

				} else {
					$response = new emp();
					$response->success = 0;
	 			    $response->message = "email  anda tidak anda";
	 			    die(json_encode($response));
				}
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

	mysqli_close($con);
	
?>	