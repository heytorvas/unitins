from flask import Flask, render_template, jsonify
from dao import Conexao


database = Conexao()
database.criar_banco()
app = Flask(__name__)


@app.route('/')
def index():
    return render_template('index.html')

@app.route('/ws/cep/<input_cep>/')
def webservice_find_cep(input_cep):
    resultado = database.find_cep(input_cep)
    return jsonify(resultado)

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port='5000')