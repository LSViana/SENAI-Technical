using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models
{
    public class Movement
    {
        public long Id { get; set; }

        public DateTime DateTime { get; set; }
        
        public PatrimonyItem PatrimonyItem { get; set; }
        public long PatrimonyItemId { get; set; }

        public Environment OriginEnvironment { get; set; }
        public long OriginEnvironmentId { get; set; }

        public Environment DestinyEnvironment { get; set; }
        public long DestinyEnvironmentId { get; set; }

        public User User { get; set; }
        public long UserId { get; set; }
    }
}
