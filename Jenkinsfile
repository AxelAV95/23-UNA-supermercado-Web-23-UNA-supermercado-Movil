pipeline{
	agent any
	
	stages{
		stage('Test selenium'){
			steps{
				sh 'npx selenium-side-runner -c "browserName=chrome" SupermercadoWebTesting.side'
			}
		}
	}
	

}
