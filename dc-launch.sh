echo "[I] Deleting old network"
docker network rm void-network
set -e
echo "[I] Building JARs"
mvn clean package -DskipTests -f ./void-es/pom.xml
mvn clean package -DskipTests -f ./void-resources/pom.xml
mvn clean package -DskipTests -f ./void-items/pom.xml
mvn clean package -DskipTests -f ./void-gateway/pom.xml
mvn clean package -DskipTests -f ./void-security/pom.xml
mvn clean package -DskipTests -f ./void-controller/pom.xml
echo "[I] Building void-db"
docker build -t void-db:latest -f void-db-Dockerfile ./
docker build -t void-es:latest ./void-es
docker build -t void-resources:latest ./void-resources
docker build -t void-items:latest ./void-items
docker build -t void-gateway:latest ./void-gateway
docker build -t void-security:latest ./void-security
docker build -t void-controller:latest ./void-controller
echo "[I] Starting docker compose"
docker compose up --force-recreate --remove-orphans
     
    
