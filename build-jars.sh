set -e
echo "[I] Building JARs"
mvn clean package -DskipTests -f ./void-es/pom.xml

mvn clean package -DskipTests -f ./void-progression/pom.xml

mvn clean package -DskipTests -f ./void-creatures/pom.xml

mvn clean package -DskipTests -f ./void-gateway/pom.xml
mvn clean package -DskipTests -f ./void-security/pom.xml
mvn clean package -DskipTests -f ./void-controller/pom.xml
