using JucaControl.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace JucaControl.Utils
{
    public static class DbInitializer
    {
        public static void Initialize(SqlServerContext sqlServerContext)
        {
            sqlServerContext.Database.EnsureCreated();
        }
    }
}
