-- Crear base de datos
CREATE DATABASE BikeTrackDB;

-- Seleccionar la base de datos
USE BikeTrackDB;

-- Tabla Ciclistas
CREATE TABLE Usuario (
    usuario INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(12),
    nombre VARCHAR(100),
    edad INT,
    peso DECIMAL(5,2),
    categoria enum("competitivo", "principante", "aficionado"),
    carga int,
    estado enum("sobreentrenamiento", "mantenimiento", "perdida", "pico de forma",  "recuperacion")
);

-- Tabla Rutas
CREATE TABLE rutas(
    ruta INT AUTO_INCREMENT PRIMARY KEY,
    nomobre varchar(255),
    usuario INT,
    distancia DECIMAL(5,2),
    dificultad VARCHAR(50),
    ubicacion VARCHAR(100),
    tiempo time,
    velocidadMedia DECIMAL(5,2),
    FOREIGN KEY (usuario) REFERENCES Usuario(usuario)
);

-- Tabla Equipamiento
CREATE TABLE Equipamiento (
    equipo INT AUTO_INCREMENT PRIMARY KEY,
    usuario INT,
    peso INT,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    estado enum("nuevo", "seminuevo", "viejo", "retirado"),
    FOREIGN KEY (usuario) REFERENCES Usuario(usuario)
);
