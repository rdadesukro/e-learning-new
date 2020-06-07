<?php
	include_once "koneksi.php";
	
	class emp{}
	

	$id_user = $_POST['id_user'];
	$nama_anak = $_POST['nama_anak'];
	$nama_ibu = $_POST['nama_ibu'];
	$jenkel = $_POST['jenkel'];
	$tgl_lahir = $_POST['tgl_lahir'];
	$email = $_POST['email'];
	$alamat = $_POST['alamat'];
	//$berat = $_POST['berat'];
	//$panjang = $_POST['panjang'];

	if (empty($id_user)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty id_user."; 
		die(json_encode($response));
    }	else if (empty($jenkel)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty jenis kelamin."; 
		die(json_encode($response));
	} else if (empty($nama_anak)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty nama_anak."; 
		die(json_encode($response));
	} else if (empty($tgl_lahir)){
	    $response = new emp();
		$response->success = 0;
		$response->message = "Please dont Tanggal Lahir."; 
		die(json_encode($response));
	} 	else if (empty($nama_ibu)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty nama_ibu."; 
		die(json_encode($response));
	} else if (empty($email)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty email."; 
		die(json_encode($response));
	} else if(empty($alamat)) {
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty alamat."; 
		die(json_encode($response));
	} else {
		$random = random_word(20);
		$tes= $random.".png";
		$path = "admin/images/".$random.".png";

		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM profil WHERE id_user='".$id_user."'"));
		
		
	       if ($num_rows == 0){
		 	    $query = mysqli_query($con, "INSERT INTO profil (foto,nama_anak,nama_ibu,email,alamat,id_user,tgl_lahir,jenkel) VALUES ('$tes','$nama_anak','$nama_ibu','$email','$alamat','$id_user','$tgl_lahir','$jenkel')");
				if ($query){
					
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
				}
			} else  {
				$query2 = mysqli_query($con, "UPDATE profil SET nama_ibu='$nama_ibu',email='$email',nama_anak='$nama_anak',alamat='$alamat',tgl_lahir='$tgl_lahir',jenkel='$jenkel' WHERE id_user='$id_user'");

				if ($query2){
				
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