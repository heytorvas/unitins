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
    public class SubcategoryDAO
    {
        public Subcategory insert(Subcategory subcategory)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "INSERT INTO subcategory(name, category_id) output INSERTED.id values (@name, @category_id)";
            cmd.Parameters.AddWithValue("@name", subcategory.Name);
            cmd.Parameters.AddWithValue("@category_id", subcategory.Category.Id);
            if (Connection.crud(cmd))
                return subcategory;
            return null;
        }

        public Subcategory update(Subcategory subcategory)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "UPDATE subcategory set name = @name, category_id = @category_id where id = @id";
            cmd.Parameters.AddWithValue("@id", subcategory.Id);
            cmd.Parameters.AddWithValue("@name", subcategory.Name);
            cmd.Parameters.AddWithValue("@category_id", subcategory.Category.Id);
            if (Connection.crud(cmd))
                return subcategory;
            return null;
        }

        public void delete(Subcategory subcategory)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "DELETE FROM subcategory WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", subcategory.Id);
            Connection.crud(cmd);
        }

        public static Subcategory findById(int id)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM subcategory WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", id);
            SqlDataReader dr = Connection.select(cmd);

            Subcategory subcategory = new Subcategory();
            if (dr.HasRows)
            {
                dr.Read();
                subcategory.Id = (int)dr["id"];
                subcategory.Name = dr["name"].ToString();
                subcategory.Category = CategoryDAO.findById((int)dr["id"]);
            }
            else
                subcategory = null;

            return subcategory;
        }

        public List<Subcategory> findAll()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM subcategory";
            SqlDataReader dr = Connection.select(cmd);
            List<Subcategory> subcategories = new List<Subcategory>();

            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    dr.Read();
                    Subcategory subcategory = new Subcategory();
                    subcategory.Id = (int)dr["id"];
                    subcategory.Name = dr["name"].ToString();
                    subcategory.Category.Id = (int)dr["id"];
                    subcategories.Add(subcategory);
                }
            }
            else
                subcategories = null;

            return subcategories;
        }

        public static DataTable returnDataSource()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM subcategory";
            SqlDataReader dr = Connection.select(cmd);
            DataTable dt = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dt);

            return dt;
        }

        public static DataTable returnDataSourceCheckbox(String name)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM subcategory WHERE subcategory.name LIKE '"+ name + "'";
            SqlDataReader dr = Connection.select(cmd);
            DataTable dt = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dt);

            return dt;
        }

    }
}
