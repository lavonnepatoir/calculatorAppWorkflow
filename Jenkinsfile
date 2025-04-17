node {
    stage('Build App') {
        echo 'Building Java project with Maven...'
        bat 'mvn clean package'
    }

    stage('Run JMeter Load Test') {
        echo 'Running JMeter load test...'
        // Make sure to update the path if yours is different
        bat '"C:\\Users\\bookw\\Downloads\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3\\bin\\jmeter.bat" -n -t "Calculator Test Plan.jmx" -l results.jtl'
    }

    stage('Archive JMeter Results') {
        echo 'Archiving JMeter test results...'
        archiveArtifacts artifacts: 'results.jtl', fingerprint: true
    }
}
