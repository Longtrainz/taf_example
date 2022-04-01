def suiteName = env.TEST_SUITE
def build_ok = true

node {
    stage("checkout repo") {
        git branch: 'main',
        credentialsId: 'd8de431a-87a6-4cc9-aef3-22eaf5455868',
        url: 'https://github.com/Longtrainz/taf_example.git'
    }

    stage("build") {
        bat 'gradle.bat clean assemble'
    }

    if (suiteName == "API" || suiteName == "ALL") {

        try {
            stage("run api tests") {
                bat 'gradle.bat api'
                bat 'exit /B 0'
            }
        }   catch (e) {
              build_ok = false
            }
    } else {
        echo  "skipped stage API"
    }

    if (suiteName == "UI" || suiteName == "ALL") {
        try {
            stage("run ui tests") {
                bat "gradle.bat web"
                bat 'exit /B 0'
            }
        }   catch (e) {
                build_ok = false
            }
        } else {
             echo "skipped stage UI"
         }


    if (build_ok) {
            currentBuild.result = "SUCCESS"
        } else {
            currentBuild.result = "FAILURE"
    }
}
