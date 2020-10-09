<?php
require_once 'koneksi.php';

$query = "SELECT laporan.id_laporan,user.id_user,user.nama_lengkap,masalah.masalah,kantor_dinas.kantor_dinas,laporan.tanggapan_dinas,
	laporan.alamat,laporan.judul,laporan.isi_laporan,laporan.saran_laporan,laporan.jenis_pengiriman,laporan.tanggal_lapor,
	laporan.tanggal_selesai, laporan.lng,laporan.lat,laporan.foto_laporan,laporan.foto_selesai,laporan.status_laporan,
	laporan.rating,user.photo,laporan.pelapor, COUNT(komentar.id_komentar) AS jumlah_komen

	FROM laporan LEFT JOIN komentar
	USING(id_laporan) LEFT JOIN masalah USING(id_masalah)LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN kantor_dinas ON laporan.id_masalah=masalah.id_masalah AND masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	GROUP BY id_laporan ORDER BY laporan.id_laporan DESC";

$result = mysqli_query($konek,$query);




$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1, "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));



mysqli_set_charset($konek,"utf8");


