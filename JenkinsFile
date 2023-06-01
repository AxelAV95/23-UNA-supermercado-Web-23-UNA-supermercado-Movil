pipeline{
	agent any
	
	stages{
		stage('Test selenium'){
			steps{
				sh 'selenium-side-runner -c "browserName=chrome" /web/supemercado/SupermercadoWebTesting.side'
			}
		}
	}
	

}
