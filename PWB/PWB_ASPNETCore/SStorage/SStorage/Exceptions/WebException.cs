using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Exceptions
{
    public abstract class WebException : Exception
    {
        public abstract void Handle(HttpContext context);
    }
}
