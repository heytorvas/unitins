using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A1.model
{
    public class Entry
    {
        private int id;
        private Product product;
        private User user;
        private float price;
        private DateTime dateEntry;
        private int quantityEntry;

        public int QuantityEntry { get => quantityEntry; set => quantityEntry = value; }
        public DateTime DateEntry { get => dateEntry; set => dateEntry = value; }
        public float Price { get => price; set => price = value; }
        public User User { get => user; set => user = value; }
        public Product Product { get => product; set => product = value; }
        public int Id { get => id; set => id = value; }
    }
}
