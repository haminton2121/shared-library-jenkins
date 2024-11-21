def awsCredentials(Closure body) {
    withCredentials([[$class: 'accounttest', credentialsId: 'my-aws-credentials']]) {
        body()
    }
}
