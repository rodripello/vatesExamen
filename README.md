# Documentacion para utilizacion de la API "Clima"

La aplicacion provee un servicio para poder consultar informacion del clima de una determinada ciudad en donde se debe pasar dos parametros de entradas


# Utilizacion del servicio

Para poder consumir el servicio se debe clonar el repositorio, correr la aplicacion desde algun IDE y una vez arriba la APP se puede consumir la aplicacion desde un swagger mediante la siguiente URL:

http://localhost:8080/swagger-ui.html

![image](https://user-images.githubusercontent.com/33293432/228846111-a66fc9ef-00f4-4bf3-abe5-d717c7a6de4f.png)

parametros: 

* codigoPais: AR
* ciudad: Salta


# Pruebas Unitarias

se cuenta tambien con un par de pruebas unitarias donde se verifica los servicios consumidos desde AccuWeather App asi como tambien la persistencia de la informacion en una base de datos que se extrae de dicha API

# Tecnologias utilizadas

   * JAVA 1.8
   * Spring Boot
   * Maven
   * Base de datos Relacional H2
   * Junit 
   * Swagger
