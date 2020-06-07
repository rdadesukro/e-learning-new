
<?php
require_once 'koneksi.php';

header("Content-type:application/json");


$quiz=$_GET['quiz'];
$nis=$_GET['nis'];
$query = "SELECT nilai.nis AS ambil_nis,siswa.nama,nilai.status,kelas.nama_kelas,nilai.quiz,nilai.nilai,siswa.foto 
FROM nilai,kelas,siswa WHERE siswa.nis=nilai.nis AND kelas.id_kelas=nilai.id_kelas AND nilai.quiz='$quiz' and siswa.nis='$nis'";


$result = mysqli_query($konek,$query);
$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1,"search_count" => count($array), "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
?>


