import psycopg2
from psycopg2.extras import NamedTupleCursor
import time

class Conexao:

  def iniciar_conexao(self):
    try:
      global connection
      connection = psycopg2.connect(user="admin",
                                    password="123456789",
                                    host="172.28.1.8",
                                    #host="127.0.0.1",
                                    port="5432",
                                    database="db_cep")
      global cursor
      cursor = connection.cursor()

    except (Exception, psycopg2.Error) as error :
      print("falha ao iniciar_conexao")
    else:
      print("sucesso ao iniciar_conexao")


  def fechar_conexao(self):
    try:
      cursor.close()
      connection.close()
    except (Exception, psycopg2.Error) as error:
      print ("falha ao fechar_conexao", error)
    else:
      print("sucesso ao fechar_conexao")


  def criar_banco(self):
  	time.sleep(120)
	
	
	with open('create-database.sql', 'r') as f:
		for linha in f.read().splitlines():
			self.iniciar_conexao()

			try:
				postgreSQL_select_Query = linha
				cursor.execute(postgreSQL_select_Query)
				connection.commit()


			except (Exception, psycopg2.Error) as error:
				print("erro do PostgreSQL", error)

			self.fechar_conexao()

			
  def find_cep(self, input_cep):
    try:
      self.iniciar_conexao()
      postgreSQL_select_Query = "SELECT cep, endereco_completo, cidade, estado FROM tb_ceps WHERE cep = " + input_cep
      cursor.execute(postgreSQL_select_Query)
      resultado_consulta = cursor.fetchall() 
      for row in resultado_consulta:
        print(row)

    except (Exception, psycopg2.Error) as error:
      print("erro do PostgreSQL", error)
    else:
      return resultado_consulta
    finally:
      self.fechar_conexao()

