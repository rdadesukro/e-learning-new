<?php 
require_once 'koneksi.php'; 

$masalah=$_GET['masalah'];

$query = "SELECT id_masalah,id_kantor_dinas FROM `masalah` WHERE masalah='$masalah'";

$result = mysqli_query($konek,$query);

  print(json_encode(mysqli_fetch_assoc($result)));


// $array = array();

// while ($row  = mysqli_fetch_assoc($result))
// {
// 	$array[] = $row; 
// }


// echo ($result) ? 
// json_encode(array("kode" => 1, "result"=>$array)) :
// json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));

// $hijri="lian";


// $myObj->id_masalah = $hijri;
// $myObj->id_kantor_dinas = 30;


// $myJSON = json_encode($myObj);

// echo $myJSON;




