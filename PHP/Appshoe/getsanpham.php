<?php
    include "connect.php";
    $query ="SELECT * FROM sanpham ORDER BY ID DESC LIMIT 12";
    $data = mysqli_query($conn,$query);
    $mangsanpham = $arrayName = array();
    while($row = mysqli_fetch_assoc($data)){
        $mangsanpham[] = $row;
    }
    echo json_encode($mangsanpham);
    class Sanphammoinhat{
        function Sanphammoinhat($id,$tensp,$giasp,$anhsp,$motasp,$idsanpham){
            $this->id=$id;
            $this->tensp=$tensp;
            $this->giasp=$giasp;
            $this->anhsp=$anhsp;
            $this->motasp=$motasp;
            $this->idsanpham=$idsanpham;

        }
    }
?>