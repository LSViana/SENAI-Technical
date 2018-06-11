using JetBrains.Annotations;
using Microsoft.EntityFrameworkCore;
using SStorage.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Data
{
    public class SStorageDbContext : DbContext
    {
        public SStorageDbContext(DbContextOptions<SStorageDbContext> options) : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Configuring Relationships
            modelBuilder.Entity<Movement>()
                .HasOne(mov => mov.OriginEnvironment)
                .WithMany(env => env.OriginMovements);
            modelBuilder.Entity<Movement>()
                .HasOne(mov => mov.DestinyEnvironment)
                .WithMany(env => env.DestinyMovements);

            var fks = modelBuilder.Model
                .GetEntityTypes()
                .SelectMany(a => a.GetForeignKeys());

            // Set all deletes to ON DELETE *NO ACTION*, it is, automatically remove all ON DELETE *CASCADE*
            foreach (var fk in fks)
            {
                fk.DeleteBehavior = DeleteBehavior.Restrict;
            }
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Patrimony> Patrimonies { get; set; }
        public DbSet<Models.Environment> Environments { get; set; }
        public DbSet<PatrimonyCategory> PatrimonyCategories { get; set; }
        public DbSet<PatrimonyItem> PatrimonyItems { get; set; }
        public DbSet<Movement> Movements { get; set; }
    }
}
