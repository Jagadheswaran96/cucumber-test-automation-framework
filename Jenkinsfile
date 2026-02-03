pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Jagadheswaran96/cucumber-test-automation-framework.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'mvn allure:report'
            }
        }

    }
    
    post {
	    always {
	        archiveArtifacts artifacts: 'target/videos/*.avi', allowEmptyArchive: true
	        archiveArtifacts artifacts: 'target/ExtentReport.html', allowEmptyArchive: true
	    }
	}
    
}