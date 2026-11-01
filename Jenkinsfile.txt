pipeline {
    agent any

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser to run tests on')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run browser in headless mode')
        string(name: 'TEST_SUITE', defaultValue: 'testng.xml', description: 'Suite file to run')
    }

    // Uncomment if you configured Maven/JDK under Manage Jenkins > Tools
    // tools {
    //     maven 'Maven3'
    //     jdk 'JDK21'
    // }

    stages {
        stage('Checkout') {
            steps {
                // For a Pipeline-from-SCM job this is automatic; keep for clarity
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile -DskipTests'
            }
        }

        stage('Run Automation Tests') {
            steps {
                bat "mvn test -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} -Dsurefire.suiteXmlFiles=${params.TEST_SUITE}"
            }
        }
    }

    post {
        always {
            // Test results (works for JUnit and TestNG surefire output)
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'

            // Screenshots, logs, HTML reports
            archiveArtifacts artifacts: 'target/**/*.html, target/screenshots/**, target/*.log', allowEmptyArchive: true
        }
        success {
            echo 'All tests passed.'
        }
        failure {
            echo 'Tests failed - check the Test Result and archived artifacts.'
            // mail to: 'team@example.com', subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}", body: "${env.BUILD_URL}"
        }
        cleanup {
            cleanWs()
        }
    }
}