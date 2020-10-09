<?php 
require_once 'koneksi.php';
$id_lokasi=$_GET['id_lokasi'];
$query = "SELECT user.token FROM user
LEFT JOIN lokasi ON user.id_lokasi_jabatan=lokasi.id_lokasi
WHERE lokasi.id_lokasi='$id_lokasi'";
$result = mysqli_query($konek,$query);

  print(json_encode(mysqli_fetch_assoc($result)));
