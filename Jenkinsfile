pipeline {
  agent any
  stages {
    stage('Install dependencies') {
      steps {
        echo "instalando"
        npm 'install'
      }
    }
    stage('Run unit test') {
      steps {
        npm "test"
      }
    }
    stage('Run deploy') {
      steps {
        echo "Aquí debería enviar a deploy with codepipeli code"
      }
    }
  }
}