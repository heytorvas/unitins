import socket, sys
print('SERVIDOR...\n')
#recebendo o hostname e IP do socket

soc = socket.socket() #atribuindo o socket a uma variavel
host_name = '0.0.0.0'
# host_name = socket.gethostname()
ip = socket.gethostbyname(host_name)
#configurando a porta
port = 3333
soc.bind((host_name, port))
#print('SOC BIND: {}'.format(soc.bind(host_name)))
print(host_name, '({})'.format(ip))
name = input('DIGITE O USUARIO: ')
#soc.listen(1) #Tentando localizar o socket
soc.listen(0)
print('SOC LISTEN: {}'.format(soc.listen(1)))
print('ESPERANDO CONEXAO...\n')
connection, addr = soc.accept()

print('CONEXAO: {}'.format(connection))
print('ADDR: {}'.format(addr))
print('CONEXAO RECEBIDA DE {}\n'.format(addr[0]))
print('CONEXAO ESTABELECIDA. CONECTADO: {}, ({})\n'.format(addr[0], addr[0]))
#recebendo conexao com o cliente
client_name = connection.recv(1024)
client_name = client_name.decode()
print(client_name + ' CONECTOU')
print('DIGITE TCHAU PARA SAIR!\n')
connection.send(name.encode())
# file_txt = open('file.txt', 'w')
while True:
   message = input('{} > '.format(name))
   if message == 'tchau':
      message = 'Saindo...'
      connection.send(message.encode())
      print("\n")
      break
   connection.send(message.encode())
   message = connection.recv(1024)
   message = message.decode()
   print(client_name, '>', message)