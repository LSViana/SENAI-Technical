using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    [Table(nameof(Movement))]
    public class Movement
    {
        public Movement()
        {
            DateTime = DateTime.Now;
        }

        public long Id { get; set; }

        public Environment Origin { get; set; }
        public long OriginId { get; set; }

        public Environment Destiny { get; set; }
        public long DestinyId { get; set; }

        [Required]
        public DateTime DateTime { get; set; }

        public User User { get; set; }
        public long UserId { get; set; }

        public PatrimonyItem PatrimonyItem { get; set; }
        public long PatrimonyItemId { get; set; }
    }
}
