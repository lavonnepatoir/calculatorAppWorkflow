node {

    stage('Build App') {
        echo 'Building Java project with Maven...'
        bat 'mvn clean package'
    }

    stage('Run JMeter Load Test') {
        echo 'Running JMeter load test...'
        bat '"C:\\Users\\bookw\\Downloads\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3\\bin\\jmeter.bat" -n -t "Calculator Test Plan.jmx" -l results.jtl'
    }

    stage('Check Logs') {
        echo 'Displaying last few lines of the log file...'
        bat 'type calculator.log'
    }

    stage('Deploy to Kubernetes (West)') {
        echo 'Deploying to us-west cluster...'
        bat 'kubectl apply -f k8s/calculator-west-deployment.yaml'
        bat 'kubectl apply -f k8s/calculator-west-service.yaml'
    }

    stage('Deploy to Kubernetes (East)') {
        echo 'Deploying to us-east cluster...'
        bat 'kubectl config use-context us-east'
        bat 'kubectl apply -f k8s/calculator-east-deployment.yaml'
        bat 'kubectl apply -f k8s/calculator-east-service.yaml'
    }

}
