pipeline {
    agent any

    // הגדרת כלי ה-Maven לפי השם המדויק שמצאת
    tools {
        maven 'Maven 3.9'
    }

    parameters {
        string(name: 'TAGS', defaultValue: '@book', description: 'Enter @tag or leave empty for all')
    }

    stages {
        stage('Cleanup') {
            steps {
                // מנקה תוצאות allure ישנות
                sh 'rm -rf target/allure-results'
            }
        }

        stage('Run Automation') {
            steps {
                // עכשיו ג'נקינס יזהה את פקודת mvn
                sh "mvn clean test -Dcucumber.filter.tags='${params.TAGS}'"
            }
        }
    }

    post {
        always {
            // יצירת דוח Allure בסיום
            allure includeProperties: false, results: [[path: 'target/allure-results']]
        }
    }
}