using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A1.model
{
    public class Product
    {
        private int id;
        private String image;
        private String name;
        private String label;
        private int quantityAvailable;
        private float price;
        private List<Subcategory> subcategories;


        public float Price { get => price; set => price = value; }
        public int QuantityAvailable { get => quantityAvailable; set => quantityAvailable = value; }
        public string Label { get => label; set => label = value; }
        public string Name { get => name; set => name = value; }
        public int Id { get => id; set => id = value; }
        public List<Subcategory> Subcategories { get => subcategories; set => subcategories = value; }
        public string Image { get => image; set => image = value; }
    }
}
