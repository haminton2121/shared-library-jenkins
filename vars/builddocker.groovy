def call(Map config) {
    awscredential.awsCredentials {
        builddockerimage.call(config)
    }
}
