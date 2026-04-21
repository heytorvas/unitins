using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Sextou
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            using (var ws = new WSCorreios.AtendeClienteClient())
            {
                try
                {
                    var resultado = ws.consultaCEP(tbCEP.Text);
                    tbAddress.Text = resultado.end;
                    tbComplement.Text = resultado.complemento2;
                    tbCity.Text = resultado.cidade;
                    tbNeighborhood.Text = resultado.bairro;
                    tbState.Text = resultado.uf;
                }

                catch(Exception ex)
                {
                    MessageBox.Show(ex.Message, "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            SqlConnection sql = new SqlConnection("Data Source=DESKTOP-CV43IN8\\SQLEXPRESS;Initial Catalog=sextou;Integrated Security=True");
            SqlCommand command = new SqlCommand("insert into correios(cep, address, complement, city, neighborhood, state) values(@cep, @address, @complement, @city, @neighborhood, @state)", sql);
            command.Parameters.Add("@cep", SqlDbType.VarChar).Value = tbCEP.Text;
            command.Parameters.Add("@address", SqlDbType.VarChar).Value = tbAddress.Text;
            command.Parameters.Add("@complement", SqlDbType.VarChar).Value = tbComplement.Text;
            command.Parameters.Add("@city", SqlDbType.VarChar).Value = tbCity.Text;
            command.Parameters.Add("@neighborhood", SqlDbType.VarChar).Value = tbNeighborhood.Text;
            command.Parameters.Add("@state", SqlDbType.VarChar).Value = tbState.Text;

            try
            {
                sql.Open();
                command.ExecuteNonQuery();
                MessageBox.Show("Endereço cadastrado com sucesso!");
                tbCEP.Text = "";
                tbAddress.Text = "";
                tbComplement.Text = "";
                tbCity.Text = "";
                tbNeighborhood.Text = "";
                tbState.Text = "";
            }
            catch(SqlException ex)
            {
                MessageBox.Show("O erro é: " + ex);
            }
            finally
            {
                sql.Close();
            }
        }
    }
}
