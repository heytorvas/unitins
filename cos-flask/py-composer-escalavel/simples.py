from flask import Flask
from elasticapm.contrib.flask import ElasticAPM
from elasticapm.handlers.logging import LoggingHandler
import time
import os


app = Flask(__name__)

apm = ElasticAPM(app, server_url='http://apm-server:8200', service_name='servico-flask', logging=True)

@app.route("/")
def index():
	apm.capture_message('retorno index')
	hostname = os.uname()[1]
	return 'Container Hostname: ' + hostname + '\n'

@app.route("/lag")
def lag():
	time.sleep(20)
	apm.capture_message('retorno lag')
	return 'lagging'

if __name__ == '__main__':
	app.run(host='0.0.0.0', port='5000')