<?php 
require_once 'koneksi.php';
$id_masalah=$_GET['id_masalah'];
$query = "SELECT masalah.id_masalah,kantor_dinas.id_kantor_dinas,user.token,user.id_user FROM masalah
	LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	LEFT JOIN user ON user.id_kantor_dinas=masalah.id_kantor_dinas
    WHERE masalah.id_masalah='$id_masalah' AND user.id_jbt='5'";
$result = mysqli_query($konek,$query);

  print(json_encode(mysqli_fetch_assoc($result)));
