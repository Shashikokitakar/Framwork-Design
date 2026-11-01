pipeline {
    agent any

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    environment {
        // Change these to your team's email addresses (comma separated)
        TEAM_EMAILS = 'shashikokitakar2606@gmail.com, shashikokitakar95v2@gmail.com'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean Old Reports') {
            steps {
                // Make sure an old report from Git or a previous run is never sent
                bat 'if exist report rmdir /s /q report'
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
            // 1. Email first, so a problem in a later step can never block it
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

            // 2. Extent Report link on the job page (needs the HTML Publisher plugin)
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'report',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])

            // 3. Keep the report downloadable from the build page
            archiveArtifacts artifacts: 'report/**', allowEmptyArchive: true
        }
    }
}