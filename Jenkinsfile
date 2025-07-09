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
                echo "Cloning source code.."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Running Maven Clean Package..."
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo "Running Maven Tests..."
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo "Archiving built .jar..."
                archiveArtifacts artifacts: "target/${JAR_NAME}", fingerprint: true
            }
        }

        stage('Stop Previous App') {
            steps {
                echo "Killing any old app process..."
                sh '''
                PID=$(ps -ef | grep "${JAR_NAME}" | grep -v grep | awk '{print $2}')
                if [ ! -z "$PID" ]; then
                    echo "Found running app with PID: $PID. Killing it..."
                    kill -9 $PID
                else
                    echo "No running app found."
                fi
                '''
            }
        }

        stage('Run App') {
            steps {
                echo "Running built .jar in background..."
                sh '''
                nohup java -jar target/${JAR_NAME} > app.log 2>&1 &
                sleep 5
                echo "App should be running now."
                '''
            }
        }

        stage('Check App Log') {
            steps {
                echo "Tailing app.log to see Spring Boot start..."
                sh 'tail -n 20 app.log'
            }
        }
    }

    post {
        always {
            echo "Pipeline finished. App should be running if no errors."
        }
        failure {
            echo "Pipeline failed."
        }
    }
}
