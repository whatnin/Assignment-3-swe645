pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "likhithn22/assignment3:latest"
        DOCKER_TAG = "${env.BUILD_ID}"
        KUBECONFIG = "${WORKSPACE}/survey.yaml"
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/whatnin/Assignment-3-swe645.git'
            }
        }
        
        stage('Build Project') {
            steps {
                script {
                    dir('demo') {
                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }
        
        stage('Verify JAR file') {
            steps {
                sh 'ls -l demo/target/'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("likhithn22/assignment3:latest", "-f demo/Dockerfile .")
                }
            }
        }
        
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', '6ec661e1-9d28-4006-965a-67643ea0af87') {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'kubernetes-token', variable: 'TOKEN')]) {
                        sh """
                            sed -i 's|token:.*|token: ${TOKEN}|g' survey.yaml
                        """
                    }
                    
                    withEnv(["KUBECONFIG=${WORKSPACE}/survey.yaml"]) {
                        sh 'kubectl apply -f deployment.yaml'
                        sh 'kubectl apply -f service.yaml'
                        sh 'kubectl rollout restart deployment/survey-deployment'
                        sh 'kubectl rollout status deployment/survey-deployment'
                    }
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
