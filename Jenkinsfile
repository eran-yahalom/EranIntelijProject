pipeline {
    agent any

    parameters {
        string(name: 'TAGS', defaultValue: '', description: 'Leave empty for ALL, or use @book')
    }

    stages {
        stage('Cleanup & Checkout') {
            steps {
                // מנקה תוצאות קודמות כדי שהדו"ח יהיה אמין
                sh 'rm -rf target/allure-results'
                git branch: 'main', url: 'https://github.com/EranIntelijProject/drmoShopProjectDuplicate.git'
            }
        }

        stage('Run Automation') {
            steps {
                // הרצה עם ה-Tags מהפרמטרים
                sh "mvn clean test -Dcucumber.filter.tags='${params.TAGS}'"
            }
        }
    }

    post {
        always {
            // הנתיב הקריטי שמתאים ל-pom.xml שלך
            allure includeProperties: false, results: [[path: 'target/allure-results']]
        }
    }
}