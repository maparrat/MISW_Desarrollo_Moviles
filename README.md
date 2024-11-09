# MISW_Desarrollo_Moviles
Este proyecto es parte de la asignatura Ingeniería de Software para Aplicaciones Móviles de la Maestría en Ingeniería de Software (MISO) de la Universidad de los Andes. A continuación, se describen los contenidos desarrollados en el marco de esta actividad:
- [🙋‍♂️ Integrantes del equipo](#Integrantes)
- [💻 Instalacion](#Instalacion)
- [💻 Ejecucion de la aplicacion](#Ejecucion-de-la-aplicacion)
- [💻 Ejecucion de las pruebas](#Ejecucion-de-las-pruebas)

---
# Integrantes
| Nombre                 | Correo                       |
|------------------------|------------------------------|
| 👨‍💻 Miguel Parra | [ma.parrat@uniandes.edu.co](mailto:ma.parrat@uniandes.edu.co)|
| 👨‍💻 Carlos Alfredo Rios | [c.riosp@uniandes.edu.co](mailto:c.riosp@uniandes.edu.co)|
| 👩‍💻 Jessica Daniela Páez Jiménez | [jd.paezj1@uniandes.edu.co](mailto:jd.paezj1@uniandes.edu.co) |
| 👨‍💻 Lucas Blandón Pulido | [l.blandon@uniandes.edu.co](mailto:l.blandon@uniandes.edu.co) |

---
# Instalacion

1. Descarga e instala la ultima versión de [Android Studio](https://developer.android.com/studio).
2. Abre android studio.
3. Escoge un proyecto en blanco.
4. Para ejecutar la aplicación, es necesario contar con un dispositivo móvil, ya sea un dispositivo físico con Android o un simulador. A continuación, se detallan las instrucciones para ambos casos:
   ## Simulador
   - En la parte izquierda del Android Studio, haz click en "Device Manager"
     
      ![image](https://github.com/user-attachments/assets/ea11ab97-eb78-411f-8eb5-19622eba6879)
     
   - Agrega un nuevo dispositivo, haz click en el botón (+), posteriormente en "Create Virtual Device"
     
     ![image](https://github.com/user-attachments/assets/57ea1c23-d1eb-4ecc-8589-af51b19c4367)
     
     ![image](https://github.com/user-attachments/assets/f694c6ea-9ce5-4c42-8c5b-2d4b7436167e)

   - Escoge el dispositivo a simular, luego haz click en "Next"
     
     ![image](https://github.com/user-attachments/assets/a54a655e-470a-43fd-b106-d4bb96976a19)
     
   - Descarga la versión de software, luego click en "Next"
     
     ![image](https://github.com/user-attachments/assets/c03d882f-4437-463c-b6cf-295431fe0e72)

   - Agrega la configuración del dispositivo y por ultimo, haz click en "Finish".
     
     ![image](https://github.com/user-attachments/assets/a9098b09-d262-4815-bf92-5fd3b7063a9b)

   - En "Device Manager" podrás visualizar el dispositivo configurado previamente
  
     ![image](https://github.com/user-attachments/assets/5f87a5a6-6f5b-45c2-8090-56f46e88ee24)
     
   - Para utilizar el dispositivo añadido, en la parte susperior cambia la opcion defaul "Medium Phone API 35" por el que se configuró anteriormente.

     ![image](https://github.com/user-attachments/assets/97429d91-0ae6-4436-b5e0-c190f933d07f)

   ## Dispositivo Fisico
   - En tu dispositivo movil, ve a configuracion, activa el modo desarrollador y permisos de Debug Usb (este proceso varia dependiendo del celular).
     
     ![DeveloperOptions](https://github.com/user-attachments/assets/dc1cf204-126f-449e-ae14-53eef086e506)
     
     ![DeveloperOptions2](https://github.com/user-attachments/assets/462d40de-72a9-459d-a949-90902f9e89a2)
     
     ![DeveloperOptions3](https://github.com/user-attachments/assets/c3bdca67-e85a-4c0d-bf11-4c34cb65104c)

   - Conecta tu dispositivo a tu PC.
   - Android Studio te detectará tu dispositivo conectado y aparecerá en "Device Manager"

  ---
# Ejecucion de la aplicacion

Se recomienda tener instalado git en el pc para realizar el siguiente paso

- Clona el repositorio desde [github](https://github.com/maparrat/MISW_Desarrollo_Moviles).
- Abre el Android Studio, si tienes abierto un proyecto en la parte superior izquierda, haz click en "File", luego "Close Folder". Android Studio se cerrará, abrelo nuevamente.
- Selecciona la opcion "Open" y busca la carpeta donde clonaste el proyecto, haz click en "Ok".
  
  ![image](https://github.com/user-attachments/assets/a02cdb08-c57a-40c6-9c4d-924abf541de9)
  
- Selecciona el dispositivo que agregaste en el tutorial de instalación, asegurate tener activo la opcion "app" y haz click en "Run".
  
  ![image](https://github.com/user-attachments/assets/5b7b59e3-88b4-4de5-976d-f9625f4c0ba0).
  
- Una vez que se compile la aplicación, verás el menu principal.
  
  ![image](https://github.com/user-attachments/assets/36e88df8-5189-4638-8752-534a60928459)

  ![MainMenu](https://github.com/user-attachments/assets/b1dc0753-cf03-4033-85e5-c60e3bb5e66d)

#  Ejecucion de las pruebas

- Abre el proyecto en Android Studio.
- Ubica la carpeta "kotlin + java", posteriormente, en la segunda carpeta titulada "com.example.vinilos (androidTest)", se encuentran dos archivos que contienen los tests, abre uno de ellos.
  
  ![image](https://github.com/user-attachments/assets/cd764e48-0d4f-40b0-9eb0-037fc05af242)

- Si deseas ejecutar todos los tests de la clase lo puedes hacer haciendo click en las flechas que se encuentran en la parte izquierda de la clase, luego selecciona la opcion "Run". Este boton activará todas las pruebas y las ejecutará.

  ![image](https://github.com/user-attachments/assets/b75a84d0-985c-45af-9cab-5bf0b315756b)

  ![image](https://github.com/user-attachments/assets/a255d5f4-22a0-4272-aa4b-8657f2208b67)

- Una vez ejecutes todas las pruebas, en la parte inferior tendrás un reporte de las pruebas ejectutas.
  
  ![image](https://github.com/user-attachments/assets/bfb6883e-2c29-4557-8a8e-44529db33244)

- Si deseas ejecutar una prueba en especifico, ubica la funcion que deseas ejecutar y en el boton de la parte izquierda haz click, luego selecciona la opcion "Run",  la prueba empezará a ejecutarse.

  ![image](https://github.com/user-attachments/assets/5cce8146-dd7d-44bd-9cd6-75f991933b81)

- Una vez que se termine de ejecturar, el reporte solo tendrá respuesta de la prueba que ejecutarse.

  ![image](https://github.com/user-attachments/assets/a09333ab-a3e8-4afd-aa93-c3b0143e0d4d)






     





