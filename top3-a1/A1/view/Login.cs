using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using A1.dao;
using A1.model;

namespace A1
{
    public partial class Login : Form
    {

        public Login()
        {
            InitializeComponent();
        }

        private void btnLoginClear_Click(object sender, EventArgs e)
        {
            tbLoginEmail.Text = "";
            tbLoginPassword.Text = "";
        }

        private void btnLoginSignIn_Click_1(object sender, EventArgs e)
        {
            Main main = new Main();
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM sysuser where email LIKE '" + tbLoginEmail.Text +
                               "' AND password LIKE '" + tbLoginPassword.Text + "'";
            SqlDataReader dr = Connection.select(cmd);

            if (dr.HasRows)
            {
                if (dr.Read())
                {
                    string typeUser = dr[6].ToString();
                    if (typeUser.Equals("Employee"))
                    {
                        main.Show();
                        main.SetLabel(typeUser);
                        this.Hide();
                    }
                    else if (typeUser.Equals("Adminstrator"))
                    {
                        main.Show();
                        main.SetLabel(typeUser);
                        this.Hide();
                    }
                }
            }
            else
            {
                MessageBox.Show("User not found!");
                btnLoginClear_Click(sender, e);
            }
        }

        private void Login_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }
    }
}
