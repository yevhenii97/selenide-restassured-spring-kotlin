pipeline {
    agent any

    environment {
        GRADLE_OPTS = "-Xmx2g -Dfile.encoding=UTF-8"
        API_TESTS_STATUS = 'SUCCESS'
        UI_TESTS_STATUS = 'SUCCESS'
        TELEGRAM_TOKEN = "8189233926:AAFJ8u0FSOw-vFySDnGTuwvsSxn1BHpupt4"
        TELEGRAM_CHAT_ID = "7659349476"
    }

    stages {
        stage('Build') {
            steps {
                bat './gradlew clean build -x test'
            }
        }

         stage('API Tests') {
             steps {
                 catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    bat './gradlew :requres-pipeline-tests:clean :requres-pipeline-tests:test --info'
                 }
             }
             post {
                 failure {
                     script {
                         env.API_TESTS_STATUS = 'FAILURE'
                     }
                 }
             }
         }

         stage('UI Tests') {
              steps {
                 catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    bat './gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info'
                 }
              }
              post {
                  failure {
                      script {
                          env.UI_TESTS_STATUS = 'FAILURE'
                      }
                  }
              }
         }

}

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
            junit '**/build/test-results/test/*.xml'
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
                        if (env.API_TESTS_STATUS == 'SUCCESS') {
                            bat """
                                curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                                     -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                                     -d text="API tests passed successfully"
                            """
                        }

                        if (env.UI_TESTS_STATUS == 'SUCCESS') {
                            bat """
                                curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                                     -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                                     -d text="UI tests passed successfully"
                            """
                        }
                    }
                }

                failure {
                    script {
                        if (env.API_TESTS_STATUS == 'FAILURE') {
                            bat """
                                curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                                     -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                                     -d text="API tests failed"
                            """
                        }

                        if (env.UI_TESTS_STATUS == 'FAILURE') {
                            bat """
                                curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage ^
                                     -d chat_id=${env.TELEGRAM_CHAT_ID} ^
                                     -d text="UI tests failed"
                            """
                        }
                    }
                }
    }
}

