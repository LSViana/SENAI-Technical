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
            // Configuring Cycle ON DELETE at Database to AVoid sQL Server Errors
            modelBuilder.Entity<PatrimonyItem>()
                .HasOne(item => item.User)
                .WithMany(user => user.PatrimonyItems)
                .HasForeignKey(item => item.UserId)
                .OnDelete(DeleteBehavior.Restrict);

            modelBuilder.Entity<Movement>()
                .HasOne(mov => mov.Origin)
                .WithMany(env => env.OriginMovements)
                .HasForeignKey(mov => mov.OriginId);

            modelBuilder.Entity<Movement>()
                .HasOne(mov => mov.Destiny)
                .WithMany(env => env.DestinyMovements)
                .HasForeignKey(mov => mov.DestinyId);
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Models.Environment> Environments { get; set; }
        public DbSet<PatrimonyItem> PatrimonyItems { get; set; }
        public DbSet<PatrimonyCategory> PatrimonyCategories { get; set; }
        public DbSet<Patrimony> Patrimonies { get; set; }
        public DbSet<Movement> Movements { get; set; }
    }
}
