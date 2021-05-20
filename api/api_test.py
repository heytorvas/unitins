import requests

URL = 'http://localhost:5000/api/'

res = requests.post('{}login/'.format(URL), json={"email":"teste@teste.com", "password": "1234"})
if res.ok:
    print(res.json())