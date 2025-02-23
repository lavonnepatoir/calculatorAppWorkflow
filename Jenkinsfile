pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'lavonnepatoir/calculator-workflow'  // Change this to your Docker Hub repository name
        DOCKER_TAG = 'latest'  // You can modify this as needed
        REGISTRY_CREDENTIALS = 'docker-hub-credentials'  // Jenkins credential ID for Docker Hub login
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/lavonnepatoir/calculatorAppWorkflow.git'
            }
        }

        //docker run -p 9090:8080 lavonnepatoir/calculator-workflow


        stage('Build') {
            steps {
                echo 'Building application...'
                bat 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'  // Build the Docker image
            }
        }

        stage('Push') {
            steps {
                echo 'Pushing Docker image to registry...'
                withDockerRegistry([credentialsId: "$REGISTRY_CREDENTIALS", url: '']) {
                    bat 'docker push $DOCKER_IMAGE:$DOCKER_TAG'  // Push image to Docker Hub
                }
            }
        }
    }
}
