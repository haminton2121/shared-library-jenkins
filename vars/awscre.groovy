
// vars/awsUtils.groovy
def call() {

    // Use withCredentials to bind AWS credentials from Jenkins to environment variables
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'my-aws-credentials']]) {
        // AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY are now available as environment variables
        echo "AWS credentials are successfully set up."
        
        // Log in to AWS ECR using the injected credentials
        sh """
            echo "Logging into AWS ECR..."
            aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 975049929854.dkr.ecr.ap-southeast-1.amazonaws.com
        """
    }
}
