pipeline {
    agent any
    stages {
        stage('Cloning our Git') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh "mvnw clean test"
            }
        }

        stage('Build') {
            steps {
                sh "mvnw clean package"
            }
        }

        stage('Build images') {
            steps {
                 sh "docker build -t exam-harry-potter:1.0 ."
            }
        }

        stage('Deploy') {
            steps{
                sh "docker run -d -p 80:8080 -t exam-harry-potter:1.0"
            }
        }
    }
}
