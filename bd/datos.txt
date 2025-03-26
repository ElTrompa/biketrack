-- Insertar ciclistas con el id_ciclista explícito
INSERT INTO Ciclistas (id_ciclista, nombre, edad, peso, categoria) VALUES 
(1, 'Carlos Sánchez', 28, 70.5, 'Competitivo'),
(2, 'Ana Pérez', 24, 58.2, 'Aficionada'),
(3, 'Luis Gómez', 30, 80.0, 'Competitivo'),
(4, 'Marta Díaz', 26, 62.3, 'Principiante'),
(5, 'Javier Ruiz', 35, 85.5, 'Aficionado'),
(6, 'Sofia López', 29, 67.0, 'Aficionada'),
(7, 'Carlos Jiménez', 32, 75.3, 'Competitivo'),
(8, 'Paula García', 25, 64.1, 'Principiante'),
(9, 'Felipe Martín', 40, 90.0, 'Aficionado'),
(10, 'Raquel Torres', 27, 63.7, 'Competitivo'),
(11, 'David Herrera', 33, 78.4, 'Aficionado'),
(12, 'Elena Mendoza', 22, 55.8, 'Aficionada'),
(13, 'Ricardo Romero', 31, 72.9, 'Competitivo'),
(14, 'Pedro González', 34, 82.0, 'Principiante'),
(15, 'Julia Sánchez', 23, 60.5, 'Competitivo'),
(16, 'Antonio Pérez', 29, 79.2, 'Aficionado'),
(17, 'Luis Rodríguez', 38, 88.5, 'Competitivo'),
(18, 'Santiago Ruiz', 25, 76.0, 'Aficionado'),
(19, 'Cristina Díaz', 30, 68.9, 'Aficionada'),
(20, 'José Martínez', 36, 81.0, 'Principiante'),
(21, 'Laura Sánchez', 28, 66.4, 'Aficionada'),
(22, 'Iván Fernández', 33, 77.5, 'Competitivo'),
(23, 'Raúl Ortega', 24, 70.2, 'Principiante'),
(24, 'Sandra Pérez', 26, 65.0, 'Aficionada'),
(25, 'Víctor López', 31, 74.3, 'Competitivo');

-- Insertar entrenamientos
INSERT INTO Entrenamientos (id_ciclista, fecha, distancia, tiempo, tipo_entrenamiento) VALUES
(1, '2025-03-01', 45.5, '01:30:00', 'Resistencia'),
(1, '2025-03-04', 32.0, '01:00:00', 'Velocidad'),
(1, '2025-03-10', 55.3, '02:00:00', 'Resistencia'),
(2, '2025-03-02', 20.0, '00:50:00', 'Intervalos'),
(2, '2025-03-06', 28.5, '01:10:00', 'Velocidad'),
(3, '2025-03-03', 40.0, '01:20:00', 'Resistencia'),
(3, '2025-03-07', 30.0, '00:55:00', 'Intervalos'),
(4, '2025-03-05', 25.0, '00:45:00', 'Resistencia'),
(4, '2025-03-08', 33.5, '01:05:00', 'Velocidad'),
(5, '2025-03-02', 50.0, '02:00:00', 'Resistencia'),
(6, '2025-03-01', 18.0, '00:40:00', 'Velocidad'),
(7, '2025-03-09', 35.5, '01:25:00', 'Intervalos'),
(8, '2025-03-10', 28.5, '01:00:00', 'Resistencia'),
(9, '2025-03-04', 48.0, '01:50:00', 'Velocidad'),
(10, '2025-03-03', 38.0, '01:15:00', 'Intervalos');

-- Insertar rutas
INSERT INTO Rutas (id_ciclista, nombre, distancia, dificultad, ubicacion) VALUES
(1, 'Ruta del Río', 50.5, 'Alta', 'Madrid'),
(2, 'Ruta de Montaña', 25.3, 'Media', 'Barcelona'),
(3, 'Ruta Planicie', 40.0, 'Baja', 'Valencia'),
(4, 'Ruta Costera', 30.0, 'Media', 'Alicante'),
(5, 'Ruta del Bosque', 60.5, 'Alta', 'Girona'),
(6, 'Ruta de Ciudad', 20.5, 'Baja', 'Sevilla'),
(7, 'Ruta del Valle', 45.0, 'Alta', 'Madrid'),
(8, 'Ruta de la Laguna', 35.5, 'Media', 'Málaga'),
(9, 'Ruta del Puente', 28.0, 'Baja', 'Zaragoza'),
(10, 'Ruta de los Montes', 50.0, 'Alta', 'Badajoz');

-- Insertar equipamiento
INSERT INTO Equipamiento (id_ciclista, tipo, marca, modelo, estado) VALUES
(1, 'Bicicleta', 'Trek', 'Emonda', 'Nuevo'),
(2, 'Bicicleta', 'Specialized', 'Roubaix', 'Usado'),
(3, 'Bicicleta', 'Giant', 'Defy', 'Nuevo'),
(4, 'Bicicleta', 'Cannondale', 'Synapse', 'Usado'),
(5, 'Bicicleta', 'BMC', 'Timemachine', 'Nuevo'),
(6, 'Bicicleta', 'Scott', 'Addict', 'Nuevo'),
(7, 'Bicicleta', 'Pinarello', 'Dogma F12', 'Usado'),
(8, 'Bicicleta', 'Cervélo', 'R5', 'Nuevo'),
(9, 'Bicicleta', 'Orbea', 'Orca', 'Nuevo'),
(10, 'Bicicleta', 'Merida', 'Scultura', 'Usado');

-- Insertar usuarios
INSERT INTO Usuarios (id_ciclista, usuario, password) VALUES
(1, 'carlos123', '1234'),
(2, 'ana4567', '5678'),
(3, 'luis7890', '7890'),
(4, 'marta2345', '2345'),
(5, 'javier9876', '9876'),
(6, 'sofia3456', '3456'),
(7, 'carlos1111', '1111'),
(8, 'paula6543', '6543'),
(9, 'felipe1122', '1122'),
(10, 'raquel3333', '3333');