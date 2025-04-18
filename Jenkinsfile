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
        echo 'Simulating deployment to us-west cluster...'
        bat 'kubectl config use-context us-west'
        bat 'kubectl create deployment calculator-west --image=myrepo/calculator-app:latest --port=3000 --namespace=us-west --dry-run=client -o yaml'
        bat 'kubectl expose deployment calculator-west --type=NodePort --port=80 --target-port=3000 --name=calculator-west-service --namespace=us-west --dry-run=client -o yaml'
    }

    stage('Deploy to Kubernetes (East)') {
        echo 'Simulating deployment to us-east cluster...'
        bat 'kubectl config use-context us-east'
        bat 'kubectl create deployment calculator-east --image=myrepo/calculator-app:latest --port=3000 --namespace=us-east --dry-run=client -o yaml'
        bat 'kubectl expose deployment calculator-east --type=NodePort --port=80 --target-port=3000 --name=calculator-east-service --namespace=us-east --dry-run=client -o yaml'
    }

}
