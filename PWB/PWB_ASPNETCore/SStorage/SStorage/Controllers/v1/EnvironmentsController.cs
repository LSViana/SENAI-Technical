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
    [Authorize]
    public class EnvironmentsController : Controller
    {

        public EnvironmentsController(SStorageDbContext context)
        {
            Context = context;
        }

        public SStorageDbContext Context { get; }

        [NonAction]
        public async Task<bool> IsValid(Models.Environment environment, long? Id = null)
        {
            if (!ModelState.IsValid)
                return false;
            var sameName = await Context.Environments.Where(a => a.Name == environment.Name).FirstOrDefaultAsync();
            if (sameName != null && sameName.Id != environment.Id)
            {
                var _name = nameof(Models.Environment.Name);
                ModelState.AddModelError(_name, String.Format(Strings.AlreadyInUse, _name));
                return false;
            }
            if (Id.HasValue)
            {
                if (environment.Id != Id)
                {
                    var _id = nameof(Models.Environment.Id);
                    ModelState.AddModelError(_id, String.Format(Strings.NoMatch, _id, _id));
                    return false;
                }
            }
            return true;
        }

        [HttpGet]
        public async Task<IActionResult> Get()
        {
            var environments = await Context.Environments.AsNoTracking().ToListAsync();
            return Ok(environments);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> Get(long id)
        {
            var environment = await Context.Environments.FindAsync(id);
            if (environment is null)
                return NotFound();
            return Ok(environment);
        }

        [HttpPost]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Create([FromBody] Models.Environment environment)
        {
            if (!await IsValid(environment))
                return UnprocessableEntity(ModelState);
            //
            await Context.Environments.AddAsync(environment);
            await Context.SaveChangesAsync();
            return CreatedAtAction(nameof(Get), new { id = environment.Id }, environment);
        }

        [HttpPut("{id}")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Update(long id, [FromBody] Models.Environment environment)
        {
            if (!await IsValid(environment))
                return UnprocessableEntity(ModelState);
            //
            Context.Environments.Update(environment);
            await Context.SaveChangesAsync();
            return Ok(environment);
        }

        [HttpDelete("{id}")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Delete(long id)
        {
            var environment = await Context.Environments.FindAsync(id);
            if (environment is null)
                return NotFound();
            Context.Environments.Remove(environment);
            await Context.SaveChangesAsync();
            return Ok();
        }

    }
}
