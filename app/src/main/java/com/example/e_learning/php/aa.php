
<?php
error_reporting(0);
include "koneksi.php";
$id_jkc = isset($_GET['id_jkc']) ? $_GET['id_jkc'] : '';  
if($id_jkc != ""){
    $aksi = "jk_cancel.php?e=true";
    $query = mysql_query("SELECT * FROM  `jk_cancel` where id_jkc = '$id_jkc'");
    $result = mysql_fetch_assoc($query);  
}else{
     $aksi = "jk_cancel.php?t=true";
}
?>	

<?php
if($page == "jk_cancel"){
?>	

<h3><center>Form Jadwal Kuliah Cancel</h3></center>
<form name="form-bobot" action="<?php echo $aksi ?>" method="post">
<input type="hidden" name="id_jkc" id="id_jkc" value="<?php echo isset($result['id_jkc']) ? $result['id_jkc'] : '';?>" />

<table width="100%" border="0" align="left">

	<tr>
    <td width="*"><b>Mata Kuliah, Nama Dosen, Hari, Pukul, Ruang</b>
		<select name="id_jk" id="id_jk" style="padding: 12px 20px;">
		<option value="">Silakan Pilih</option>
			 <?php     
		include "koneksi.php";
        $h = mysql_query("SELECT * 
FROM jadwal_kuliah jk, mata_kuliah mk, dosen ds
WHERE jk.kode_dosen = ds.kode_dosen
AND jk.kode_mk = mk.kode_mk
ORDER BY hari asc") or die(mysql_error());    
        while ($baris = mysql_fetch_array($h)) {
			if($baris['id_jk'] == $result['id_jk']) {
				echo '<option value="' . $baris['id_jk'] . '" selected>' . $baris['mk'].' ||' . $baris['nama_dosen'].' ||--- '.$baris['hari'].' ---|| '.$baris['pukul'].'|| '.$baris['ruang'].'</option>';    
			} else {
				echo '<option value="' . $baris['id_jk'] . '">' . $baris['mk'].' ||' . $baris['nama_dosen'].' ||--- '.$baris['hari'].' ---|| '.$baris['pukul'].'|| '.$baris['ruang'].'</option>';    
			}
        
          }    
        echo '</select>';    
        ?>
	    </select>
	</td>
  </tr>
   <tr style="display:none">
		<td width="*"><b>Tanggal</b> <input type="date" name="tgl" id="tgl" maxlength="40" value="<?php echo $result['tgl'] ?>"></td>
	</tr>
  <tr>
    <td><button type="submit" >Submit</button></td>
  </tr>
</table>
</form>



<h3><center>Data Jadwal Kuliah Cancel</h3></center>
<table id='example' class='display' cellspacing='0' width='100%'>
		<thead>
		<tr bgcolor="#CCCCCC">
			<th>No.</th>
			<th>ID Jadwal Kuliah Cancel</th>
			<th>Mata Kuliah</th>
			<th>Nama Dosen</th>
			<th>Hari</th>
			<th>Pukul</th>
			<th>Ruang</th>
			<th>Status</th>
			<th>Tanggal</th>
			<th>Aksi</th>
		</tr>
		</thead>
		<?php
		include('koneksi.php');
		$query = mysql_query("SELECT * FROM `jk_cancel` jc, jadwal_kuliah jk, mata_kuliah mk, dosen ds
where
jk.id_jk = jc.id_jk
and jk.kode_mk = mk.kode_mk
and ds.kode_dosen = jk.kode_dosen
order by jc.id_jkc desc") or die(mysql_error());
			$no = 1;
			while($data = mysql_fetch_assoc($query)){
				
				$edit	= "home.php?page=jk_cancel&id_jkc=".$data['id_jkc'];
				$hapus	= "jk_cancel.php?h=true&id_jkc=".$data['id_jkc'];
				
				?>
				<tr>
					<td><?php echo $no++; ?></td>
					<td><?php echo $data['id_jkc']; ?></td>
					<td><?php echo $data['mk']; ?></td>
					<td><?php echo $data['nama_dosen']; ?></td>
					<td><?php echo $data['hari']; ?></td>
					<td><?php echo $data['pukul']; ?></td>
					<td><?php echo $data['ruang']; ?></td>
					<td><?php echo $data['status_history']; ?></td>
					<td><?php echo date_format(date_create($data['tgl']), 'd-m-Y') ?></td>
					<td>
					<a href="<?php echo $edit ?>" class="btn btn-success" title="edit">
							<span class="glyphicon glyphicon-edit"></span> 
						</a>
						<a href="<?php echo $hapus ?>" class="btn btn-danger" title="hapus" onclick="return confirm('Apakah anda yakin akan menghapus data ini ?')">
							<span class="glyphicon glyphicon-trash"></span>
						</a>
					</td>
				</tr>	
		<?php	
			}
		?>
	</table>

<?php } ?>
	
<?php

if($_GET['t']){

	$id_jk			= $_POST['id_jk'];
	$tgl			= date("Y-m-d");
	
	$rsqljk = mysql_query("SELECT jk.*, mt.mk FROM jadwal_kuliah jk 
							LEFT JOIN mata_kuliah mt ON jk.kode_mk = mt.kode_mk WHERE jk.id_jk = '$id_jk'") or die(mysql_error());
	if(mysql_num_rows($rsqljk)) {
		$jdk = mysql_fetch_assoc($rsqljk);
		
		mysql_query("insert into jk_cancel value(
				0,
				'$tgl',
				'$id_jk',
				'Cancel',
				'')") or die (mysql_error());

		//$topics = $jdk['id_jk'];
		$topics = $jdk['id_jk'];
		$message = 'Ruang : '.$jdk['ruang'].' Jam : '.$jdk['pukul'].' Cancel';
		
		$res = array();
		$res['title'] = $jdk['mk'];
		$res['body'] = $message;

		
		$fields = array(
			'to' => '/topics/' . $topics,
			'notification' => $res
		);
						
		// Set POST variables
		$url = 'https://fcm.googleapis.com/fcm/send';
		$server_key = "AAAAFMf7FmA:APA91bE6xD4IOwJt813GFukVBS_g_8jwxBpIUx-8A665vwhJEE9VdUp5D3zveqqWU0HMI2P5-yPXirJVe58BWcVerQ0Oij92kJejVXggSoV1wO426DsDnIQf_UFu2ctGS7icNmvJeRH5";
		
		$headers = array(
			'Authorization: key=' . $server_key,
			'Content-Type: application/json'
		);
		// Open connection
		$ch = curl_init();

		// Set the url, number of POST vars, POST data
		curl_setopt($ch, CURLOPT_URL, $url);

		curl_setopt($ch, CURLOPT_POST, true);
		curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

		// Disabling SSL Certificate support temporarly
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);

		curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));

		// Execute post
		$result = curl_exec($ch);
		if ($result === FALSE) {
			echo 'Curl failed: ' . curl_error($ch);
		}

		// Close connection
		curl_close($ch);
		
	} else {
		
	}

	
	

echo "<script>alert('Berhasil disimpan');  
			window.location='home.php?page=jk_cancel'</script>";			

}else if($_GET['e']){
	
	echo "edit";

}else if($_GET['h']){
	
	mysql_query("delete from jk_cancel where id_jkc = '$_GET[id_jkc]' ") or die (mysql_error());
	
	echo "<script>alert('Berhasil disimpan');  
			window.location='home.php?page=jk_cancel'</script>";
	
}

?>