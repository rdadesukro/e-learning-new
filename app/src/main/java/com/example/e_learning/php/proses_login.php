<?php
session_start();
include "konek.php";
$username = $_POST['username'];
$password = $_POST['password'];
$login = mysql_query("select * from admin 
			where username='$username' and password=('$password')");
	$row = mysql_num_rows($login);
	if($row == 0){
		echo "<script>alert('Login Gagal'); window.location = 'login.php'</script>";		
		exit;
	}else{
		$_SESSION['login'] 		= "ok";
		$_SESSION['nama_user'] 	= $username;
		echo "<script> window.location = 'index.php?hal=buku'</script>";
		exit;		
	}
?>