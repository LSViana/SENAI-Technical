using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank.Business
{
    class Product
    {
        private long barCode;

        public long BarCode
        {
            get { return barCode; }
            set { if (value < Math.Abs(1_000_000_000L)) throw new InvalidOperationException("You can't define a bar code with less than 10 digits."); barCode = value; }
        }

        private String description;

        public String Description
        {
            get { return description == null ? "<Empty>" : description; }
            set { if (value.Length < 5) throw new InvalidOperationException("You can't define a description with less than 5 characters."); description = value; }
        }

        private double price;

        public double Price
        {
            get { return price; }
            set { if (value <= 0) throw new InvalidOperationException("The value for price must be a positive value."); price = value; }
        }

        private String category;

        public String Category
        {
            get { return category == null ? "<Empty>" : category; }
            set { if (value.Length < 5) throw new InvalidOperationException("You can't define a category with less than 10 characters.");  category = value; }
        }

        private bool perishable;

        public bool Perishable
        {
            get { return perishable; }
            set { perishable = value; }
        }

        public Product()
        {
            // Standard constructor
        }
        public Product(long BarCode, String Description, double Price, String Category, bool Perishable) : base()
        {
            this.BarCode = BarCode;
            this.Description = Description;
            this.Price = Price;
            this.Category = Category;
            this.Perishable = Perishable;
        }
        // Overwritten methods
        public override string ToString()
        {
            return String.Format("\tProduct description:\nBarcode: {0,-15}\nDescription: {1}\nPrice: {2:C2}\nPerishable: {3}", BarCode, Description, Price, Category, Perishable ? "Yes" : "No");
        }
    }
}
