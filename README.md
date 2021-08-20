# Correr en local con docker
IMPORTANTE: Para correr en local solicitarme por favor 
la URI de la base de datos y de la caché.
 1. Hacer clean de los jars viejos (RECOMENDADO)
    ~~~
    gradle :modulolectura:clean
    ~~~
 2. Hacer el build para crear el jar
    ~~~
    gradle :modulolectura:build
    ~~~
    
 3. ~~~ 
     cd modulolectura 
     ~~~

 4. ~~~
    sudo docker build .
    ~~~
    
    ~~~
    sudo docker run -it --rm -p8086:8086 [iddelcontenedor]
    ~~~

----------------------------------------------------------
1. Hacer clean de los jars viejos (RECOMENDADO)
     ~~~
      gradle :moduloescritura:clean
     ~~~
   
2. Hacer el build para crear el jar
    ~~~
   gradle :moduloescritura:build
   ~~~
   
3. ~~~ 
   cd moduloescritura
   ~~~
   
4. ~~~
   sudo docker build .
   ~~~
   
5. ~~~
    sudo docker run -it --rm -p8080:8080 [iddelcontenedor]
    ~~~

# Correr pruebas unitarias y generar reporte de coverage
~~~
gradle :modulolectura:test
gradle :modulolectura:jacocoTestReport
~~~

~~~
gradle :moduloescritura:test
gradle :moduloescritura:jacocoTestReport
~~~
Una vez generados los reportes de ambos microservicios se puede ir
dentro de cada uno a la carpeta:
~~~
build/jacocoHtml/index.html
~~~

# Correr pruebas de carga
IMPORTANTE: Es recomendable correr las pruebas de carga cuando
la arquitectura de nube esté prendida para poder ver en acción
todo el potencial de la arquitectura planteada.

1. Instalar JMeter
2. Importar el archivo 'Plan de Pruebas.jmx'
3. Seleccionar el número de usuarios (hilos deseados) y dale run
4. Ver reporte

# Conceptos aplicados
- CQRS
- Clean Architecture
- Cache con REDIS
- Pool de hilos para la base de datos (provisto por el framework)
- Arquitectura de microservicios con desacoplamiento a nivel de servicio del módulo de lectura y escritura.
- Escalamiento vertical a nivel de infraestructura con Azure Kubernetes
- Load Balancing en Azure
- Deployment en la nube de Azure
- Test unitarios
- Test de carga
- Ahead-of-time compilation (Provisto por el framework)

# Peticiones en local
- Consultar las estadisticas de mutantes y humanos:
~~~

curl -i -k --location --request GET 'http://localhost:8086/mutantchecker/queries/stats' \
--data-raw ''
~~~

- Verificar si un humano es mutante:
~~~

curl -i -k --location --request POST 'http://localhost:8080/mutantchecker/mutant' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "dna":["AGGGGA", "CAGTGC", "TGAGGT", "GGAAGG", "CCCCTA", "TCACTT"]
  }'
~~~

Las peticiones en ambiente nube se pueden realizar una vez se cree,
la infraestructura dado que de momento se tiene apagada para no perder 
horas máquina de la capa gratuita.

# Arquitectura
1. Se crean dos microservicios (uno para el modelo de consultas y otro para el modelo de escritura)
2. Se crean varias pods de un mismo servicio para mayor disponibilidad
3. el acceso a los pods es através de un load balancer
4. Para garantizar una mayor disponibilidad se introduce 
una caché con redis: cuando se crea un nuevo registro se almacena en caché los contadores,
y cuando se consulta se va primero a la caché y si esta tiene la
información disponible se devuelve y sino se va a base de datos.
5. Se maneja un pool de hilos para el acceso a operaciones de bases de datos.

# Mejoras futuras
- Introducir un API Gateway
- Utilizar DNS para que no haya que poner la IP en el request de los servicios.
- Automatizar el despliegue con herramientas como jenkins o azure devops o cualquier otra
- Validar que las letras de las secuencias si pertenezcan a las especificadas.
- Diagrama de arquitectura
- Mejor manejo de errores
- Logueo
- Monitoreo
- Circuit Breaker hacia la base de datos y caché
- Documentación de la API con swagger