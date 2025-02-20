pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean package'  // Adjust for your build command
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'  // Runs Maven tests
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
