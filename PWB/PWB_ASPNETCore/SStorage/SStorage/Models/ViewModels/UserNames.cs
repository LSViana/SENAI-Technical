using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models.ViewModels
{
    public class UserNames
    {

        [Required]
        [StringLength(20, MinimumLength = 1)]
        public string Name { get; set; }

        [Required]
        [StringLength(40, MinimumLength = 1)]
        public string LastName { get; set; }

    }
}
