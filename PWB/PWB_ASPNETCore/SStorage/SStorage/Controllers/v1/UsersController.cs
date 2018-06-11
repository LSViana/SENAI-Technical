using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SStorage.Data;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SStorage.Controllers.V1
{
    [Route("rest/v1/[controller]")]
    public class UsersController : Controller
    {
        public UsersController(SStorageDbContext context)
        {
            Context = context;
        }

        public SStorageDbContext Context { get; }

        [HttpGet]
        [Authorize]
        public async Task<IActionResult> Get()
        {
            var list = await Context.Users.ToListAsync();
            return Ok(list);
        }
    }
}
