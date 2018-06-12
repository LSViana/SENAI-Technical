using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    public class Patrimony
    {
        public static string[] PropertiesNotToUpdate = new string[] { nameof(Id), nameof(PatrimonyCategory), nameof(User), nameof(DateTime) };

        public long Id { get; set; }

        [Required]
        [StringLength(40, MinimumLength = 1)]
        public string Name { get; set; }

        public PatrimonyCategory PatrimonyCategory { get; set; }
        public long PatrimonyCategoryId { get; set; }

        public User User { get; set; }
        public long UserId { get; set; }

        public DateTime DateTime { get; set; }

        //public ICollection<PatrimonyItem> PatrimonyItems { get; set; }
    }
}
