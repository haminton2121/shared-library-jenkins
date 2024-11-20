//def call(Map stageParams) {
//                checkout([$class: 'GitSCM', branches: [[name: stageParams.branch]], doGenerateSubmoduleConfigurations: false,
//                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: stageParams.dir]],
//                submoduleCfg: [], userRemoteConfigs: [[credentialsId: stageParams.credential_git , url: stageParams.url]]])
//}
def call(Map config) {
    def targetBranch = config.targetBranch ?: 'main' // Default to 'main' if no branch provided
    def service = config.service ?: 'defaultService' // Default service name if not provided

    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${targetBranch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [
            [
                $class: 'RelativeTargetDirectory',
                relativeTargetDir: "AsiaPolicyUI${service}"
            ]
        ],
        submoduleCfg: [],
        userRemoteConfigs: [
            [
                credentialsId: config.credentialsId ?: 'hnguyen421_git',
                url: "https://github.com/haminton2121/python_scripts.git"
            ]
        ]
    ])
}
