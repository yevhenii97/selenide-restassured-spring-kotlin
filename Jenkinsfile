pipeline {
    agent any

    environment {
        GRADLE_OPTS = "-Xmx2g -Dfile.encoding=UTF-8"
        TELEGRAM_TOKEN = "8189233926:AAFJ8u0FSOw-vFySDnGTuwvsSxn1BHpupt4"
        TELEGRAM_CHAT_ID = "7659349476"
    }

    options {
        skipDefaultCheckout()
    }

    stages {
        stage('Build') {
            steps {
                bat './gradlew clean build -x test'
            }
        }

        stage('API Tests') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        bat './gradlew :requres-pipeline-tests:clean :requres-pipeline-tests:test --info'
                    }
                }
            }
        }

        stage('UI Tests') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        bat './gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info'
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

        success {
            script {
                def apiTestPassed = currentBuild.rawBuild.getExecutedStages().find { it.name == 'API Tests' }?.status?.toString() == 'SUCCESS'
                def uiTestPassed = currentBuild.rawBuild.getExecutedStages().find { it.name == 'UI Tests' }?.status?.toString() == 'SUCCESS'

                if (apiTestPassed) {
                    bat """
                        curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                             -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                             -d text="✅ API tests passed successfully"
                    """
                }

                if (uiTestPassed) {
                    bat """
                        curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                             -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                             -d text="✅ UI tests passed successfully"
                    """
                }
            }
        }

        failure {
            script {
                def apiTestFailed = currentBuild.rawBuild.getExecutedStages().find { it.name == 'API Tests' }?.status?.toString() == 'FAILURE'
                def uiTestFailed = currentBuild.rawBuild.getExecutedStages().find { it.name == 'UI Tests' }?.status?.toString() == 'FAILURE'

                if (apiTestFailed) {
                    bat """
                        curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                             -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                             -d text="❌ API tests failed"
                    """
                }

                if (uiTestFailed) {
                    bat """
                        curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                             -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                             -d text="❌ UI tests failed"
                    """
                }
            }
        }
    }
}
