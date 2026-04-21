namespace A1
{
    partial class Login
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Login));
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.tbLoginEmail = new System.Windows.Forms.TextBox();
            this.btnLoginSignIn = new System.Windows.Forms.Button();
            this.btnLoginClear = new System.Windows.Forms.Button();
            this.tbLoginPassword = new System.Windows.Forms.TextBox();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Britannic Bold", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.ForeColor = System.Drawing.SystemColors.Control;
            this.label2.Location = new System.Drawing.Point(44, 117);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(56, 17);
            this.label2.TabIndex = 1;
            this.label2.Text = "EMAIL:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Britannic Bold", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.ForeColor = System.Drawing.SystemColors.Control;
            this.label3.Location = new System.Drawing.Point(44, 189);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(93, 17);
            this.label3.TabIndex = 2;
            this.label3.Text = "PASSWORD:";
            // 
            // tbLoginEmail
            // 
            this.tbLoginEmail.BackColor = System.Drawing.SystemColors.Window;
            this.tbLoginEmail.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tbLoginEmail.Location = new System.Drawing.Point(47, 137);
            this.tbLoginEmail.Name = "tbLoginEmail";
            this.tbLoginEmail.Size = new System.Drawing.Size(271, 26);
            this.tbLoginEmail.TabIndex = 3;
            // 
            // btnLoginSignIn
            // 
            this.btnLoginSignIn.BackColor = System.Drawing.Color.Red;
            this.btnLoginSignIn.Font = new System.Drawing.Font("Britannic Bold", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnLoginSignIn.ForeColor = System.Drawing.SystemColors.Control;
            this.btnLoginSignIn.Location = new System.Drawing.Point(47, 268);
            this.btnLoginSignIn.Name = "btnLoginSignIn";
            this.btnLoginSignIn.Size = new System.Drawing.Size(75, 33);
            this.btnLoginSignIn.TabIndex = 5;
            this.btnLoginSignIn.Text = "SIGN IN";
            this.btnLoginSignIn.UseVisualStyleBackColor = false;
            this.btnLoginSignIn.Click += new System.EventHandler(this.btnLoginSignIn_Click_1);
            // 
            // btnLoginClear
            // 
            this.btnLoginClear.BackColor = System.Drawing.Color.Red;
            this.btnLoginClear.Font = new System.Drawing.Font("Britannic Bold", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnLoginClear.ForeColor = System.Drawing.SystemColors.Control;
            this.btnLoginClear.Location = new System.Drawing.Point(243, 268);
            this.btnLoginClear.Name = "btnLoginClear";
            this.btnLoginClear.Size = new System.Drawing.Size(75, 33);
            this.btnLoginClear.TabIndex = 6;
            this.btnLoginClear.Text = "CLEAR";
            this.btnLoginClear.UseVisualStyleBackColor = false;
            this.btnLoginClear.Click += new System.EventHandler(this.btnLoginClear_Click);
            // 
            // tbLoginPassword
            // 
            this.tbLoginPassword.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tbLoginPassword.Location = new System.Drawing.Point(47, 209);
            this.tbLoginPassword.Name = "tbLoginPassword";
            this.tbLoginPassword.PasswordChar = '*';
            this.tbLoginPassword.Size = new System.Drawing.Size(271, 26);
            this.tbLoginPassword.TabIndex = 9;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::A1.Properties.Resources._1200px_New_England_Patriots_logo_svg;
            this.pictureBox1.Location = new System.Drawing.Point(94, 26);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(160, 73);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 10;
            this.pictureBox1.TabStop = false;
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Navy;
            this.ClientSize = new System.Drawing.Size(361, 341);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.tbLoginPassword);
            this.Controls.Add(this.btnLoginClear);
            this.Controls.Add(this.btnLoginSignIn);
            this.Controls.Add(this.tbLoginEmail);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Login";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "[PATRIOTS STORE] - LOGIN";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Login_FormClosed);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tbLoginEmail;
        private System.Windows.Forms.Button btnLoginSignIn;
        private System.Windows.Forms.Button btnLoginClear;
        private System.Windows.Forms.TextBox tbLoginPassword;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}

