using JucaControl.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace JucaControl.Data
{
    public class SqlServerContext : DbContext
    {
        public SqlServerContext(DbContextOptions<SqlServerContext> options) : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<OccurrencyCategory>().ToTable(nameof(OccurrencyCategory));
        }

        public DbSet<OccurrencyCategory> OccurrencyCategories { get; set; }
    }
}