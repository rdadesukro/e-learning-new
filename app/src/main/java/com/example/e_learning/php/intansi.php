<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$nik=$_GET['nik'];
$result = mysqli_query($konek, "select pemohons.instansi_id,instansis.tipe_dinas,instansis.instansi_id as id_ambil from pemohons,instansis where nik='$nik' and pemohons.`instansi_id`=instansis.id");

while($row = mysqli_fetch_assoc($result)){
$output=$row; 
}
echo ($result) ?

json_encode(array("status" => true,"desc"=>"data kecamatan", "result"=>$output)) :
json_encode(array("status" => 0, "pesan"=>"data tidak ditemukan"));



mysqli_close($konek);

?>
