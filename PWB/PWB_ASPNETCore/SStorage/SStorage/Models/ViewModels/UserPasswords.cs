using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Models.ViewModels
{
    public class UserPasswords
    {
        [Required]
        [StringLength(20, MinimumLength = 6)]
        public string Current { get; set; }

        [Required]
        [StringLength(20, MinimumLength = 6)]
        public string Password { get; set; }

    }
}
