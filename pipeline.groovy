pipeline{
    agent { label 'verisoft-2' }

    environment{
        PROJECT_NAME = 'myFirstProject'

    }
    parameters{
        choice(choices: ["cake", "bread", "milk"], name: "Food")
        string(defaultValue: "Yshai", name: "lastName", description: "myName")
    }
    options{
        skipStagesAfterUnstable()
        disableConcurrentBuild()
    }
    stage{
        stage('print parameters') {
            steps {
                echo "parameters ${params.Food}"
                echo "parameters ${params}"
                echo "environment variable ${env.PROJECT_NAME}"
                echo "${env.BUILD_NAME}"
            }
        }
        stage('abort if true') {
            when {
                expression {
                    return params.isTrue
                }
            }
            steps {
                script {
                    currentBuild.result = 'UNSTABLE'
                }
            }
        }
        stage('input') {
            steps {
                input(message: "click if to continue run", ok: "ok")
            }
        }
        stage('Build') {
            steps {
                script {
                    echo 'Building ...'

                }
            }

        }

        stage('Test') {

            steps {
                echo 'Testing ... '
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying ... '

            }
        }

    }
}