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


    try {
        stage("run api tests", false) {
            bat 'gradle.bat api'
            bat 'exit /B 0'
        }
    } catch (e) {
          build_ok = false
      }


    try {
        stage("run ui tests", false) {
            bat "gradle.bat web"
            bat 'exit /B 0'
        }
    } catch (e) {
        build_ok = false
    }

    if (build_ok) {
            currentBuild.result = "SUCCESS"
        } else {
            currentBuild.result = "FAILURE"
    }
}
