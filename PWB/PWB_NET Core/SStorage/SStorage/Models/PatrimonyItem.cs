using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    [Table(nameof(PatrimonyItem))]
    public class PatrimonyItem
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public long Id { get; set; }

        public Patrimony Patrimony { get; set; }
        public long PatrimonyId { get; set; }

        public Environment Environment { get; set; }
        public long EnvironmentId { get; set; }

        public User User { get; set; }
        public long UserId { get; set; }

        public ICollection<Movement> Movements { get; set; }
    }
}
