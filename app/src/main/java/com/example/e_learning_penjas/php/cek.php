<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$nik=$_GET['nik'];
$tanggal_lahir=$_GET['tanggal_lahir'];
$result = mysqli_query($konek, "SELECT user.`nik` AS nik_Cek, pemohons.`tanggal_lahir`,pemohons.`nama` FROM USER,pemohons WHERE user.nik='$nik' AND pemohons.`tanggal_lahir`='$tanggal_lahir'  AND user.`nik`=pemohons.`nik`");

while($row = mysqli_fetch_assoc($result)){
$output[]=$row; 
}
echo ($result) ?

json_encode(array("status" => true,"desc"=>"data kecamatan", "result"=>$output)) :
json_encode(array("status" => 0, "pesan"=>"data tidak ditemukan"));



mysqli_close($konek);

?>
