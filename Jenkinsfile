pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/lavonnepatoir/calculatorAppWorkflow.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building application...'
                bat 'mvn clean package'  // Runs Maven to build the project
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'  // Runs the tests after the build
            }
        }
    }
}
