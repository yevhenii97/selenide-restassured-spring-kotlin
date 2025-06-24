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
            input {
                        message "Run API tests?"
                        ok "run"
                        submitter "admin,developer"
                    }
            steps {
                bat './gradlew :requres-pipeline-test:clean :requres-pipeline-test:test --info'
            }
        }

        stage('UI Tests') {
            input {
                            message "Run API tests?"
                            ok "run"
                            submitter "admin,developer"
                        }
            steps {
                bat './gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info'
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

//     post {
//         always {
//             archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
//             junit '**/build/test-results/test/*.xml'
//             allure results: [[path: 'requres-pipeline-test/build/allure-results'],
//                              [path: 'saucedemo-ui-pipeline-tests/build/allure-results']],
//                    includeProperties: false
//         }
//     }
