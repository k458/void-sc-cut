echo "[I] Deleting old network"
docker network rm void-network
set -e
echo "[I] Building JARs"
mvn clean package -DskipTests -f ./void-es/pom.xml

mvn clean package -DskipTests -f ./void-resources/pom.xml
mvn clean package -DskipTests -f ./void-dungeons/pom.xml
mvn clean package -DskipTests -f ./void-rooms/pom.xml
mvn clean package -DskipTests -f ./void-hubs/pom.xml

mvn clean package -DskipTests -f ./void-items/pom.xml
mvn clean package -DskipTests -f ./void-characters/pom.xml
mvn clean package -DskipTests -f ./void-enemies/pom.xml

mvn clean package -DskipTests -f ./void-gateway/pom.xml
mvn clean package -DskipTests -f ./void-security/pom.xml
mvn clean package -DskipTests -f ./void-controller/pom.xml
echo "[I] Building void-db"
docker build -t void-db:latest -f void-db-Dockerfile ./
docker build -t void-es:latest ./void-es

docker build -t void-resources:latest ./void-resources
docker build -t void-dungeons:latest ./void-dungeons
docker build -t void-rooms:latest ./void-rooms
docker build -t void-hubs:latest ./void-hubs

docker build -t void-items:latest ./void-items
docker build -t void-characters:latest ./void-characters
docker build -t void-enemies:latest ./void-enemies

docker build -t void-gateway:latest ./void-gateway
docker build -t void-security:latest ./void-security
docker build -t void-controller:latest ./void-controller
echo "[I] Starting docker compose"
docker compose up --force-recreate --remove-orphans
     
    
