pipeline {
    agent any

    tools {
        maven 'Maven 3.9' // וודא שזה השם ב-Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test Execution') {
            steps {
                // הוספת PATH ליתר ביטחון כדי ש-Maven ימצא את ה-Chromium
                script {
                    sh 'mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -DthreadCount=4'
                }
            }
        }
    }

    post {
        always {
            // יצירת הדו"ח הגרפי של Allure בסיום כל ריצה
            allure includeProperties: false, results: [[path: 'allure-results']]
        }
        success {
            echo 'Tests passed successfully!'
        }
        failure {
            echo 'Tests failed. Check Allure report for details.'
        }
    }
}