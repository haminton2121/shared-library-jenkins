// vars/awsUtils.groovy
def call(String region, String registryUrl) {

    // Use withCredentials to bind AWS credentials from Jenkins to environment variables
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'my-aws-credentials']]) {
        // AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY are now available as environment variables
        echo "AWS credentials are successfully set up."
        
        // Log in to AWS ECR using the injected credentials
        sh """
            echo "Logging into AWS ECR..."
            aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${registryUrl}
        """
    }
}
