# Challenge - Travel Optimization API

Este proyecto es una API RESTful desarrollada en Spring Boot para la optimización de viajes entre estaciones. Permite registrar estaciones y caminos, y calcular el camino más barato entre dos estaciones basándose en el costo.

---

## Características
- Registro de estaciones.
- Registro de caminos bidireccionales con un costo específico.
- Cálculo del camino más barato entre dos estaciones.
- Almacenamiento de datos en memoria (no se requiere una base de datos externa).
- Dockerización para portabilidad.
- Pruebas de integración para asegurar la funcionalidad.

---

## Requisitos previos

Antes de ejecutar este proyecto, asegúrate de tener instalado:
- [Java 17+](https://adoptium.net/temurin/releases/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/) (opcional, para ejecutar con Docker)

---

## Configuración del Proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Fondi99/challenge-travel-api.git
   cd challenge-travel-api

---

## Endpoints

### **1. Registrar una estación**
Registra una nueva estación con un nombre único.

- **Método**: `PUT`
- **URL**: `/stations/{station_id}`
- **Parámetros de URL**:
   - `station_id` (long): ID único de la estación a registrar.
- **Cuerpo de la solicitud**:
  ```json
  {
    "name": "Nombre de la estación"
  }

### **2. Registrar un camino**
Registra una nueva estación con un nombre único.

- **Método**: `PUT`
- **URL**: `/paths/{path_id}`
- **Parámetros de URL**:
   - `path_id` (long): ID único del camino a registrar.
- **Cuerpo de la solicitud**:
  ```json
    
  {
  "source_id": 1,
  "destination_id": 2,
  "cost": 10.5
  }

### **3. Obtener el camino más barato**
Calcula el camino más barato entre dos estaciones.

- **Método**: `GET`
- **URL**: `/paths/{source_id}/{destination_id}`
- **Parámetros de URL**:
   - `source_id` (long): ID de la estación de origen.
   - `destination_id` (long): ID de la estación de destino.
