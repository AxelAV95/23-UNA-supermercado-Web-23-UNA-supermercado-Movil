pipeline{
	agent any
	
	stages{
		stage('Test selenium'){
			steps{
				  bat 'start /B npx selenium-side-runner -c "browserName=chrome" Testing.side'
			}
		}

		stage('Test Python') {
		    steps {
			 bat '''
			    rem Install dependencies
			    python -m pip install -r requirements.txt
			    rem Run the test using pytest
			    python -m pytest iniciar.py
			'''
		    }
        	}
        
	}
}
