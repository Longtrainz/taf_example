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
    stage("SonarQube analysis") {
        withSonarQubeEnv('SonarQube') {
            sh './gradlew sonarqube'
        }
    }

    stage("Quality Gate"){
          timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
          }
    }

    stage("checkout repo") {
        git branch: 'jenkins_docker',
        credentialsId: '1dbdf731-0ac0-4cfa-97d4-ebe3de88835a',
        url: 'https://github.com/Longtrainz/taf_example.git'
    }

    stage("build") {
        sh "./gradlew clean assemble"
    }

    if (suiteName == "API" || suiteName == "ALL") {

        try {
            stage("run api tests") {
                sh "./gradlew api -Dthreads=${threads} -Dweb.remote.driver.url=${remoteUrl}"
                sh 'exit 0'
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
                sh "./gradlew web -Dbrowser.name=${browser} -Dthreads=${threads} -Dweb.remote.driver.url=${remoteUrl}"
                sh 'exit 0'
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
