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
            script {
               def runApi = input(message: 'Run API tests?', ok: 'Run')
                  if (runApi) {
                    bat './gradlew :requres-pipeline-test:clean :requres-pipeline-test:test --info'
               }
            }
        }
    }

    stage('UI Tests') {
        steps {
            script {
                def runUI = input(message: 'Run UI tests?', ok: 'Run')
                if (runUI) {
                    bat './gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info'
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
                    [path: 'requres-pipeline-test/build/allure-results'],
                    [path: 'saucedemo-ui-pipeline-tests/build/allure-results']
                ],
                includeProperties: false
            ])
        }
    }
}
}