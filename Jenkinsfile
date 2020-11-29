properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactDaysToKeepStr: '10', artifactNumToKeepStr: '30', daysToKeepStr: '365', numToKeepStr: '']]]);

timestamps {
    node('master'){

        workspace = pwd()
        // Mark the code checkout 'stage'....
        stage('Checkout'){
            // Get some code from a GitHub repository
            checkout scm
        }
        stage('Clone and check tasks'){
            sh '''
            BRANCH_CLEAN=$(echo $BRANCH_NAME | sed \'s#feature/##g\' | perl -pe \'s/[^\\w]+//g\' | perl -pe \'s/$//g\')
            VERSION=$(date +%Y.%m.%d)
            export BRANCH_CLEAN
            ls -la
            java -version
           ./gradlew tasks
            '''

        }

        stage('Test and check'){
            sh './gradlew check'
            sh './gradlew clean test --no-daemon'
            sh 'find $PWD -mindepth 1 -maxdepth 1 -exec du -hs {} + 2>/dev/null | sort -hr | head -20 '
        }

        stage('Unit test'){

        sh './gradlew clean test --no-daemon'
        sh './gradlew build -x check'


        }

        stage('Create artifact and copy to DEV'){

        //create artifact and build docker container

            sh 'echo $PATH'
        }

        stage('Deploy to Pre-Prod'){
            sh 'find $PWD -mindepth 1 -maxdepth 1 -exec du -hs {} + 2>/dev/null | sort -hr | head -20'
        }

    }
  }
