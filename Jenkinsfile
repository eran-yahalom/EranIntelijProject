pipeline {
    agent any

    parameters {
        string(name: 'TAGS', defaultValue: '', description: 'Leave empty for ALL, or enter @book')
    }

    stages {
        stage('Cleanup') {
            steps {
                // מנקה תוצאות ישנות
                sh 'rm -rf target/allure-results'
            }
        }

        stage('Run Automation') {
            steps {
                // מריץ את הטסטים על הקוד שג'נקינס כבר משך אוטומטית
                sh "mvn clean test -Dcucumber.filter.tags='${params.TAGS}'"
            }
        }
    }

    post {
        always {
            allure includeProperties: false, results: [[path: 'target/allure-results']]
        }
    }
}