node {
    stage("checkout repo") {
        git branch: 'main',
        credentials: 'd8de431a-87a6-4cc9-aef3-22eaf5455868',
        url: 'https://github.com/Longtrainz/taf_example.git'
    }

    stage("build") {
        bat 'gradle.bat clean web:assemble'
    }

    stage("run api tests") {
        bat "clean api"
    }

    stage("run ui tests") {
        bat "clean web"
    }
}