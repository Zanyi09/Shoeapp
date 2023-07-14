<?php
    $host = "localhost";
    $username ="root";
    $password ="";
    $database="app-shoe";

    $conn = mysqli_connect($host,$username,$password,$database);
    mysqli_set_charset($conn,"utf8");
  
?>