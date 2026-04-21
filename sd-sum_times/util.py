from flask import request

def query_string():
    args_list = []
    args_list.append(request.args.get('n1'))
    args_list.append(request.args.get('n2'))
    return args_list