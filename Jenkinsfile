pipeline {
    agent any

    environment {
        JMETER_HOME = 'C:\\Users\\bookw\\Downloads\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3\\bin'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/lavonnepatoir/calculatorAppWorkflow.git'
            }
        }

        stage('Build App') {
            steps {
                echo 'Building Java project with Maven...'
                bat 'mvn clean package'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Deploying to us-east and us-west Kubernetes clusters...'
                bat '''
                    kubectl apply -f k8s/deployment-east.yaml --namespace=us-east
                    kubectl apply -f k8s/service-east.yaml --namespace=us-east
                    kubectl apply -f k8s/deployment-west.yaml --namespace=us-west
                    kubectl apply -f k8s/service-west.yaml --namespace=us-west
                '''
            }
        }

        stage('Run JMeter Load Test') {
            steps {
                echo 'Running JMeter load test...'
                bat "\"${env.JMETER_HOME}\\jmeter.bat\" -n -t \"Calculator Test Plan.jmx\" -l results.jtl"
            }
        }

        stage('Archive JMeter Results') {
            steps {
                archiveArtifacts artifacts: 'results.jtl', fingerprint: true
            }
        }

        stage('Collect Application Logs') {
            steps {
                echo 'Collecting logs from Kubernetes pods...'
                bat '''
                    kubectl logs -l app=calculator-east --namespace=us-east > logs-east.log
                    kubectl logs -l app=calculator-west --namespace=us-west > logs-west.log
                '''
                archiveArtifacts artifacts: 'logs-east.log, logs-west.log', fingerprint: true
            }
        }
    }
}
