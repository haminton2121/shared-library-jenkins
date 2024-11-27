def call() {
            dir("${WORKSPACE}/${githubRepo}") {
                sh '''
                    terraform init
                    terraform validate
                    terraform fmt
                    terraform plan -var-file="terraform.tfvars" -out=tfplan 
                '''
    }
}
