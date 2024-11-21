def call(Map config) {
    awscredential.awscredential {
        builddockerimage.call(config)
    }
}
