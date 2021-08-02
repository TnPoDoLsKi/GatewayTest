
if mvn -v | grep -q 'Maven home'
then
   echo "Maven installed"
else
   echo "Maven is not installed"
   exit 1
fi

if sudo lsof -Pi :8080 -sTCP:LISTEN -t >/dev/null ; then
    echo "Ports 8080 already taken"
    exit 1
else
    echo "Ports 8080 not taken"
fi

if sudo lsof -Pi :3306 -sTCP:LISTEN -t >/dev/null ; then
    echo "Ports 3306 already taken"
    exit 1
else
    echo "Ports 3306 not taken"
fi

if systemctl status docker | grep -q 'active (running)'
then
    echo "docker is installed"
else
    echo "Docker is not installed"
    exit 1
fi

mvn install -Dmaven.test.skip=true && docker-compose up -d


