def call(Map config) {
    def orgGithub        = 'terraform194920'
    def branch           = config.branch  // Will be defined as an environment name.
    def deploymentUnits  = config.deploymentUnits  // Fixed typo: "test" should map the deploymentUnits correctly
    def duRepoList = '''DocumentDB:documentdb'''.replaceAll("\n", " ")

    // Using deploymentUnits instead of deploymentUnit (fixed typo)
    def githubRepo = sh(returnStdout: true, script: "echo ${duRepoList} | tr ' ' '\n' | grep ^${deploymentUnits}: | cut -d':' -f2 || echo ''").trim()

    // Use withCredentials block for GitHub credentials
    withCredentials([usernamePassword(credentialsId: 'hoanguyengit', usernameVariable: 'gitUsername', passwordVariable: 'gitPassword')]) {
        sh '''
            rm -rf ${githubRepo} || true
            apk add git -y
            #git config --global credential.helper '!f() { sleep 1; echo "username=${gitUsername}"; echo "password=${gitPassword}"; }; f'
            sudo git config --system user.name "My Name"
            git clone -b ${branch} --single-branch "https://gitlab.com/${orgGithub}/${githubRepo}.git"

            git config --global --remove-section credential
        '''
    }
}
