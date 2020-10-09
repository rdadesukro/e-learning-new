
<?php
require_once 'koneksi.php';

 $nik=$_POST['nik'];
 $tgl_lahir=$_POST['tgl_lahir'];
 $password = md5($_POST['password']);
 
$query = "SELECT user.nik, pemohons.tanggal_lahir FROM user,pemohons WHERE user.nik=pemohons.nik AND user.nik='$nik'";
$result = mysqli_query($konek,$query);
$row = mysqli_fetch_assoc($result);

if (empty($_POST)) {
    echo json_encode(array("kode"=>404,"message"=>"null value please input nik anda tgl lahir"));
}
else if($nik== $row['nik'] && $tgl_lahir==$row['tanggal_lahir'] ){
	$query = "UPDATE `user` SET `password` = '$password' WHERE `user`.`nik` = '$nik'";
   	$exeQuery = mysqli_query($konek, $query);
    echo json_encode(array("kode"=>1,"message"=>"Data cocok"));
}
else{
echo json_encode(array("kode"=>0,"message"=>"Data Tidak cocok"));
}

?>

