<?php
require_once 'koneksi.php';

header("Content-type:application/json");
$search = $_POST['search'];
$id_guru=$_POST['id_guru'];




$query = "SELECT * FROM `siswa`,kelas WHERE siswa.id_kelas=kelas.id_kelas and siswa.nama LIKE '%$search%' and siswa.id_guru='$id_guru'";


$result = mysqli_query($konek,$query);

 // print(json_encode(mysqli_fetch_assoc($result)));



 // print(json_encode(mysqli_fetch_assoc($result)));


$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1,"search_count" => count($array), "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
?>


