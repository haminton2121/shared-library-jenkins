def call(Map config) {
    awscredential.awscredential {
        builddockerimage.call(Map config)
    }
}
