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
			    rem Create a virtual environment
			    python -m venv venv
			    venv\\Scripts\\activate.bat

			    rem Install dependencies
			    pip install -r requirements.txt

			    rem Run the test using pytest
			    pytest iniciar.py
			'''
		    }
        	}
        
	}
}
