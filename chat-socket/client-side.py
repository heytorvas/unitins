import socket, sys
print('CLIENTE...\n')
file_txt = open('file.txt', 'w')
#recebendo o hostname e IP do socket

soc = socket.socket() #atribuindo o socket a uma variavel
# shost = socket.gethostname()
# file_txt.writelines('SHOST: {}\n'.format(shost))
# ip = socket.gethostbyname(shost)
# #recebendo a info pra conectar no servidor
# print(shost, '({})'.format(ip))
server_host = input('DIGITE O IP DO SERVIDOR: ')
file_txt.writelines('IP: {}\n'.format(server_host))
name = input('DIGITE O USUARIO: ')
#configurando a porta
port = 3333
print('TENTANDO CONECTAR NO SERVIDOR: {}, ({})'.format(server_host, port))
soc.connect((server_host, port))
print("CONECTADO...\n")
soc.send(name.encode())
file_txt.writelines('NOME: {}\n'.format(name.encode()))
#recebendo com o servidor
server_name = soc.recv(1024)
server_name = server_name.decode()
file_txt.writelines('NOME SERVIDOR: {}\n'.format(server_name))
print('{} ENTROU...'.format(server_name))
print('DIGITE TCHAU PARA SAIR!\n')

while True:
   message = soc.recv(1024)
   file_txt.writelines('MENSAGEM RECEBIDA DO SERVIDOR (RECV1024): {}\n'.format(message))
   message = message.decode()
   file_txt.writelines('MENSAGEM RECEBIDA DO SERVIDOR: {}\n'.format(message))
   print(server_name, '>', message)
   message = input(str('{} > '.format(name)))
   if message == 'tchau':
      message = 'Saindo...'
      soc.send(message.encode())
      print("\n")
      break
   file_txt.writelines('MENSAGEM ENTREGUE: {}\n'.format(message))
   file_txt.writelines('MENSAGEM ENTREGUE ENCODE: {}\n'.format(message.encode()))
   soc.send(message.encode())