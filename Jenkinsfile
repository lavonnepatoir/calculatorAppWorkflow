pipeline {
  agent any

  stages {
    stage('Build App') {
      steps {
        echo 'Building Java project with Maven...'
        bat 'mvn clean package'
      }
    }

    stage('Run JMeter Load Test') {
      steps {
        echo 'Running JMeter load test...'
        bat 'jmeter -n -t "Calculator Test Plan.jmx" -l results.jtl'
      }
    }

    stage('Archive JMeter Results') {
      steps {
        archiveArtifacts artifacts: 'results.jtl', fingerprint: true
      }
    }
  }
}
