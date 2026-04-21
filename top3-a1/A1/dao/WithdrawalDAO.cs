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
    public class WithdrawalDAO
    {
        public Withdrawal insert(Withdrawal withdrawal)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "INSERT INTO withdrawal(user_id, product_id, quantity_withdrawal, date_withdrawal) output INSERTED.id values (@user_id, @product_id, @quantity_withdrawal, @date_withdrawal)";
            cmd.Parameters.AddWithValue("@user_id", withdrawal.User.Id);
            cmd.Parameters.AddWithValue("@product_id", withdrawal.Product.Id);
            cmd.Parameters.AddWithValue("@quantity_withdrawal", withdrawal.QuantityWithdrawal);
            cmd.Parameters.AddWithValue("@date_withdrawal", withdrawal.DateWithdrawal);
            if (Connection.crud(cmd))
                return withdrawal;
            return null;
        }

        public Withdrawal update(Withdrawal withdrawal)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "UPDATE withdrawal set user_id = @user_id, product_id = @product_id, quantity_withdrawal = @quantity_withdrawal, date_withdrawal = @date_withdrawal where id = @id";
            cmd.Parameters.AddWithValue("@id", withdrawal.Id);
            cmd.Parameters.AddWithValue("@user_id", withdrawal.User.Id);
            cmd.Parameters.AddWithValue("@product_id", withdrawal.Product.Id);
            cmd.Parameters.AddWithValue("@quantity_withdrawal", withdrawal.QuantityWithdrawal);
            cmd.Parameters.AddWithValue("@date_withdrawal", withdrawal.DateWithdrawal);
            if (Connection.crud(cmd))
                return withdrawal;
            return null;
        }

        public void delete(Withdrawal withdrawal)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "DELETE FROM withdrawal WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", withdrawal.Id);
            Connection.crud(cmd);
        }

        public static Withdrawal findById(int id)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM withdrawal WHERE id = @id";
            cmd.Parameters.AddWithValue("@id", id);
            SqlDataReader dr = Connection.select(cmd);

            Withdrawal withdrawal = new Withdrawal();
            if (dr.HasRows)
            {
                dr.Read();
                withdrawal.Id = (int)dr["id"];
                withdrawal.User = UserDAO.findById((int)dr["user_id"]);
                withdrawal.Product = ProductDAO.findById((int)dr["product_id"]);
                withdrawal.QuantityWithdrawal = (int)dr["quantity_withdrawal"];
                withdrawal.DateWithdrawal = (DateTime)dr["date_withdrawal"];
            }
            else
                withdrawal = null;

            return withdrawal;
        }

        public List<Withdrawal> findAll()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM withdrawal";
            SqlDataReader dr = Connection.select(cmd);
            List<Withdrawal> withdrawals = new List<Withdrawal>();

            if (dr.HasRows)
            {
                while (dr.Read())
                {
                    dr.Read();
                    Withdrawal withdrawal = new Withdrawal();
                    withdrawal.Id = (int)dr["id"];
                    withdrawal.User = UserDAO.findById((int)dr["user_id"]);
                    withdrawal.Product = ProductDAO.findById((int)dr["product_id"]);
                    withdrawal.QuantityWithdrawal = (int)dr["quantity_withdrawal"];
                    withdrawal.DateWithdrawal = (DateTime)dr["date_withdrawal"];
                    withdrawals.Add(withdrawal);
                }
            }
            else
                withdrawals = null;

            return withdrawals;
        }

        public static DataTable returnDataSource()
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM withdrawal";
            SqlDataReader dr = Connection.select(cmd);
            DataTable dt = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dt);
            return dt;
        }
    }
}
