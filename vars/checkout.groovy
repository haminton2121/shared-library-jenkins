def call(Map stageParams) {
                checkout([$class: 'GitSCM', branches: [[name: */stageParams.branch]], doGenerateSubmoduleConfigurations: false,
                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: stageParams.dir]],
                submoduleCfg: [], userRemoteConfigs: [[credentialsId: stageParams.credential_git , url: stageParams.url]]])
}
