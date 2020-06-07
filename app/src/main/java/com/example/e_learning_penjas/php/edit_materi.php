<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

    $response = array();
   // $syarat_value = addslashes(trim($_POST['syarat_value']));
	$nama=addslashes(trim($_POST['nama']));
	$id_materi=addslashes(trim($_POST['id_materi']));
	$bab=addslashes(trim($_POST['bab']));
	$smester=addslashes(trim($_POST['smester']));
	$random = random_word(20);
	$file_path = "materi/".$random;
	$file_path2 = $random;
	$tes = $file_path2.$_FILES['materi']['name'];
		$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
require_once('dbConnect.php');
    if(move_uploaded_file($_FILES['materi']['tmp_name'],$file_path.$_FILES['materi']['name'])){
		
		
		

$query1 = "UPDATE materi SET url='$tes',nama='$nama',smester='$smester',bab='$bab' WHERE id_materi='$id_materi'";

	          
		
     if(mysqli_query($con,$query1)) {
	  
       $response["value"] = "1";
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = "0";
       $response["message"] = "oops! Coba lagi aa!";
       echo json_encode($response);
     }
	 
	
  // }
   // tutup database
   mysqli_close($con);
} else {
  $response["value"] = 0;
   $response["message"] = "oops! Coba lagi xx!";
  echo json_encode($response);
  
}
}
function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}