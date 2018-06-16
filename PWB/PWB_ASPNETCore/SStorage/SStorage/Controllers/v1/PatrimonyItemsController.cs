using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
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
    [Authorize]
    public class PatrimonyItemsController : Controller
    {
        public SStorageDbContext Context { get; }

        public PatrimonyItemsController(SStorageDbContext context)
        {
            Context = context;
        }

        [NonAction]
        // Update not allowed
        public async Task<bool> IsValid(PatrimonyItem patrimonyItem, long? Id = null, bool Create = false)
        {
            var result = true;
            if (!Create)
                throw new InvalidOperationException("Update not allowed");
            if (!ModelState.IsValid)
                result = false;
            // Business
            if (patrimonyItem.Id == 0)
            {
                var _id = nameof(PatrimonyItem.Id);
                ModelState.AddModelError(_id, String.Format(Strings.Required, _id));
                result = false;
            }
            var sameId = await Context.PatrimonyItems.Where(a => a.Id == Id).FirstOrDefaultAsync();
            if (sameId != null)
            {
                var _id = nameof(PatrimonyItem.Id);
                ModelState.AddModelError(_id, String.Format(Strings.AlreadyInUse, _id));
                result = false;
            }
            // FKs
            // Enviroment
            var environment = await Context.Environments.Where(a => a.Id == patrimonyItem.EnvironmentId).FirstOrDefaultAsync();
            if (environment is null)
            {
                var _env = nameof(PatrimonyItem.EnvironmentId);
                ModelState.AddModelError(_env, String.Format(Strings.NotFound, _env));
                result = false;
            }
            // Patrimony
            var patrimony = await Context.Patrimonies.Where(a => a.Id == patrimonyItem.PatrimonyId).FirstOrDefaultAsync();
            if (patrimony is null)
            {
                var _patrimony = nameof(PatrimonyItem.PatrimonyId);
                ModelState.AddModelError(_patrimony, String.Format(Strings.NotFound, _patrimony));
                result = false;
            }
            // User
            var userEmail = User.Claims.FirstOrDefault(a => a.Type == ClaimTypes.Email).Value;
            var user = await Context.Users.Where(a => a.Email == userEmail).FirstOrDefaultAsync();
            patrimonyItem.UserId = user.Id;
            patrimonyItem.User = user;
            return result;
        }

        [HttpGet]
        public async Task<IActionResult> Get()
        {
            return Ok(await Context.PatrimonyItems.AsNoTracking().ToListAsync());
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> Get(long id)
        {
            var patrimonyItem = await Context.PatrimonyItems
                .Where(a => a.Id == id)
                .Include(a => a.Patrimony)
                    .ThenInclude(a => a.PatrimonyCategory)
                .Include(a => a.Environment)
                .FirstOrDefaultAsync();
            if (patrimonyItem is null)
                return NotFound();
            return Ok(patrimonyItem);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] PatrimonyItem patrimonyItem)
        {
            if (!await IsValid(patrimonyItem, Create: true))
                return UnprocessableEntity(ModelState);
            // Standard values
            patrimonyItem.State = PatrimonyItemState.Active;
            patrimonyItem.LastMovement = DateTime.Now;
            //
            var fromDb = await Context.PatrimonyItems.Where(a => a.Id == patrimonyItem.Id).FirstOrDefaultAsync();
            if (fromDb != null)
                return Conflict(new { Reason = "ID already in use" });
            // Saving at database
            await Context.PatrimonyItems.AddAsync(patrimonyItem);
            await Context.SaveChangesAsync();
            return CreatedAtAction(nameof(Get), new { patrimonyItem.Id }, patrimonyItem);
        }

        [HttpDelete("{id}")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Delete(long id)
        {
            var patrimonyItem = await Context.PatrimonyItems.Where(a => a.Id == id).FirstOrDefaultAsync();
            if (patrimonyItem is null)
                return NotFound();
            Context.PatrimonyItems.Remove(patrimonyItem);
            await Context.SaveChangesAsync();
            return Ok();
        }

        [HttpPost("{patrimonyItemId}/moveto/{environmentId}")]
        public async Task<IActionResult> Move(long patrimonyItemId, long environmentId)
        {
            // Getting values supplied from URL
            var item = await Context.PatrimonyItems
                .Where(a => a.Id == patrimonyItemId)
                // This line loads from the database the Environment that will be filled, by EF, at Movement object
                .Include(a => a.Environment)
                .FirstOrDefaultAsync();
            if (item is null)
                return NotFound(new { Reason = String.Format(Strings.NotFound, nameof(patrimonyItemId)) });
            var environment = await Context.Environments.Where(a => a.Id == environmentId).FirstOrDefaultAsync();
            if (environment is null)
                return NotFound(new { Reason = String.Format(Strings.NotFound, nameof(environmentId)) });
            // Verifying if Origin and Destiny are the same
            if(environmentId == item.EnvironmentId)
            {
                return Conflict(new { Reason = String.Format(Strings.CantChange, item.EnvironmentId, environmentId) });
            }
            // Getting user from Token
            var userEmail = User.Claims.FirstOrDefault(a => a.Type == ClaimTypes.Email)?.Value;
            var user = await Context.Users.Where(a => a.Email == userEmail).FirstOrDefaultAsync();
            // Creating the movement
            var movement = new Movement()
            {
                DateTime = DateTime.Now,
                DestinyEnvironmentId = environmentId,
                OriginEnvironmentId = item.EnvironmentId,
                PatrimonyItemId = item.Id,
                UserId = user.Id
            };
            // Updating PatrimonyItem
            item.LastMovement = DateTime.Now;
            item.EnvironmentId = environmentId;
            // Adding to Context, then, Database
            await Context.Movements.AddAsync(movement);
            await Context.SaveChangesAsync();
            return CreatedAtAction(nameof(Get), new { id = movement.Id }, movement);
        }

        [HttpGet("{id}/moves")]
        public async Task<IActionResult> GetMovements(long id)
        {
            var patrimonyItem = await Context.PatrimonyItems.Where(a => a.Id == id).AsNoTracking().FirstOrDefaultAsync();
            if (patrimonyItem is null)
                return NotFound();
            var movements = await Context.Movements
                .AsNoTracking()
                .Where(a => a.PatrimonyItemId == patrimonyItem.Id)
                .ToListAsync();
            return Ok(movements);
        }

        [HttpGet("{id}/requestdown")]
        public async Task<IActionResult> RequestDown(long id)
        {
            var patrimonyItem = await Context.PatrimonyItems.Where(a => a.Id == id).FirstOrDefaultAsync();
            if (patrimonyItem is null)
                return NotFound();
            if(patrimonyItem.State != PatrimonyItemState.Active)
            {
                return Conflict(new { Reason = String.Format(Strings.CantChange, patrimonyItem.State, PatrimonyItemState.Requested) });
            }
            patrimonyItem.State = PatrimonyItemState.Requested;
            await Context.SaveChangesAsync();
            return Ok(patrimonyItem);
        }

        [HttpGet("{id}/turndown")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> TurnDown(long id)
        {
            var patrimonyItem = await Context.PatrimonyItems.Where(a => a.Id == id).FirstOrDefaultAsync();
            if (patrimonyItem is null)
                return NotFound();
            if (patrimonyItem.State != PatrimonyItemState.Requested)
            {
                return Conflict(new { Reason = String.Format(Strings.CantChange, patrimonyItem.State, PatrimonyItemState.Removed) });
            }
            patrimonyItem.State = PatrimonyItemState.Removed;
            await Context.SaveChangesAsync();
            return Ok(patrimonyItem);
        }

    }
}
