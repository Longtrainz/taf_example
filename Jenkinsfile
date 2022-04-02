properties([
    parameters([
        choice(
            description: "Choose test suite",
            choices: ['ALL','API','UI'],
            name: 'TEST_SUITE'
            ),
         choice(
            description: "Choose browser",
            choices: ['chrome','firefox'],
            name: 'BROWSER_NAME'
            ),
        string(
            description: "Choose threads number",
            defaultValue: '2',
            name: 'THREADS'
            ),
        string(
            description: "Choose remote url",
            defaultValue: 'http://167.71.48.36:8080/wd/hub',
            name: 'REMOTE_URL'
            )
    ])
])

def suiteName = params.TEST_SUITE
def browser = params.BROWSER_NAME
def threads = params.THREADS
def remoteUrl = params.REMOTE_URL
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
                bat "gradle.bat api -Dthreads=${threads} -Dweb.remote.driver.url=${remoteUrl}"
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
                bat "gradle.bat web -Dbrowser.name=${browser} -Dthreads=${threads} -Dweb.remote.driver.url=${remoteUrl}"
                bat 'exit /B 0'
            }
        }   catch (e) {
                build_ok = false
            }
        } else {
             echo "skipped stage UI"
         }

    stage('Reports') {
        allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'build/allure-results']]
        ])
    }

    if (build_ok) {
            currentBuild.result = "SUCCESS"
        } else {
            currentBuild.result = "FAILURE"
    }
}
