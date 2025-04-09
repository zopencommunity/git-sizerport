node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/git-sizerport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/git-sizerport.git'), string(name: 'PORT_DESCRIPTION', value: 'git-sizer computes various size metrics for a local Git repository, flagging those that might cause you problems or inconvenience' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
