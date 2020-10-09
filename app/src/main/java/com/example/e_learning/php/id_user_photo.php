<?php
require_once 'koneksi.php';
$nik=$_GET['nik'];
$query = "SELECT id_user FROM `user` WHERE nik='$nik'";
$result = mysqli_query($konek,$query);

  print(json_encode(mysqli_fetch_assoc($result)));
