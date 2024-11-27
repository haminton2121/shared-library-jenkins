def call(String deploymentAction, String githubRepo) {
    dir("${WORKSPACE}/${githubRepo}") {
        sh """
        if [ "${deploymentAction}" == "apply" ]; then
            terraform apply -auto-approve tfplan
        elif [ "${deploymentAction}" == "destroy" ]; then
            terraform destroy -auto-approve
        fi
        """
    }
}
