namespace Conditionals
{
    partial class RollDie
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
            this.panelRight = new System.Windows.Forms.Panel();
            this.button1 = new System.Windows.Forms.Button();
            this.panelWrong = new System.Windows.Forms.Panel();
            this.label1 = new System.Windows.Forms.Label();
            this.labelResult = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.panelRight.SuspendLayout();
            this.panelWrong.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelRight
            // 
            this.panelRight.Controls.Add(this.label3);
            this.panelRight.Location = new System.Drawing.Point(13, 13);
            this.panelRight.Name = "panelRight";
            this.panelRight.Size = new System.Drawing.Size(252, 59);
            this.panelRight.TabIndex = 0;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(190, 129);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 0;
            this.button1.Text = "&Retornar";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // panelWrong
            // 
            this.panelWrong.Controls.Add(this.label1);
            this.panelWrong.Location = new System.Drawing.Point(13, 13);
            this.panelWrong.Name = "panelWrong";
            this.panelWrong.Size = new System.Drawing.Size(252, 59);
            this.panelWrong.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold);
            this.label1.ForeColor = System.Drawing.Color.Red;
            this.label1.Location = new System.Drawing.Point(69, 17);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(112, 21);
            this.label1.TabIndex = 0;
            this.label1.Text = "Você errou! :/";
            // 
            // labelResultado
            // 
            this.labelResult.AutoSize = true;
            this.labelResult.Location = new System.Drawing.Point(176, 91);
            this.labelResult.Name = "labelResultado";
            this.labelResult.Size = new System.Drawing.Size(13, 13);
            this.labelResult.TabIndex = 1;
            this.labelResult.Text = "1";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(88, 91);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(66, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Valor rolado:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold);
            this.label3.ForeColor = System.Drawing.Color.Green;
            this.label3.Location = new System.Drawing.Point(70, 19);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(128, 21);
            this.label3.TabIndex = 1;
            this.label3.Text = "Você acertou! :)";
            // 
            // RollDie
            // 
            this.AcceptButton = this.button1;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(277, 164);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.labelResult);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.panelRight);
            this.Controls.Add(this.panelWrong);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "RollDie";
            this.ShowIcon = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Rolar o Dado";
            this.Load += new System.EventHandler(this.RollDie_Load);
            this.panelRight.ResumeLayout(false);
            this.panelRight.PerformLayout();
            this.panelWrong.ResumeLayout(false);
            this.panelWrong.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel panelRight;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Panel panelWrong;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label labelResult;
        private System.Windows.Forms.Label label2;
    }
}