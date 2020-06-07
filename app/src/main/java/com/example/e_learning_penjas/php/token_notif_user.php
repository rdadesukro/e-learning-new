<?php
require_once 'koneksi.php';
$id_user=$_GET['id_user'];
$query = "SELECT id_user,token FROM user WHERE id_user='$id_user'";
$result = mysqli_query($konek,$query);

  print(json_encode(mysqli_fetch_assoc($result)));
