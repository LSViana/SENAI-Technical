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
                return _password;
            }
            set {
                _password = value;
                PasswordDatabase = Hash(value);
            }
        }

        private string _password;

        // Validation here is never used because the Json.NET is disabled here
        [Required]
        [StringLength(128)]
        public string PasswordDatabase { get; private set; }

        [Required]
        public UserType UserType { get; set; }

        //public ICollection<Movement> Movements { get; set; }

        //public ICollection<PatrimonyItem> PatrimonyItems { get; set; }

        //public ICollection<Patrimony> Patrimonies { get; set; }

        public static string[] PropertiesNotToUpdate = new string[] { nameof(Id), nameof(Password), nameof(PasswordDatabase) };

        public static String Hash(String value)
        {
            var alg = SHA256.Create();
            return Encoding.UTF8.GetString(alg.ComputeHash(Encoding.UTF8.GetBytes(value)));
        }

        // It avoids the JSON Serialization of PasswordDatabase, because of the name [ShouldSerialize?]PasswordDatabase
        public bool ShouldSerializePasswordDatabase() {
            return false;
        }

        // Same as ShouldSerializePasswordDatabase
        public bool ShouldSerializePassword() {
            return false;
        }

        public bool ShouldDeserializePasswordDatabase()
        {
            return false;
        }
    }
}
