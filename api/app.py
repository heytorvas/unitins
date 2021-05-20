from flask import Flask, request, jsonify
app = Flask(__name__)

@app.route('/api/login/', methods=['POST'])
def login():
    content = request.json
    print(content['email'])
    return jsonify({"message": "Login successfully"})

if __name__ == '__main__':
    app.run(host= '0.0.0.0',debug=True)