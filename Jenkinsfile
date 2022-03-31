pipeline{
  agent any
  stages{
    stage('Test'){
      steps{
        withGradle {
          sh './gradlew test'
        }
      }
    }
  }

}