/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : final_project_ntlttt_db

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 27/11/2021 15:16:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for beverages
-- ----------------------------
DROP TABLE IF EXISTS `beverages`;
CREATE TABLE `beverages`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Manufacturer` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Quantity` int NULL DEFAULT NULL,
  `Price` float NULL DEFAULT NULL,
  `AcoholeByVolume` float NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE INDEX `beverages_Name_uindex`(`Name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of beverages
-- ----------------------------
INSERT INTO `beverages` VALUES (1, '1', '1', 2, 1, 1);
INSERT INTO `beverages` VALUES (2, '2', '2', 0, 2, 0);
INSERT INTO `beverages` VALUES (3, 'a', 'a', 0, 2, 1);
INSERT INTO `beverages` VALUES (4, 'Cocacola', 'coca', 90, 15000, 0);
INSERT INTO `beverages` VALUES (5, 'Sprite', 'sprite', 15, 20000, 0);
INSERT INTO `beverages` VALUES (6, 'Pepsi', 'pepsi', 20, 12000, 0);
INSERT INTO `beverages` VALUES (7, 'Sting', 'sting', 55, 17000, 0);
INSERT INTO `beverages` VALUES (8, 'Lavie', 'lavie', 22, 13000, 0);
INSERT INTO `beverages` VALUES (9, 'Aquafina', 'aqua', 20, 9000, 0);
INSERT INTO `beverages` VALUES (10, 'Bò húc', 'bohuc', 30, 30000, 0);
INSERT INTO `beverages` VALUES (11, 'Hổ vằn', 'hovan', 25, 19000, 0);
INSERT INTO `beverages` VALUES (12, 'Fanta', 'tanfa', 15, 25000, 0);
INSERT INTO `beverages` VALUES (13, 'Chivas10', 'chiv', 30, 1000000, 10);
INSERT INTO `beverages` VALUES (14, 'Chivas20', 'chiv', 30, 1500000, 15);
INSERT INTO `beverages` VALUES (15, 'Vang ý', 'ac', 15, 2500000, 10);
INSERT INTO `beverages` VALUES (16, 'Ripa Di Sotto Primitivo', 'ita', 38, 100000000, 15);
INSERT INTO `beverages` VALUES (17, '333', 'handmade', 20, 250000, 3);
INSERT INTO `beverages` VALUES (18, 'Heniken', 'handmade', 9, 260000, 5);

-- ----------------------------
-- Table structure for detailbeverageorders
-- ----------------------------
DROP TABLE IF EXISTS `detailbeverageorders`;
CREATE TABLE `detailbeverageorders`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `IdOrder` int NULL DEFAULT NULL,
  `IdBeverage` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `IdBeverage`(`IdBeverage`) USING BTREE,
  INDEX `IdOrderBeverage`(`IdOrder`) USING BTREE,
  CONSTRAINT `IdBeverage` FOREIGN KEY (`IdBeverage`) REFERENCES `beverages` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `IdOrderBeverage` FOREIGN KEY (`IdOrder`) REFERENCES `orders` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detailbeverageorders
-- ----------------------------
INSERT INTO `detailbeverageorders` VALUES (7, 6, 7, 3);
INSERT INTO `detailbeverageorders` VALUES (8, 7, 6, 1);

-- ----------------------------
-- Table structure for detailfoodorders
-- ----------------------------
DROP TABLE IF EXISTS `detailfoodorders`;
CREATE TABLE `detailfoodorders`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `IdOrder` int NULL DEFAULT NULL,
  `IdFood` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `IdFood`(`IdFood`) USING BTREE,
  INDEX `IdOrder`(`IdOrder`) USING BTREE,
  CONSTRAINT `IdFood` FOREIGN KEY (`IdFood`) REFERENCES `foods` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `IdOrder` FOREIGN KEY (`IdOrder`) REFERENCES `orders` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detailfoodorders
-- ----------------------------
INSERT INTO `detailfoodorders` VALUES (6, 6, 9, 2);
INSERT INTO `detailfoodorders` VALUES (7, 6, 8, 1);
INSERT INTO `detailfoodorders` VALUES (8, 7, 9, 1);
INSERT INTO `detailfoodorders` VALUES (9, 7, 8, 2);
INSERT INTO `detailfoodorders` VALUES (10, 7, 17, 1);
INSERT INTO `detailfoodorders` VALUES (11, 8, 17, 1);
INSERT INTO `detailfoodorders` VALUES (12, 8, 8, 1);
INSERT INTO `detailfoodorders` VALUES (13, 8, 16, 1);
INSERT INTO `detailfoodorders` VALUES (14, 9, 16, 1);
INSERT INTO `detailfoodorders` VALUES (15, 9, 17, 1);

-- ----------------------------
-- Table structure for foods
-- ----------------------------
DROP TABLE IF EXISTS `foods`;
CREATE TABLE `foods`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Price` float NULL DEFAULT NULL,
  `Quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foods
-- ----------------------------
INSERT INTO `foods` VALUES (1, 'a', 1, 0);
INSERT INTO `foods` VALUES (3, 'b', 2, 100);
INSERT INTO `foods` VALUES (4, 'c', 2, 0);
INSERT INTO `foods` VALUES (5, 'e', 2, 0);
INSERT INTO `foods` VALUES (6, 'Đậu hũ', 15000, 20);
INSERT INTO `foods` VALUES (7, 'Rau muống luộc', 20000, 30);
INSERT INTO `foods` VALUES (8, 'Thịt kho trứng', 30000, 50);
INSERT INTO `foods` VALUES (9, 'Tôm kho thịt', 17000, 40);
INSERT INTO `foods` VALUES (10, 'Bánh xèo', 30000, 30);
INSERT INTO `foods` VALUES (11, 'Cá Hồi', 50000, 15);
INSERT INTO `foods` VALUES (12, 'Sushi', 55000, 53);
INSERT INTO `foods` VALUES (13, 'Táo hấp lê', 14000, 20);
INSERT INTO `foods` VALUES (14, 'Gà ủ muối', 100000, 30);
INSERT INTO `foods` VALUES (15, 'Tiramisu', 150000, 20);
INSERT INTO `foods` VALUES (16, 'Panna cotta lựu', 200000, 20);
INSERT INTO `foods` VALUES (17, 'Bánh mì', 10000, 100);

-- ----------------------------
-- Table structure for menu_beverages
-- ----------------------------
DROP TABLE IF EXISTS `menu_beverages`;
CREATE TABLE `menu_beverages`  (
  `daysinweek` int NOT NULL,
  `id_beverage` int NOT NULL,
  `quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`daysinweek`, `id_beverage`) USING BTREE,
  INDEX `id_beverage`(`id_beverage`) USING BTREE,
  CONSTRAINT `id_beverage` FOREIGN KEY (`id_beverage`) REFERENCES `beverages` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_beverages
