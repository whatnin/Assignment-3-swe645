pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "my-web-app"
        DOCKER_REGISTRY = "sha256:49b9f1b801bb1cab01e82b11454946f974b69c00c115afe6ac8d8deb26848360" // e.g., Docker Hub or private registry
        KUBECONFIG = credentials('docker-desktop') // Replace with Jenkins credential ID for kubeconfig
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pull code from the Git repository
                git 'https://github.com/whatnin/SWE-645.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the Docker image
                script {
                    docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                // Push the Docker image to a registry
                script {
                    docker.withRegistry("https://${DOCKER_REGISTRY}", 'chinmayee2001') {
                        docker.image("${DOCKER_IMAGE}").push("latest")
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Use kubectl to apply deployment and service YAML files
                sh '''
                    kubectl apply -f deployment.yaml
                    kubectl apply -f service.yaml
                '''
            }
        }
    }

    post {
        always {
            // Archive results, clean workspace, etc.
            cleanWs()
        }
    }
}
