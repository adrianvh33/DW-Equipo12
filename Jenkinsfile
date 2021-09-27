pipeline {
  agent any
  tools {nodejs "DW-Equipo12"}

  environment {
        ATLAS_CONNECTION = 'mongodb+srv://av-equipo12:av-equipo12@grupo12cluster.gjstf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority'
        PORT    = '5000'
    }

  stages {
    stage('Install dependencies') {
      steps {
        echo "instalando"
        sh 'npm install'
      }
    }
    stage('Run unit test') {
      steps {
        sh 'npm test'
      }
    }
    stage('Run deploy') {
      steps {
        echo "Aquí debería enviar a deploy with codepipeli code"
        sh 'npm start'
      }
    }
  }
}