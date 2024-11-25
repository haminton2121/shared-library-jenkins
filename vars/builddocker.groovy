def call(String registryUrl, String pathImage, String region) {
    def now = new Date()
    def setDate = now.format("yyyy.MM.dd", TimeZone.getTimeZone('UTC'))
    def DPLVERSION = "${setDate}.${BUILD_NUMBER}"
    def registryUrl = ${registryUrl}
    def pathImage = ${pathImage}
    def dockerfile = libraryResource "docker/Dockerfile"
    writeFile file: "${WORKSPACE}/Dockerfile", text: dockerfile
    def index = libraryResource "docker/index.html"
    writeFile file: "${WORKSPACE}/index.html", text: index
    sh """
        echo "Building Docker Image with version"  
        # Display the Dockerfile contents (optional)
        cat Dockerfile
        # Build the Docker image and tag it with ${DPLVERSION}
        docker build -t ${registryUrl}/${pathImage}:${DPLVERSION} .
        
        # Log in to AWS ECR
        #/usr/local/bin/aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${registryUrl}
        
        # Push the image to AWS ECR
        docker push ${registryUrl}/${pathImage}:${DPLVERSION}
        
        # Optionally, remove the image after pushing
        docker rmi ${registryUrl}/${pathImage}:${DPLVERSION}
    """
}
