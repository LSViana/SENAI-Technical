using SStorage.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Data
{
    public static class DbInitializer
    {
        public static void Initialize(SStorageDbContext context)
        {
            if (context.Users.Any())
                return; // Already seeded
            // Creating Administrator User
            var admin = new User()
            {
                Email = "admin@email.com",
                LastName = "System",
                Name = "Administrator",
                Password = "admin1",
                UserType = UserType.ADMINISTRATOR
            };
            context.Users.Add(admin);
            context.SaveChanges(); // Not async, because it will be run at the startup, and the server can't start while the seeding operation happens
        }
    }
}
