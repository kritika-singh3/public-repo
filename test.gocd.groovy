/*
 * This file was automatically exported by the GoCD Groovy DSL Plugin.
 */

GoCD.script {
  pipelines {
    pipeline('test') {
      group = 'test'
      labelTemplate = '${COUNT}'
      lockBehavior = 'none'
      materials {
        git {
          branch = 'master'
          shallowClone = false
          url = 'file:///test-repo'
        }
      }
      stages {
        stage('first_stage') {
          artifactCleanupProhibited = false
          cleanWorkingDir = false
          fetchMaterials = true
          approval {
          }
          jobs {
            job('first_job') {
              timeout = 0
              tasks {
                exec {
                  commandLine = ['ls']
                  runIf = 'passed'
                }
              }
            }
          }
        }
        stage('stage_second') {
          artifactCleanupProhibited = false
          cleanWorkingDir = false
          fetchMaterials = true
          approval {
            allowOnlyOnSuccess = true
            type = 'manual'
          }
          jobs {
            job('second_job') {
              timeout = 0
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

