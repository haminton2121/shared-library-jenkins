def call(String registryUrl, String pathImage){
    def now = new Date()
    def setDate = now.format("yyyy.MM.dd", TimeZone.getTimeZone('UTC'))
    def DPLVERSION = "${setDate}.${BUILD_NUMBER}"
    sh """
    /usr/local/bin/trivy image ${registryUrl}/${pathImage}:${DPLVERSION} > a.txt
    cat a.txt
    """
}
