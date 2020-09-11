/*
 * This file was automatically exported by the GoCD Groovy DSL Plugin.
 */

GoCD.script {
  branches {
    matching {
//      working for normal git
//      pattern= ~/refs\/heads\/.*/
//      from = git {
//        url = "http://github.com/krazybird/public-repo"
//        materialUsername = "krazybird"
//        materialPassword = lookup('github.auth.token')
//      }
//      from = github {
//        fullRepoName = 'gocd-private/aws'
//        materialUrl = "https://github.com/gocd-private/aws"
//        apiAuthToken = lookup('github.auth.token')
//      }
//      from = gitlab {
//        apiAuthToken = lookup("gitlab.auth.token")
//        fullRepoName = "testing-group12/public-repo"
//        materialUsername = "kritika-singh3"
//        materialPassword = lookup("gitlab.auth.token")
//      }
      from = bitbucket {
        fullRepoName = "kritika-singh3/public-repo"
        //apiUser = "kritika-singh3"
        apiPass = lookup("bitbucket.auth.token")
      }
  
// works for notifications with 196 one      
//      from = github {
//        fullRepoName = 'krazybird/public-repo'
////        materialUrl = "https://github.com/krazybird/public-repo"
//        apiAuthToken = lookup('github.auth.token')
//      }

      onMatch { ctx ->
      
        pipeline("testing-branch-${ctx.branchSanitized}") {
          group = "testing"

          materials { add(ctx.repo) }
          stages {
            stage('stage') {
              jobs {
                job('job') {
                  tasks {
                    exec { commandLine = ['sleep', '30'] }
                    exec { commandLine = ['ls'] }
                  }
                }
              }
            }
          }
          
        ctx.repo.notifiesBy(ctx.provider)
        }
      }
    }
  }
}

