using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.SharpLib.Exceptions;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JucaControl.Exceptions
{
    public class ValidationException : WebHandledException
    {
        public ValidationException(object Errors, Boolean ResponseAsHeaders = false)
        {
            this.Errors = Errors;
            this.ResponseAsHeaders = ResponseAsHeaders;
        }

        public object Errors { get; }
        public bool ResponseAsHeaders { get; }

        public override int GetStatusCode()
        {
            return StatusCodes.Status422UnprocessableEntity;
        }

        public override void HandleException(HttpContext httpContext)
        {
            if (httpContext.Response.HasStarted)
                throw new InvalidOperationException($"You must not throw a {nameof(ValidationException)} after start sending the Response back to the user");
            var props = Errors.GetType().GetProperties();
            //
            if (ResponseAsHeaders)
            {
                foreach (var prop in props)
                {
                    httpContext.Response.Headers.Add($"X-{prop.Name}", prop.GetValue(Errors).ToString());
                }
            }
            else
            {
                httpContext.Response.ContentType = "application/json";
                var responseBytes = Encoding.UTF8.GetBytes(JObject.FromObject(Errors).ToString());
                httpContext.Response.Body.WriteAsync(responseBytes, 0, responseBytes.Length);
            }
        }
    }
}
