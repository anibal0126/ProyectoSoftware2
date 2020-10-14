
--
-- Base de datos: `reservacasarural`
--
CREATE DATABASE IF NOT EXISTS `reservacasarural` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `reservacasarural`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casarural`
--

CREATE TABLE `casarural` (
  `codigo` int(11) NOT NULL,
  `poblacion` varchar(45) DEFAULT NULL,
  `noDormitorios` varchar(45) DEFAULT NULL,
  `noCocinas` varchar(45) DEFAULT NULL,
  `noBaños` varchar(45) DEFAULT NULL,
  `noComedores` varchar(45) DEFAULT NULL,
  `noPlazas` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `Propietario_cedula` varchar(100) NOT NULL,
  `estado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `casarural`
--

INSERT INTO `casarural` (`codigo`, `poblacion`, `noDormitorios`, `noCocinas`, `noBaños`, `noComedores`, `noPlazas`, `descripcion`, `Propietario_cedula`, `estado`) VALUES
(4, 'sasa', '2', '3', '4', '5', '6', 'fff', '1', 'Activo'),
(17, 'hh', '6', '6', '6', '6', '6', '2', '1', 'null'),
(18, 'jjjjj', '8', '9', '9', '9', '9', '9', '1', 'null'),
(19, '777', '7', '78', '7', '7', '7', '7', '1', 'null'),
(20, '777', '1', '2', '3', '4', '5', '6', '1', 'null'),
(21, 'Armenia', '1', '1', '1', '1', '1', 'Muy Buena', '1', 'null'),
(22, 'Armenia', '1', '1', '1', '1', '1', 'Muy Buena', '1', 'null');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cocina`
--

CREATE TABLE `cocina` (
  `codigo` int(11) NOT NULL,
  `tieneLavajillas` varchar(45) DEFAULT NULL,
  `tieneLavadora` varchar(45) DEFAULT NULL,
  `CasaRural_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cocina`
--

INSERT INTO `cocina` (`codigo`, `tieneLavajillas`, `tieneLavadora`, `CasaRural_codigo`) VALUES
(1, '1', '1', 4),
(2, '1', '1', 4),
(3, '1', '1', 4),
(4, '1', '1', 17),
(5, '1', '1', 17),
(6, '1', '1', 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disponibildadcasa`
--

