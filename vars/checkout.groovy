//def call(Map stageParams) {
//                checkout([$class: 'GitSCM', branches: [[name: stageParams.branch]], doGenerateSubmoduleConfigurations: false,
//                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: stageParams.dir]],
//                submoduleCfg: [], userRemoteConfigs: [[credentialsId: stageParams.credential_git , url: stageParams.url]]])
//}
def call(Map config) {
    def targetBranch = config.targetBranch ?: 'main'  
    def service = config.service ?: 'defaultService'  
    def credentialsId = config.credentialsId ?: 'default-credentials'  
    def targetDirectory = config.targetDirectory ?: "default-directory" 
    git(
        url: "https://github.com/haminton2121/python_scripts.git",
        branch: targetBranch,
        credentialsId: credentialsId,
        changelog: false,  
        poll: false,
        extensions: [
            [
                $class: 'RelativeTargetDirectory', 
                relativeTargetDir: targetDirectory  
            ]
        ]
    )
}
