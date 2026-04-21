using A1.dao;
using A1.model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Globalization;

namespace A1
{
    public partial class Main : Form
    {
        User user = new User();
        Category category = new Category();
        Product product = new Product();
        Subcategory subcategory = new Subcategory();
        Entry entry = new Entry();
        Withdrawal withdrawal = new Withdrawal();

        UserDAO userDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        SubcategoryDAO subcategoryDAO = new SubcategoryDAO();
        EntryDAO entryDAO = new EntryDAO();
        WithdrawalDAO withdrawalDAO = new WithdrawalDAO();

        public int idUser;
        public int idCategory;
        public int idProduct;
        public int idSubcategory;
        public int idEntry;
        public int idWithdrawal;

        public Main()
        {
            InitializeComponent();
            DataTable dtSubCategory = SubcategoryDAO.returnDataSource();
            for (int i = 0; i < dtSubCategory.Rows.Count; i++)
                checkedListBoxSubcategory.Items.Add(dtSubCategory.Rows[i]["name"].ToString());

            flSearchCategory.Controls.Clear();
            DataTable dtCategory = CategoryDAO.returnDataSource();
            for (int i = 0; i < dtCategory.Rows.Count; i++)
            {
                RadioButton rb = new RadioButton();
                rb.Text = dtCategory.Rows[i]["name"].ToString();
                flSearchCategory.Controls.Add(rb);
            }
        }

        public void SetLabel(string newText)
        {
            Invoke(new Action(() => labelTypeUser.Text = newText));
            if (labelTypeUser.Text == "Employee")
                tabControl.TabPages.Remove(tabPageUser);
        }

        private void btnCategorySave_Click(object sender, EventArgs e)
        {
            if (tbCategoryName.Text != "")
            {
                category.Name = tbCategoryName.Text;

                categoryDAO.insert(category);
                dataGridCategory.DataSource = CategoryDAO.returnDataSource();
                cbSubcategoryCategory.DataSource = dataGridCategory.DataSource;

                flSearchCategory.Controls.Clear();
                DataTable dtCategory = CategoryDAO.returnDataSource();
                for (int i = 0; i < dtCategory.Rows.Count; i++)
                {
                    RadioButton rb = new RadioButton();
                    rb.Text = dtCategory.Rows[i]["name"].ToString();
                    flSearchCategory.Controls.Add(rb);
                }
                btnCategoryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Name field cannot be blank. Try again!");

        }

        private void Main_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'a1DataSet1.withdrawal' table. You can move, or remove it, as needed.
            this.withdrawalTableAdapter1.Fill(this.a1DataSet1.withdrawal);
            // TODO: This line of code loads data into the 'a1DataSet1.entry' table. You can move, or remove it, as needed.
            this.entryTableAdapter1.Fill(this.a1DataSet1.entry);
            // TODO: This line of code loads data into the 'a1DataSet1.product' table. You can move, or remove it, as needed.
            this.productTableAdapter1.Fill(this.a1DataSet1.product);
            // TODO: This line of code loads data into the 'a1DataSet1.subcategory' table. You can move, or remove it, as needed.
            this.subcategoryTableAdapter1.Fill(this.a1DataSet1.subcategory);
            // TODO: This line of code loads data into the 'a1DataSet1.category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter1.Fill(this.a1DataSet1.category);
            // TODO: This line of code loads data into the 'a1DataSet1.sysuser' table. You can move, or remove it, as needed.
            this.sysuserTableAdapter1.Fill(this.a1DataSet1.sysuser);
        }

        private void btnUserSave_Click(object sender, EventArgs e)
        {
            if (tbUserName.Text != "")
            {
                user.Name = tbUserName.Text;
                user.Cpf = tbUserCPF.Text;
                user.Email = tbUserEmail.Text;
                user.Password = tbUserPassword.Text;
                user.Telephone = tbUserTelephone.Text;

                userDAO.insert(user);
                dataGridUser.DataSource = UserDAO.returnDataSource();
                cbEntryUser.DataSource = dataGridUser.DataSource;
                cbWithdrawalUser.DataSource = dataGridUser.DataSource;
                btnUserClear_Click(sender, e);
            }
            else
                MessageBox.Show("Name field cannot be blank. Try again!");

        }

