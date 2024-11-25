def call(String registryUrl, String pathImage) {
    def now = new Date()
    def setDate = now.format("yyyy.MM.dd", TimeZone.getTimeZone('UTC'))
    def DPLVERSION = "${setDate}.${BUILD_NUMBER}"
    sh """
        echo "Remove Docker Image with version"  
        # Optionally, remove the image after pushing
        docker rmi ${registryUrl}/${pathImage}:${DPLVERSION}
        
    """
}
