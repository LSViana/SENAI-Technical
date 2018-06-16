using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SStorage.Models
{
    [Table(nameof(User))]
    public class User
    {
        public long Id { get; set; }

        [StringLength(20, MinimumLength = 1)]
        public string Name { get; set; }

        [StringLength(40, MinimumLength = 1)]
        public string LastName { get; set; }

        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [StringLength(20, MinimumLength = 6)]
        public string Password { get; set; }

        public ICollection<PatrimonyItem> PatrimonyItems { get; set; }

        public ICollection<Patrimony> Patrimonies { get; set; }

        [Required]
        public UserType UserType { get; set; }

        public void HashPassword()
        {
            var passwordBytes = Encoding.UTF8.GetBytes(Password);
            var alg = System.Security.Cryptography.HashAlgorithm.Create().ComputeHash(passwordBytes);
            Password = Encoding.UTF8.GetString(passwordBytes);
        }
    }
}
