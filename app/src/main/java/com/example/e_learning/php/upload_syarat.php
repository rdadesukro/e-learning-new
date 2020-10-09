<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$layanan_id=$_GET['layanan_id'];
$result = mysqli_query($konek, "SELECT syarats.deskripsi,syarats.nama,layanan_syarats.`syarat_id`,layanan_syarats.`layanan_id` FROM syarats,layanan_syarats WHERE layanan_syarats.`layanan_id`='$layanan_id' AND layanan_syarats.`syarat_id`=syarats.`id`");
while($row = mysqli_fetch_assoc($result)){
$output[]=$row; 
}
echo ($result) ?

json_encode(array("status" => true,"desc"=>"data layanan", "result"=>$output)) :
json_encode(array("status" => 0, "pesan"=>"data tidak ditemukan"));





mysqli_close($konek);

?>
