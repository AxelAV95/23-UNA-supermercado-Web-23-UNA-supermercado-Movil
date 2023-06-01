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
			    C:\Users\AxelAV\AppData\Local\Programs\Python\Python311\python.exe -m pytest sesion.py
			'''
		    }
        	}
        
	}
}
