<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$id=$_GET['id'];
$result = mysqli_query($konek, "SELECT nama as nama_ins from instansis where id='$id'");

while($row = mysqli_fetch_assoc($result)){
$output=$row; 
}
echo ($result) ?

json_encode(array("status" => true,"desc"=>"data kecamatan", "result"=>$output)) :
json_encode(array("status" => 0, "pesan"=>"data tidak ditemukan"));



mysqli_close($konek);

?>
