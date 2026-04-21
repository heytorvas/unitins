using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A1.model
{
    public class Subcategory
    {
        private int id;
        private String name;
        private Category category;

        public Category Category { get => category; set => category = value; }
        public string Name { get => name; set => name = value; }
        public int Id { get => id; set => id = value; }
    }
}
