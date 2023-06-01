pipeline{
	agent any
	
	stages{
		stage('Test selenium'){
			steps{
				 bat 'start /B npx selenium-side-runner -c "browserName=chrome" Testing.side'
			}
		}
	}
	

}
