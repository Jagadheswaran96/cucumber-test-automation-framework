pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
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
            // Publish Cucumber JSON
            cucumber 'target/cucumber.json'

            // Publish Allure Report
            publishHTML([
                reportDir: 'target/allure-maven-plugin',
                reportFiles: 'index.html',
                reportName: 'Allure Report',
                keepAll: true,
                alwaysLinkToLastBuild: true
            ])

            // Archive videos & Extent
            archiveArtifacts artifacts: 'videos/**/*.avi', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/ExtentReport.html', allowEmptyArchive: true
        }

        success {
            echo '✅ Cucumber tests passed'
        }

        failure {
            echo '❌ Cucumber tests failed – check Allure report'
        }

        cleanup {
            cleanWs()
        }
    }
}