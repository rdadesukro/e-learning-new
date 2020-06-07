<?php 

require_once 'koneksi.php'; 



$id_laporan=$_GET['id_laporan'];



$query = "SELECT user.nama_lengkap,laporan.id_laporan,komentar.isi_komentar,komentar.tanggal,user.photo

FROM komentar,laporan,user WHERE komentar.id_user=user.id_user AND komentar.id_laporan=laporan.id_laporan AND

laporan.id_laporan='$id_laporan' ORDER BY tanggal ASC";

 

 //$query2 = "SELECT COUNT(id_komentar) AS jumlah_komen FROM komentar,laporan,user WHERE komentar.id_user=user.id_user AND komentar.id_laporan=laporan.id_laporan AND

//laporan.id_laporan='$id_laporan'";

 

 

//  $query = "SELECT user.id_user,user.nama_lengkap,laporan.judul FROM laporan,user 

 //WHERE user.id_user=laporan.id_user AND laporan.id_user='$id_user'

 //AND status_laporan='$status_laporan' ORDER BY id_laporan DESC & LIMIT $page";

 



$result = mysqli_query($konek,$query);





 // print(json_encode(mysqli_fetch_assoc($result)));





$array = array();



while ($row  = mysqli_fetch_assoc($result))

{

	$array[] = $row; 

}



$jumlah_komen=(count($array));;



echo ($result) ? 



json_encode(array("kode" => 1,"jumlah" => $jumlah_komen, "result"=>$array)) :

json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));





// $hijri="lian";





// $myObj->id_masalah = $hijri;

// $myObj->id_kantor_dinas = 30;





// $myJSON = json_encode($myObj);



// echo $myJSON;













