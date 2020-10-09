<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

    $response = array();
   // $syarat_value = addslashes(trim($_POST['syarat_value']));
	$id_list_info_publik=addslashes(trim($_POST['id_list_info_publik']));
	$kategori=addslashes(trim($_POST['kategori']));
	$keterangan=addslashes(trim($_POST['keterangan']));
	$nama_file=addslashes(trim($_POST['nama_file']));
	$random = random_word(20);
	$file_path = "../files/".$random;
	$file_path2 = $random;
	$tes = $file_path2.$_FILES['name'].".pdf";
		$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
require_once('dbConnect.php');

f ( ! empty($nama_file)) {



}else {
	if(move_uploaded_file($_FILES['file']['tmp_name'],$file_path.$_FILES['name'].".pdf")){
		
		
		

$query1 = "UPDATE list_info_publik SET nama_file='$nama_file',kategori='$kategori',keterangan='$keterangan',file='$tes'  WHERE id_list_info_publik='".$id_list_info_publik."'";

	          
		
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


    
}
function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}