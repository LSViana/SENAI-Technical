﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using SStorage.Data;

namespace SStorage.Migrations
{
    [DbContext(typeof(SStorageDbContext))]
    partial class SStorageDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "2.1.0-rtm-30799")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("SStorage.Models.Environment", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(40);

                    b.HasKey("Id");

                    b.ToTable("Environment");
                });

            modelBuilder.Entity("SStorage.Models.Movement", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<DateTime>("DateTime");

                    b.Property<long>("DestinyId");

                    b.Property<long>("OriginId");

                    b.Property<long>("PatrimonyItemId");

                    b.Property<long>("UserId");

                    b.HasKey("Id");

                    b.HasIndex("DestinyId");

                    b.HasIndex("OriginId");

                    b.HasIndex("PatrimonyItemId");

                    b.HasIndex("UserId");

                    b.ToTable("Movement");
                });

            modelBuilder.Entity("SStorage.Models.Patrimony", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<DateTime>("DateTime");

                    b.Property<string>("Name")
                        .HasMaxLength(40);

                    b.Property<long>("PatrimonyCategoryId");

                    b.Property<long>("UserId");

                    b.HasKey("Id");

                    b.HasIndex("PatrimonyCategoryId");

                    b.HasIndex("UserId");

                    b.ToTable("Patrimony");
                });

            modelBuilder.Entity("SStorage.Models.PatrimonyCategory", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(30);

                    b.HasKey("Id");

                    b.ToTable("PatrimonyCategory");
                });

            modelBuilder.Entity("SStorage.Models.PatrimonyItem", b =>
                {
                    b.Property<long>("Id");

                    b.Property<long>("EnvironmentId");

                    b.Property<long>("PatrimonyId");

                    b.Property<long>("UserId");

                    b.HasKey("Id");

                    b.HasIndex("EnvironmentId");

                    b.HasIndex("PatrimonyId");

                    b.HasIndex("UserId");

                    b.ToTable("PatrimonyItem");
                });

            modelBuilder.Entity("SStorage.Models.User", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Email")
                        .IsRequired();

                    b.Property<string>("LastName")
                        .HasMaxLength(40);

                    b.Property<string>("Name")
                        .HasMaxLength(20);

                    b.Property<string>("Password")
                        .HasMaxLength(20);

                    b.Property<int>("UserType");

                    b.HasKey("Id");

                    b.ToTable("User");
                });

            modelBuilder.Entity("SStorage.Models.Movement", b =>
                {
                    b.HasOne("SStorage.Models.Environment", "Destiny")
                        .WithMany("DestinyMovements")
                        .HasForeignKey("DestinyId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("SStorage.Models.Environment", "Origin")
                        .WithMany("OriginMovements")
                        .HasForeignKey("OriginId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("SStorage.Models.PatrimonyItem", "PatrimonyItem")
                        .WithMany("Movements")
                        .HasForeignKey("PatrimonyItemId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("SStorage.Models.User", "User")
                        .WithMany()
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("SStorage.Models.Patrimony", b =>
                {
                    b.HasOne("SStorage.Models.PatrimonyCategory", "PatrimonyCategory")
                        .WithMany("Patrimonies")
                        .HasForeignKey("PatrimonyCategoryId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("SStorage.Models.User", "User")
                        .WithMany("Patrimonies")
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("SStorage.Models.PatrimonyItem", b =>
                {
                    b.HasOne("SStorage.Models.Environment", "Environment")
                        .WithMany("PatrimonyItems")
                        .HasForeignKey("EnvironmentId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("SStorage.Models.Patrimony", "Patrimony")
                        .WithMany("PatrimonyItems")
                        .HasForeignKey("PatrimonyId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("SStorage.Models.User", "User")
                        .WithMany("PatrimonyItems")
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Restrict);
                });
#pragma warning restore 612, 618
        }
    }
}
