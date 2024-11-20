def call() {
    // Generate the DPLVERSION based on the current date and build number
//    def now = new Date()
//    def setDate = now.format("yyyy.MM.dd", TimeZone.getTimeZone('UTC'))
//    def DPLVERSION = "${setDate}.${BUILD_NUMBER}"
    
    // Ensure proper interpolation within the shell script
    def dockerfile = libraryResource "docker/Dockerfile"
    writeFile file: "${WORKSPACE}/Dockerfile", text: dockerfile
    def index = libraryResource "docker/index.html"
    writeFile file: "${WORKSPACE}/index.html", text: index
    sh """
        echo "Building Docker Image with version"  
        # Display the Dockerfile contents (optional)
        cat Dockerfile

        # Build the Docker image and tag it with DPLVERSION
        docker build -t 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com/hnguyen/test/nginx:latest .
        
        # Log in to AWS ECR
        /usr/local/bin/aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com
        
        # Push the image to AWS ECR
        docker push 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com/hnguyen/test/nginx:latest
        
        # Optionally, remove the image after pushing
        docker rmi 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com/hnguyen/test/nginx:latest
    """
}
