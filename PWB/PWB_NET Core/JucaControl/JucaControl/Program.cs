using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using JucaControl.Data;
using JucaControl.Utils;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;

namespace JucaControl
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var host = BuildWebHost(args);
            // Trying to verify database creation on application startup
            using (var scope = host.Services.CreateScope())
            {
                var sqlServerContext = scope.ServiceProvider.GetRequiredService<SqlServerContext>();
                DbInitializer.Initialize(sqlServerContext);
            }
            //
            host.Run();
        }

        public static IWebHost BuildWebHost(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
                .UseStartup<Startup>()
                .Build();
    }
}
