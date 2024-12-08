pipeline {
    agent any

    environment {
        DOCKER_COMPOSE = 'docker-compose' // Docker Compose command
        DOCKER_IMAGE = 'producia-image'   // Image name
    }

    stages {
        stage('Checkout') {
            steps {

                git 'https://github.com/Douaesb/Producia.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {

                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }

        stage('Run Tests with Docker Compose') {
            steps {
                script {
                    // Run tests inside Docker Compose
                    sh 'docker-compose -f docker-compose.yml up --build -d'
                    // Wait for the app to be fully started
                    sh 'docker-compose exec app mvn test'
                    // Stop the services after tests
                    sh 'docker-compose down'
                }
            }
        }

        stage('Cleanup') {
            steps {
                // Clean up Docker images and containers
                sh 'docker system prune -f'
            }
        }
    }

    post {
        always {
            // Clean up and remove temporary files after the pipeline
            cleanWs()
        }
    }
}
