node {

    stage('Build App') {
        echo 'Building Java project with Maven...'
        bat 'mvn clean package'
    }

    stage('Run Unit Tests') {
        echo 'Running unit tests...'
        bat 'mvn test'
    }

    stage('Run JMeter Load Test') {
        echo 'Running JMeter load test...'
        bat '"C:\\Users\\bookw\\Downloads\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3\\bin\\jmeter.bat" -n -t "Calculator Test Plan.jmx" -l results.jtl'
    }

    stage('Check Logs') {
        echo 'Displaying last few lines of the log file...'
        bat 'type calculator.log'
    }

}
