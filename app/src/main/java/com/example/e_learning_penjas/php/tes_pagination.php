<?php


if ($page==1) {
  $kali=0;
}
else {
  $kali=($page-1)*20;
}

  $query = "Select id_user,nama_lengkap From user
         LIMIT 20 OFFSET $kali";

     $result = mysqli_query($konek,$query);

     echo ($result) ?

     :
     ;