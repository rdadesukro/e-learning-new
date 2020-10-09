<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$pemohon_id=$_GET['pemohon_id'];
$result = mysqli_query($konek, "SELECT SUM(IF(registrasi_syarats.`status`=0,1,0)) AS SALAH,SUM(IF(registrasi_syarats.`status`=1,1,0)) AS BENAR, layanans.nama,layanans.foto,registrasis.id AS id_regis,registrasis.pemohon_id,layanans.`produk`,registrasis.`status`,registrasis.tgl_reg FROM layanans,registrasis,registrasi_syarats WHERE layanans.id=registrasis.layanan_id AND  registrasis.pemohon_id='$pemohon_id' and registrasi_syarats.`registrasi_id`=registrasis.`id`  group by registrasi_syarats.`registrasi_id`");


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
