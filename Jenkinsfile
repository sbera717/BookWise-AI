pipeline{
    agent{
        docker{
            image 'sbera717/dockeragent:v2'
            args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages{
        stage('Checkout'){
            steps{
                sh 'echo passed'
            }
        }
        stage("Build"){
            steps{
                sh 'mvn clean package'
            }
        }
        stage('Build and Push Docker Image') {
      environment {
        DOCKER_IMAGE = "sbera717/java:${BUILD_NUMBER}"
        REGISTRY_CREDENTIALS = credentials('docker-login')
      }
      steps {
        script {
            sh 'docker build -t ${DOCKER_IMAGE} .'
            def dockerImage = docker.image("${DOCKER_IMAGE}")
            docker.withRegistry('https://index.docker.io/v1/', "docker-login") {
                dockerImage.push()
            }
        }
      }
    }
    }
}
