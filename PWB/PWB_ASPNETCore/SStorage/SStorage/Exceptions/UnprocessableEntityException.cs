using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.ModelBinding;

namespace SStorage.Exceptions
{
    public class UnprocessableEntityException : WebException
    {
        public UnprocessableEntityException(ModelStateDictionary modelState)
        {
            ModelState = modelState;
        }

        public ModelStateDictionary ModelState { get; }

        public override void Handle(HttpContext context)
        {
            context.Response.StatusCode = StatusCodes.Status422UnprocessableEntity;
        }
    }
}
