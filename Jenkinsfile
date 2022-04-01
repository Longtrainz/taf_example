def build_ok = true

def stage(name, execute, block) {
    return stage(name, execute ? block : {echo "skipped stage $name"})
}

node {
    stage("checkout repo", true) {
        git branch: 'main',
        credentialsId: 'd8de431a-87a6-4cc9-aef3-22eaf5455868',
        url: 'https://github.com/Longtrainz/taf_example.git'
    }

    stage("build") {
        bat 'gradle.bat clean assemble'
    }

    if (env.TEST_SUITE == "API") {

        try {
            stage("run api tests") {
                bat 'gradle.bat api'
                bat 'exit /B 0'
            }
        }   catch (e) {
              build_ok = false
            }
    } else {
        echo "skipped stage $name"
    }

    if (env.TEST_SUITE == "UI") {
        try {
            stage("run ui tests") {
                bat "gradle.bat web"
                bat 'exit /B 0'
            }
        }   catch (e) {
                build_ok = false
            }
        } else {
             echo "skipped stage $name"
         }


    if (build_ok) {
            currentBuild.result = "SUCCESS"
        } else {
            currentBuild.result = "FAILURE"
    }
}
