# 🚴‍♂️ BikeTrack - Sistema de Gestión de Entrenamiento Ciclista 🏆

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-5586A4?style=for-the-badge)

## 1️⃣ Descripción General
BikeTrack es una aplicación de escritorio desarrollada en Java con interfaz gráfica (JavaFX o Swing) y una base de datos MySQL para gestionar los entrenamientos de ciclistas. La aplicación permite registrar usuarios, planificar entrenamientos, guardar rutas y analizar el rendimiento de los ciclistas mediante reportes y estadísticas. 📊

## 2️⃣ Objetivos 🎯
✅ **Facilitar el seguimiento del entrenamiento de ciclistas.**  
✅ **Gestionar información sobre rutas y equipamiento.**  
✅ **Registrar el rendimiento en cada sesión de entrenamiento.**  
✅ **Generar informes y gráficos de progreso.** 📈

## 3️⃣ Funcionalidades ⚙️
### 🔹 Módulo de Usuarios (Ciclistas y Entrenadores) 👤
- Registro y gestión de ciclistas.
- Asignación de entrenadores a ciclistas.

### 🔹 Módulo de Entrenamientos 🏋️
- Creación de sesiones de entrenamiento con detalles como fecha, duración, distancia y tipo de entrenamiento (resistencia, velocidad, intervalos, etc.).
- Análisis del progreso con gráficos de rendimiento.

### 🔹 Módulo de Rutas 🗺️
- Registro de rutas con información sobre distancia, dificultad y ubicación.
- Asociación de entrenamientos a rutas específicas.

### 🔹 Módulo de Equipamiento 🚲
- Registro de bicicletas y accesorios de cada ciclista.
- Seguimiento del estado del equipamiento (mantenimiento, reparaciones).

### 🔹 Reportes y Estadísticas 📊
- Generación de reportes sobre entrenamientos, progreso y uso del equipamiento.
- Gráficos de rendimiento basado en datos históricos.

## 4️⃣ Base de Datos (MySQL) 🗄️
### Tablas principales:
#### 📌 Ciclistas 🚴
```sql
id_ciclista (INT, PK, AUTO_INCREMENT)
nombre (VARCHAR)
edad (INT)
peso (DECIMAL)
categoria (VARCHAR)
```
#### 📌 Entrenamientos 🏅
```sql
id_entrenamiento (INT, PK, AUTO_INCREMENT)
id_ciclista (INT, FK)
fecha (DATE)
distancia (DECIMAL)
tiempo (TIME)
tipo_entrenamiento (VARCHAR)
```
#### 📌 Rutas 🗺️
```sql
id_ruta (INT, PK, AUTO_INCREMENT)
nombre (VARCHAR)
distancia (DECIMAL)
dificultad (VARCHAR)
ubicacion (VARCHAR)
```
#### 📌 Equipamiento ⚙️
```sql
id_equipo (INT, PK, AUTO_INCREMENT)
id_ciclista (INT, FK)
tipo (VARCHAR)
marca (VARCHAR)
modelo (VARCHAR)
estado (VARCHAR)
```

### 📌 Usuarios 🔑
```sql
id_usuario (INT, PK, AUTO_INCREMENT)
id_ciclista (INT, FK)
usuario (VARCHAR UNIQUE)
password (VARCHAR)
```
## 5️⃣ Tecnologías a Utilizar 🖥️
✅ **Java (Swing o JavaFX)** - Para la interfaz gráfica.  
✅ **MySQL** - Para la base de datos.  
✅ **JDBC** - Para la conexión con la base de datos.  
✅ **JasperReports (Opcional)** - Para generación de reportes en PDF.  

## 📌 Extras que puedes agregar: 🎁
✔ Exportación de datos en **CSV o PDF**. 📄  
✔ Sistema de autenticación con **roles (ciclistas y entrenadores)**. 🔑  
✔ Integración con **APIs para obtener datos climáticos en entrenamientos**. 🌦️  

