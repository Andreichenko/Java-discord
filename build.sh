./gradlew jar
 echo $2 | docker login -u $1 --password-stdin
 docker buildx build --platform linux/amd64 --tag $DOCKER_USERNAME/centos7:$TRAVIS_BRANCH --push .