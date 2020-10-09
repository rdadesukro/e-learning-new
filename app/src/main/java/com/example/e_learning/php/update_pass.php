<?php

 require_once 'koneksi.php';

$nik = $_POST['nik'];
$password = md5($_POST['password']);
$password_baru = md5($_POST['password_baru']);
  
$sql = "SELECT `password` FROM `user` WHERE nik='$nik'";
$result = mysqli_query($konek, $sql);
$row = mysqli_fetch_assoc($result);
$pass = $row['password'];

if ($password==$pass) {
   	$query = "UPDATE `user` SET `password` = '$password_baru' WHERE `user`.`nik` = '$nik'";
		$exeQuery = mysqli_query($konek, $query);
		echo json_encode(array('kode' =>0, 'pesan' => 'Berhasil memperbarui password'));
}
else {
		echo json_encode(array('kode' =>1, 'pesan' => 'gagal update password, Password Lama tidak cocok'));
}

 ?>
