<?php 
require_once 'koneksi.php'; 
$id_user=$_GET['id_user'];
$query = "SELECT lokasi.id_lokasi,
lokasi.kelurahan,lokasi.kecamatan,user.username
FROM user
LEFT JOIN lokasi ON user.id_lokasi_jabatan=lokasi.id_lokasi WHERE user.id_user='$id_user'";
$result = mysqli_query($konek,$query);
print(json_encode(mysqli_fetch_assoc($result))); 
 //get nama kelurahan untuk judul di menu fragment 2 

