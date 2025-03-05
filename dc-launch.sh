set -e
echo "[I] Building JARs"
mvn clean package -DskipTests -f ./void-users/pom.xml
mvn clean package -DskipTests -f ./void-gateway/pom.xml
mvn clean package -DskipTests -f ./void-security/pom.xml
echo "[I] Building void-db"
docker build -t void-db:latest -f void-db-Dockerfile .
docker build -t void-users:latest ./void-users
docker build -t void-gateway:latest ./void-gateway
docker build -t void-security:latest ./void-security
echo "[I] Starting docker compose"
docker compose up --build --force-recreate
     
    
