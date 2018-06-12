using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Microsoft.EntityFrameworkCore;
using SStorage.Data;
using SStorage.Models;
using SStorage.Models.ViewModels;
using SStorage.Utils;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SStorage.Controllers.V1
{
    [Route("rest/v1/[controller]")]
    [Authorize]
    public class UsersController : Controller
    {
        public UsersController(SStorageDbContext context)
        {
            Context = context;
        }

        public SStorageDbContext Context { get; }

        [HttpGet]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Get()
        {
            return Ok(await Context.Users.AsNoTracking().ToListAsync());
        }

        [HttpGet("{id}", Name = "GetUserById")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Get(long id)
        {
            User user = await Context.Users.Where(a => a.Id == id).AsNoTracking().FirstOrDefaultAsync();
            if (user is null)
                return NotFound();
            return Ok(user);
        }

        [HttpPost]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Create([FromBody] User user)
        {
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            await Context.Users.AddAsync(user);
            await Context.SaveChangesAsync();
            return CreatedAtAction(nameof(Get), new { id = user.Id }, user);
        }

        [HttpPut("{id}")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Update(long id, [FromBody] User user)
        {
            if (id != user.Id)
                return BadRequest(new { Reason = "ID supplied at query string doesn't match entity at body" });
            foreach (var prop in Models.User.PropertiesNotToUpdate)
            {
                ModelState.Remove(prop);
            }
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            // Updating from Database
            var fromDb = await Context.Users.FindAsync(id);
            if (fromDb is null)
                return NotFound();
            fromDb.Copy(user, Models.User.PropertiesNotToUpdate);
            Context.Users.Update(fromDb);
            await Context.SaveChangesAsync();
            return Ok(user);
        }

        [HttpDelete("{id}")]
        [Authorize(Policies.Administrator)]
        public async Task<IActionResult> Delete(long id)
        {
            var user = await Context.Users.FindAsync(id);
            if (user is null)
                return NotFound();
            Context.Users.Remove(user);
            await Context.SaveChangesAsync();
            return Ok();
        }

        [HttpPost("changename")]
        public async Task<IActionResult> ChangeName([FromBody] UserNames userNames)
        {
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            // Getting user from User (ASP.NET Core Injected)
            var emailClaim = User.Claims.FirstOrDefault(c => c.Type == ClaimTypes.Email);
            if (emailClaim is null)
                return new StatusCodeResult(500);
            //
            var email = emailClaim.Value;
            var user = Context.Users.Where(a => a.Email == email).FirstOrDefault();
            if (user is null)
                return BadRequest();
            user.Name = userNames.Name;
            user.LastName = userNames.LastName;
            Context.Users.Update(user);
            await Context.SaveChangesAsync();
            return Ok(userNames);
        }

        [HttpPost("changepassword")]
        public async Task<IActionResult> ChangePassword([FromBody] UserPasswords userPasswords)
        {
            if (!ModelState.IsValid)
                return UnprocessableEntity(ModelState);
            // Getting user from User (ASP.NET Core Injected)
            var emailClaim = User.Claims.FirstOrDefault(c => c.Type == ClaimTypes.Email);
            if (emailClaim is null)
                return new StatusCodeResult(500);
            //
            var email = emailClaim.Value;
            var user = Context.Users.Where(a => a.Email == email).FirstOrDefault();
            if (user is null)
                return BadRequest();
            userPasswords.Current = Models.User.Hash(userPasswords.Current);
            if (userPasswords.Current != user.Password)
                return BadRequest(new { Reason = "Current password doesn't match with password stored" });
            user.Password = userPasswords.Password;
            Context.Users.Update(user);
            await Context.SaveChangesAsync();
            return Ok();
        }
    }
}