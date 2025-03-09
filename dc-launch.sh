echo "[I] Deleting old network"
docker network rm void-network
echo "[I] Building void-db"
docker build -t void-db:latest -f void-db-Dockerfile ./
docker build -t void-es:latest ./void-es

docker build -t void-progression:latest ./void-progression

docker build -t void-creatures:latest ./void-creatures

docker build -t void-gateway:latest ./void-gateway
docker build -t void-security:latest ./void-security
docker build -t void-controller:latest ./void-controller
echo "[I] Starting docker compose"
docker compose up --force-recreate --remove-orphans
     
    