        private void btnUserClear_Click(object sender, EventArgs e)
        {
            tbUserName.Text = "";
            tbUserCPF.Text = "";
            tbUserEmail.Text = "";
            tbUserPassword.Text = "";
            tbUserTelephone.Text = "";
            cbUserType.Text = "";
        }

        private void btnCategoryClear_Click(object sender, EventArgs e)
        {
            tbCategoryName.Text = "";
        }

        private void btnSubCategorySave_Click(object sender, EventArgs e)
        {
            if (tbSubCategoryName.Text != "")
            {
                if (subcategory.Category == null)
                    subcategory.Category = new Category();

                subcategory.Name = tbSubCategoryName.Text;
                subcategory.Category.Id = (int)cbSubcategoryCategory.SelectedValue;

                subcategoryDAO.insert(subcategory);
                dataGridSubcategory.DataSource = SubcategoryDAO.returnDataSource();

                checkedListBoxSubcategory.Items.Clear();

                DataTable dtSubCategory1 = SubcategoryDAO.returnDataSource();
                for (int i = 0; i < dtSubCategory1.Rows.Count; i++)
                    checkedListBoxSubcategory.Items.Add(dtSubCategory1.Rows[i]["name"].ToString());
                btnSubCategoryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Name field cannot be blank. Try again!");
        }

        private void btnProductSave_Click(object sender, EventArgs e)
        {
            if (tbProductName.Text != "")
            {
                if (product.Subcategories == null)
                    product.Subcategories = new List<Subcategory>();

                foreach (string word in checkedListBoxSubcategory.CheckedItems)
                {
                    DataTable returnDataTableSelect = SubcategoryDAO.returnDataSourceCheckbox(word);
                    for (int i = 0; i < returnDataTableSelect.Rows.Count; i++)
                    {
                        Subcategory sub = new Subcategory();
                        sub.Id = (int)returnDataTableSelect.Rows[i]["id"];
                        sub.Name = returnDataTableSelect.Rows[i]["name"].ToString();
                        if (sub.Category == null)
                            sub.Category = new Category();
                        sub.Category.Id = (int)returnDataTableSelect.Rows[i]["category_id"];
                        product.Subcategories.Add(sub);
                    }
                }

                product.Name = tbProductName.Text;
                product.Label = tbProductLabel.Text;
                product.Price = 0;
                product.QuantityAvailable = 0;

                string fname = tbProductName.Text + ".jpg";
                string folder = "C:\\Users\\Heytor\\Desktop\\devCSharp\\A1\\A1\\Files";
                string pathstring = System.IO.Path.Combine(folder, fname);
                Image a = pbProductImage.Image;
                a.Save(pathstring);

                product.Image = pathstring;

                int product_id = productDAO.insertReturned(product);

                foreach (Subcategory sub1 in product.Subcategories)
                {
                    SqlCommand cmd = new SqlCommand();
                    cmd.CommandText = "INSERT INTO product_subcategory(subcategory_id, product_id) values (@subcategory_id, @product_id)";
                    cmd.Parameters.AddWithValue("@subcategory_id", sub1.Id);
                    cmd.Parameters.AddWithValue("@product_id", product_id);
                    Connection.crud(cmd);
                }

                dataGridProduct.DataSource = ProductDAO.returnDataSource();
                cbEntryProduct.DataSource = dataGridProduct.DataSource;
                cbWithdrawalProduct.DataSource = dataGridProduct.DataSource;
                dataGridSearchProduct.DataSource = ProductDAO.returnDataSource();
                btnProductClear_Click(sender, e);
            }
            else
                MessageBox.Show("Name field cannot be blank. Try again!");
        }

        private void cbUserType_SelectedIndexChanged(object sender, EventArgs e)
        {
            user.TypeUser = cbUserType.Text;
        }

        private void btnSubCategoryClear_Click(object sender, EventArgs e)
        {
            tbSubCategoryName.Text = "";
            cbSubcategoryCategory.Text = "";
        }

        private void btnProductClear_Click(object sender, EventArgs e)
        {
            tbProductName.Text = "";
            tbProductLabel.Text = "";
            tbProductImage.Text = "";
            pbProductImage.Image = null;
            while (checkedListBoxSubcategory.CheckedIndices.Count > 0)
                checkedListBoxSubcategory.SetItemChecked(checkedListBoxSubcategory.CheckedIndices[0], false);
        }

        private void dataGridUser_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            idUser = (int)dataGridUser.CurrentRow.Cells[0].Value;
            tbUserName.Text = dataGridUser.CurrentRow.Cells[1].Value.ToString();
            tbUserCPF.Text = dataGridUser.CurrentRow.Cells[2].Value.ToString();
            tbUserEmail.Text = dataGridUser.CurrentRow.Cells[3].Value.ToString();
            tbUserPassword.Text = dataGridUser.CurrentRow.Cells[4].Value.ToString();
            tbUserTelephone.Text = dataGridUser.CurrentRow.Cells[5].Value.ToString();
            cbUserType.Text = dataGridUser.CurrentRow.Cells[6].Value.ToString();
        }

