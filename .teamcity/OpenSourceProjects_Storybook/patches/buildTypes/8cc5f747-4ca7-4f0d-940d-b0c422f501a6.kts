package OpenSourceProjects_Storybook.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with uuid = '8cc5f747-4ca7-4f0d-940d-b0c422f501a6' (id = 'OpenSourceProjects_Storybook_Examples')
accordingly and delete the patch script.
*/
changeBuildType("8cc5f747-4ca7-4f0d-940d-b0c422f501a6") {
    expectSteps {
        script {
            name = "Bootstrap"
            scriptContent = """
                yarn
                yarn bootstrap --core
            """.trimIndent()
            dockerImage = "node:latest"
        }
        script {
            name = "official-storybook"
            scriptContent = """
                #!/bin/sh
                
                set -e -x
                
                cd examples/official-storybook
                yarn build-storybook
            """.trimIndent()
            dockerImage = "node:latest"
        }
        script {
            name = "Image storyshots"
            scriptContent = """
                #!/bin/sh
                
                set -e -x
                
                # Workaround for https://github.com/GoogleChrome/puppeteer/issues/290
                
                apt-get update
                apt-get install -yq gconf-service libasound2 libatk1.0-0 libc6 libcairo2 libcups2 libdbus-1-3 \
                  libexpat1 libfontconfig1 libgcc1 libgconf-2-4 libgdk-pixbuf2.0-0 libglib2.0-0 libgtk-3-0 libnspr4 \
                  libpango-1.0-0 libpangocairo-1.0-0 libstdc++6 libx11-6 libx11-xcb1 libxcb1 libxcomposite1 \
                  libxcursor1 libxdamage1 libxext6 libxfixes3 libxi6 libxrandr2 libxrender1 libxss1 libxtst6 \
                  ca-certificates fonts-liberation libappindicator1 libnss3 lsb-release xdg-utils wget
                yarn test --image --teamcity
            """.trimIndent()
            dockerImage = "node:8"
        }
    }
    steps {
        update<ScriptBuildStep>(1) {
            scriptContent = """
                #!/bin/sh
                
                set -e -x
                
                cd examples/official-storybook
                rm -rf storybook-static
                yarn build-storybook
            """.trimIndent()
        }
    }

    dependencies {
        expect("OpenSourceProjects_Storybook_CRA") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = "cra.zip!** => examples/cra-kitchen-sink/storybook-static"
            }
        }
        update("OpenSourceProjects_Storybook_CRA") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                cleanDestination = true
                artifactRules = "cra.zip!** => examples/cra-kitchen-sink/storybook-static"
            }
        }

        expect("OpenSourceProjects_Storybook_Vue") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = "vue.zip!** => examples/vue-kitchen-sink/storybook-static"
            }
        }
        update("OpenSourceProjects_Storybook_Vue") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                cleanDestination = true
                artifactRules = "vue.zip!** => examples/vue-kitchen-sink/storybook-static"
            }
        }

        expect("OpenSourceProjects_Storybook_Angular") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = "angular.zip!** => examples/angular-cli/storybook-static"
            }
        }
        update("OpenSourceProjects_Storybook_Angular") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                cleanDestination = true
                artifactRules = "angular.zip!** => examples/angular-cli/storybook-static"
            }
        }

        expect("OpenSourceProjects_Storybook_Polymer") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = "polymer.zip!** => examples/polymer-cli/storybook-static"
            }
        }
        update("OpenSourceProjects_Storybook_Polymer") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                cleanDestination = true
                artifactRules = "polymer.zip!** => examples/polymer-cli/storybook-static"
            }
        }

        expect("OpenSourceProjects_Storybook_Mithril") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = "mithril.zip!** => examples/mithril-kitchen-sink/storybook-static"
            }
        }
        update("OpenSourceProjects_Storybook_Mithril") {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                cleanDestination = true
                artifactRules = "mithril.zip!** => examples/mithril-kitchen-sink/storybook-static"
            }
        }

    }
}
