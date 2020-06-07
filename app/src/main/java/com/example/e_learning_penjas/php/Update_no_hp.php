<?php 
 require_once 'koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$no_hp = $_POST['no_hp'];
 	$nik = $_POST['nik'];


 	$query = "UPDATE `pemohons` SET `no_hp` = '$no_hp' WHERE nik = '$nik'";

 	$exeQuery = mysqli_query($konek, $query); 

	 echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil mengupdate nomor Hp')) : 
	  json_encode(array('kode' =>2, 'pesan' => 'gagal update Nomor HP'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>
