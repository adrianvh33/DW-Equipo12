pipeline {
  agent any
  stages {
    stage('Install dependencies') {
      steps {
        sudo npm 'install'
      }
    }
    stage('Run unit test') {
      steps {
        sudo npm "test"
      }
    }
    stage('Run deploy') {
      steps {
        echo "Aquí debería enviar a deploy with codepipeli code"
      }
    }
  }
}