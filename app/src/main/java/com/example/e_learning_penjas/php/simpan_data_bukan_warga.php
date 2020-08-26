<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
        $nama = addslashes(trim($_POST['nama']));
		$no_hp = addslashes(trim($_POST['no_hp']));
		

		$uuid = addslashes(trim($_POST['uuid']));
		$password =md5($_POST["password"]);
 		$nik = addslashes(trim($_POST['nik']));
		$alamat = addslashes(trim($_POST['alamat']));
		$tanggal_lahir = addslashes(trim($_POST['tanggal_lahir']));
		$tempat_lahir = addslashes(trim($_POST['tempat_lahir']));
		$jk = addslashes(trim($_POST['jk']));
		$instansi_id = addslashes(trim($_POST['instansi_id']));
 		$token =addslashes(trim( $_POST['token']));
 	

 		$pekerjaan = addslashes(trim($_POST['pekerjaan']));
		$foto_ktp = addslashes(trim($_POST['foto_ktp']));
 		
$random = random_word(20);
		$path = "images/before/".$random.".png";
		$name = $random.".png";

		$actualpath = "http://192.168.43.48/e_pelayanan/$path";

   require_once('dbConnect.php');
   
      
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM user WHERE nik ='$nik'";

   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! NPM sudah terdaftar!";
     echo json_encode($response);
   } else {
     $sql =  "INSERT INTO pemohons (
		id,
		nik,
		nama,
		jk,
		tempat_lahir,
		tanggal_lahir,
		no_hp,
		instansi_id,
		uuid,
		status_warga,
		alamat,
		pekerjaan,
		foto_ktp)
		VALUES (NULL,'$nik','$nama','$jk','$tempat_lahir','$tanggal_lahir','$no_hp','$instansi_id','$uuid','Luar Kota Jambi','$alamat','$pekerjaan','$name')";
		
		
		$sql2 =  "INSERT INTO user (
		id,
		nik,
		password,
		token,
		status)
		VALUES (NULL,'$nik','$password','$token','0')";
		
		
     if(mysqli_query($con,$sql)) {
	  file_put_contents($path,base64_decode($foto_ktp));
       $response["value"] = 1;
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi xx!";
       echo json_encode($response);
     }
	 
	 if(mysqli_query($con,$sql2)) {
		 
       $response["value"] = 1;
       $response["message"] = "Sukses mendaftar2";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi xx!";
       echo json_encode($response);
     }
   }
   // tutup database
   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Coba lagi! cc";
  echo json_encode($response);
}
function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}