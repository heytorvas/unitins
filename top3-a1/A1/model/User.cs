using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A1.model
{
    public class User
    {
        private int id;
        private String name;
        private String cpf;
        private String email;
        private String password;
        private String telephone;
        private String typeUser;

        public string TypeUser { get => typeUser; set => typeUser = value; }
        public string Telephone { get => telephone; set => telephone = value; }
        public string Password { get => password; set => password = value; }
        public string Email { get => email; set => email = value; }
        public string Cpf { get => cpf; set => cpf = value; }
        public string Name { get => name; set => name = value; }
        public int Id { get => id; set => id = value; }
    }
}
