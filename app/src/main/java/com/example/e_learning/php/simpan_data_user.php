<?php



   $response = array();
   //mendapatkan data
 
		$no_hp = addslashes(trim($_POST['no_hp']));
		$password =md5($_POST["password"]);
 		$nik = addslashes(trim($_POST['nik']));
 		$token =addslashes(trim( $_POST['token']));
 		

   require_once('dbConnect.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM user WHERE nik ='$nik'";

   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! NPM sudah terdaftar!";
     echo json_encode($response);
   } else {
     $sql =  "INSERT INTO user (
		id,
		nik,
		password,
		token,
		status)
		VALUES (NULL,'$nik','$password','$token','1')";
		
		 $sql2 =  "UPDATE `pemohons` SET `no_hp` = '$no_hp' WHERE nik = '$nik'";
		
		
     if(mysqli_query($con,$sql)) {
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
       $response["message"] = "Sukses update data";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi xx!";
       echo json_encode($response);
     }
   }
   // tutup database
   mysqli_close($con);
