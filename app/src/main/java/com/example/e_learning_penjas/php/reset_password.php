<?php

 require_once 'koneksi.php';

	$nik = addslashes(trim($_POST['nik']));
    

      $query = "UPDATE `user` SET `password` = '9e2846e5d133a29871f7a4d864f2ab70' WHERE `user`.`nik` = '$nik'";
         		$exeQuery = mysqli_query($konek, $query);
            echo json_encode(array('kode' =>1, 'pesan' => 'Berhasil memperbarui password'));
      
// $sql = "SELECT `password` FROM `user` WHERE nik='$nik'";
// $result = mysqli_query($konek, $sql);
// $row = mysqli_fetch_assoc($result);
// $pass = $row['password'];

// if ($password==$pass) {
//    	$query = "UPDATE `user` SET `password` = '$password_baru' WHERE `user`.`id_user` = '$id_user'";
//    		$exeQuery = mysqli_query($konek, $query);
//       echo json_encode(array('kode' =>0, 'pesan' => 'Berhasil memperbarui password'));
// }
// else {
//   echo json_encode(array('kode' =>1, 'pesan' => 'gagal update password, Password Lama tidak cocok'));
// }


 // }
 ?>
