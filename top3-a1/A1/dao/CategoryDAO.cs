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
    public class CategoryDAO
    {

        public Category insert(Category category)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "INSERT INTO category(name) output INSERTED.id values (@name)";
            cmd.Parameters.AddWithValue("@name", category.Name);
            if (Connection.crud(cmd))
                return category;
            return null;
        }

        public Category update(Category category)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "UPDATE category set name = @name where id = @id";
            cmd.Parameters.AddWithValue("@id", category.Id);
            cmd.Parameters.AddWithValue("@name", category.Name);
            if (Connection.crud(cmd))
                return category;
            return null;
        }

        public void delete(Category category)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "DELETE FROM category WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", category.Id);
            Connection.crud(cmd);
        }

        public static Category findById(int id)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM category WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", id);
            SqlDataReader dr = Connection.select(cmd);

            Category category = new Category();
            if (dr.HasRows)
            {
                dr.Read();
                category.Id = (int)dr["id"];
                category.Name = dr["name"].ToString();
            }
            else
                category = null;

            return category;
        }

        public List<Category> findAll()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM category";
            SqlDataReader dr = Connection.select(cmd);
            List<Category> categories = new List<Category>();

            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    dr.Read();
                    Category category = new Category();
                    category.Id = (int)dr["id"];
                    category.Name = dr["name"].ToString();
                    categories.Add(category);
                }
            }
            else
                categories = null;

            return categories;
        }

        public static DataTable returnDataSource()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM category";
            SqlDataReader dr = Connection.select(cmd);
            DataTable dt = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dt);

            return dt;
        }

    }
}
