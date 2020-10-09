
<?php
require_once 'koneksi.php';

 $nip=$_POST['nip'];
 $tgl_lahir=$_POST['tgl_lahir'];
 $password =md5( $_POST['password']);
 
 
$query = "SELECT guru.nip, guru.tgl_lahir FROM guru,user WHERE user.nis=guru.nip AND user.nis='$nip'";
$result = mysqli_query($konek,$query);
$row = mysqli_fetch_assoc($result);

if (empty($_POST)) {
    echo json_encode(array("kode"=>404,"message"=>"null value please input nik anda tgl lahir"));
}
else if($nip== $row['nip'] && $tgl_lahir==$row['tgl_lahir'] ){
	$query = "UPDATE `user` SET `password` = '$password' WHERE nis= '$nip'";
   	$exeQuery = mysqli_query($konek, $query);
    echo json_encode(array("kode"=>1,"message"=>"Data cocok"));
}
else{
echo json_encode(array("kode"=>0,"message"=>"Data Tidak cocok"));
}

?>