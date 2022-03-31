pipeline{
  agent any
  stages{
  stage('Build Jar') {
      steps {
          //sh
           withGradle {
              sh './gradlew build'
            }
      }
  }
    stage('Test'){
      steps{
        withGradle {
          sh './gradlew test'
        }
      }
    }
  }

}