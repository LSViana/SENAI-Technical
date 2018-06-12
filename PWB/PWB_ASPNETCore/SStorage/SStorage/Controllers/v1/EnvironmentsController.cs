using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SStorage.Data;
using SStorage.Utils;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SStorage.Controllers.v1
{
    [Route("rest/v1/[controller]")]
    [Authorize(Policies.Administrator)]
    public class EnvironmentsController : Controller
    {

        public EnvironmentsController(SStorageDbContext context)
        {
            Context = context;
        }

        public SStorageDbContext Context { get; }

        [HttpGet]
        [Authorize]
        public async Task<IActionResult> Get()
        {
            var environments = await Context.Environments.AsNoTracking().ToListAsync();
            return Ok(environments);
        }

        [HttpGet("{id}", Name = "GetEnvironmentById")]
        [Authorize]
        public async Task<IActionResult> Get(long id)
        {
            var environment = await Context.Environments.FindAsync(id);
            if (environment is null)
                return NotFound();
            return Ok(environment);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] Models.Environment environment)
        {
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            await Context.Environments.AddAsync(environment);
            await Context.SaveChangesAsync();
            return CreatedAtAction("GetEnvironmentById", new { id = environment.Id }, environment);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Update(long id, [FromBody] Models.Environment environment)
        {
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            if (id != environment.Id)
                return BadRequest(new { Reason = "ID supplied at query string doesn't match entity at body" });
            Context.Environments.Update(environment);
            await Context.SaveChangesAsync();
            return Ok(environment);
        }

    }
}
