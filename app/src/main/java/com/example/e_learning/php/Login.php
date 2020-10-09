<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
require_once 'koneksi.php'; 
	class usr{}
	   $response = array();
	$nis = addslashes(trim($_POST["nis"]));
	$password =  addslashes(trim(md5($_POST['password'])));


	
	$query1 = mysqli_query($konek, "SELECT siswa.id_kelas,siswa.nama,kelas.nama_kelas,siswa.id_siswa,user.status,siswa.id_guru,user.nis as nis_ambil FROM siswa,USER,kelas WHERE user.nis=siswa.nis AND user.nis='$nis' and kelas.id_kelas=siswa.id_kelas AND user.password='$password' and user.status='siswa'");
	//$row = mysqli_fetch_array($query);
	$row2 = mysqli_fetch_array($query1);
	//var_dump ($row2);
	
	if (!empty($row2)){
		$response = new usr();
		$response->success = 1;
		$response->message = "Selamat datang ".$row2['nama'];
		$response->id_siswa = $row2['id_siswa'];
		$response->kelas = $row2['nama_kelas'];
		$response->nama = $row2['nama'];
		$response->id_kelas = $row2['id_kelas'];
		$response->status = $row2['status'];
		$response->id_guru = $row2['id_guru'];
		$response->nis_ambil = $row2['nis_ambil'];
		
		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = "nis atau password salah";
		die(json_encode($response));
	}
	
   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Coba lagi! cc";
  echo json_encode($response);
	
}
	
	

?>