pipeline {
  agent any
  tools {nodejs "DW-Equipo12"}
  stages {
    stage('Install dependencies') {
      steps {
        echo "instalando"
        sh 'npm install'
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