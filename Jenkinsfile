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
			    "C:/Users/AxelAV/AppData/Local/Programs/Python/Python311/python.exe" -m pytest sesion.py
			'''
		    }
        	}
		
		 stage('Deploy to FTP') {
		    steps {
			bat '''
			    git config git-ftp.url "ftp://192.168.100.252"
			    git config git-ftp.user "ftptest"
			    git config git-ftp.password "admin"
			    start git ftp push
			    start git ftp init
			    echo "new content" >> index.txt
			    git commit index.txt -m "Add new content"
			    git push
			    start git ftp push
                	'''
		    }
        }
        
	}
}
