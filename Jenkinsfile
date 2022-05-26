pipeline {
    agent any
    stages{
        stage("Build"){
            steps{
                echo("Build the project")
            }
        }
        
        
        stage("Run UTs"){
            steps{
                echo("Run unit test cases")
            }
        }
            
        stage("Deploy to Dev"){
            steps{
                echo("Automation in DEV")
            }
        }
        
        
        stage("Deploy to QA"){
            steps{
                echo("Automation script execution in QA deployment")
            }
        }
        
        
        stage("Run Automation test to QA"){
            steps{
                echo("Automation script execution")
            }
                        
        }
        
        
                stage("Deploy to Stage"){
            steps{
                echo("Automation script execution in Stage deployment")
            }
        }
        
        
        stage("Run Automation test to Stage"){
            steps{
                echo("Automation script execution on stage")
            }
                        
        }
        
        stage("Deploy to PROD"){
            steps{
                echo("Deploy to prod")
            }
        }
        
    }
}