Unfound - APP


## Descripción de la App

Es una aplicación diseñada para aquellos que buscan salir de la rutina diaria y valoran la exploración y la espontaneidad.
Está dirigida a personas que a menudo se sienten abrumadas por la gran cantidad de opciones disponibles a la hora de elegir lugares para comer. 

Esta app es ideal para quienes buscan nuevas experiencias y desean enriquecer sus vidas con aventuras emocionantes, ya sea disfrutando de lugares
únicos o compartiendo momentos especiales en sus redes sociales. Con recomendaciones aleatorias, **Unfound** transforma cada salida en una 
oportunidad para descubrir algo inesperado y único.

Para enriquecer la experiencia del usuario, **Unfound** utiliza varios servicios externos que proporcionan autenticación y datos 
en tiempo real. A continuación, se detallan los servicios y sus roles en la aplicación:


1. **Autenticación**: La app empleará un servicio de autenticación en línea para gestionar el inicio de sesión de los usuarios, asegurando que
 cada sesión esté protegida y vinculada a una cuenta única.

2. **Nearby Search de Google Cloud**: A través de la **API de Google Cloud**, se implementa **Nearby Search** para obtener recomendaciones precisas
 y localizadas de lugares cercanos al usuario. Este servicio permite que **Unfound** proporcione datos de ubicaciones específicas en tiempo real, considerando
 la posición actual del usuario, el tipo de lugar y la relevancia de las recomendaciones.

4. **Datos de Lugares y Recomendaciones**: La aplicación realizará llamadas a una API de lugares adicional para obtener información complementaria
 de ubicaciones cercanas, ayudando a crear recomendaciones más diversas y actualizadas.

5. **API Fake para Datos Adicionales**: En caso de no encontrar una API específica para ciertos datos (como el tipo de cocina, rango de precios, o
  características del lugar), **Unfound** implementará una "API fake" o utilizará una base de datos local para simular estas respuestas y mantener la funcionalidad completa de la app.

### Base de Datos Local

Para mejorar la velocidad y asegurar la disponibilidad de ciertos datos sin conexión, **Unfound** incluirá una base de datos local. Esta base
de datos almacenará información relevante, como datos de usuario, historial de recomendaciones, y configuraciones personales, permitiendo una experiencia más fluida.

### Librerías Externas

Para optimizar el desarrollo y el rendimiento de la aplicación, **Unfound** emplea varias librerías externas:

1. **Jetpack Compose**:
   - **`androidx.compose.*`**: Utilizado para construir la interfaz de usuario de manera declarativa, optimizando el flujo de desarrollo y permitiendo una experiencia de usuario moderna y receptiva.
   
2. **Coil**:
   - **`Coil`**: Librería para cargar imágenes desde internet de manera eficiente, ideal para mostrar fotos de los lugares recomendados de forma rápida y con caché integrado.

3. **Mapbox Compose**:
   - **`maps.compose`**: Se usa para integrar mapas interactivos en la app, permitiendo al usuario visualizar ubicaciones de manera dinámica y detallada.

4. **Kotlin Serialization**:
   - **`kotlinx.serialization.json`**: Permite manejar la serialización y deserialización de datos JSON, facilitando el intercambio de información con APIs externas.

5. **Navigation**:
   - **`androidx.compose.navigation`**: Librería para gestionar la navegación entre pantallas en la app, garantizando una experiencia fluida y estructurada para el usuario.

