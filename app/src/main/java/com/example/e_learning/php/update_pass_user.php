<?php

 require_once 'koneksi.php';

	$nik = $_POST['nik'];
	$password = md5($_POST['password']);
  	$password_baru = md5($_POST['password_baru']);
  //
  // $query2 = "SELECT `password` FROM `user` WHERE nik='$nik'";
  //   $exeQuery2 = mysqli_query($konek, $query2);
  //
  // echo "pass".$exeQuery2;
$sql = "SELECT `password` FROM `user` WHERE nik='$nik'";
$result = mysqli_query($konek, $sql);
$row = mysqli_fetch_assoc($result);
$pass = $row['password'];

if ($password==$pass) {
   	$query = "UPDATE `user` SET `password` = '$password_baru' WHERE `user`.`nik` = '$nik'";
   		$exeQuery = mysqli_query($konek, $query);
      echo json_encode(array('kode' =>"0", 'pesan' => 'Berhasil memperbarui password'));
}
else {
  echo json_encode(array('kode' =>"1", 'pesan' => 'gagal update password, Password Lama tidak cocok'));
}




 //
 //
 //
 // 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'Berhasil mengirimkan penilaian ke OPD terkait')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
 //
 // }
 // else
 // {
 //
 // }
 ?>
