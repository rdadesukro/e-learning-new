<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
        $uuid = addslashes(trim($_POST['uuid']));
        $syarat_value = addslashes(trim($_POST['syarat_value']));
		
		$random = random_word(20);
		$path = "images/before/".$random.".png";
		$name = $random.".png";

		$actualpath = "http://192.168.43.48/e_pelayanan/$path";
  sql1 =  "INSERT INTO registrasis (id,pemohon_id,layanan_id,instansi_id,status,tgl_reg,created_at,created_by,updated_at,updated_by,uuid)
	 VALUES  (NULL,'1','1','$name','1','','','','','1')";
  
 		
 $sql = "SELECT id FROM registrasis WHERE uuid ='$uuid'";
 
 
 
 $result=mysqli_query($konek,$sql);
 
       while($row = mysqli_fetch_array($result)){
				
				  //print(json_encode(array("kode" => 1,"status" => "ditemukan",'result' =>(array('data'=>(mysqli_fetch_assoc($result)))))));
				 // print(json_encode(array("kode" => 1,"pesan" => "NIK terdaftar",'result' =>(mysqli_fetch_assoc($result)))));
	               // print(json_encode(array("kode" => 1,"pesan"=>"NIK ditemukan",(mysqli_fetch_assoc($result)))));
					 echo $row['id'];
					 $ta= $row['id'];
				
				}
 
   require_once('dbConnect.php');
   //Cek npm sudah terdaftar apa belum
  
    sql2 =  "INSERT INTO registrasis (id,pemohon_id,layanan_id,instansi_id,status,tgl_reg,created_at,created_by,updated_at,updated_by,uuid)
	 VALUES  (NULL,'$ta','1','$name','1','','','','','1')";
  
   
  
		
		
     if(mysqli_query($con,$sql2)) {
	  file_put_contents($path,base64_decode($syarat_value));
       $response["value"] = 1;
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi xx!";
       echo json_encode($response);
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