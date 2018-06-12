using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SStorage.Data;
using SStorage.Models;
using SStorage.Utils;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SStorage.Controllers.v1
{
    [Route("rest/v1/[controller]")]
    [Authorize(Policies.Administrator)]
    public class PatrimoniesController : Controller
    {
        public SStorageDbContext Context { get; }

        public PatrimoniesController(SStorageDbContext context)
        {
            Context = context;
        }
        
        [HttpGet]
        [Authorize]
        public async Task<IActionResult> Get()
        {
            return Ok(await Context.Patrimonies.ToListAsync());
        }

        [HttpGet("{id}", Name = "GetPatrimonyById")]
        [Authorize]
        public async Task<IActionResult> Get(long id)
        {
            var patrimony = await Context.Patrimonies.Where(a => a.Id == id).AsNoTracking().FirstOrDefaultAsync();
            if (patrimony is null)
                return NotFound();
            return Ok(patrimony);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(long id)
        {
            var patrimony = await Context.Patrimonies.Where(a => a.Id == id).AsNoTracking().FirstOrDefaultAsync();
            if (patrimony is null)
                return NotFound();
            Context.Patrimonies.Remove(patrimony);
            await Context.SaveChangesAsync();
            return Ok();
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Update(long id, [FromBody] Patrimony patrimony)
        {
            if (id != patrimony.Id)
                return BadRequest();
            foreach (var prop in Patrimony.PropertiesNotToUpdate)
            {
                ModelState.Remove(prop);
            }
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            // Updating from database
            var fromDb = await Context.Patrimonies.FindAsync(id);
            if (fromDb is null)
                return NotFound();
            fromDb.Copy(patrimony, Patrimony.PropertiesNotToUpdate);
            Context.Patrimonies.Update(fromDb);
            await Context.SaveChangesAsync();
            return Ok(patrimony);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] Patrimony patrimony)
        {
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            await Context.Patrimonies.AddAsync(patrimony);
            await Context.SaveChangesAsync();
            return CreatedAtAction("GetPatrimonyById", new { id = patrimony.Id }, patrimony);
        }
    }
}
