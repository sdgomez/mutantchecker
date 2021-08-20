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
ENV MONGODBURI="mongodb://sdgomez:sg1067904806@cluster0-shard-00-00.ugodx.mongodb.net:27017,cluster0-shard-00-01.ugodx.mongodb.net:27017,cluster0-shard-00-02.ugodx.mongodb.net:27017/xmen?ssl=true&replicaSet=atlas-zt318k-shard-0&authSource=admin&retryWrites=false&maxIdleTimeMS=120000&w=majority"
ENV CACHEURI="redis://3QCwPcV5IVKgdEdS9ASxtBxFNoO36I3r@redis-17240.c238.us-central1-2.gce.cloud.redislabs.com:17240/0"

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