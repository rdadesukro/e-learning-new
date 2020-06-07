<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$pemohon_id=$_GET['pemohon_id'];
$result = mysqli_query($konek, "select layanans.`nama`, cetak.`url`,cetak.`id_cetak`,cetak.`id_layanan`,cetak.tgl_reg,cetak.status,cetak.id_layanan from cetak,layanans where cetak.`id_layanan`=layanans.`id` and cetak.`id_pemohon`='$pemohon_id'");


$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row; 
}


echo ($result) ? 

json_encode(array("status" => true,"desc"=>"data kecamatan", "result"=>$array)) :
json_encode(array("status" => 0, "pesan"=>"data tidak ditemukan"));



mysqli_close($konek);

?>
