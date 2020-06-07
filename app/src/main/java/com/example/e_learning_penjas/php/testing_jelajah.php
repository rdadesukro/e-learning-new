<?php
$arr = array(
    array(
        "Nama_lokasi" => "SPBU Pasti Pas Kebun Jeruk",
        "alamat" => "Jalan Professor Dr. Soemantri, Bojonegoro, Solok Sipin, Telanaipura, Solok Sipin, Telanaipura, Kota Jambi, Jambi 36124",
				"foto" => "https://lh5.googleusercontent.com/p/AF1QipNCRjXRNToZsIaCGgZmEN2szoJnChsjKvuUrzKm=w426-h240-k-no",
				"langitude" => "-1.600426",
				"latitude" => "103.601549"
    ),
    array(
        "region" => "valore",
        "price" => "valore2"
    ),
    array(
        "region" => "valore",
        "price" => "valore2"
    )
);

echo json_encode(array(
	"jenis_lokasi" => "SPBU",
	"Jumlah_lokasi" => count($arr),

"Lokasi"=>$arr)) ;

?>
