# 📍 Unfound - App

**Unfound** es una aplicación móvil diseñada para quienes desean descubrir nuevos lugares recreativos de manera espontánea. Su objetivo es ofrecer recomendaciones únicas y personalizadas, utilizando la ubicación actual del usuario para presentar lugares cercanos que podrían ser de interés, desde parques y cafeterías hasta museos y miradores. La aplicación se enfoca en la autenticidad y en brindar experiencias nuevas que pueden ser compartidas en redes sociales.

## 📖 Descripción

**Unfound** es la aplicación ideal para quienes buscan romper la rutina y vivir experiencias únicas. Con una interfaz intuitiva y basada en la ubicación actual del usuario, ofrece recomendaciones de lugares recreativos únicos y personalizados. Desde cafeterías y parques hasta puntos turísticos, Unfound asegura que cada experiencia sea inesperada y emocionante.

## 🔗 Servicios

Unfound hace uso de varios servicios externos para proporcionar autenticación segura y acceso a datos en tiempo real. A continuación, se listan los servicios empleados y su rol en la app:

- **Firebase Authentication**: Gestiona la autenticación de usuarios en la aplicación, permitiendo a cada usuario acceder a su cuenta personal de manera segura. Este servicio asegura que cada sesión esté protegida y que los datos del usuario se mantengan privados y vinculados a una cuenta específica.
  
- **Firebase Realtime Database**: Utilizado para almacenar y sincronizar los datos de usuario, como preferencias de lugares y el historial de recomendaciones. Permite que los usuarios puedan acceder a sus preferencias personalizadas y que los datos se mantengan actualizados y sincronizados en tiempo real.

- **Nearby Search de Google Places API**: Este servicio de Google permite a Unfound realizar búsquedas de lugares cercanos a la ubicación del usuario en tiempo real. Utilizando la funcionalidad de Nearby Search, la app puede filtrar lugares por tipo (como “cafetería” o “parque”) y devolver una lista de opciones en función de la proximidad y relevancia.

- **Place Details de Google Places API**: Ofrece información adicional sobre cada lugar recomendado, incluyendo nombre, dirección, calificaciones, horarios y más. Esta funcionalidad mejora la experiencia del usuario, brindándole detalles útiles para decidir si quiere visitar el lugar sugerido.

## 📚 Librerías

Para desarrollar y optimizar el rendimiento de Unfound, se utilizan varias librerías externas:

- **Jetpack Compose**: Herramienta principal para el desarrollo de la interfaz de usuario, proporcionando una forma declarativa de crear una UI moderna, interactiva y eficiente.

- **Retrofit**: Utilizado para realizar llamadas a APIs de manera eficiente. Retrofit facilita el manejo de las solicitudes HTTP y es fundamental para realizar las llamadas a los servicios de Google Places y Firebase.

- **Coil**: Biblioteca para la carga y visualización de imágenes. Al integrarse con la API de Google Places, permite cargar y mostrar imágenes de los lugares recomendados, proporcionando una experiencia visual rápida y fluida.

- **Room Database**: Utilizado para el almacenamiento de datos localmente en el dispositivo del usuario, permitiendo acceder a las preferencias e historial de recomendaciones cuando la conexión a internet no está disponible. Room asegura que los datos críticos de usuario se mantengan accesibles en todo momento.

- **Kotlin Serialization**: Esta librería permite manejar la serialización y deserialización de datos en formato JSON, simplificando el intercambio de información con las APIs externas de Google y Firebase.

- **Navigation Component de Jetpack Compose**: Gestiona la navegación entre las diferentes pantallas de la aplicación, garantizando que los usuarios tengan una experiencia fluida y coherente al desplazarse por la app.
