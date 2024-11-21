def call() {
//    def AWS_CREDENTIALS = config.credentialsId 
//    def AWS_REGION = config.region ?: 'ap-southeast-1' 
    withAWS(credentials: 'my-aws-credentials', region: 'ap-southeast-1') {
        // Now you can call AWS commands using these credentials
        echo "AWS credentials are successfully set up."
    }
}
