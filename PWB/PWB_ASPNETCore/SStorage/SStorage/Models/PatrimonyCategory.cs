using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    public class PatrimonyCategory
    {
        public static string[] PropertiesNotToUpdate = new string[] { nameof(Id) };

        public long Id { get; set; }

        [Required]
        [StringLength(30, MinimumLength = 1)]
        public string Name { get; set; }

        //public ICollection<Patrimony> Patrimonies { get; set; }
    }
}
