using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    [Table(nameof(PatrimonyCategory))]
    public class PatrimonyCategory
    {
        public long Id { get; set; }

        [Required]
        [StringLength(30, MinimumLength = 1)]
        public String Name { get; set; }

        public ICollection<Patrimony> Patrimonies { get; set; }
    }
}
