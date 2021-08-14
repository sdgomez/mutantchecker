FROM amazoncorretto:11
WORKDIR /opt/
COPY build/libs/moduloescritura-*-all.jar ./moduloescritura.jar
EXPOSE 8080

COPY src/main/resources .

ENV APPNAME="mutantchecker"
ENV APPPORT=8080
ENV MAXWAITQUEUESIZE=5
ENV MAXPOOLSIZE=20
ENV MONGODBNAME="xmen"
ENV MONGODBURI=""

CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar moduloescritura.jar

# Hacer clean de los jars viejos (RECOMENDADO)
# Hacer el build para crear el jar
# cd moduloescritura
#  sudo docker build .
# sudo docker run -it --rm -p8080:8080 8789b2b418ca

# sudo docker build  -t "moduloescritura:moduloescritura" .
# sudo docker images

# az acr build --registry $ACR_NAME --image helloacrtasks:v1 .
# az acr build --registry "crmutantes" --image moduloescritura:v1 .

# az acr repository list --name $ACR_NAME --output table
# az acr repository list --name "crmutantes" --output table