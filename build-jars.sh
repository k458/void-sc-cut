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
