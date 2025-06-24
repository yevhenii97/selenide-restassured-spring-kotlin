pipeline {
    agent any

    environment {
//         JAVA_HOME = "${tool 'jdk17'}"
//         PATH = "${JAVA_HOME}/bin;${env.PATH}"
    }

    stages {
        stage('Build') {
            steps {
                bat './gradlew clean build -x test'
            }
        }

        stage('API Tests') {
            steps {
                bat './gradlew :requres-pipeline-test:clean :requres-pipeline-test:test --info'
            }
        }

        stage('UI Tests') {
            steps {
                bat './gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
            junit '**/build/test-results/test/*.xml'
            allure includeProperties: false, jdk: ''
        }


// pipeline {
//     agent any
//
//     environment {
//         GRADLE_OPTS = "-Dorg.gradle.jvmargs='-Xmx2g -Dfile.encoding=UTF-8'"
//     }
//
//     stages {
//         stage('Build') {
//             steps {
//                 sh './gradlew clean build -x test'
//             }
//         }
//
//         stage('API Tests') {
//             steps {
//                 sh './gradlew :requres-pipeline-test:clean :requres-pipeline-test:test --info'
//                 allure([
//                     includeProperties: false,
//                     jdk: '',
//                     results: [[path: 'requres-pipeline-test/build/allure-results']]
//                 ])
//             }
//         }
//
//         stage('UI Tests') {
//             steps {
//                 sh './gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info'
//                 allure([
//                     includeProperties: false,
//                     jdk: '',
//                     results: [[path: 'ui-pipeline-test/build/allure-results']]
//                 ])
//             }
//         }
//     }
//
//     post {
//         always {
//             junit '**/build/test-results/test/*.xml'
//         }
//     }
// }
