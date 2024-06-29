-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 28-06-2024 a las 19:06:05
-- Versión del servidor: 11.3.2-MariaDB
-- Versión de PHP: 8.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `fablab_asistencia`
--

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS fablab_asistencia;

-- Usar la base de datos recién creada
USE fablab_asistencia;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adm_user`
--

CREATE TABLE `adm_user` (
  `id` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `adm_user`
--

INSERT INTO `adm_user` (`id`, `password`, `id_persona`) VALUES
(1, 'admin', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aula`
--

CREATE TABLE `aula` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `aula`
--

INSERT INTO `aula` (`id`, `nombre`) VALUES
(1, 'aulaaa'),
(4, 'asd'),
(5, 'asd'),
(6, 'sadasasa'),
(7, 'curso'),
(8, 'pepe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colegio`
--

CREATE TABLE `colegio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `tel_contacto` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `colegio`
--

INSERT INTO `colegio` (`id`, `nombre`, `direccion`, `tel_contacto`) VALUES
(3, 'colegio 1', 'sds', '3333333');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`id`, `nombre`) VALUES
(2, '123'),
(3, 'asdas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingreso_salida_personal`
--

CREATE TABLE `ingreso_salida_personal` (
  `id` int(11) NOT NULL,
  `hora` time NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_registro` date NOT NULL,
  `entrada_salida` tinyint(1) NOT NULL,
  `id_practicante` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `ingreso_salida_personal`
--

INSERT INTO `ingreso_salida_personal` (`id`, `hora`, `fecha_ingreso`, `fecha_registro`, `entrada_salida`, `id_practicante`) VALUES
(1, '08:00:00', '2024-06-27', '2024-06-27', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motivo_visita`
--

CREATE TABLE `motivo_visita` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `motivo_visita`
--

INSERT INTO `motivo_visita` (`id`, `nombre`) VALUES
(1, 'grabacion'),
(2, 'curso'),
(3, 'curso a colegios'),
(4, 'steam school'),
(5, 'steam young'),
(6, 'socializacion'),
(7, 'clase'),
(8, 'socializacion colegio'),
(9, 'semillero'),
(10, 'grabacion'),
(11, 'sustentacion proyecto de grado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `documento` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `id_tipo_usuario` int(11) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `id_poblacion_especial` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `apellido`, `documento`, `telefono`, `codigo`, `id_tipo_usuario`, `fecha_nacimiento`, `sexo`, `id_poblacion_especial`) VALUES
(2, 'nombre editado', 'apellido editado', '31544', '31544', 'admin', 4, '2024-06-11', 'M', 1),
(3, 'nombre 2', 'apellidoo', '0001', '31544', '1151111', 4, '2024-06-12', 'M', 1),
(4, 'nombre 3', 'apellidoo', '0002', '31544', '1151111', 4, '2024-06-12', 'M', 1),
(19, 'pepe', 'panchez', '60395681', '123', '1155909', 5, '2024-06-18', 'F', 1),
(20, 'sasad', 'sadssda', '1090502416', 'saasdads', 'sdaddsa', 4, '2024-06-09', 'F', 1),
(21, 'sadsa', 'adsdas', '213123', 'asdsa', 'sadas', 4, '2024-06-11', 'F', 1),
(22, 'asdsa', 'asdsa', '1232', 'asdsa', 'sadas', 4, '2024-06-04', 'F', 1),
(23, 'dasdas', 'sddsfd', '12321', 'dfdsf', 'fsdfsd', 4, '2024-06-12', 'F', 1),
(24, 'asdas', 'sadasd', '21321321', 'asdsa', 'sadasd', 4, '2024-06-10', 'F', 1),
(25, 'sdsads', 'sadsa', '213123223', 'sadds', 'sadsa', 5, '2024-06-16', 'F', 1),
(26, 'adsasd', 'asdsad', '4234322', 'asdas', 'sad', 4, '2024-06-03', 'M', 1),
(27, 'asdsad', 'sadasdsa', '1234', '3213213', '2312', 4, '2024-06-10', 'M', 1),
(28, 'asdsad', 'sadasdsa', '1234', '3213213', '2312', 4, '2024-06-10', 'M', 1),
(29, 'saaaaaaa', 'sadsa', '123434444', '123', 'sadas', 4, '2024-06-24', 'M', 1),
(30, 'pepe', 'asdsa', 'jhk', 'jkhkj', 'jhkjh', 4, '2024-06-24', 'M', 1),
(31, 'pepe ', 'nuevo', '0003', '12323', '1232', 4, '2024-06-12', 'M', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poblacion_especial`
--

CREATE TABLE `poblacion_especial` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `poblacion_especial`
--

INSERT INTO `poblacion_especial` (`id`, `nombre`) VALUES
(1, 'Ninguna');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `practicante_pasante_beca`
--

CREATE TABLE `practicante_pasante_beca` (
  `id` int(11) NOT NULL,
  `id_carnet` int(11) NOT NULL,
  `semestre` varchar(6) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `practicante_pasante_beca`
--

INSERT INTO `practicante_pasante_beca` (`id`, `id_carnet`, `semestre`, `estado`, `id_persona`) VALUES
(1, 123, '123', 1, 31),
(2, 222, '1', 0, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `programa_academico`
--

CREATE TABLE `programa_academico` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `programa_academico`
--

INSERT INTO `programa_academico` (`id`, `nombre`, `descripcion`) VALUES
(2, 'programa', 'test');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `semillero`
--

CREATE TABLE `semillero` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `sigla` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `semillero`
--

INSERT INTO `semillero` (`id`, `nombre`, `sigla`) VALUES
(2, 'test test', 'tt');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `steam_student`
--

CREATE TABLE `steam_student` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `id_poblacion_especial` int(11) DEFAULT NULL,
  `semestre` varchar(10) NOT NULL,
  `id_colegio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `steam_student`
--

INSERT INTO `steam_student` (`id`, `nombre`, `sexo`, `id_poblacion_especial`, `semestre`, `id_colegio`) VALUES
(4, 'juan', 'M', 1, '2024-1', 3),
(5, 'juan', 'M', 1, '2024-1', 3),
(6, 'pepe', 'M', 1, '2024-1', 3),
(7, 'pepe', 'M', 1, '2024-1', 3),
(8, 'pancha', 'M', 1, '12312', 3),
(9, 'pepe', 'M', 1, 'wasdsdasda', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id`, `tipo`) VALUES
(1, 'Administrativo'),
(2, 'Docente'),
(3, 'Docente Investigador'),
(4, 'Estudiante'),
(5, 'Graduado'),
(6, 'Independiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `universidad`
--

CREATE TABLE `universidad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `tel_contacto` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `universidad`
--

INSERT INTO `universidad` (`id`, `nombre`, `direccion`, `tel_contacto`) VALUES
(2, 'test', 'test', '123'),
(3, 'asdaassds', 'asdasdas', '123213'),
(5, 'asdaassds', 'asdasdas', '123213'),
(7, 'algo8', 'otro', '000'),
(9, 'qweerty', '122', '12'),
(11, 'otro', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_clase`
--

CREATE TABLE `visita_clase` (
  `id` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_aula` int(11) DEFAULT NULL,
  `id_universidad` int(11) DEFAULT NULL,
  `id_programa_academico` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  `otro_programa` varchar(50) DEFAULT NULL,
  `otro_universidad` varchar(50) DEFAULT NULL,
  `codigo_carrera` varchar(50) NOT NULL,
  `nombre_materia` varchar(50) NOT NULL,
  `nombre_docente` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `visita_clase`
--

INSERT INTO `visita_clase` (`id`, `id_persona`, `id_aula`, `id_universidad`, `id_programa_academico`, `fecha`, `otro_programa`, `otro_universidad`, `codigo_carrera`, `nombre_materia`, `nombre_docente`) VALUES
(5, 19, 1, 2, 2, '2024-06-03', NULL, NULL, '233', '34434', '22332'),
(6, 20, 1, 2, NULL, '2024-06-16', 'sasd', NULL, 'sasdasad', 'sadsadsad', 'saadssadasd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_curso`
--

CREATE TABLE `visita_curso` (
  `id` int(11) NOT NULL,
  `id_universidad` int(11) NOT NULL,
  `fecha_visita` date NOT NULL,
  `id_persona` int(11) NOT NULL,
  `sesion` int(11) NOT NULL,
  `id_programa_academico` int(11) DEFAULT NULL,
  `id_curso` int(11) NOT NULL,
  `otro_programa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `visita_curso`
--

INSERT INTO `visita_curso` (`id`, `id_universidad`, `fecha_visita`, `id_persona`, `sesion`, `id_programa_academico`, `id_curso`, `otro_programa`) VALUES
(2, 2, '2024-06-16', 22, 1, NULL, 2, 'sasd'),
(3, 2, '2024-06-10', 29, 2, 2, 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_curso_colegio`
--

CREATE TABLE `visita_curso_colegio` (
  `id` int(11) NOT NULL,
  `fecha_visita` date NOT NULL,
  `id_colegio` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `sesion` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `visita_curso_colegio`
--

INSERT INTO `visita_curso_colegio` (`id`, `fecha_visita`, `id_colegio`, `id_persona`, `sesion`, `id_curso`) VALUES
(1, '2024-06-17', 3, 23, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_grabacion`
--

CREATE TABLE `visita_grabacion` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_semillero` int(11) DEFAULT NULL,
  `id_universidad` int(11) NOT NULL,
  `id_programa_academico` int(11) DEFAULT NULL,
  `externo` varchar(50) DEFAULT NULL,
  `otro_programa` varchar(50) DEFAULT NULL,
  `razon_grabacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `visita_grabacion`
--

INSERT INTO `visita_grabacion` (`id`, `fecha`, `id_persona`, `id_semillero`, `id_universidad`, `id_programa_academico`, `externo`, `otro_programa`, `razon_grabacion`) VALUES
(1, '2024-06-12', 2, NULL, 2, 2, 'externo', 'otroprograma', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_semillero`
--

CREATE TABLE `visita_semillero` (
  `id` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_semillero` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  `id_universidad` int(11) NOT NULL,
  `id_programa_academico` int(11) DEFAULT NULL,
  `otro_programa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `visita_semillero`
--

INSERT INTO `visita_semillero` (`id`, `id_persona`, `id_semillero`, `fecha`, `id_universidad`, `id_programa_academico`, `otro_programa`) VALUES
(1, 24, 2, '2024-06-03', 2, 2, NULL),
(2, 26, 2, '2024-06-10', 2, 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_socializacion`
--

CREATE TABLE `visita_socializacion` (
  `id` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_universidad` int(11) NOT NULL,
  `id_programa_academico` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  `otro_programa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_socializacion_colegio`
--

CREATE TABLE `visita_socializacion_colegio` (
  `id` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_colegio` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_steam_school`
--

CREATE TABLE `visita_steam_school` (
  `id` int(11) NOT NULL,
  `id_steam_student` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  `id_colegio` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `visita_steam_school`
--

INSERT INTO `visita_steam_school` (`id`, `id_steam_student`, `id_curso`, `id_colegio`, `fecha`) VALUES
(4, 4, 3, 3, '2024-06-12'),
(5, 5, 3, 3, '2024-06-12'),
(6, 6, 3, 3, '2024-06-12'),
(7, 7, 3, 3, '2024-06-12'),
(8, 8, 2, 3, '2024-06-20'),
(9, 9, 2, 3, '2024-06-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_steam_young`
--

CREATE TABLE `visita_steam_young` (
  `id` int(11) NOT NULL,
  `id_steam_student` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `id_colegio` int(11) DEFAULT NULL,
  `id_curso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita_sustentacion_proyecto_grado`
--

CREATE TABLE `visita_sustentacion_proyecto_grado` (
  `id` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_universidad` int(11) NOT NULL,
  `id_programa_academico` int(11) DEFAULT NULL,
  `otro_programa` varchar(50) DEFAULT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `adm_user`
--
ALTER TABLE `adm_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`);

--
-- Indices de la tabla `aula`
--
ALTER TABLE `aula`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `colegio`
--
ALTER TABLE `colegio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ingreso_salida_personal`
--
ALTER TABLE `ingreso_salida_personal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ingreso_salida_personal_ibfk_1` (`id_practicante`);

--
-- Indices de la tabla `motivo_visita`
--
ALTER TABLE `motivo_visita`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tipo usuario` (`id_tipo_usuario`),
  ADD KEY `id_poblacion_especial` (`id_poblacion_especial`);

--
-- Indices de la tabla `poblacion_especial`
--
ALTER TABLE `poblacion_especial`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `practicante_pasante_beca`
--
ALTER TABLE `practicante_pasante_beca`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`);

--
-- Indices de la tabla `programa_academico`
--
ALTER TABLE `programa_academico`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `semillero`
--
ALTER TABLE `semillero`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `steam_student`
--
ALTER TABLE `steam_student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_poblacion_especial` (`id_poblacion_especial`),
  ADD KEY `id_colegio` (`id_colegio`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `universidad`
--
ALTER TABLE `universidad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `visita_clase`
--
ALTER TABLE `visita_clase`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_aula` (`id_aula`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `id_programa_academico` (`id_programa_academico`),
  ADD KEY `id_universidad` (`id_universidad`);

--
-- Indices de la tabla `visita_curso`
--
ALTER TABLE `visita_curso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `visita_curso_ibfk_1` (`id_universidad`),
  ADD KEY `visita_curso_ibfk_2` (`id_curso`),
  ADD KEY `id_programa_academico` (`id_programa_academico`),
  ADD KEY `id_persona` (`id_persona`);

--
-- Indices de la tabla `visita_curso_colegio`
--
ALTER TABLE `visita_curso_colegio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_colegio` (`id_colegio`),
  ADD KEY `id_curso` (`id_curso`),
  ADD KEY `visita_curso_colegio_ibfk_3` (`id_persona`);

--
-- Indices de la tabla `visita_grabacion`
--
ALTER TABLE `visita_grabacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `id_programa_academico` (`id_programa_academico`),
  ADD KEY `id_semillero` (`id_semillero`),
  ADD KEY `id_universidad` (`id_universidad`);

--
-- Indices de la tabla `visita_semillero`
--
ALTER TABLE `visita_semillero`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `id_programa_academico` (`id_programa_academico`),
  ADD KEY `id_semillero` (`id_semillero`),
  ADD KEY `id_universidad` (`id_universidad`);

--
-- Indices de la tabla `visita_socializacion`
--
ALTER TABLE `visita_socializacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `id_programa_academico` (`id_programa_academico`),
  ADD KEY `id_universidad` (`id_universidad`);

--
-- Indices de la tabla `visita_socializacion_colegio`
--
ALTER TABLE `visita_socializacion_colegio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `id_colegio` (`id_colegio`);

--
-- Indices de la tabla `visita_steam_school`
--
ALTER TABLE `visita_steam_school`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_colegio` (`id_colegio`),
  ADD KEY `id_curso` (`id_curso`),
  ADD KEY `id_steam_student` (`id_steam_student`);

--
-- Indices de la tabla `visita_steam_young`
--
ALTER TABLE `visita_steam_young`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_colegio` (`id_colegio`),
  ADD KEY `id_curso` (`id_curso`),
  ADD KEY `id_steam_student` (`id_steam_student`);

--
-- Indices de la tabla `visita_sustentacion_proyecto_grado`
--
ALTER TABLE `visita_sustentacion_proyecto_grado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `id_programa_academico` (`id_programa_academico`),
  ADD KEY `id_universidad` (`id_universidad`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `adm_user`
--
ALTER TABLE `adm_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `aula`
--
ALTER TABLE `aula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `colegio`
--
ALTER TABLE `colegio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ingreso_salida_personal`
--
ALTER TABLE `ingreso_salida_personal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `motivo_visita`
--
ALTER TABLE `motivo_visita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `poblacion_especial`
--
ALTER TABLE `poblacion_especial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `practicante_pasante_beca`
--
ALTER TABLE `practicante_pasante_beca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `programa_academico`
--
ALTER TABLE `programa_academico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `semillero`
--
ALTER TABLE `semillero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `steam_student`
--
ALTER TABLE `steam_student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `universidad`
--
ALTER TABLE `universidad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `visita_clase`
--
ALTER TABLE `visita_clase`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `visita_curso`
--
ALTER TABLE `visita_curso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `visita_curso_colegio`
--
ALTER TABLE `visita_curso_colegio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `visita_grabacion`
--
ALTER TABLE `visita_grabacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `visita_semillero`
--
ALTER TABLE `visita_semillero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `visita_socializacion`
--
ALTER TABLE `visita_socializacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `visita_socializacion_colegio`
--
ALTER TABLE `visita_socializacion_colegio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `visita_steam_school`
--
ALTER TABLE `visita_steam_school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `visita_steam_young`
--
ALTER TABLE `visita_steam_young`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `visita_sustentacion_proyecto_grado`
--
ALTER TABLE `visita_sustentacion_proyecto_grado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `adm_user`
--
ALTER TABLE `adm_user`
  ADD CONSTRAINT `adm_user_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ingreso_salida_personal`
--
ALTER TABLE `ingreso_salida_personal`
  ADD CONSTRAINT `ingreso_salida_personal_ibfk_1` FOREIGN KEY (`id_practicante`) REFERENCES `practicante_pasante_beca` (`id`);

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`id_poblacion_especial`) REFERENCES `poblacion_especial` (`id`),
  ADD CONSTRAINT `tipo usuario` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`id`);

--
-- Filtros para la tabla `practicante_pasante_beca`
--
ALTER TABLE `practicante_pasante_beca`
  ADD CONSTRAINT `practicante_pasante_beca_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `steam_student`
--
ALTER TABLE `steam_student`
  ADD CONSTRAINT `steam_student_ibfk_1` FOREIGN KEY (`id_poblacion_especial`) REFERENCES `poblacion_especial` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `steam_student_ibfk_2` FOREIGN KEY (`id_colegio`) REFERENCES `colegio` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Filtros para la tabla `visita_clase`
--
ALTER TABLE `visita_clase`
  ADD CONSTRAINT `visita_clase_ibfk_1` FOREIGN KEY (`id_aula`) REFERENCES `aula` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `visita_clase_ibfk_2` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_clase_ibfk_3` FOREIGN KEY (`id_programa_academico`) REFERENCES `programa_academico` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `visita_clase_ibfk_4` FOREIGN KEY (`id_universidad`) REFERENCES `universidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_curso`
--
ALTER TABLE `visita_curso`
  ADD CONSTRAINT `visita_curso_ibfk_1` FOREIGN KEY (`id_universidad`) REFERENCES `universidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_curso_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_curso_ibfk_3` FOREIGN KEY (`id_programa_academico`) REFERENCES `programa_academico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_curso_ibfk_4` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_curso_colegio`
--
ALTER TABLE `visita_curso_colegio`
  ADD CONSTRAINT `visita_curso_colegio_ibfk_1` FOREIGN KEY (`id_colegio`) REFERENCES `colegio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_curso_colegio_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_curso_colegio_ibfk_3` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_grabacion`
--
ALTER TABLE `visita_grabacion`
  ADD CONSTRAINT `visita_grabacion_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_grabacion_ibfk_2` FOREIGN KEY (`id_programa_academico`) REFERENCES `programa_academico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_grabacion_ibfk_3` FOREIGN KEY (`id_semillero`) REFERENCES `semillero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_grabacion_ibfk_4` FOREIGN KEY (`id_universidad`) REFERENCES `universidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_semillero`
--
ALTER TABLE `visita_semillero`
  ADD CONSTRAINT `visita_semillero_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_semillero_ibfk_2` FOREIGN KEY (`id_programa_academico`) REFERENCES `programa_academico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_semillero_ibfk_3` FOREIGN KEY (`id_semillero`) REFERENCES `semillero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_semillero_ibfk_4` FOREIGN KEY (`id_universidad`) REFERENCES `universidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_socializacion`
--
ALTER TABLE `visita_socializacion`
  ADD CONSTRAINT `visita_socializacion_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_socializacion_ibfk_2` FOREIGN KEY (`id_programa_academico`) REFERENCES `programa_academico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_socializacion_ibfk_3` FOREIGN KEY (`id_universidad`) REFERENCES `universidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_socializacion_colegio`
--
ALTER TABLE `visita_socializacion_colegio`
  ADD CONSTRAINT `visita_socializacion_colegio_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_socializacion_colegio_ibfk_2` FOREIGN KEY (`id_colegio`) REFERENCES `colegio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_steam_school`
--
ALTER TABLE `visita_steam_school`
  ADD CONSTRAINT `visita_steam_school_ibfk_1` FOREIGN KEY (`id_colegio`) REFERENCES `colegio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_steam_school_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_steam_school_ibfk_3` FOREIGN KEY (`id_steam_student`) REFERENCES `steam_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_steam_young`
--
ALTER TABLE `visita_steam_young`
  ADD CONSTRAINT `visita_steam_young_ibfk_1` FOREIGN KEY (`id_colegio`) REFERENCES `colegio` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `visita_steam_young_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_steam_young_ibfk_3` FOREIGN KEY (`id_steam_student`) REFERENCES `steam_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visita_sustentacion_proyecto_grado`
--
ALTER TABLE `visita_sustentacion_proyecto_grado`
  ADD CONSTRAINT `visita_sustentacion_proyecto_grado_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_sustentacion_proyecto_grado_ibfk_2` FOREIGN KEY (`id_programa_academico`) REFERENCES `programa_academico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visita_sustentacion_proyecto_grado_ibfk_3` FOREIGN KEY (`id_universidad`) REFERENCES `universidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
