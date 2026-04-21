using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using A1.model;
using A1.dao;

namespace A1.dao
{
    public class UserDAO
    {
        public User insert(User user)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "INSERT INTO sysuser(name, cpf, email, password, telephone, type_user) " +
                "output INSERTED.id values (@name, @cpf, @email, @password, @telephone, @type_user)";
            cmd.Parameters.AddWithValue("@name", user.Name);
            cmd.Parameters.AddWithValue("@cpf", user.Cpf);
            cmd.Parameters.AddWithValue("@email", user.Email);
            cmd.Parameters.AddWithValue("@password", user.Password);
            cmd.Parameters.AddWithValue("@telephone", user.Telephone);
            cmd.Parameters.AddWithValue("@type_user", user.TypeUser);
            if (Connection.crud(cmd))
                return user;
            return null;
        }

        public User update(User user)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "UPDATE sysuser set name = @name, cpf = @cpf, email = @email, " +
                "password = @password, telephone = @telephone, type_user = @type_user where id = @id";
            cmd.Parameters.AddWithValue("@name", user.Name);
            cmd.Parameters.AddWithValue("@cpf", user.Cpf);
            cmd.Parameters.AddWithValue("@email", user.Email);
            cmd.Parameters.AddWithValue("@password", user.Password);
            cmd.Parameters.AddWithValue("@telephone", user.Telephone);
            cmd.Parameters.AddWithValue("@type_user", user.TypeUser);
            cmd.Parameters.AddWithValue("@id", user.Id);
            if (Connection.crud(cmd))
                return user;
            return null;
        }

        public void delete(User user)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "DELETE FROM sysuser WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", user.Id);
            Connection.crud(cmd);
        }

        public static User findById(int id)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM sysuser WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", id);
            SqlDataReader dr = Connection.select(cmd);

            User user = new User();
            if (dr.HasRows)
            {
                dr.Read();
                user.Id = (int)dr["id"];
                user.Name = dr["name"].ToString();
                user.Cpf = dr["cpf"].ToString();
                user.Email = dr["password"].ToString();
                user.Telephone = dr["telephone"].ToString();
                user.TypeUser = dr["type_user"].ToString();
            }
            else
                user = null;

            return user;
        }

        public List<User> findAll()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM sysuser";
            SqlDataReader dr = Connection.select(cmd);
            List<User> users = new List<User>();

            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    dr.Read();
                    User user = new User();
                    user.Id = (int)dr["id"];
                    user.Name = dr["name"].ToString();
                    user.Cpf = dr["cpf"].ToString();
                    user.Email = dr["password"].ToString();
                    user.Telephone = dr["telephone"].ToString();
                    user.TypeUser = dr["type_user"].ToString();
                    users.Add(user);
                }
            }
            else
                users = null;

            return users;
        }

        public static DataTable returnDataSource()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM sysuser";
            SqlDataReader dr = Connection.select(cmd);
            DataTable dt = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dt);
            return dt;
        }
    }
}
