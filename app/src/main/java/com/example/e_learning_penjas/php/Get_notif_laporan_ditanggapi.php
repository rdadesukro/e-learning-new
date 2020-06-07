<?php 
require_once 'koneksi.php'; 
$id_user=$_GET['id_user'];
$query = "SELECT COUNT(view) as laporan_ditanggapi FROM laporan WHERE id_user=$id_user AND status_laporan='tanggapan' AND view='1'";
$result = mysqli_query($konek,$query);

  print(json_encode(mysqli_fetch_assoc($result)));