        private void btnUserUpdate_Click_1(object sender, EventArgs e)
        {
            if (tbUserName.Text != "")
            {
                user.Id = idUser;
                user.Name = tbUserName.Text;
                user.Cpf = tbUserCPF.Text;
                user.Email = tbUserEmail.Text;
                user.Password = tbUserPassword.Text;
                user.Telephone = tbUserTelephone.Text;

                userDAO.update(user);
                dataGridUser.DataSource = UserDAO.returnDataSource();
                cbEntryUser.DataSource = dataGridUser.DataSource;
                cbWithdrawalUser.DataSource = dataGridUser.DataSource;
                btnUserClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on user at DataGrid to update. Try again!");

        }

        private void btnUserDelete_Click(object sender, EventArgs e)
        {
            if (tbUserName.Text != "")
            {
                user.Id = idUser;
                user.Name = tbUserName.Text;
                user.Cpf = tbUserCPF.Text;
                user.Email = tbUserEmail.Text;
                user.Password = tbUserPassword.Text;
                user.Telephone = tbUserPassword.Text;

                userDAO.delete(user);
                dataGridUser.DataSource = UserDAO.returnDataSource();
                cbEntryUser.DataSource = dataGridUser.DataSource;
                cbWithdrawalUser.DataSource = dataGridUser.DataSource;
                btnUserClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on user at DataGrid to delete. Try again!");
        }

        private void dataGridCategory_CellContentDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            idCategory = (int)dataGridCategory.CurrentRow.Cells[0].Value;
            tbCategoryName.Text = dataGridCategory.CurrentRow.Cells[1].Value.ToString();
        }

        private void btnCategoryUpdate_Click(object sender, EventArgs e)
        {
            if (tbCategoryName.Text != "")
            {
                category.Id = idCategory;
                category.Name = tbCategoryName.Text;

                categoryDAO.update(category);
                dataGridCategory.DataSource = CategoryDAO.returnDataSource();
                cbSubcategoryCategory.DataSource = dataGridCategory.DataSource;

                flSearchCategory.Controls.Clear();
                DataTable dtCategory = CategoryDAO.returnDataSource();
                for (int i = 0; i < dtCategory.Rows.Count; i++)
                {
                    RadioButton rb = new RadioButton();
                    rb.Text = dtCategory.Rows[i]["name"].ToString();
                    flSearchCategory.Controls.Add(rb);
                }

                btnCategoryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on category at DataGrid to update. Try again!");
        }

        private void btnCategoryDelete_Click(object sender, EventArgs e)
        {
            if (tbCategoryName.Text != "")
            {
                category.Id = idCategory;
                category.Name = tbCategoryName.Text;

                categoryDAO.delete(category);
                dataGridCategory.DataSource = CategoryDAO.returnDataSource();
                cbSubcategoryCategory.DataSource = dataGridCategory.DataSource;

                flSearchCategory.Controls.Clear();
                DataTable dtCategory = CategoryDAO.returnDataSource();
                for (int i = 0; i < dtCategory.Rows.Count; i++)
                {
                    RadioButton rb = new RadioButton();
                    rb.Text = dtCategory.Rows[i]["name"].ToString();
                    flSearchCategory.Controls.Add(rb);
                }

                btnCategoryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on category at DataGrid to delete. Try again!");
        }
            
        private void dataGridSubcategory_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            idSubcategory = (int)dataGridSubcategory.CurrentRow.Cells[0].Value;
            tbSubCategoryName.Text = dataGridSubcategory.CurrentRow.Cells[1].Value.ToString();
            int idSubcategoryCategory = (int)dataGridSubcategory.CurrentRow.Cells[2].Value;
            Category c = CategoryDAO.findById(idSubcategoryCategory);
            cbSubcategoryCategory.Text = c.Name;
        }

        private void btnSubCategoryUpdate_Click(object sender, EventArgs e)
        {
            if (tbSubCategoryName.Text != "")
            {
                subcategory.Id = idSubcategory;
                subcategory.Name = tbSubCategoryName.Text;
                if (subcategory.Category == null)
                    subcategory.Category = new Category();
                subcategory.Category.Id = (int)cbSubcategoryCategory.SelectedValue;

                subcategoryDAO.update(subcategory);
                dataGridSubcategory.DataSource = SubcategoryDAO.returnDataSource();

                checkedListBoxSubcategory.Items.Clear();

                DataTable dtSubCategory1 = SubcategoryDAO.returnDataSource();
                for (int i = 0; i < dtSubCategory1.Rows.Count; i++)
                    checkedListBoxSubcategory.Items.Add(dtSubCategory1.Rows[i]["name"].ToString());
                btnSubCategoryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on subcategory at DataGrid to update. Try again!");
        }

        private void btnSubCategoryDelete_Click(object sender, EventArgs e)
        {
            if (tbSubCategoryName.Text != "")
            {
                subcategory.Id = idSubcategory;
                subcategory.Name = tbSubCategoryName.Text;
                if (subcategory.Category == null)
                    subcategory.Category = new Category();
                subcategory.Category.Id = (int)cbSubcategoryCategory.SelectedValue;

                subcategoryDAO.delete(subcategory);
                dataGridSubcategory.DataSource = SubcategoryDAO.returnDataSource();

                checkedListBoxSubcategory.Items.Clear();

                DataTable dtSubCategory1 = SubcategoryDAO.returnDataSource();
                for (int i = 0; i < dtSubCategory1.Rows.Count; i++)
                    checkedListBoxSubcategory.Items.Add(dtSubCategory1.Rows[i]["name"].ToString());
                btnSubCategoryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on subcategory at DataGrid to delete. Try again!");
        }

        private void dataGridProduct_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            idProduct = (int)dataGridProduct.CurrentRow.Cells[0].Value;
            tbProductName.Text = dataGridProduct.CurrentRow.Cells[1].Value.ToString();
            tbProductLabel.Text = dataGridProduct.CurrentRow.Cells[2].Value.ToString();

            string pathstring = dataGridProduct.CurrentRow.Cells[5].Value.ToString();
            pbProductImage.Image = Image.FromFile(pathstring);
            tbProductImage.Text = dataGridProduct.CurrentRow.Cells[5].Value.ToString();

            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT * FROM product_subcategory WHERE product_id = " + idProduct;
            SqlDataReader dr = Connection.select(cmd);
            DataTable dtsub = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dtsub);
            int idSubcategoryFind = 0;

            DataTable dt = dtsub;
            for (int i = 0; i < dt.Rows.Count; i++)
            {
                idSubcategoryFind = int.Parse(dt.Rows[i]["subcategory_id"].ToString());
                Subcategory sc = SubcategoryDAO.findById(idSubcategoryFind);
                for (int h = 0; h < checkedListBoxSubcategory.Items.Count; h++)
                {
                    if (sc.Name == (checkedListBoxSubcategory.Items[h]).ToString())
                    {
                        checkedListBoxSubcategory.SetItemChecked(h, true);
                    }
                }
            }
        }

        private void btnProductUpdate_Click(object sender, EventArgs e)
        {
            if (tbProductName.Text != "")
            {
                if (product.Subcategories == null)
                    product.Subcategories = new List<Subcategory>();

                SqlCommand cmd = new SqlCommand();
                cmd.CommandText = "DELETE FROM product_subcategory WHERE product_id = @product_id";
                cmd.Parameters.AddWithValue("@product_id", idProduct);
                Connection.crud(cmd);

                foreach (string word in checkedListBoxSubcategory.CheckedItems)
                {
                    DataTable returnDataTableSelect = SubcategoryDAO.returnDataSourceCheckbox(word);
                    for (int i = 0; i < returnDataTableSelect.Rows.Count; i++)
                    {
                        Subcategory sub = new Subcategory();
                        sub.Id = (int)returnDataTableSelect.Rows[i]["id"];
                        sub.Name = returnDataTableSelect.Rows[i]["name"].ToString();
                        if (sub.Category == null)
                            sub.Category = new Category();
                        sub.Category.Id = (int)returnDataTableSelect.Rows[i]["category_id"];
                        product.Subcategories.Add(sub);
                    }
                }

                product.Id = idProduct;
                product.Name = tbProductName.Text;
                product.Label = tbProductLabel.Text;
                product.Price = 0;

                productDAO.update(product);

                foreach (Subcategory sub1 in product.Subcategories)
                {
                    SqlCommand cmd1 = new SqlCommand();
                    cmd1.CommandText = "INSERT INTO product_subcategory(subcategory_id, product_id) values (@subcategory_id, @product_id)";
                    cmd1.Parameters.AddWithValue("@subcategory_id", sub1.Id);
                    cmd1.Parameters.AddWithValue("@product_id", idProduct);
                    Connection.crud(cmd1);
                }

                dataGridProduct.DataSource = ProductDAO.returnDataSource();
                dataGridSearchProduct.DataSource = ProductDAO.returnDataSource();
                btnProductClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on product at DataGrid to update. Try again!");
        }

        private void btnProductDelete_Click(object sender, EventArgs e)
        {
            if (tbProductName.Text != "")
            {
                if (product.Subcategories == null)
                    product.Subcategories = new List<Subcategory>();

                SqlCommand cmd = new SqlCommand();
                cmd.CommandText = "DELETE FROM product_subcategory WHERE product_id = @product_id";
                cmd.Parameters.AddWithValue("@product_id", idProduct);
                Connection.crud(cmd);

                foreach (string word in checkedListBoxSubcategory.CheckedItems)
                {
                    DataTable returnDataTableSelect = SubcategoryDAO.returnDataSourceCheckbox(word);
                    for (int i = 0; i < returnDataTableSelect.Rows.Count; i++)
                    {
                        Subcategory sub = new Subcategory();
                        sub.Id = (int)returnDataTableSelect.Rows[i]["id"];
                        sub.Name = returnDataTableSelect.Rows[i]["name"].ToString();
                        if (sub.Category == null)
                            sub.Category = new Category();
                        sub.Category.Id = (int)returnDataTableSelect.Rows[i]["category_id"];
                        product.Subcategories.Add(sub);
                    }
                }

                product.Id = idProduct;
                product.Name = tbProductName.Text;
                product.Label = tbProductLabel.Text;
                product.Price = 0;

                productDAO.delete(product);
                dataGridProduct.DataSource = ProductDAO.returnDataSource();
                dataGridSearchProduct.DataSource = ProductDAO.returnDataSource();
                btnProductClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on product at DataGrid to delete. Try again!");
        }

        private void btnEntrySave_Click(object sender, EventArgs e)
        {
            if (tbEntryQuantity.Text != "")
            {
                if (entry.Product == null)
                    entry.Product = new Product();
                if (entry.User == null)
                    entry.User = new User();

                entry.Product.Id = (int)cbEntryProduct.SelectedValue;
                entry.User.Id = (int)cbEntryUser.SelectedValue;
                entry.Price = float.Parse(tbEntryPrice.Text.Trim());
                entry.QuantityEntry = int.Parse(tbEntryQuantity.Text.Trim());
                entry.DateEntry = dtEntryDate.Value;

                entryDAO.insert(entry);
                dataGridEntry.DataSource = EntryDAO.returnDataSource();

                SqlCommand cmd1 = new SqlCommand();
                cmd1.CommandText = "SELECT * FROM product WHERE id = " + entry.Product.Id;
                Connection.select(cmd1);

                SqlDataReader dr = Connection.select(cmd1);
                DataTable dtsub = new DataTable();
                SqlDataAdapter da = new SqlDataAdapter(cmd1.CommandText, Connection.connect());
                da.Fill(dtsub);
                int qtd_now = 0;

                DataTable dt = dtsub;
                for (int i = 0; i < dt.Rows.Count; i++)
                    qtd_now = int.Parse(dt.Rows[i]["quantity_available"].ToString());

                SqlCommand cmd = new SqlCommand();
                cmd.CommandText = "UPDATE product set quantity_available = @quantity_available, price = @price WHERE id = @product_id";
                cmd.Parameters.AddWithValue("@quantity_available", qtd_now + entry.QuantityEntry);
                cmd.Parameters.AddWithValue("@price", entry.Price);
                cmd.Parameters.AddWithValue("@product_id", entry.Product.Id);
                Connection.crud(cmd);

                dataGridProduct.DataSource = ProductDAO.returnDataSource();

                btnEntryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Quantity field cannot be blank. Try again!");
        }

        private void dataGridEntry_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            idEntry = (int)dataGridEntry.CurrentRow.Cells[0].Value;
            int idEntryProduct = (int)dataGridEntry.CurrentRow.Cells[1].Value;
            Product p = ProductDAO.findById(idEntryProduct);
            cbEntryProduct.Text = p.Name;
            int idEntryUser = (int)dataGridEntry.CurrentRow.Cells[2].Value;
            User u = UserDAO.findById(idEntryUser);
            cbEntryUser.Text = u.Name;
            tbEntryPrice.Text = dataGridEntry.CurrentRow.Cells[3].Value.ToString();
            dtEntryDate.Value = (DateTime)dataGridEntry.CurrentRow.Cells[4].Value;
            tbEntryQuantity.Text = dataGridEntry.CurrentRow.Cells[5].Value.ToString();
        }

        private void btnEntryClear_Click(object sender, EventArgs e)
        {
            cbEntryUser.Text = "";
            cbEntryProduct.Text = "";
            tbEntryPrice.Text = "";
            tbEntryQuantity.Text = "";
            dtEntryDate.ResetText();
        }

        private void btnEntryUpdate_Click(object sender, EventArgs e)
        {
            if (tbEntryQuantity.Text != "")
            {
                if (entry.Product == null)
                    entry.Product = new Product();
                if (entry.User == null)
                    entry.User = new User();

                entry.Id = idEntry;
                entry.Product.Id = (int)cbEntryProduct.SelectedValue;
                entry.User.Id = (int)cbEntryUser.SelectedValue;
                entry.Price = float.Parse(tbEntryPrice.Text.Trim());
                entry.QuantityEntry = int.Parse(tbEntryQuantity.Text.Trim());
                entry.DateEntry = dtEntryDate.Value;

                entryDAO.update(entry);
                dataGridEntry.DataSource = EntryDAO.returnDataSource();
                btnEntryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on entry at DataGrid to update. Try again!");
        }

        private void btnEntryDelete_Click(object sender, EventArgs e)
        {
            if (tbEntryQuantity.Text != "") 
            {
                if (entry.Product == null)
                    entry.Product = new Product();
                if (entry.User == null)
                    entry.User = new User();

                entry.Id = idEntry;
                entry.Product.Id = (int)cbEntryProduct.SelectedValue;
                entry.User.Id = (int)cbEntryUser.SelectedValue;
                entry.Price = float.Parse(tbEntryPrice.Text.Trim());
                entry.QuantityEntry = int.Parse(tbEntryQuantity.Text.Trim());
                entry.DateEntry = dtEntryDate.Value;

                entryDAO.delete(entry);
                dataGridEntry.DataSource = EntryDAO.returnDataSource();
                btnEntryClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on entry at DataGrid to delete. Try again!");
        }

        private void btnWithdrawalSave_Click(object sender, EventArgs e)
        {
            if (tbWithdrawalQuantity.Text != "")
            {
                if (withdrawal.Product == null)
                    withdrawal.Product = new Product();
                if (withdrawal.User == null)
                    withdrawal.User = new User();

                withdrawal.Product.Id = (int)cbWithdrawalProduct.SelectedValue;
                withdrawal.User.Id = (int)cbWithdrawalUser.SelectedValue;
                withdrawal.QuantityWithdrawal = int.Parse(tbWithdrawalQuantity.Text.Trim());
                withdrawal.DateWithdrawal = dtWithdrawalDate.Value;

                withdrawalDAO.insert(withdrawal);
                dataGridWithdrawal.DataSource = WithdrawalDAO.returnDataSource();

                SqlCommand cmd1 = new SqlCommand();
                cmd1.CommandText = "SELECT * FROM product WHERE id = " + withdrawal.Product.Id;
                Connection.select(cmd1);

                SqlDataReader dr = Connection.select(cmd1);
                DataTable dtsub = new DataTable();
                SqlDataAdapter da = new SqlDataAdapter(cmd1.CommandText, Connection.connect());
                da.Fill(dtsub);
                int qtd_now = 0;

                DataTable dt = dtsub;
                for (int i = 0; i < dt.Rows.Count; i++)
                    qtd_now = int.Parse(dt.Rows[i]["quantity_available"].ToString());

                SqlCommand cmd = new SqlCommand();
                cmd.CommandText = "UPDATE product set quantity_available = @quantity_available WHERE id = @product_id";
                cmd.Parameters.AddWithValue("@quantity_available", qtd_now - withdrawal.QuantityWithdrawal);
                cmd.Parameters.AddWithValue("@product_id", withdrawal.Product.Id);
                Connection.crud(cmd);

                dataGridProduct.DataSource = ProductDAO.returnDataSource();

                btnWithdrawalClear_Click(sender, e);
            }
            else
                MessageBox.Show("Quantity field cannot be blank. Try again!");
        }

        private void dataGridWithdrawal_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            idWithdrawal = (int)dataGridWithdrawal.CurrentRow.Cells[0].Value;
            int idWithdrawalUser = (int)dataGridWithdrawal.CurrentRow.Cells[1].Value;
            User u = UserDAO.findById(idWithdrawalUser);
            int idWithdrawalProduct = (int)dataGridWithdrawal.CurrentRow.Cells[2].Value;
            Product p = ProductDAO.findById(idWithdrawalProduct);
            cbWithdrawalProduct.Text = p.Name;
            cbWithdrawalUser.Text = u.Name;
            tbWithdrawalQuantity.Text = dataGridWithdrawal.CurrentRow.Cells[3].Value.ToString();
            dtWithdrawalDate.Value = (DateTime)dataGridWithdrawal.CurrentRow.Cells[4].Value;
        }

        private void btnWithdrawalClear_Click(object sender, EventArgs e)
        {
            cbWithdrawalUser.Text = "";
            cbWithdrawalProduct.Text = "";
            tbWithdrawalQuantity.Text = "";
            dtWithdrawalDate.ResetText();
        }

        private void btnWithdrawalUpdate_Click(object sender, EventArgs e)
        {
            if (tbWithdrawalQuantity.Text != "")
            {
                if (withdrawal.Product == null)
                    withdrawal.Product = new Product();
                if (withdrawal.User == null)
                    withdrawal.User = new User();

                withdrawal.Id = idWithdrawal;
                withdrawal.Product.Id = (int)cbWithdrawalProduct.SelectedValue;
                withdrawal.User.Id = (int)cbWithdrawalUser.SelectedValue;
                withdrawal.QuantityWithdrawal = int.Parse(tbWithdrawalQuantity.Text.Trim());
                withdrawal.DateWithdrawal = dtWithdrawalDate.Value;

                withdrawalDAO.update(withdrawal);
                dataGridWithdrawal.DataSource = WithdrawalDAO.returnDataSource();
                btnWithdrawalClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on withdrawal at DataGrid to update. Try again!");
        }

        private void btnWithdrawalDelete_Click(object sender, EventArgs e)
        {
            if (tbWithdrawalQuantity.Text != "")
            {
                if (withdrawal.Product == null)
                    withdrawal.Product = new Product();
                if (withdrawal.User == null)
                    withdrawal.User = new User();

                withdrawal.Id = idWithdrawal;
                withdrawal.Product.Id = (int)cbWithdrawalProduct.SelectedValue;
                withdrawal.User.Id = (int)cbWithdrawalUser.SelectedValue;
                withdrawal.QuantityWithdrawal = int.Parse(tbWithdrawalQuantity.Text.Trim());
                withdrawal.DateWithdrawal = dtWithdrawalDate.Value;

                withdrawalDAO.delete(withdrawal);
                dataGridWithdrawal.DataSource = WithdrawalDAO.returnDataSource();
                btnWithdrawalClear_Click(sender, e);
            }
            else
                MessageBox.Show("Click on withdrawal at DataGrid to delete. Try again!");
        }

        private void tabPageUser_Click(object sender, EventArgs e)
        {
            dataGridUser.DataSource = UserDAO.returnDataSource();
        }

        private void tabPageCategory_Click(object sender, EventArgs e)
        {
            dataGridCategory.DataSource = CategoryDAO.returnDataSource();
        }

        private void tabPageSubCategory_Click(object sender, EventArgs e)
        {
            dataGridCategory.DataSource = CategoryDAO.returnDataSource();
            dataGridSubcategory.DataSource = SubcategoryDAO.returnDataSource();
        }

        private void tabPageProduct_Click(object sender, EventArgs e)
        {
            dataGridProduct.DataSource = ProductDAO.returnDataSource();
        }

        private void tabPageEntry_Click(object sender, EventArgs e)
        {
            dataGridUser.DataSource = UserDAO.returnDataSource();
            dataGridProduct.DataSource = ProductDAO.returnDataSource();
            dataGridEntry.DataSource = EntryDAO.returnDataSource();
        }

        private void tabPageWithdrawal_Click(object sender, EventArgs e)
        {
            dataGridUser.DataSource = UserDAO.returnDataSource();
            dataGridProduct.DataSource = ProductDAO.returnDataSource();
            dataGridWithdrawal.DataSource = WithdrawalDAO.returnDataSource();
        }

        private void pbProductImage_Click(object sender, EventArgs e)
        {
            OpenFileDialog open = new OpenFileDialog();
            PictureBox p = sender as PictureBox;

            if (p != null)
            {
                open.Filter = "(*.jpg;*.jpeg;*.bmp;)| *.jpg; *.jpeg; *.bmp; ";
                if (open.ShowDialog() == DialogResult.OK)
                {
                    p.Image = Image.FromFile(open.FileName);
                }
            }
        }

        private void Main_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Login login = new Login();
            login.Show();
            this.Hide();
        }

        private void btnSearchName_Click(object sender, EventArgs e)
        {
            dataGridSearchProduct.DataSource = ProductDAO.returnDataSourceSearch(tbSearchName.Text.Trim());
        }

        private void button2_Click(object sender, EventArgs e)
        {
            tbSearchName.Text = "";
            dataGridSearchProduct.DataSource = ProductDAO.returnDataSource();
            foreach (RadioButton rb in flSearchCategory.Controls.OfType<RadioButton>())
            {
                if (rb.Checked)
                    rb.Checked = false;
            }
        }

        private void tabPageSearch_Click(object sender, EventArgs e)
        {
            flSearchCategory.Controls.Clear();
            DataTable dtCategory = CategoryDAO.returnDataSource();
            for (int i = 0; i < dtCategory.Rows.Count; i++)
            {
                RadioButton rb = new RadioButton();
                rb.Text = dtCategory.Rows[i]["name"].ToString();
                flSearchCategory.Controls.Add(rb);
            }
        }

        private void btnSearchCategory_Click(object sender, EventArgs e)
        {
            List<String> values = new List<String>();
            foreach (RadioButton rb in flSearchCategory.Controls.OfType<RadioButton>())
            {
                if (rb.Checked)
                    values.Add(rb.Text);
            }
            String nameCategory = values.ElementAt(0).ToString();

            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "SELECT id FROM category WHERE category.name = '" + nameCategory + "'";
            SqlDataReader dr = Connection.select(cmd);
            DataTable dtNameCategory = new DataTable();
            SqlDataAdapter da = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da.Fill(dtNameCategory);
            int idCategory = int.Parse(dtNameCategory.Rows[0]["id"].ToString());

            cmd.CommandText = "SELECT id FROM subcategory WHERE subcategory.category_id = " + idCategory;
            SqlDataReader dr1 = Connection.select(cmd);
            DataTable dtNameCategory1 = new DataTable();
            SqlDataAdapter da1 = new SqlDataAdapter(cmd.CommandText, Connection.connect());
            da1.Fill(dtNameCategory1);

            List<int> listIdSubcategory = new List<int>();
            for (int i = 0; i < dtNameCategory1.Rows.Count; i++)
                listIdSubcategory.Add(int.Parse(dtNameCategory1.Rows[i]["id"].ToString()));

            DataTable combinedData = new DataTable();

            foreach(int element in listIdSubcategory)
            {
                cmd.CommandText = "SELECT product.id, product.name, product.label, product.quantity_available, product.price, product.image FROM product, product_subcategory WHERE product_subcategory.subcategory_id = " + element + " AND product_subcategory.product_id = product.id";
                SqlDataReader dr2 = Connection.select(cmd);
                DataTable dtNameCategory2 = new DataTable();
                SqlDataAdapter da2 = new SqlDataAdapter(cmd.CommandText, Connection.connect());
                da2.Fill(dtNameCategory2);
                combinedData.Merge(dtNameCategory2);
            }
            dataGridSearchProduct.DataSource = combinedData;
        }
    }
}
