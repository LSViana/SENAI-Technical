using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using SStorage.Data;
using SStorage.Models;
using SStorage.Models.ViewModels;
using SStorage.Utils;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace SStorage.Controllers.V1
{
    [Route("rest/v1/auth")]
    public class TokenController : Controller
    {
        public TokenController(IConfiguration configuration, SStorageDbContext context)
        {
            Configuration = configuration;
            Context = context;
        }

        public IConfiguration Configuration { get; }
        public SStorageDbContext Context { get; }

        // GET: api/<controller>
        [HttpPost("jwt")]
        public IActionResult GetToken([FromBody] Login login)
        {
            if (login is null)
                return BadRequest();
            if (login.Email is null || login.Password is null)
                return UnprocessableEntity(ModelState);

            login.Password = Models.User.Hash(login.Password);

            var user = Context.Users.Where(x => x.Email == login.Email && x.PasswordDatabase == login.Password).FirstOrDefault();

            if (user is null)
                return NotFound();

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Configuration["TokenOptions:Key"]));

            var claims = new Claim[]
            {
                new Claim(ClaimTypes.Email, user.Email, ClaimValueTypes.String),
                new Claim(ClaimTypes.Authentication, user.PasswordDatabase, ClaimValueTypes.String),
                new Claim(CustomClaimTypes.Subject, user.Email, ClaimValueTypes.String),
                new Claim(CustomClaimTypes.Hash, user.PasswordDatabase, ClaimValueTypes.String),
                new Claim(CustomClaimTypes.Extra, user.UserType.ToString(), ClaimValueTypes.String)
            };

            var jwt = new JwtSecurityToken(
                issuer: Configuration["TokenOptions:Issuer"],
                audience: Configuration["TokenOptions:Audience"],
                claims: claims,
                expires: DateTime.Now.Add(TimeSpan.FromDays(1)),
                signingCredentials: new SigningCredentials(key, SecurityAlgorithms.HmacSha512)
                );

            return Ok(new
            {
                Username = $"{user.Name} {user.LastName}",
                Token = new JwtSecurityTokenHandler().WriteToken(jwt)
            });
        }
    }
}
