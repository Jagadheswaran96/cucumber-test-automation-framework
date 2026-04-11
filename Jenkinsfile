pipeline {

// webhook
    agent any

    tools {
        maven 'Maven'
        //jdk 'JDK17'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }
    
    parameters {

        choice(
            name: 'TEST_TAG',
            choices: ['@smoke','@regression','@smoke or @regression'],
            description: 'Select test suite'
        )

        string(
            name: 'THREADS',
            defaultValue: '3',
            description: 'Parallel scenario threads'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
         stage('Install Browsers + Drivers') {
            steps {
                bat '''
                :: Install Google Chrome + ChromeDriver
                choco install googlechrome -y
                choco install chromedriver -y

                :: Install Firefox + GeckoDriver
                choco install firefox -y
                choco install geckodriver -y

                :: Install Microsoft Edge + EdgeDriver
                choco install microsoft-edge -y
                choco install edgedriver -y
                '''
            }
        }

        stage('Clean & Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Cross Browser Execution') {

            matrix {

                axes {
                    axis {
                        name 'BROWSER'
                        values 'chrome', 'edge', 'firefox'
                    }
                }
				
				stages {
                stage('Run Cucumber Tests') {
					steps {
						bat "mvn test"
					}
            
					post {
						always {
							script {
								if (fileExists('target/failed_scenarios.txt')) {
									def failedTests = readFile('target/failed_scenarios.txt').trim()
									if (failedTests.length() > 0) {
										echo 'Failed scenarios found — Re-running only failed tests'
										bat 'mvn test -Prerun'
									} else {
										echo 'No failed scenarios to rerun'
									}
								}
							}
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
            cucumber 'target/cucumber.html'

            // Publish Allure Report
            publishHTML([
                reportDir: 'target/allure-maven-plugin',
                reportFiles: 'index.html',
                reportName: 'Allure Report',
                keepAll: true,
                alwaysLinkToLastBuild: true
                allowMissing: false
            ])

            // Archive artifacts
            archiveArtifacts artifacts: 'video/**/*.avi', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/ExtentReport.html', allowEmptyArchive: true
        }

        success {
            echo 'All Cucumber tests passed'
        }

        failure {
            echo 'Tests failed even after rerun — Check Allure Report'
        }

        cleanup {
            cleanWs()
        }
    }
}