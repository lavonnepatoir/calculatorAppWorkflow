pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                bat 'mvn clean package'  // Adjust for your build command
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'  // Runs Maven tests
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Add Docker or deployment steps here
            }
        }
    }
}
