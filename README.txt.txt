Para usar la función de docker-compose, se abre el archivo desde el directorio del proyecto de eureka y se tienen que crear las imagenes de eureka-app, balancer-app, backempresa-app,
backweb8081-app, backweb8082-app, backweb8083-app.

eureka: localhost:8761

backweb1: localhost:8081/h2-console    busDB1  
backweb2: localhost:8082/h2-console    busDB2  
backweb3: localhost:8083/h2-console    busDB3

backEmpresa: localhost:8090/h2-console busDB  