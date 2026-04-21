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
    public class EntryDAO
    {
        public Entry insert(Entry entry)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "INSERT INTO entry(product_id, user_id, price, date_entry, quantity_entry) output INSERTED.id values (@product_id, @user_id, @price, @date_entry, @quantity_entry)";
            cmd.Parameters.AddWithValue("@product_id", entry.Product.Id);
            cmd.Parameters.AddWithValue("@user_id", entry.User.Id);
            cmd.Parameters.AddWithValue("@price", entry.Price);
            cmd.Parameters.AddWithValue("@date_entry", entry.DateEntry);
            cmd.Parameters.AddWithValue("@quantity_entry", entry.QuantityEntry);
            if (Connection.crud(cmd))
                return entry;
            return null;
        }

        public Entry update(Entry entry)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "UPDATE entry set product_id = @product_id, user_id = @user_id, price = @price, date_entry = @date_entry, quantity_entry = @quantity_entry where id = @id";
            cmd.Parameters.AddWithValue("@id", entry.Id);
            cmd.Parameters.AddWithValue("@product_id", entry.Product.Id);
            cmd.Parameters.AddWithValue("@user_id", entry.User.Id);
            cmd.Parameters.AddWithValue("@price", entry.Price);
            cmd.Parameters.AddWithValue("@date_entry", entry.DateEntry);
            cmd.Parameters.AddWithValue("@quantity_entry", entry.QuantityEntry);
            if (Connection.crud(cmd))
                return entry;
            return null;
        }

        public void delete(Entry entry)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "DELETE FROM entry WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", entry.Id);
            Connection.crud(cmd);
        }

        public static Entry findById(int id)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM entry WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", id);
            SqlDataReader dr = Connection.select(cmd);

            Entry entry = new Entry();
            if (dr.HasRows)
            {
                dr.Read();
                entry.Id = (int)dr["id"];
                entry.Product = ProductDAO.findById((int)dr["product_id"]);
                entry.User = UserDAO.findById((int)dr["user_id"]);
                entry.Price = (float)dr["price"];
                entry.DateEntry = (DateTime)dr["date_entry"];
                entry.QuantityEntry = (int)dr["quantity_entry"];
            }
            else
                entry = null;

            return entry;
        }

        public List<Entry> findAll()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM entry";
            SqlDataReader dr = Connection.select(cmd);
            List<Entry> entries = new List<Entry>();

            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    Entry entry = new Entry();
                    entry.Id = (int)dr["id"];
                    entry.Product.Id = (int)dr["product_id"];
                    entry.User.Id = (int)dr["user_id"];
                    entry.Price = (float)dr["price"];
                    entry.DateEntry = (DateTime)dr["date_entry"];
                    entry.QuantityEntry = (int)dr["quantity_entry"];
                    entries.Add(entry);
                }
            }
            else
                entries = null;

            return entries;
        }

        public static DataTable returnDataSource()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM entry";
            SqlDataReader dr = Connection.select(cmd);
            DataTable dt = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dt);
            return dt;
        }
    }
}
