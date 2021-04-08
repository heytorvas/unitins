from flask import Flask
from util import query_string

app = Flask(__name__)

@app.route("/")
def times():
    args = query_string()
    return f'{int(args[0]) * int(args[1])}'

if __name__ == "__main__":
    app.run()