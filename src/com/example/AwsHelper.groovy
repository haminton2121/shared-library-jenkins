package com.example

class AwsHelper {
    def loginToECR() {

        sh """
            echo "Logging into AWS ECR..."
            aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com
        """
    }
}
