pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Khaleddamascene/Temperature_Console.git'
            }
        }

        stage('Build, Test and Coverage') {
            steps {
                bat 'mvn clean verify'
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco(
                        execPattern: 'target/jacoco.exec',
                        classPattern: 'target/classes',
                        sourcePattern: 'src/main/java'
                )

                archiveArtifacts(
                        artifacts: 'target/site/jacoco/**',
                        allowEmptyArchive: false
                )
            }
        }
    }
}