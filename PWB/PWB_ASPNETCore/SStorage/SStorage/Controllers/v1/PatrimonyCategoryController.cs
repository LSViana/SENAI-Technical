using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SStorage.Data;
using SStorage.Models;
using SStorage.Utils;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Controllers.v1
{
    [Route("rest/v1/[controller]")]
    [Authorize(Policies.Administrator)]
    public class PatrimonyCategoryController : Controller
    {

        public PatrimonyCategoryController(SStorageDbContext context)
        {
            Context = context;
        }

        public SStorageDbContext Context { get; }

        [Authorize]
        public async Task<IActionResult> Get()
        {
            return Ok(await Context.PatrimonyCategories.AsNoTracking().ToListAsync());
        }

        [HttpGet("{id}", Name = "GetPatrimonyCategoryById")]
        [Authorize]
        public async Task<IActionResult> Get(long id)
        {
            var patrimonyCategory = await Context.PatrimonyCategories.AsNoTracking().Where(a => a.Id == id).FirstOrDefaultAsync();
            if (patrimonyCategory is null)
                return NotFound();
            return Ok(patrimonyCategory);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] PatrimonyCategory patrimonyCategory)
        {
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            await Context.PatrimonyCategories.AddAsync(patrimonyCategory);
            await Context.SaveChangesAsync();
            return CreatedAtAction("GetPatrimonyCategoryById", new { id = patrimonyCategory.Id }, patrimonyCategory);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Update(long id, [FromBody] PatrimonyCategory patrimonyCategory)
        {
            if (id != patrimonyCategory.Id)
                return BadRequest(new { Reason = "ID supplied at query string doesn't match entity at body" });
            foreach (var prop in PatrimonyCategory.PropertiesNotToUpdate)
            {
                ModelState.Remove(prop);
            }
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            // Updating from Database
            var fromDb = await Context.PatrimonyCategories.Where(a => a.Id == id).FirstOrDefaultAsync();
            if (fromDb is null)
                return NotFound();
            fromDb.Copy(patrimonyCategory, PatrimonyCategory.PropertiesNotToUpdate);
            Context.PatrimonyCategories.Update(fromDb);
            await Context.SaveChangesAsync();
            return Ok(fromDb);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(long id)
        {
            var patrimonyCategory = await Context.PatrimonyCategories.Where(a => a.Id == id).FirstOrDefaultAsync();
            if (patrimonyCategory is null)
                return NotFound();
            Context.PatrimonyCategories.Remove(patrimonyCategory);
            await Context.SaveChangesAsync();
            return Ok();
        }

    }
}