-- ----------------------------
INSERT INTO `menu_beverages` VALUES (1, 2, 2);
INSERT INTO `menu_beverages` VALUES (1, 4, 10);
INSERT INTO `menu_beverages` VALUES (1, 5, 5);
INSERT INTO `menu_beverages` VALUES (1, 6, 15);
INSERT INTO `menu_beverages` VALUES (1, 7, 1);
INSERT INTO `menu_beverages` VALUES (1, 12, 6);
INSERT INTO `menu_beverages` VALUES (1, 15, 10);
INSERT INTO `menu_beverages` VALUES (1, 17, 2);
INSERT INTO `menu_beverages` VALUES (2, 2, 2);
INSERT INTO `menu_beverages` VALUES (2, 5, 20);
INSERT INTO `menu_beverages` VALUES (2, 6, 10);
INSERT INTO `menu_beverages` VALUES (2, 7, 1);
INSERT INTO `menu_beverages` VALUES (3, 5, 15);
INSERT INTO `menu_beverages` VALUES (3, 8, 12);
INSERT INTO `menu_beverages` VALUES (3, 9, 1);
INSERT INTO `menu_beverages` VALUES (4, 1, 10);
INSERT INTO `menu_beverages` VALUES (4, 2, 12);
INSERT INTO `menu_beverages` VALUES (4, 3, 0);
INSERT INTO `menu_beverages` VALUES (4, 4, 21);
INSERT INTO `menu_beverages` VALUES (4, 14, 3);
INSERT INTO `menu_beverages` VALUES (4, 17, 10);
INSERT INTO `menu_beverages` VALUES (4, 18, 7);
INSERT INTO `menu_beverages` VALUES (5, 10, 6);
INSERT INTO `menu_beverages` VALUES (5, 11, 3);
INSERT INTO `menu_beverages` VALUES (5, 13, 10);
INSERT INTO `menu_beverages` VALUES (5, 15, 4);
INSERT INTO `menu_beverages` VALUES (6, 7, 5);
INSERT INTO `menu_beverages` VALUES (6, 8, 9);
INSERT INTO `menu_beverages` VALUES (6, 9, 9);
INSERT INTO `menu_beverages` VALUES (7, 6, 2);
INSERT INTO `menu_beverages` VALUES (7, 7, 2);
INSERT INTO `menu_beverages` VALUES (7, 8, 1);
INSERT INTO `menu_beverages` VALUES (7, 16, 2);
INSERT INTO `menu_beverages` VALUES (7, 18, 1);

