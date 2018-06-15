using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using SStorage.Exceptions;

namespace SStorage.Middlewares
{
    // You may need to install the Microsoft.AspNetCore.Http.Abstractions package into your project
    public class WebExceptionMiddleware
    {
        private readonly RequestDelegate _next;

        public WebExceptionMiddleware(RequestDelegate next)
        {
            _next = next;
        }

        public async Task Invoke(HttpContext httpContext)
        {
            try
            {
                await _next(httpContext);
            }
            catch (Exception e)
            {
                if (e is WebException we)
                {
                    we.Handle(httpContext);
                }
                else
                    throw e;
            }
        }
    }

    // Extension method used to add the middleware to the HTTP request pipeline.
    public static class WebExceptionMiddlewareExtensions
    {
        public static IApplicationBuilder UseWebExceptionMiddleware(this IApplicationBuilder builder)
        {
            return builder.UseMiddleware<WebExceptionMiddleware>();
        }
    }
}
