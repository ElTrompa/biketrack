-- Crear base de datos
CREATE DATABASE IF NOT EXISTS BikeTrackDB;

-- Seleccionar la base de datos
USE BikeTrackDB;

-- Tabla Usuario (Ciclistas)
CREATE TABLE IF NOT EXISTS Usuario (
    usuario INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(12),
    nombre VARCHAR(100),
    edad INT,
    peso DECIMAL(5,2),
    categoria ENUM('competitivo', 'principiante', 'aficionado'),
    carga INT,
    estado ENUM('sobreentrenamiento', 'mantenimiento', 'perdida', 'pico de forma', 'recuperacion')
);

-- Tabla Bicicleta
CREATE TABLE IF NOT EXISTS Bicicleta (
    equipo INT AUTO_INCREMENT PRIMARY KEY,
    usuario INT,
    peso DOUBLE,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    estado ENUM('nuevo', 'seminuevo', 'viejo', 'retirado'),
    FOREIGN KEY (usuario) REFERENCES Usuario(usuario) ON DELETE CASCADE
);

-- Tabla Rutas
CREATE TABLE IF NOT EXISTS Rutas (
    ruta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    usuario INT,
    distancia DECIMAL(7,2),
    dificultad VARCHAR(50),
    ubicacion VARCHAR(100),
    tiempo TIME,
    velocidadMedia DECIMAL(5,2),
    FOREIGN KEY (usuario) REFERENCES Usuario(usuario) ON DELETE CASCADE
);

-- Nueva tabla para almacenar archivos GPX relacionados con rutas y bicicletas
CREATE TABLE IF NOT EXISTS GPXArchivo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ruta INT,
    bicicleta INT,
    archivo LONGBLOB,
    fecha_subida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ruta) REFERENCES Rutas(ruta) ON DELETE CASCADE,
    FOREIGN KEY (bicicleta) REFERENCES Bicicleta(equipo) ON DELETE CASCADE
);

