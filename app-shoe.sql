-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2021 at 05:18 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app-shoe`
--

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanh` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanh`) VALUES
(1, 'Giày thời trang', 'https://static.ecosite.vn/8775/category/2021/01/06/d38b9870-f69b-43e0-acd3-0f28c8591d7d-1609923723.png'),
(2, 'Giày thể thao', 'https://banner2.cleanpng.com/20180621/buy/kisspng-katrangun-computer-icons-sneakers-sneakers-icon-5b2c4455e79125.2733408015296277339485.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` varchar(2000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Giày Lười Nam TOMOYO', 1300000, 'https://cf.shopee.vn/file/1f87c757b74c77afaf37671537d6e45a', 'Giày Lười Nam TOMOYO Penny Loafer Star TMN05703\r\n\r\n-GIÀY LƯỜI NAM: Giày Lười Nam TOMOYO Penny Loafer Star TMN05703 được thiết kế với mẫu mã đơn giản với những đường may tỉ mỉ từ mũi cho đến gót giày, hạn chế tối đa việc dùng keo trong khâu kỹ thuật. Đây là mẫu giày các bạn nam nên có trong tủ giày của mình bởi tính da dụng của giày lười TOMOYO.', 1),
(2, 'Giày Lười Nam Tomoyo TMN05601', 1300000, 'https://cf.shopee.vn/file/d8433317c31af79a00d5f6107c20703a', 'Giày Lười Nam TOMOYO Penny Loafer Star TMN05601\r\n\r\n-GIÀY LƯỜI NAM: Giày Lười Nam TOMOYO Penny Loafer Star TMN05601 được thiết kế với mẫu mã đơn giản với những đường may tỉ mỉ từ mũi cho đến gót giày, hạn chế tối đa việc dùng keo trong khâu kỹ thuật. Đây là mẫu giày các bạn nam nên có trong tủ giày của mình bởi tính da dụng của giày lười TOMOYO.', 1),
(3, 'Martin boost giày Nam', 999000, 'https://cf.shopee.vn/file/7c3b52bf3de1aa72beb8d2a7c5758a86', 'Giầy Nam cao cổ Martin boost. Giúp giữ ấm. Đế cao. Chất liệu gia thật bền, Rất hợp cho thời tiết mua đồng, không lỗi mode', 1),
(4, 'Giày nam GD2510 ', 690000, 'https://cf.shopee.vn/file/b46644be33e2747bc17d4f4943f06ee6', '✔️ Chất liệu: Da PU cao cấp mềm mại, kết hợp vải sợi thoáng khí bền đẹp theo thời gian\r\n✔️ Đế giày được làm bằng chất liệu cao su đúc nguyên khối chắc chắn có khắc họa tiết để tăng độ ma sát, chống trơn trượt.\r\n✔️ Màu sắc:  Đen - Trắng - Be\r\n✔️ Size: 39 - 44', 1),
(5, 'Giày Thể Thao Nam', 295000, 'https://cf.shopee.vn/file/65850a7f5927cb2c5e9b2e0ec7897b6b', '✔️ Chất liệu: Da PU cao cấp mềm mại, kết hợp vải sợi thoáng khí bền đẹp theo thời gian\r\n✔️ Đế giày được làm bằng chất liệu cao su đúc nguyên khối chắc chắn có khắc họa tiết để tăng độ ma sát, chống trơn trượt.\r\n✔️ Màu sắc:  Đen - Trắng - Be\r\n✔️ Size: 39 - 44', 2),
(6, 'Giày thể thao pikachu', 2550000, 'https://cf.shopee.vn/file/2b85a9c3a913004130691edcd140407f', ' ==================================================\r\n Dành cho: Nam, Nữ \r\n Có hộp đựng giày hay không ： Không\r\n Kiểu dáng: Cổ thấp\r\n Kích thước: 35-44\r\n Chất liệu ： Canvas\r\n Đặc điểm: Thoải mái và thoáng khí\r\n ==================================================', 2),
(7, 'Giày Sneaker TKTMS41 ', 299000, 'https://cf.shopee.vn/file/d810441565762371558114499cea6461', '✔️Về sản phẩm: Shop cam kết cả về chất liệu cũng như hình dáng ( đúng với những gì được nêu bật trong phần mô tả sản phẩm).\r\n\r\n✔️Về giá cả: Shop nhập với số lượng nhiều và trực tiếp nên chi phí sẽ cực cạnh tranh nhé.\r\n\r\n✔️Về dịch vụ: Khách hàng có thể hoàn toàn yên tâm về việc đổi size, màu nếu có nhu cầu. Shop sẽ hỗ trợ tận tình.\r\n\r\n✔️Thời gian chuẩn bị hàng: Hàng có sẵn, thời gian chuẩn bị tối ưu nhất.', 2),
(8, ' Jordan panda ', 525000, 'https://cf.shopee.vn/file/62c638945bc385e82b5c819ed98638a8', '✔️ Chất liệu: Da PU cao cấp mềm mại, kết hợp vải sợi thoáng khí bền đẹp theo thời gian\r\n✔️ Đế giày được làm bằng chất liệu cao su đúc nguyên khối chắc chắn có khắc họa tiết để tăng độ ma sát, chống trơn trượt.\r\n✔️ Màu sắc:  Đen - Trắng - Be\r\n✔️ Size: 39 - 44', 2),
(9, 'Giày Thể Thao Nam Năng Động', 550000, 'https://cf.shopee.vn/file/c1e20267c20dc24dde2e7ab5146f9ab1', 'Size:39-48\r\nColor:Black,Gray,Red\r\n\r\nSize\r\nEUR 39= Foot Length 245mm=US6.5\r\nEUR 40= Foot Length 250mm=US7\r\nEUR 41= Foot Length 255mm=US8\r\nEUR 42= Foot Length 260mm=US8.5\r\nEUR 43= Foot Length 265mm=US9.5\r\nEUR 44= Foot Length 270mm=US10\r\nEUR 45=Foot Length 275mm=US11\r\nEUR 46=Foot Length 280mm=US12\r\nEUR 47=Foot Length 285mm=US12.5\r\nEUR 48=Foot Length 290mm=US13', 2),
(10, 'Giày nam thể thao 2021 - G205', 950000, 'https://cf.shopee.vn/file/966f7ad30fc4e50ddd8c407e892b6c81', '✅ Size: 39, 40, 41, 42, 43.\r\n✅ màu sắc: như hình\r\n✅ sản xuất: hàng nhập xưởng hôngkong\r\n✅ Kiểu dáng: thể thao', 2),
(11, 'Giày lười nam ORSIL', 1230000, 'https://cf.shopee.vn/file/ffbb5faa43b03c2831cceec654bfd3df', 'THÔNG TIN SẢN PHẨM Giày lười da bò màu đen:\r\n- Sản phẩm: Giày lười da bò màu đen\r\n- Thương hiệu: ORSIL\r\n- Xuất xứ: Việt Nam\r\n- Kiểu dáng: Giày lười, giày mọi\r\n- Chất liệu: Da bò\r\n- Chất liệu đế: Cao su đế mềm\r\n- Miếng lót 3D Air\r\n- Size: 38 - 39 - 40 - 41 - 42 - 43\r\n- Bảo hành: 1 tháng', 1),
(12, 'Giày SMARTMEN GD3-02', 650000, 'https://cf.shopee.vn/file/afbd9cebaf4a354c79b7014b203b0344', '✔️ Chất liệu: Da PU cao cấp mềm mại, kết hợp vải sợi thoáng khí bền đẹp theo thời gian\r\n✔️ Đế giày được làm bằng chất liệu cao su đúc nguyên khối chắc chắn có khắc họa tiết để tăng độ ma sát, chống trơn trượt.\r\n✔️ Màu sắc:  Đen - Trắng - Be\r\n✔️ Size: 39 - 44', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lk_sanpham` (`idsanpham`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD CONSTRAINT `lk_sanpham` FOREIGN KEY (`id`) REFERENCES `sanpham` (`idsanpham`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
