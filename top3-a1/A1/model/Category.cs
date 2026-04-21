using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A1.model
{
    public class Category
    {
        private int id;
        private String name;

        public string Name { get => name; set => name = value; }
        public int Id { get => id; set => id = value; }
    }
}
