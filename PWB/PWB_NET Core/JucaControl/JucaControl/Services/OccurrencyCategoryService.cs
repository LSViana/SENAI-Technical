using JucaControl.Data;
using JucaControl.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace JucaControl.Services
{
    public class OccurrencyCategoryService
    {
        public OccurrencyCategoryService(SqlServerContext context)
        {
            Context = context;
        }

        public SqlServerContext Context { get; }

        public IEnumerable<OccurrencyCategory> Get()
        {
            return Context.OccurrencyCategories.ToArray();
        }
    }
}
