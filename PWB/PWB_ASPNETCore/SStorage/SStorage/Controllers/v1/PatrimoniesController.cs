using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SStorage.Data;
using SStorage.Models;
using SStorage.Utils;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SStorage.Controllers.v1
{
    [Route("rest/v1/[controller]")]
    [Authorize]
    public class PatrimoniesController : Controller
    {
        public SStorageDbContext Context { get; }

        public PatrimoniesController(SStorageDbContext context)
        {
            Context = context;
        }

        [NonAction]
        public async Task<bool> IsValid(Patrimony patrimony, long? Id = null)
        {
            var result = true;
            if (!ModelState.IsValid)
                result = false;
            // Business
            var sameName = await Context.Patrimonies.Where(a => a.Name == patrimony.Name).FirstOrDefaultAsync();
            if (sameName != null && sameName.Id != patrimony.Id)
            {
                var _name = nameof(Patrimony.Name);
                ModelState.AddModelError(_name, String.Format(Strings.AlreadyInUse, _name));
                result = false;
            }
            if (Id.HasValue && patrimony.Id != Id)
            {
                var _id = nameof(Patrimony.Id);
                ModelState.AddModelError(_id, String.Format(Strings.NoMatch, _id));
                result = false;
            }
            // Foreign Keys
            // PatrimonyCategory
            var patrimonyCategory = await Context.PatrimonyCategories.Where(a => a.Id == patrimony.PatrimonyCategoryId).FirstOrDefaultAsync();
            if (patrimonyCategory is null)
            {
                var _pc = nameof(Patrimony.PatrimonyCategoryId);
                ModelState.AddModelError(_pc, String.Format(Strings.NotFound, _pc));
                result = false;
            }
            // User
            var user = await Context.Users.Where(a => a.Id == patrimony.UserId).FirstOrDefaultAsync();
            if (user is null)
            {
                var _user = nameof(Patrimony.UserId);
                ModelState.AddModelError(_user, String.Format(Strings.NotFound, _user));
                result = false;
            }
            return result;
        }

        [HttpGet]
        public async Task<IActionResult> Get()
        {
            return Ok(await Context.Patrimonies.ToListAsync());
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> Get(long id)
        {
            var patrimony = await Context.Patrimonies.Where(a => a.Id == id).AsNoTracking().FirstOrDefaultAsync();
            if (patrimony is null)
                return NotFound();
            return Ok(patrimony);
        }

        [HttpDelete("{id}")]
        [Authorize(Policies.Administrator)]
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
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Update(long id, [FromBody] Patrimony patrimony)
        {
            foreach (var prop in Patrimony.PropertiesNotToUpdate)
            {
                ModelState.Remove(prop);
            }
            //
            await SetPatrimonyUser(patrimony);
            //
            if (!await IsValid(patrimony, id))
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
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Create([FromBody] Patrimony patrimony)
        {
            // Getting user from Token
            await SetPatrimonyUser(patrimony);
            //
            if (!await IsValid(patrimony))
                return UnprocessableEntity(ModelState);
            //
            await Context.Patrimonies.AddAsync(patrimony);
            await Context.SaveChangesAsync();
            return CreatedAtAction(nameof(Get), new { id = patrimony.Id }, patrimony);
        }

        [HttpGet("items/{id}")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> GetItems(long id)
        {
            var patrimony = await Context.Patrimonies.Where(a => a.Id == id).AsNoTracking().FirstOrDefaultAsync();
            if (patrimony is null)
                return NotFound();
            var items = await Context.PatrimonyItems
                .AsNoTracking()
                .Where(a => a.PatrimonyId == patrimony.Id)
                .Include(a => a.Patrimony)
                    .ThenInclude(a => a.PatrimonyCategory)
                .Include(a => a.Environment)
                .ToListAsync();
            return Ok(items);
        }

        [NonAction]
        private async Task SetPatrimonyUser(Patrimony patrimony)
        {
            var userEmail = User.Claims.FirstOrDefault(a => a.Type == ClaimTypes.Email);
            var user = await Context.Users.Where(a => a.Email == userEmail.Value).AsNoTracking().FirstOrDefaultAsync();
            patrimony.UserId = user.Id;
            if(patrimony.DateTime == default(DateTime))
                // Considering that no DateTime was supplied
                patrimony.DateTime = DateTime.Now;
        }
    }
}
