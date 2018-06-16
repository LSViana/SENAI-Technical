using System;
using System.Collections.Generic;
using JucaControl.Data;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace JucaControl
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            var mvcBuilder = services.AddMvc();
            // Configuring database access
            services.AddDbContext<SqlServerContext>(opt =>
            {
                opt.UseSqlServer(Configuration.GetSection("ConnectionStrings").GetSection("DefaultConnection").Get<string>());
            });
            // Adding Services with classes suffixed by "Service"
            services.AddServices("Service");
            // Configure JSON parser
            mvcBuilder.AddJsonOptions(opt =>
            {
                opt.SerializerSettings.NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore;
                opt.SerializerSettings.ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore;
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseSharpExceptionHandler();

            app.UseMvc();
        }
    }
}
