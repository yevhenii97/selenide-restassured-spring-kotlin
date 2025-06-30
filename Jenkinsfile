pipeline {
    agent any

    environment {
        GRADLE_OPTS = "-Xmx2g -Dfile.encoding=UTF-8"
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
                            sendTelegramNotification("API tests have been passed")
                    }
                }
                failure {
                    script {
                            sendTelegramNotification("API tests have NOT been passed")
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
                            sendTelegramNotification("UI tests have been passed")
                    }
                }
                failure {
                    script {
                            sendTelegramNotification("UI tests have NOT been passed")
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
        curl -s -X POST https://api.telegram.org/bot8189233926:AAFJ8u0FSOw-vFySDnGTuwvsSxn1BHpupt4/sendMessage ^
            -d chat_id=7659349476 ^
            -d text="${message}"
        """
    }

