pipeline {
    agent any

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    environment {
        // Change these to your team's email addresses (comma separated)
        TEAM_EMAILS = 'teammate1@company.com, teammate2@company.com'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Cross-Browser Tests') {
            steps {
                // Browsers are defined in testng.xml
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }
    }

    post {
        always {
            // Extent Report: viewable from the job page in Jenkins
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'report',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])

            // Email only the Extent report
            emailext(
                to: "${env.TEAM_EMAILS}",
                subject: "${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                mimeType: 'text/html',
                body: """
                    <p>Automation run finished with status: <b>${currentBuild.currentResult}</b></p>
                    <p>The Extent report is attached. Open it in a browser.</p>
                """,
                attachmentsPattern: 'report/ExtentReport.html'
            )
        }
    }
}