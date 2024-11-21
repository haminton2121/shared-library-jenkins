def awsCredentials(Closure body) {
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'my-aws-credentials']]) {
        body()
    }
}