-- ----------------------------
-- Table structure for menu_foods
-- ----------------------------
DROP TABLE IF EXISTS `menu_foods`;
CREATE TABLE `menu_foods`  (
  `daysinweek` int NOT NULL,
  `id_food` int NOT NULL,
  `quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_food`, `daysinweek`) USING BTREE,
  CONSTRAINT `id_food` FOREIGN KEY (`id_food`) REFERENCES `foods` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_foods
-- ----------------------------
INSERT INTO `menu_foods` VALUES (5, 1, 0);
INSERT INTO `menu_foods` VALUES (4, 3, 71);
INSERT INTO `menu_foods` VALUES (5, 4, 0);
INSERT INTO `menu_foods` VALUES (3, 5, 19);
INSERT INTO `menu_foods` VALUES (5, 5, 0);
INSERT INTO `menu_foods` VALUES (2, 6, 5);
INSERT INTO `menu_foods` VALUES (1, 7, 8);
INSERT INTO `menu_foods` VALUES (5, 7, 7);
INSERT INTO `menu_foods` VALUES (1, 8, 9);
INSERT INTO `menu_foods` VALUES (2, 8, 10);
INSERT INTO `menu_foods` VALUES (5, 8, 12);
INSERT INTO `menu_foods` VALUES (6, 8, 1);
INSERT INTO `menu_foods` VALUES (7, 8, 7);
INSERT INTO `menu_foods` VALUES (1, 9, 5);
INSERT INTO `menu_foods` VALUES (2, 9, 1);
INSERT INTO `menu_foods` VALUES (3, 9, 4);
INSERT INTO `menu_foods` VALUES (7, 9, 4);
INSERT INTO `menu_foods` VALUES (3, 10, 20);
INSERT INTO `menu_foods` VALUES (4, 10, 5);
INSERT INTO `menu_foods` VALUES (6, 10, 8);
INSERT INTO `menu_foods` VALUES (3, 11, 10);
INSERT INTO `menu_foods` VALUES (3, 12, 2);
INSERT INTO `menu_foods` VALUES (1, 13, 2);
INSERT INTO `menu_foods` VALUES (4, 13, 20);
INSERT INTO `menu_foods` VALUES (4, 14, 12);
INSERT INTO `menu_foods` VALUES (2, 15, 3);
INSERT INTO `menu_foods` VALUES (6, 16, 5);
INSERT INTO `menu_foods` VALUES (7, 16, 12);
INSERT INTO `menu_foods` VALUES (5, 17, 7);
INSERT INTO `menu_foods` VALUES (6, 17, 8);
INSERT INTO `menu_foods` VALUES (7, 17, 12);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `dateOrder` datetime NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `total` float NULL DEFAULT NULL,
  `IdCustomer` int NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `IdShipper` int NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `IdCustomer`(`IdCustomer`) USING BTREE,
  INDEX `IdShipper`(`IdShipper`) USING BTREE,
  CONSTRAINT `IdCustomer` FOREIGN KEY (`IdCustomer`) REFERENCES `persons` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `IdShipper` FOREIGN KEY (`IdShipper`) REFERENCES `persons` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (6, '2021-11-10 14:18:26', 3, 15000, 10, 1, 20);
