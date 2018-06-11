using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace SStorage.Models
{
    public class User
    {
        public long Id { get; set; }

        [Required]
        [StringLength(20, MinimumLength = 1)]
        public string Name { get; set; }

        [Required]
        [StringLength(40, MinimumLength = 1)]
        public string LastName { get; set; }

        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        [StringLength(20, MinimumLength = 6)]
        [NotMapped]
        public string Password {
            get
            {
                return PasswordDatabase;
            }
            set {
                PasswordDatabase = value;
            }
        }

        [Required]
        [StringLength(256, MinimumLength = 128)]
        public string PasswordDatabase { get; private set; }

        [Required]
        public UserType UserType { get; set; }

        public ICollection<Movement> Movements { get; set; }

        public ICollection<PatrimonyItem> PatrimonyItems { get; set; }

        public ICollection<Patrimony> Patrimonies { get; set; }

        public static String Hash(String value)
        {
            var alg = SHA256.Create();
            return Encoding.UTF8.GetString(alg.ComputeHash(Encoding.UTF8.GetBytes(value)));
        }

        public bool ShouldSerializePasswordDatabase() {
            return false;
        }

        public bool ShouldSerializePassword() {
            return false;
        }
    }
}
