def call(Map config) {
    def orgGithub        = 'terraform194920'
    def branch           = config.branch //Will be defined as an environment name.
    def githubCredential = config.githubCredential
    def deploymentUnits  = congfig.deploymentUnits
    def duRepoList = '''DocumentDB:documentdb'''.replaceAll("\n"," ")
    def githubRepo = sh(returnStdout: true, script: "echo ${duRepoList} | tr ' ' '\n' | grep ^${deploymentUnit}: | cut -d':' -f2 || echo ''").trim()
    withCredentials([usernamePassword(credentialsId: my-aws-credentials, usernameVariable: 'gitUsername', passwordVariable: 'gitPassword')]){
            sh'''
              rm -rf ${githubRepo} || true
			  git config --global credential.helper '!f() { sleep 1; echo \"username=${gitUsername}\"; echo \"password=${gitPassword}\"; }; f'
              git clone -b ${branch} --single-branch "https://gitlab.com/${orgGithub}/${githubRepo}.git"
			  git config --global --remove-section credential
            '''
}
}
