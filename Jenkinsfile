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

        stage('Build') {
            steps {
                bat 'mvn clean install' // sh for linux and ios
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('JaCoCo') {
            steps {
                jacoco(
                        execPattern: 'target/jacoco.exec',
                        classPattern: 'target/classes',
                        sourcePattern: 'src/main/java'
                )
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
    }
}