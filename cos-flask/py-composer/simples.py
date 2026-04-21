from flask import Flask
from elasticapm.contrib.flask import ElasticAPM
from elasticapm.handlers.logging import LoggingHandler
import time

app = Flask(__name__)

app.config['ELASTIC_APM'] = {
	'APP_NAME': 'mytestamp',
	'SERVICE_NAME': 'myapp',
	'SERVER_URL': 'http://172.28.1.6:8200',
}

apm = ElasticAPM(app)
#apm = ElasticAPM(app, logging=True)

@app.route('/', methods= ['GET', 'POST'])
def index():
	apm.capture_message('retorno inicial')
	return 'inicial'

@app.route('/teste-lag', methods= ['GET', 'POST'])
def slow():
	time.sleep(20)
	apm.capture_message('retorno ok')
	return 'durma'

if __name__ == '__main__':
	app.run(host='0.0.0.0', port='5000')