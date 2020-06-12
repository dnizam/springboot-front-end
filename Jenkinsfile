def ProjectId="myproject-278611"
pipeline{
    agent any
    environment {
        Image_name = "gcr.io/${ProjectId}/spring-frontend:V_${BUILD_ID}"
    }
    stages{
        stage('dependancy versions'){
            steps{
                sh '''
                    git --version
                    docker --version
                    mvn --version
                '''
            }    
        }
        stage('git checkout'){
            steps{
                    git 'https://github.com/dnizam/springboot-front-end.git'
            }    
        }
        stage('git test'){
            steps{
                sh '''
                    ls -a
                    cd spring-boot-form-submit-example
                    echo "install dependencies and test internal code ..!"
                    mvn install
                    mvn test
                ''' 
            }    
        }
        stage('build'){
            steps{
                sh '''
                    echo "${Image_name}"
                    echo "build and push docker image for internal app ..!"
                    pwd
                    gcloud builds submit --tag ${Image_name} .
                ''' 
            }    
        }
        stage('deploy'){
            steps{
                sh """
                    gcloud container clusters get-credentials my-app-cluster --zone us-central1-a --project myproject-278611
                    kubectl set image deployment/events-data events-data=${Image_name}
                """
            }    
        }  
    }
}
