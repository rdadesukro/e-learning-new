<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
        
          $uuid=addslashes(trim($_POST['uuid']));
          $pemohon_id=addslashes(trim($_POST['pemohon_id']));
          $layanan_id=addslashes(trim($_POST['layanan_id']));
          $instansi_id=addslashes(trim($_POST['instansi_id']));
		$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
   require_once('dbConnect.php');
   
  $sql = "SELECT * FROM registrasis WHERE pemohon_id ='$pemohon_id' and layanan_id ='$layanan_id' ";

 $check = mysqli_fetch_array(mysqli_query($con,$sql));
   if(isset($check)){
     $response["value"] = "2";
     $response["message"] = "Anda Sudah terdaftar!";
     echo json_encode($response);
   } else {
     $sql2 =  "INSERT INTO registrasis (
        id,
       pemohon_id,
layanan_id,
instansi_id,
status,
tgl_reg,
created_at,
created_by,
updated_at,
updated_by,
uuid) VALUES (0,'$pemohon_id','$layanan_id','$instansi_id','1','$dateNow','1','1','1','1','$uuid')";
		
		
     if(mysqli_query($con,$sql2)) {
       $response["value"] = "1";
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = "0";
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