def call(String registryUrl, String pathImage){
    def setDate = now.format("yyyy.MM.dd", TimeZone.getTimeZone('UTC'))
    def DPLVERSION = "${setDate}.${BUILD_NUMBER}"
    sh """
    trivy image ${registryUrl}/${pathImage}:${DPLVERSION} > a.txt
    cat a.txt
    """
}
