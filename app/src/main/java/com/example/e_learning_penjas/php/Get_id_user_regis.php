<?php 
require_once 'koneksi.php'; 
$nik=$_GET['nik'];
$query = "SELECT id_user,photo FROM user WHERE nik='$nik'";
$result = mysqli_query($konek,$query);
print(json_encode(mysqli_fetch_assoc($result))); 
 //get id user saat register untuk SP session karena hanya mendapatkan nik untuk register sheingga not allow untuk kirim laporan