CREATE TABLE `disponibildadcasa` (
  `idDisponibildadCasa` int(11) NOT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `CasaRural_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dormitorio`
--

CREATE TABLE `dormitorio` (
  `codigo` int(11) NOT NULL,
  `noCamasDobles` varchar(45) DEFAULT NULL,
  `noCamasSencillas` varchar(45) DEFAULT NULL,
  `precio` varchar(45) DEFAULT NULL,
  `CasaRural_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `dormitorio`
--

INSERT INTO `dormitorio` (`codigo`, `noCamasDobles`, `noCamasSencillas`, `precio`, `CasaRural_codigo`) VALUES
(1, '3', '2', NULL, 4),
(2, '9', '8', NULL, 4),
(3, '3', '3', NULL, 17),
(4, '1', '1', NULL, 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquete`
--

CREATE TABLE `paquete` (
  `idPaquete` int(11) NOT NULL,
  `periodo` varchar(45) DEFAULT NULL,
  `precio` varchar(45) DEFAULT NULL,
  `paqueteEntero` varchar(45) DEFAULT NULL,
  `soloDormitorios` varchar(45) DEFAULT NULL,
  `CasaRural_codigo` int(11) NOT NULL,
  `Reserva_noReserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `cedula` varchar(100) NOT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  `cuentaCorriente` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`cedula`, `usuario`, `clave`, `cuentaCorriente`) VALUES
('1', 'German', '12345', '12345678901');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `noReserva` int(11) NOT NULL,
  `fechaInicioReserva` varchar(50) DEFAULT NULL,
  `estadoPago` varchar(45) DEFAULT NULL,
  `casaEntera` varchar(45) DEFAULT NULL,
  `precio` varchar(45) DEFAULT NULL,
  `estadoReserva` varchar(45) DEFAULT NULL,
  `Usuario_cedula` int(11) NOT NULL,
  `fechaFinReserva` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`noReserva`, `fechaInicioReserva`, `estadoPago`, `casaEntera`, `precio`, `estadoReserva`, `Usuario_cedula`, `fechaFinReserva`) VALUES
(1, 'yyy', 'yy', '1', '777', 'Reservada', 1, 'yy');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservad`
--

CREATE TABLE `reservad` (
  `codigo` int(11) NOT NULL,
  `importe` varchar(45) DEFAULT NULL,
  `Reserva_noReserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `cedula` int(11) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `nombreCompleto` varchar(255) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`cedula`, `telefono`, `nombreCompleto`, `direccion`) VALUES
(1, '123', 'Germin G', 'Armenia');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `casarural`
--
ALTER TABLE `casarural`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fk_CasaRural_Propietario1_idx` (`Propietario_cedula`);

--
-- Indices de la tabla `cocina`
--
ALTER TABLE `cocina`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fk_Cocina_CasaRural_idx` (`CasaRural_codigo`);

--
-- Indices de la tabla `disponibildadcasa`
--
ALTER TABLE `disponibildadcasa`
  ADD PRIMARY KEY (`idDisponibildadCasa`),
  ADD KEY `fk_DisponibildadCasa_CasaRural1_idx` (`CasaRural_codigo`);

--
-- Indices de la tabla `dormitorio`
--
ALTER TABLE `dormitorio`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fk_Dormitorio_CasaRural1_idx` (`CasaRural_codigo`);

--
-- Indices de la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`idPaquete`),
  ADD KEY `fk_Paquete_CasaRural1_idx` (`CasaRural_codigo`),
  ADD KEY `fk_Paquete_Reserva1_idx` (`Reserva_noReserva`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`noReserva`),
  ADD KEY `fk_Reserva_Usuario1_idx` (`Usuario_cedula`);

--
-- Indices de la tabla `reservad`
--
ALTER TABLE `reservad`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fk_ReservaD_Reserva1_idx` (`Reserva_noReserva`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cedula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `casarural`
--
ALTER TABLE `casarural`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `cocina`
--
ALTER TABLE `cocina`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `disponibildadcasa`
--
ALTER TABLE `disponibildadcasa`
  MODIFY `idDisponibildadCasa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dormitorio`
--
ALTER TABLE `dormitorio`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `paquete`
--
ALTER TABLE `paquete`
  MODIFY `idPaquete` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `noReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `casarural`
--
ALTER TABLE `casarural`
  ADD CONSTRAINT `fk_CasaRural_Propietario1` FOREIGN KEY (`Propietario_cedula`) REFERENCES `propietario` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cocina`
--
ALTER TABLE `cocina`
  ADD CONSTRAINT `fk_Cocina_CasaRural` FOREIGN KEY (`CasaRural_codigo`) REFERENCES `casarural` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `disponibildadcasa`
--
ALTER TABLE `disponibildadcasa`
  ADD CONSTRAINT `fk_DisponibildadCasa_CasaRural1` FOREIGN KEY (`CasaRural_codigo`) REFERENCES `casarural` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `dormitorio`
--
ALTER TABLE `dormitorio`
  ADD CONSTRAINT `fk_Dormitorio_CasaRural1` FOREIGN KEY (`CasaRural_codigo`) REFERENCES `casarural` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD CONSTRAINT `fk_Paquete_CasaRural1` FOREIGN KEY (`CasaRural_codigo`) REFERENCES `casarural` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Paquete_Reserva1` FOREIGN KEY (`Reserva_noReserva`) REFERENCES `reserva` (`noReserva`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk_Reserva_Usuario1` FOREIGN KEY (`Usuario_cedula`) REFERENCES `usuario` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reservad`
--
ALTER TABLE `reservad`
  ADD CONSTRAINT `fk_ReservaD_Reserva1` FOREIGN KEY (`Reserva_noReserva`) REFERENCES `reserva` (`noReserva`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

