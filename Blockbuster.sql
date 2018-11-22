-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.22-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura para tabla videoclub2.carritos
CREATE TABLE IF NOT EXISTS `carritos` (
  `idCarrito` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `total` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`idCarrito`),
  KEY `FK_carritos_usuarios` (`idUsuario`),
  CONSTRAINT `FK_carritos_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.carritos: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `carritos` DISABLE KEYS */;
INSERT INTO `carritos` (`idCarrito`, `idUsuario`, `total`) VALUES
	(42, 1, 525.21),
	(43, 1, 45.98);
/*!40000 ALTER TABLE `carritos` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.carrito_producto
CREATE TABLE IF NOT EXISTS `carrito_producto` (
  `idCarritoProducto` int(11) NOT NULL AUTO_INCREMENT,
  `idCarrito` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `tipo_producto` int(11) NOT NULL,
  `tipo_compra` int(11) NOT NULL,
  PRIMARY KEY (`idCarritoProducto`),
  KEY `FK__carritos` (`idCarrito`),
  KEY `FK__productos` (`idProducto`),
  CONSTRAINT `FK__carritos` FOREIGN KEY (`idCarrito`) REFERENCES `carritos` (`idCarrito`),
  CONSTRAINT `FK__productos` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.carrito_producto: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `carrito_producto` DISABLE KEYS */;
INSERT INTO `carrito_producto` (`idCarritoProducto`, `idCarrito`, `idProducto`, `subtotal`, `cantidad`, `tipo_producto`, `tipo_compra`) VALUES
	(72, 42, 9, 500.34, 1, 2, 1),
	(73, 42, 1, 24.87, 1, 1, 2),
	(74, 43, 2, 45.98, 1, 1, 1);
/*!40000 ALTER TABLE `carrito_producto` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(50) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.categorias: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`idCategoria`, `nombre_categoria`, `descripcion`) VALUES
	(1, 'Drama', 'asdasd'),
	(2, 'Suspenso', 'asfsd'),
	(3, 'Musical', 'as'),
	(4, 'Terror', 'dfg'),
	(5, 'Comedia', 'sdf'),
	(6, 'Amor', 'sdf');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.compras
CREATE TABLE IF NOT EXISTS `compras` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `total_compra` double NOT NULL,
  `idDatosPago` int(11) NOT NULL,
  `fecha_compra` date NOT NULL,
  `fecha_entrega` date DEFAULT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `FK__usuarios` (`idUsuario`),
  KEY `FK_compras_datos_pago` (`idDatosPago`),
  CONSTRAINT `FK__usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`),
  CONSTRAINT `FK_compras_datos_pago` FOREIGN KEY (`idDatosPago`) REFERENCES `datos_pago` (`idDatosPago`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.compras: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` (`idCompra`, `idUsuario`, `total_compra`, `idDatosPago`, `fecha_compra`, `fecha_entrega`) VALUES
	(1, 1, 34.12, 5, '2018-11-20', '2018-12-20'),
	(2, 1, 525.21, 6, '2018-11-20', '2018-12-20'),
	(3, 1, 45.98, 7, '2018-11-20', '2018-12-20');
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.datos_pago
CREATE TABLE IF NOT EXISTS `datos_pago` (
  `idDatosPago` int(11) NOT NULL AUTO_INCREMENT,
  `tarjeta` varchar(50) NOT NULL,
  `fecha_expiracion` date DEFAULT NULL,
  `codigo_esguridad` varchar(50) NOT NULL,
  PRIMARY KEY (`idDatosPago`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.datos_pago: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `datos_pago` DISABLE KEYS */;
INSERT INTO `datos_pago` (`idDatosPago`, `tarjeta`, `fecha_expiracion`, `codigo_esguridad`) VALUES
	(5, 'yghjg', NULL, 'hjakjdjk'),
	(6, 'hsgdf', NULL, 'ahjsv'),
	(7, 'sdsd', NULL, 'sfsdf');
/*!40000 ALTER TABLE `datos_pago` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.detalle_compra
CREATE TABLE IF NOT EXISTS `detalle_compra` (
  `idDetalleCompra` int(11) NOT NULL AUTO_INCREMENT,
  `idProducto` int(11) NOT NULL,
  `idCompra` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `cantidad` int(11) DEFAULT '1',
  PRIMARY KEY (`idDetalleCompra`),
  KEY `FK_detalle_compra_compras` (`idCompra`),
  KEY `FK_detalle_compra_productos` (`idProducto`),
  CONSTRAINT `FK_detalle_compra_compras` FOREIGN KEY (`idCompra`) REFERENCES `compras` (`idCompra`),
  CONSTRAINT `FK_detalle_compra_productos` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.detalle_compra: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detalle_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_compra` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.detalle_renta
CREATE TABLE IF NOT EXISTS `detalle_renta` (
  `idDetalleRenta` int(11) NOT NULL AUTO_INCREMENT,
  `idProducto` int(11) NOT NULL,
  `idRenta` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDetalleRenta`),
  KEY `FK__rentas` (`idRenta`),
  KEY `FK_detalle_renta_productos` (`idProducto`),
  CONSTRAINT `FK__rentas` FOREIGN KEY (`idRenta`) REFERENCES `rentas` (`idRenta`),
  CONSTRAINT `FK_detalle_renta_productos` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.detalle_renta: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detalle_renta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_renta` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.peliculas
CREATE TABLE IF NOT EXISTS `peliculas` (
  `idPelicula` int(11) NOT NULL AUTO_INCREMENT,
  `idProducto` int(11) NOT NULL,
  `director` varchar(50) NOT NULL,
  `duracion` varchar(15) NOT NULL,
  `clasificacion` varchar(15) NOT NULL,
  `cantidad_almacen` int(11) NOT NULL,
  `cantidad_renta` int(11) NOT NULL,
  `precio_compra` double NOT NULL,
  `precio_renta` double NOT NULL,
  PRIMARY KEY (`idPelicula`),
  KEY `FK_peliculas_productos` (`idProducto`),
  CONSTRAINT `FK_peliculas_productos` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.peliculas: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` (`idPelicula`, `idProducto`, `director`, `duracion`, `clasificacion`, `cantidad_almacen`, `cantidad_renta`, `precio_compra`, `precio_renta`) VALUES
	(1, 1, 'Kenneth Branagh', '2:50:13', 'PG-13', 13, 11233, 24.87, 34.87),
	(2, 2, 'Francis Ford Coppola', '2:10:48', 'B-15', 32, 12234, 45.98, 23.12),
	(3, 3, 'Damien Chazelle', '1:59:12', 'PG-13', 23, 34, 34.89, 12.9),
	(4, 4, 'Scott Derrickson', '1:54:12', 'B', 33, 45, 12.45, 23.54),
	(5, 5, 'William Friedkin', '1:54:34', 'B', 34, 12, 56.98, 45.2),
	(6, 6, 'Glenn Ficarra', '2:34:12', 'A', 21, 52, 12.34, 1.12),
	(7, 7, 'John Carney', '2:12:34', 'A', 45, 12, 45.22, 12.4),
	(8, 8, 'Rawson Marshall Thurber', '1:45:23', 'B', 34, 20, 34.12, 12.5),
	(9, 16, 'Drake Doremus', '1:54:34', 'PG-13', 23, 23, 80.23, 34.6);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `idCategoria` int(11) NOT NULL,
  `tipo` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `sinopsis` varchar(255) NOT NULL,
  `año` varchar(4) NOT NULL,
  `rating` double NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `FK_producto_categorias` (`idCategoria`),
  CONSTRAINT `FK_producto_categorias` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.productos: ~16 rows (aproximadamente)
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` (`idProducto`, `idCategoria`, `tipo`, `titulo`, `sinopsis`, `año`, `rating`) VALUES
	(1, 1, 1, 'Thor', 'El dios (Chris Hemsworth) Norse es expulsado de Asgard y es obligado a vivir entre los humanos en la Tierra después de que sus acciones imprudentes reiniciaron una guerra antigua.', '2011', 7),
	(2, 2, 1, 'El Padrino', 'Una adaptación ganadora del Premio de la Academia, de la novela de Mario Puzo acerca de la familia Corleone.', '1972', 9.3),
	(3, 3, 1, 'La La Land', 'Sebastian, un pianista de jazz, y Mia, una aspirante a actriz, se enamoran locamente; pero la ambición desmedida que tienen por triunfar en sus respectivas carreras, en una ciudad como Los Ángeles', '2016', 8.1),
	(4, 4, 1, 'Siniestro', 'Ellison Oswald (Ethan Hawke) escritor de historias criminales está en una mala racha; no ha tenido un éxito en más de 10 años y está desesperado. Cuando descubre una película que muestra las muertes de una familia, él promete resolver el misterio.', '2012', 8.4),
	(5, 4, 1, 'El Exorcista', 'Una actriz llama a unos sacerdotes jesuitas para que intenten terminar con la posesión demoníaca de su hija de 12 años.', '2001', 8.6),
	(6, 5, 1, 'Loco y Estupido Amor', 'Cal Weaver está viviendo el sueño americano. Él tiene un buen trabajo, una casa preciosa, niños excelentes y una esposa hermosa, llamada Emily. Sin embargo, la aparente vida perfecta de Cal se derrumba.', '2011', 7.4),
	(7, 3, 1, 'Sing Street', 'En el Dublín de los años 80, un joven de una familia con problemas entra a una escuela nueva y forma un grupo de rock para intentar encajar y atraer a una chica.', '2016', 8),
	(8, 5, 1, 'Somos los Miller', 'El distribuidor de marihuana David aprende por las malas que ninguna buena acción queda sin castigo; él intenta ayudar a algunos jóvenes, y es sorprendido por unos criminales, perdiendo su dinero y producto. ', '2013', 9.3),
	(9, 1, 2, 'Game of Thrones', 'Esta serie, basada en los libros de George R.R. Martin, muestra la competencia entre familias nobles de siete reinos de Westeros, cuya finalidad es ganar el control sobre el Trono de Hierro.', '2011', 9.5),
	(10, 4, 2, 'The Walking Dead', 'Tras estar en estado de coma, el policía Rick Grimes descubre que una enfermedad originó un apocalipsis zombi. Rick liderará un grupo de sobrevivientes para buscar un lugar seguro, pero las luchas más peligrosas surgirán entre ellos mismos.', '2010', 9.4),
	(11, 1, 2, 'Breaking Bad', 'Walter White un profesor de química de secundaria agobiado por problemas económicos para sostener a su familia y con un cáncer terminal, toma una decisión para ganar dinero y transforma un viejo vehículo en un laboratorio de metanfetaminas rodante.', '2008', 9.6),
	(12, 2, 2, 'Maniac', 'Maniac es una miniserie estadounidense de comedia negra basada en la serie de televisión noruega del mismo nombre de Hakon Bast Mossige y Espen PA Lervaag que se estrenó el 21 de septiembre de 2018 en Netflix.', '2018', 9.4),
	(13, 2, 2, 'Dark', 'La desaparición de dos niños muestra los vínculos entre cuatro familias y expone el pasado de una pequeña ciudad.', '2017', 9.6),
	(14, 5, 2, 'Skins', 'Las vidas de un grupo de adolescentes en Bristol, Inglaterra son seguidas a lo largo de dos años.', '2007', 9.4),
	(15, 1, 2, 'Black Mirror', 'Black Mirror explora en sus tres capítulos el lado oscuro de la era tecnológica en la que vivimos: la paranoia de ser vigilados como en un panóptico los usos terroristas de las nuevas herramientas y su relación con la experiencia cotidiana.', '2011', 9.4),
	(16, 6, 1, 'Like Crazy', 'Una joven mujer (Felicity Jones) británica y su amante (Anton Yelchin) estadounidense luchan con una relación a larga distancia después de que ella es expulsada de Estados Unidos por superar la vigencia de su visa.', '2011', 7.4);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.rentas
CREATE TABLE IF NOT EXISTS `rentas` (
  `idRenta` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `fecha_renta` date NOT NULL,
  `fecha_devolucion` date NOT NULL,
  `idDatosPago` int(11) NOT NULL,
  `fecha_entrega` date NOT NULL,
  `total_renta` double NOT NULL,
  PRIMARY KEY (`idRenta`),
  KEY `FK_rentas_usuarios` (`idUsuario`),
  KEY `FK_rentas_datos_pago` (`idDatosPago`),
  CONSTRAINT `FK_rentas_datos_pago` FOREIGN KEY (`idDatosPago`) REFERENCES `datos_pago` (`idDatosPago`),
  CONSTRAINT `FK_rentas_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.rentas: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `rentas` DISABLE KEYS */;
INSERT INTO `rentas` (`idRenta`, `idUsuario`, `fecha_renta`, `fecha_devolucion`, `idDatosPago`, `fecha_entrega`, `total_renta`) VALUES
	(1, 1, '2018-11-20', '2018-12-30', 6, '2018-12-20', 525.21);
/*!40000 ALTER TABLE `rentas` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.series
CREATE TABLE IF NOT EXISTS `series` (
  `idSerie` int(11) NOT NULL AUTO_INCREMENT,
  `idProducto` int(11) NOT NULL,
  `numero_temporadas` int(11) NOT NULL,
  `precio_compra` double NOT NULL,
  `estudio` varchar(50) NOT NULL,
  `cantidad_almacen` int(11) NOT NULL,
  PRIMARY KEY (`idSerie`),
  KEY `FK_series_productos` (`idProducto`),
  CONSTRAINT `FK_series_productos` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.series: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
INSERT INTO `series` (`idSerie`, `idProducto`, `numero_temporadas`, `precio_compra`, `estudio`, `cantidad_almacen`) VALUES
	(1, 15, 6, 160.54, 'Netflix', 29),
	(2, 11, 7, 200.56, 'FOX', 234),
	(3, 13, 1, 150.34, 'Netflix', 23),
	(4, 9, 8, 500.34, 'HBO', 53),
	(5, 12, 1, 200.34, 'Netflix', 20),
	(6, 14, 8, 300.35, 'A4', 2),
	(7, 10, 8, 460.34, 'FOX', 31);
/*!40000 ALTER TABLE `series` ENABLE KEYS */;

-- Volcando estructura para tabla videoclub2.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `correo` varchar(50) NOT NULL,
  `contraseña` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidoP` varchar(50) NOT NULL,
  `apellidoM` varchar(50) NOT NULL,
  `calle` varchar(50) NOT NULL,
  `colonia` varchar(50) NOT NULL,
  `municipio` varchar(50) NOT NULL,
  `cp` varchar(50) NOT NULL,
  `celular` varchar(50) NOT NULL,
  `telefono_fijo` varchar(50) NOT NULL,
  `no_int` int(11) NOT NULL,
  `no_ext` int(11) NOT NULL,
  `rol` varchar(50) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla videoclub2.usuarios: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`idUsuario`, `correo`, `contraseña`, `nombre`, `apellidoP`, `apellidoM`, `calle`, `colonia`, `municipio`, `cp`, `celular`, `telefono_fijo`, `no_int`, `no_ext`, `rol`) VALUES
	(1, 'aaronfrank_16@hotmail.com', 'IRONmaiden', 'Aaron', 'Gonzalez', 'Mejia', 'Hermenegildo Galeana', 'Barrio del Calvario', 'Zinacantepec', '51350', '7224150069', '2418071', 304, 23, 'comprador'),
	(2, 'osky@hotmail.com', 'IRONmaiden', 'Osky', 'Vladimir', 'Chokosky', 'Jesus Carranza', 'Barrio de la Cruz', 'Toluca', '51235', '7224563787', '2546738', 34, 34, 'comprador'),
	(3, 'jorge@hotmail.com', 'IRONmaiden', 'Jorge', 'Garcia', 'Martinez', 'Miguel Hidalgo', 'La Nueva Oxtotitlan', 'Toluca', '54879', '722763848', '74658374', 23, 24, 'comprador');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
