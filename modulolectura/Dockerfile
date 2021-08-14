FROM amazoncorretto:11
WORKDIR /opt/
COPY build/libs/modulolectura-*-all.jar ./modulolectura.jar
EXPOSE 8086

COPY src/main/resources .

ENV APPNAME="mutantchecker"
ENV APPPORT=8086
ENV MAXWAITQUEUESIZE=50
ENV MAXPOOLSIZE=5
ENV MONGODBNAME="xmen"
ENV MONGODBURI=""

CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar modulolectura.jar

# Hacer clean de los jars viejos (RECOMENDADO)
# Hacer el build para crear el jar
# cd modulolectura
#  sudo docker build .
# sudo docker run -it --rm -p8086:8086 daba7b1ada39

# docker build  -t "modulolectura:modulolectura" .
# sudo docker images

# az acr build --registry $ACR_NAME --image helloacrtasks:v1 .
# az acr build --registry "crmutantes" --image modulolectura:v1 .

# az acr repository list --name $ACR_NAME --output table
# az acr repository list --name "crmutantes" --output table