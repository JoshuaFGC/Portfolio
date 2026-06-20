# Usa una imagen base de C++ y un sistema operativo ligero
FROM gcc:latest

# Instala las dependencias necesarias como lcurl
RUN apt-get update && apt-get install -y libcurl4-openssl-dev

# Establece el directorio de trabajo
WORKDIR /app

# Copia todos los archivos en el directorio actual al contenedor
COPY . .

# Copia el código fuente de httplib
COPY httplib.h /app/httplib.h

# Compila la aplicación y httplib
RUN g++ -o server server.cpp /app/httplib.h -lcurl

# Copia los archivos HTML y recursos estáticos al directorio del servidor web (por ejemplo, 'public')
RUN mkdir /app/public
COPY requestHtml.html /app/public/requestHtml.html

# COPY assets/* /app/public/assets/

# Asigna el puerto
EXPOSE 8080

# Comando predeterminado para ejecutar el servidor
CMD ["./server"]
