using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using JucaControl.Exceptions;
using JucaControl.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace JucaControl.Controllers
{
    [Route("api/[controller]")]
    public class OccurrencyCategoryController : Controller
    {
        public OccurrencyCategoryController(OccurrencyCategoryService service)
        {
            Service = service;
        }

        public OccurrencyCategoryService Service { get; }

        // GET: api/<controller>
        [HttpGet]
        public IActionResult Get()
        {
            return Ok(Service.Get());
        }
    }
}
