﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    [Table(nameof(Patrimony))]
    public class Patrimony
    {
        public long Id { get; set; }

        [StringLength(40, MinimumLength = 1)]
        public string Name { get; set; }

        public DateTime DateTime { get; set; }

        public PatrimonyCategory PatrimonyCategory { get; set; }
        public long PatrimonyCategoryId { get; set; }

        public ICollection<PatrimonyItem> PatrimonyItems { get; set; }

        public User User { get; set; }
        public long UserId { get; set; }
    }
}
