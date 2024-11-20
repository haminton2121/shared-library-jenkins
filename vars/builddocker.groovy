// vars/buildDockerImage.groovy


def buildBaseDocker(Map config) {
    def contextPath = config.contextPath ?: 'core'
    def environment = config.environment ?: 'dev'
    def defaultUrl = config.defaultUrl ?: 'http://default.url'
    def pdxcPath = config.pdxPath ?: 'default/path'
    def now = new Date()
    def setDate = now.format("yyyy.MM.dd", TimeZone.getTimeZone('UTC'))
    def DPLVERSION = "${setDate}.${BUILD_NUMBER}"
        sh """
            echo Building Docker Image

            cd ${WORKSPACE}/@libs/my-shared-library/resources/docker/ && ls -al

            cat Dockerfile

            # Build and push to AWS ECR
            docker build -t 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com/hnguyen/test/nginx:${DPLVERSION} .
            /usr/local/bin/aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com
            docker push 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com/hnguyen/test/nginx:${DPLVERSION}
            docker rmi 116762474585.dkr.ecr.ap-southeast-1.amazonaws.com/digitalinsurance-docker/integral/life/uiux/master/base:uiux-integral.${DPLVERSION}

        """
    }
}
