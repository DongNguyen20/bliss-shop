pipeline {
    agent any

    environment {
        IMAGE_NAME = 'dong020/bliss-shop-ui'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://github.com/DongNguyen20/bliss-shop.git'
            }
        }
        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t ${IMAGE_NAME} ./ui'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                // Push Docker image lên DockerHub
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh 'docker login -u $USERNAME -p $PASSWORD'
                    sh 'docker push ${IMAGE_NAME}'
                }
            }
        }
        stage('Deploy Docker Image') {
            steps {
                sh 'docker pull ${IMAGE_NAME}'
                sh 'docker stop bliss-shop-ui || true && docker rm bliss-shop-ui || true'
                sh 'docker run -d --name bliss-shop-ui -p 8080:8080 ${IMAGE_NAME}'
            }
        }
    }
}
