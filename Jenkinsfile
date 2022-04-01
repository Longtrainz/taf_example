node {
    stage("checkout repo") {
        git branch: 'main',
        credentialsId: 'd8de431a-87a6-4cc9-aef3-22eaf5455868',
        url: 'https://github.com/Longtrainz/taf_example.git'
    }

    stage("build") {
        bat 'gradle.bat clean assemble'
    }

   stage("run api tests") {
        bat "gradle.bat api"
    }

    stage("run ui tests") {
        bat "gradle.bat web"
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                bat 'exit /B 0'
        }
    }
}
