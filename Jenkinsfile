pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/lavonnepatoir/calculatorAppWorkflow.git'
            }
        }

        stage('Build App') {
              steps {
                echo 'Building Java project with Maven...'
                sh 'mvn clean package'
              }
            }

            stage('Run JMeter Load Test') {
              steps {
                echo 'Running JMeter load test...'
                sh 'jmeter -n -t "Calculator Test Plan.jmx" -l results.jtl'
              }
            }

            stage('Archive JMeter Results') {
              steps {
                archiveArtifacts artifacts: 'results.jtl', fingerprint: true
              }
            }
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
