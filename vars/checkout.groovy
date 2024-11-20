//def call(Map stageParams) {
//                checkout([$class: 'GitSCM', branches: [[name: stageParams.branch]], doGenerateSubmoduleConfigurations: false,
//                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: stageParams.dir]],
//                submoduleCfg: [], userRemoteConfigs: [[credentialsId: stageParams.credential_git , url: stageParams.url]]])
//}
def call(Map config) {
    def targetBranch = config.targetBranch ?: 'main'  
    def service = config.service ?: 'defaultService'  
    def credentialsId = config.credentialsId ?: 'default-credentials'  
    def targetDirectory = config.targetDirectory ?: "default-directory"  // Thư mục đích mặc định

    // Sử dụng lệnh 'git' trong Jenkins Pipeline để checkout mã nguồn
    git(
        url: "https://github.dxc.com/insurance/AsiaPolicyUI${service}",
        branch: targetBranch,
        credentialsId: credentialsId,
        changelog: false,  
        poll: false  
         extensions: [
            [
                $class: 'RelativeTargetDirectory',  // Đặt thư mục đích
                relativeTargetDir: targetDirectory  // Thư mục bạn muốn clone vào
            ]
        ]
    )
}
