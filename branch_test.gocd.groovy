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
//        fullRepoName = 'gocd/docs.go.cd'
//        materialUrl = "https://github.com/gocd/docs.go.cd"
//        //apiAuthToken = "some-value"
//        apiAuthToken = lookup('github.auth.token')
//        //materialUsername = "krazybird"
//        //materialPassword = "testing"
//        //materialPassword = lookup('github.auth.token')
//      }
//      from = gitlab {
//        apiAuthToken = lookup("gitlab.auth.token")
//        fullRepoName = "testing-group12/public-repo"

//        // OPTIONAL for public repos.
//        materialUsername = "kritika-singh3"
//        materialPassword = lookup("gitlab.auth.token")
//      }
      from = bitbucket {
        fullRepoName = "kritika-singh3/public-repo"

        // OPTIONAL for public repos, but recommended.
        //apiUser = "kritika-singh3"

        // lookup() allows one to provide secret/secure values to the script.
        // These arbitrary key+values are configurable from the UI on the GoCD server.
        //
        // lookup() is resolvable/usable anywhere in config script block.
        //apiPass = lookup("bitbucket.auth.token")

        // OPTIONAL for public repos
        //materialUsername = "kritika-singh3"
        //materialPassword = lookup("bitbucket.auth.token")
      }

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

