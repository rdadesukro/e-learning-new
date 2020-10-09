
<?php
require_once 'koneksi.php';

 $nis=$_POST['nis'];
 $tgl_lahir=$_POST['tgl_lahir'];
 $password =md5( $_POST['password']);
 
 
$query = "SELECT siswa.nis, siswa.tgl_lahir FROM siswa,user WHERE user.nis=siswa.nis AND user.nis='$nis'";
$result = mysqli_query($konek,$query);
$row = mysqli_fetch_assoc($result);

if (empty($_POST)) {
    echo json_encode(array("kode"=>404,"message"=>"null value please input nik anda tgl lahir"));
}
else if($nis== $row['nis'] && $tgl_lahir==$row['tgl_lahir'] ){
	$query = "UPDATE `user` SET `password` = '$password' WHERE nis= '$nis'";
   	$exeQuery = mysqli_query($konek, $query);
    echo json_encode(array("kode"=>1,"message"=>"Data cocok"));
}
else{
echo json_encode(array("kode"=>0,"message"=>"Data Tidak cocok"));
}

?>