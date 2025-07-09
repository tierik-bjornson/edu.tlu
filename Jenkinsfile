pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK'
    }

    environment {
        JAR_NAME = "edu.tlu-0.0.1-SNAPSHOT.jar"
        PID_FILE = "app.pid"
        LOG_FILE = "app.log"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Cloning source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Running Maven clean package..."
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo "Running unit tests..."
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo "🗄Archiving built JAR..."
                archiveArtifacts artifacts: "target/${JAR_NAME}", fingerprint: true
            }
        }

        stage('Stop Previous App') {
            steps {
                echo "Stopping previous app if running..."
                sh '''
                    if [ -f ${PID_FILE} ]; then
                        PID=$(cat ${PID_FILE})
                        if ps -p $PID > /dev/null; then
                            echo "Found running app with PID: $PID. Killing..."
                            kill -9 $PID
                            echo "Killed."
                        else
                            echo "No process found with PID $PID. Removing stale PID file."
                        fi
                        rm -f ${PID_FILE}
                    else
                        echo "No PID file found."
                    fi
                '''
            }
        }

        stage('Run App') {
            steps {
                echo "🚀 Running new JAR in background with nohup..."
                sh '''
                    nohup java -jar target/${JAR_NAME} > ${LOG_FILE} 2>&1 &
                    echo $! > ${PID_FILE}
                    sleep 5
                    echo "Started with PID $(cat ${PID_FILE})"
                '''
            }
        }

        stage('Check App Log') {
            steps {
                echo "🔍 Showing last 20 lines of log..."
                sh 'tail -n 20 ${LOG_FILE}'
            }
        }
    }

    post {
        always {
            echo "✅ Pipeline completed."
        }
        failure {
            echo "❌ Pipeline failed."
        }
    }
}
