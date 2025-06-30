pipeline {
    agent any

    environment {
        GRADLE_OPTS = "-Xmx2g -Dfile.encoding=UTF-8"
        TELEGRAM_BOT_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
        CHAT_ID = credentials('CHAT_ID')
    }

    stages {
        stage('Build') {
            steps {
                bat './gradlew clean build -x test'
            }
        }

        stage('API Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat './gradlew :requres-pipeline-tests:clean :requres-pipeline-test:test --info'
                }
            }
            post {
                success {
                    script {
                            sendTelegramNotification("API tests have been PASSED")
                    }
                }
                failure {
                    script {
                            sendTelegramNotification("API tests have been FAILED")
                    }
                }
            }
        }

        stage('UI Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat './gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info'
                }
            }
            post {
                success {
                    script {
                            sendTelegramNotification("UI tests have been PASSED")
                    }
                }
                failure {
                    script {
                            sendTelegramNotification("UI tests have been FAILED")
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
            allure([
                results: [
                    [path: 'requres-pipeline-tests/build/allure-results'],
                    [path: 'saucedemo-ui-pipeline-tests/build/allure-results']
                ],
                includeProperties: false
            ])
        }
    }
}

def sendTelegramNotification(String message) {
        bat """
        curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_BOT_TOKEN}/sendMessage ^
            -d chat_id=${env.CHAT_ID} ^
            -d text="${message}"
        """
    }

