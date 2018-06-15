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
    [Authorize]
    public class PatrimonyCategoriesController : Controller
    {

        public PatrimonyCategoriesController(SStorageDbContext context)
        {
            Context = context;
        }

        public SStorageDbContext Context { get; }

        [NonAction]
        private async Task<bool> IsValid(PatrimonyCategory patrimonyCategory, long? Id = null)
        {
            if (!ModelState.IsValid)
                return false;
            var sameName = await Context.PatrimonyCategories.Where(a => a.Name == patrimonyCategory.Name).FirstOrDefaultAsync();
            if (sameName != null && sameName.Id != patrimonyCategory.Id)
            {
                ModelState.AddModelError(nameof(PatrimonyCategory.Name), "Name is already in use");
                return false;
            }
            if (Id.HasValue && patrimonyCategory.Id != Id)
            {
                ModelState.AddModelError(nameof(PatrimonyCategory.Id), "ID at query string doesn't match with body");
                return false;
            }
            return true;
        }

        [HttpGet]
        public async Task<IActionResult> Get()
        {
            return Ok(await Context.PatrimonyCategories.AsNoTracking().ToListAsync());
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> Get(long id)
        {
            var patrimonyCategory = await Context.PatrimonyCategories.AsNoTracking().Where(a => a.Id == id).FirstOrDefaultAsync();
            if (patrimonyCategory is null)
                return NotFound();
            return Ok(patrimonyCategory);
        }

        [HttpPost]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Create([FromBody] PatrimonyCategory patrimonyCategory)
        {
            if (!await IsValid(patrimonyCategory))
                return UnprocessableEntity(ModelState);
            await Context.PatrimonyCategories.AddAsync(patrimonyCategory);
            await Context.SaveChangesAsync();
            return CreatedAtAction(nameof(Get), new { id = patrimonyCategory.Id }, patrimonyCategory);
        }

        [HttpPut("{id}")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Update(long id, [FromBody] PatrimonyCategory patrimonyCategory)
        {
            foreach (var prop in PatrimonyCategory.PropertiesNotToUpdate)
            {
                ModelState.Remove(prop);
            }
            if (!await IsValid(patrimonyCategory))
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
        [Authorize(Policies.Administrator)]
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