using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    public class Environment
    {
        public long Id { get; set; }

        [Required]
        [StringLength(40, MinimumLength = 1)]
        public string Name { get; set; }

        //public ICollection<Movement> OriginMovements { get; set; }

        //public ICollection<Movement> DestinyMovements { get; set; }

        //public ICollection<PatrimonyItem> PatrimonyItems { get; set; }
    }
}
