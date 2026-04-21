using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A1.model
{
    public class Withdrawal
    {
        private int id;
        private User user;
        private Product product;
        private int quantityWithdrawal;
        private DateTime dateWithdrawal;

        public DateTime DateWithdrawal { get => dateWithdrawal; set => dateWithdrawal = value; }
        public int QuantityWithdrawal { get => quantityWithdrawal; set => quantityWithdrawal = value; }
        public Product Product { get => product; set => product = value; }
        public User User { get => user; set => user = value; }
        public int Id { get => id; set => id = value; }
    }
}
