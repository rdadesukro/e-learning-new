<?php 
require_once 'koneksi.php'; 
$query = "SELECT kantor_dinas.id_kantor_dinas,kantor_dinas.kantor_dinas,kantor_dinas.alamat,kantor_dinas.kontak,kantor_dinas.Foto,masalah.id_masalah,laporan.id_laporan,SUM(laporan.rating),
(CASE
        WHEN (SUM(laporan.rating)/COUNT(laporan.id_masalah)) IS NULL THEN 0
        ELSE SUM(laporan.rating)/COUNT(laporan.id_masalah)
    END) AS rating_dinas
 FROM kantor_dinas LEFT JOIN masalah ON kantor_dinas.id_kantor_dinas=masalah.id_kantor_dinas LEFT JOIN laporan ON masalah.id_masalah=laporan.id_masalah 
 GROUP BY kantor_dinas.id_kantor_dinas
 ORDER BY rating_dinas DESC";
$result = mysqli_query($konek,$query);
$array = array();
while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row; 
}
echo ($result) ? 
json_encode(array("kode" => 1, "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));



