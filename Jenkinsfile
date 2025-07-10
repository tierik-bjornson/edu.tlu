pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK'
    }

    environment {
        JAR_NAME = "edu.tlu-0.0.1-SNAPSHOT.jar"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "📥 Cloning source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "⚙️ Building Spring Boot JAR..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo "✅ Running tests..."
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo "🗄️ Archiving built JAR..."
                archiveArtifacts artifacts: "target/${JAR_NAME}", fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                echo "🚀 Deploying with systemd..."
                sh '''
                echo "Reloading systemd..."
                sudo systemctl daemon-reload

                echo "Restarting edu-tlu service..."
                sudo systemctl restart edu-tlu

                echo "Checking service status..."
                sudo systemctl status edu-tlu --no-pager
                '''
            }
        }

    }

    post {
        always {
            echo "✅ Pipeline finished."
        }
        success {
            echo "🎉 Deployment succeeded."
        }
        failure {
            echo "❌ Pipeline failed."
        }
    }
}

