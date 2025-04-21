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

    stage('Simulate Crash Test') {
        echo 'Triggering simulated crash and checking response...'
        def crashResponse = bat(
            script: 'curl -s -o crash_output.txt -w "%{http_code}" "http://localhost:3000/calculate?a=999&b=999&op=%2F"',
            returnStdout: true
        ).trim()

        echo "Crash test HTTP response code: ${crashResponse}"

        def output = readFile('crash_output.txt').trim()
        echo "Crash test response: ${output}"

        if (!output.contains("Simulated crash triggered!")) {
            error("Crash simulation did not produce expected output. Failing build.")
        }
    }

    stage('Check Logs') {
        echo 'Displaying last few lines of the log file...'
        bat 'type calculator.log'
    }



}
