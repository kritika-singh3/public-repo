GoCD.script {
  pipelines {
    pipeline('test-config-repo') {
      group = 'internal'
      lockBehavior = 'none'
      materials {
        git('master') {
          branch = 'master'
          shallowClone = false
          url = 'https://github.com/krazybird/public-repo'
        }
      }
      stages {
        stage('test') {
          artifactCleanupProhibited = false
          cleanWorkingDir = false
          fetchMaterials = true
          jobs {
            job('job1') {
              elasticProfileId = 'agent1'
              tasks {
                exec {
                  commandLine = ['ls']
                  runIf = 'passed'
                }
              }
            }
          }
        }
      }
    }
  }
}
