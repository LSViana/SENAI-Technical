using System;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

namespace SStorage.Migrations
{
    public partial class Initial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Environment",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 40, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Environment", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "PatrimonyCategory",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 30, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_PatrimonyCategory", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "User",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 20, nullable: true),
                    LastName = table.Column<string>(maxLength: 40, nullable: true),
                    Email = table.Column<string>(nullable: false),
                    Password = table.Column<string>(maxLength: 20, nullable: true),
                    UserType = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_User", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Patrimony",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 40, nullable: true),
                    DateTime = table.Column<DateTime>(nullable: false),
                    PatrimonyCategoryId = table.Column<long>(nullable: false),
                    UserId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Patrimony", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Patrimony_PatrimonyCategory_PatrimonyCategoryId",
                        column: x => x.PatrimonyCategoryId,
                        principalTable: "PatrimonyCategory",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Patrimony_User_UserId",
                        column: x => x.UserId,
                        principalTable: "User",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "PatrimonyItem",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false),
                    PatrimonyId = table.Column<long>(nullable: false),
                    EnvironmentId = table.Column<long>(nullable: false),
                    UserId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_PatrimonyItem", x => x.Id);
                    table.ForeignKey(
                        name: "FK_PatrimonyItem_Environment_EnvironmentId",
                        column: x => x.EnvironmentId,
                        principalTable: "Environment",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_PatrimonyItem_Patrimony_PatrimonyId",
                        column: x => x.PatrimonyId,
                        principalTable: "Patrimony",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_PatrimonyItem_User_UserId",
                        column: x => x.UserId,
                        principalTable: "User",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Movement",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    OriginId = table.Column<long>(nullable: false),
                    DestinyId = table.Column<long>(nullable: false),
                    DateTime = table.Column<DateTime>(nullable: false),
                    UserId = table.Column<long>(nullable: false),
                    PatrimonyItemId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Movement", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Movement_Environment_DestinyId",
                        column: x => x.DestinyId,
                        principalTable: "Environment",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Movement_Environment_OriginId",
                        column: x => x.OriginId,
                        principalTable: "Environment",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Movement_PatrimonyItem_PatrimonyItemId",
                        column: x => x.PatrimonyItemId,
                        principalTable: "PatrimonyItem",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Movement_User_UserId",
                        column: x => x.UserId,
                        principalTable: "User",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Movement_DestinyId",
                table: "Movement",
                column: "DestinyId");

            migrationBuilder.CreateIndex(
                name: "IX_Movement_OriginId",
                table: "Movement",
                column: "OriginId");

            migrationBuilder.CreateIndex(
                name: "IX_Movement_PatrimonyItemId",
                table: "Movement",
                column: "PatrimonyItemId");

            migrationBuilder.CreateIndex(
                name: "IX_Movement_UserId",
                table: "Movement",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_Patrimony_PatrimonyCategoryId",
                table: "Patrimony",
                column: "PatrimonyCategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Patrimony_UserId",
                table: "Patrimony",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_PatrimonyItem_EnvironmentId",
                table: "PatrimonyItem",
                column: "EnvironmentId");

            migrationBuilder.CreateIndex(
                name: "IX_PatrimonyItem_PatrimonyId",
                table: "PatrimonyItem",
                column: "PatrimonyId");

            migrationBuilder.CreateIndex(
                name: "IX_PatrimonyItem_UserId",
                table: "PatrimonyItem",
                column: "UserId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Movement");

            migrationBuilder.DropTable(
                name: "PatrimonyItem");

            migrationBuilder.DropTable(
                name: "Environment");

            migrationBuilder.DropTable(
                name: "Patrimony");

            migrationBuilder.DropTable(
                name: "PatrimonyCategory");

            migrationBuilder.DropTable(
                name: "User");
        }
    }
}
