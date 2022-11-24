echo $2 | docker login -u $1 --password-stdin
docker buildx build --platform linux/amd64 --tag $1/java-bot:${GITHUB_REF##*/} --push .