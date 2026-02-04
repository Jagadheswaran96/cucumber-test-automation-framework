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
                    script {
                        if (fileExists('target/rerun.txt')) {
                            def failedTests = readFile('target/rerun.txt').trim()
                            if (failedTests.length() > 0) {
                                echo '⚠ Failed scenarios found — Re-running only failed tests'
                                bat 'mvn test -Prerun'
                            } else {
                                echo '✅ No failed scenarios to rerun'
                            }
                        }
                    }
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
            // Publish Cucumber Report
            cucumber 'target/cucumber.json'

            // Publish Allure Report
            publishHTML([
                reportDir: 'target/allure-maven-plugin',
                reportFiles: 'index.html',
                reportName: 'Allure Report',
                keepAll: true,
                alwaysLinkToLastBuild: true
            ])

            // Archive artifacts
            archiveArtifacts artifacts: 'videos/**/*.avi', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/ExtentReport.html', allowEmptyArchive: true
        }

        success {
            echo '🎉 All Cucumber tests passed'
        }

        failure {
            echo '❌ Tests failed even after rerun — Check Allure Report'
        }

        cleanup {
            cleanWs()
        }
    }
}