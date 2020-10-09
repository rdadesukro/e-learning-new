<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$registrasi_id=$_GET['registrasi_id'];
$result = mysqli_query($konek, "SELECT registrasi_syarats.id AS id_syarat,registrasi_syarats.`registrasi_id`,registrasi_syarats.`syarat_id`,registrasi_syarats.`syarat_value`,registrasi_syarats.`status`,syarats.`nama` FROM registrasi_syarats,syarats WHERE registrasi_syarats.`registrasi_id`='$registrasi_id' AND registrasi_syarats.`syarat_id`=syarats.`id`");
while($row = mysqli_fetch_assoc($result)){
$output[]=$row; 
}
echo ($result) ?

json_encode(array("status" => true,"desc"=>"data layanan", "result"=>$output)) :
json_encode(array("status" => 0, "pesan"=>"data tidak ditemukan"));





mysqli_close($konek);

?>
