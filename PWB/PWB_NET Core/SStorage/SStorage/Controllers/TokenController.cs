using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using SStorage.Data;
using SStorage.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SStorage.Controllers
{
    [Route("rest/auth")]
    public class TokenController : Controller
    {
        public TokenController(SStorageDbContext context)
        {
            Context = context;
        }

        public SStorageDbContext Context { get; }

        [HttpPost("/jwt")]
        public IActionResult GenerateToken([FromBody] User user)
        {
            return null;
        }
    }
}
