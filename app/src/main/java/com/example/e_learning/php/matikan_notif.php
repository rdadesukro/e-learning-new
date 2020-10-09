<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
    $nik = addslashes(trim($_POST['nik']));
   
   require_once('dbConnect.php');
$query1 = "UPDATE user SET token='0'  WHERE nik='$nik'";

	          
  
		
     if(mysqli_query($con,$query1)) {
	
       $response["value"] = "1";
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = "0";
       $response["message"] = "oops! Coba lagi xx!";
       echo json_encode($response);
     }
	 
	
  // }
   // tutup database
   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Coba lagi! cc";
  echo json_encode($response);
}