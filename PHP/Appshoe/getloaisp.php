<?php
    include "connect.php";
    $query ="SELECT * FROM loaisanpham";
    $data = mysqli_query($conn,$query);
    $mangloaisp = array();
    while ($row = mysqli_fetch_assoc($data)){
         $mangloaisp[] = $row;
    }
    echo json_encode($mangloaisp);
    class Loaisp{
        function Loaisp($id,$tenloaisp,$anhloaisp){
            $this->id = $id;
            $this->tenloaisp = $tenloaisp;
            $this->anhloaisp = $anhloaisp;
        }
    }
  
?>