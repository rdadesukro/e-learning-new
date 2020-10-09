<?php
require_once 'koneksi.php';
header("Content-type:application/json");

	class emp{}


if(!$konek){
die('Could not connect: '.mysqli_error());
}

$uuid=$_POST['uuid'];
$pemohon_id=$_POST['pemohon_id'];
$layanan_id=$_POST['layanan_id'];
$instansi_id=$_POST['instansi_id'];
$syarat_value = $_POST['syarat_value'];
$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);

$query = mysqli_query($konek, "INSERT INTO registrasis (
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
uuid) VALUES (0,'$pemohon_id','$layanan_id','$instansi_id','1','$dateNow','1','1','1','1','$uuid')");

		if ($query){
			

			$response = new emp();
			$response->value = 1;
			$response->message = "valuefully Uploaded";
			$result = mysqli_query($konek, "SELECT id FROM registrasis where uuid='$uuid'");
                  while($row = mysqli_fetch_assoc($result)){
                  $output=$row; 
				  $kalimat = implode(" ",$output);

}

                 echo ($result) ?

               json_encode(array("status" => true,"desc"=>"data kecamatan", "result"=>$output)) :
                  json_encode(array("status" => 0, "pesan"=>"data tidak ditemukan")); 
				  $tes=$output;
				  
				  $random = random_word(20);

		

		$path = "images/".$random.".png";

		

		// sesuiakan ip address laptop/pc atau URL server

		$actualpath = "http://192.168.43.51/android/upload_image/$path";
$query1 = mysqli_query($konek, "INSERT INTO registrasi_syarats (
id,
registrasi_id,
syarat,
syarat_value,
status,
created_at,
created_by,
updated_at,
updated_by,
uuid) VALUES (0,'$kalimat','1','$path','1','1','1','1','1','$uuid')");


               if ($query1){
			
            file_put_contents($path,base64_decode($syarat_value));
			$response = new emp();
			$response->value = 1;
			$response->message = "valuefully Uploaded";
			die(json_encode($response));
	            	}
				  
		   die(json_encode($response));
		} else{
			$response = new emp();
			$response->value = 0;
			$response->message = "Gagal Mengirim Laporan , periksa jaringan anda xxx...";
			die(json_encode($response));
		}
	
function random_word($id = 20){

		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';

		

		$word = '';

		for ($i = 0; $i < $id; $i++){

			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);

		}

		return $word; 

	}


mysqli_close($konek);




?>
