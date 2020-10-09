<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
    $id_materi = addslashes(trim($_POST['id_materi']));
	
  
   require_once('dbConnect.php');
$query1 = "DELETE FROM materi WHERE id_materi='$id_materi'";

	          
  
		
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