INSERT INTO `orders` VALUES (7, '2021-11-27 14:49:46', 4, 3864200, 10, 1, 24);
INSERT INTO `orders` VALUES (8, '2021-11-27 14:59:59', 3, 2502550, 11, 1, 21);
INSERT INTO `orders` VALUES (9, '2021-11-27 15:00:35', 2, 1915200, 12, 1, 19);

-- ----------------------------
-- Table structure for persons
-- ----------------------------
DROP TABLE IF EXISTS `persons`;
CREATE TABLE `persons`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Lastname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Age` int NULL DEFAULT NULL,
  `Address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `Distance` float NULL DEFAULT NULL,
  `Salary` float NULL DEFAULT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Role` int NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE INDEX `Email_UNIQUE`(`Email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persons
-- ----------------------------
INSERT INTO `persons` VALUES (1, 'Duc Thang', 'Duong', '0989053642', 'Male', 21, '', NULL, NULL, 'Thang1407@gmail.com', 'Thang1407', 1);
INSERT INTO `persons` VALUES (3, 'thang1', 'thang', '12345', 'Male', 21, 'thang', 37.7429, NULL, 'thang', '123', 2);
INSERT INTO `persons` VALUES (4, 'thang', 'thang', '1234', 'Female', 29, '', NULL, 10000000, '1234', '1234', 3);
INSERT INTO `persons` VALUES (5, 'ab', 'a', 'a', 'Male', 22, 'a', NULL, NULL, 'a', 'a', 1);
INSERT INTO `persons` VALUES (6, '2', '2', '2', 'Male', 20, '2', 24.3463, NULL, '2', '2', 2);
INSERT INTO `persons` VALUES (7, '34', '3', '3', 'Male', 30, NULL, NULL, 100000, '3', '3', 3);
INSERT INTO `persons` VALUES (9, '8', '8', '8', 'Male', 80, NULL, NULL, NULL, '8', '8', 1);
INSERT INTO `persons` VALUES (10, 'Trung Thành', 'Ninh Phạm', '09036531575', 'Male', 30, '69523 Thi Cầm Spurs, Apt. 853, 90956, Thừa Thiên-Huế, Vermont, Vietnam', 39.0323, NULL, 'ThanhTrung231@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (11, 'Hiếu Đan', 'Nguyễn', '09103648752', 'Other', 30, '5368 Tre Views, Apt. 068, 23110, East Timmothy, Michigan, United States', 10.4273, NULL, 'DanN213@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (12, 'Minh Nguyên', 'Lê', '09875495217', 'Female', 20, '14449 Tiểu My Islands, Apt. 939, 99203, Hậu Giang, Texas, Vietnam', 9.12, NULL, 'MinhNL@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (13, 'Đức Tâm', 'Phạm', '09736547157', 'Female', 28, '8137 Hegmann Freeway, Apt. 668, 98360-2163, Modestomouth, Utah, United States', 3.112, NULL, 'TamPham@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (14, 'Tài Mạnh', 'Phương', '03254894137', 'Other', 50, '6877 Phạm Trail, Suite 580, 30169, Thanh Hoá, New Hampshire, Vietnam', 1, NULL, 'phuongtai@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (15, 'Lê Trọng', 'Đinh', '09654214773', 'Other', 38, '37034 Luettgen Route, Apt. 270, 54808-5961, East Shanon, South Carolina, United States', 2.01, NULL, 'trongdinh@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (16, 'Phương Mạnh', 'Quỳnh', '09731244754', 'Other', 40, '21220 Gabrielle Trail, Apt. 825, 35372, New Clayview, Mississippi, United States', 15.013, NULL, 'quynhmanh@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (17, 'Thanh Đỗ', 'Nguyễn Đỗ', '07989214753', 'Female', 28, '3269 Lý Parkways, Apt. 105, 40059, Yên Bái, Oregon, Vietnam', 10.467, NULL, 'ThanhDo@gmail.com', '1234567', 2);
INSERT INTO `persons` VALUES (18, 'Đinh Mạnh', 'Ninh', '09624757561', 'Male', 50, '', NULL, 10000000, 'manhNinh@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (19, 'My Huyền', 'Đỗ', '07952152486', 'Female', 20, NULL, NULL, 3000000, 'my12@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (20, 'Mạnh Hùng', 'Phan', '01775158963', 'Male', 19, NULL, NULL, 20000000, 'manhHung01@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (21, 'Tiến Thành', 'Đinh', '09893124721', 'Other', 55, NULL, NULL, 50000000, 'thanh1@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (22, 'Minh Chiến ', 'Nguyễn', '09871889917', 'Female', 22, NULL, NULL, 10000000, 'abx@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (23, 'Diệu Huyền', 'Trần', '07817144772', 'Female', 33, NULL, NULL, 15000000, 'huyenDieu@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (24, 'Minh Đức ', 'Trần Tiến', '01392487892', 'Male', 55, NULL, NULL, 2500000, 'vhxuaqn@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (25, 'Đỗ Tiên', 'Nguyễn ', '01688524792', 'Other', 20, NULL, NULL, 1500000, 'Tiendi@gmail.com', '1234567', 3);
INSERT INTO `persons` VALUES (26, 'Đan Hiếu', 'Nguyễn', '09787785217', 'Male', 22, NULL, NULL, NULL, 'Admin01', '1234567', 1);
INSERT INTO `persons` VALUES (27, 'Thành Trung', 'Ninh', '09797417717', 'Male', 22, NULL, NULL, NULL, 'Admin02', '1234567', 1);
INSERT INTO `persons` VALUES (28, 'Đức Thắng', 'Dương', '09798851723', 'Male', 22, NULL, NULL, NULL, 'Admin03', '1234567', 1);
INSERT INTO `persons` VALUES (29, 'Admin', 'Admin', '11111111111', 'Male', 22, NULL, NULL, NULL, 'Admin', '1234567', 1);

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `rating` int NULL DEFAULT NULL,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `IdCustomer` int NULL DEFAULT NULL,
  `IdOrder` int NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `review_persons_Id_fk`(`IdCustomer`) USING BTREE,
  INDEX `reviews_orders_Id_fk`(`IdOrder`) USING BTREE,
  CONSTRAINT `review_persons_Id_fk` FOREIGN KEY (`IdCustomer`) REFERENCES `persons` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `reviews_orders_Id_fk` FOREIGN KEY (`IdOrder`) REFERENCES `orders` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviews
-- ----------------------------
INSERT INTO `reviews` VALUES (2, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 10, 6);

-- ----------------------------
-- Table structure for statusofshippers
-- ----------------------------
DROP TABLE IF EXISTS `statusofshippers`;
CREATE TABLE `statusofshippers`  (
  `IdShipper` int NOT NULL,
  `status` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`IdShipper`) USING BTREE,
  CONSTRAINT `statusofshipper_persons_Id_fk` FOREIGN KEY (`IdShipper`) REFERENCES `persons` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statusofshippers
-- ----------------------------
INSERT INTO `statusofshippers` VALUES (4, 0);
INSERT INTO `statusofshippers` VALUES (7, 0);
INSERT INTO `statusofshippers` VALUES (18, 0);
INSERT INTO `statusofshippers` VALUES (19, 0);
INSERT INTO `statusofshippers` VALUES (20, 0);
INSERT INTO `statusofshippers` VALUES (21, 0);
INSERT INTO `statusofshippers` VALUES (22, 0);
INSERT INTO `statusofshippers` VALUES (23, 0);
INSERT INTO `statusofshippers` VALUES (24, 0);
INSERT INTO `statusofshippers` VALUES (25, 0);

SET FOREIGN_KEY_CHECKS = 1;
