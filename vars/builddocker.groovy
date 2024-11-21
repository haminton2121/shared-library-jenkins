def call(Map config) {
    // Khai báo AWS credentials trước khi gọi bất kỳ lệnh AWS nào
    awscredential.awscredential {
        // Gọi hàm uploadToS3 từ awsUtils.groovy để upload file lên S3
        builddockerimage.call(Map config)
    }
}
