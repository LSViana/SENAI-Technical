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
                name: "Environments",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 40, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Environments", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "PatrimonyCategories",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 30, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_PatrimonyCategories", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 20, nullable: false),
                    LastName = table.Column<string>(maxLength: 40, nullable: false),
                    Email = table.Column<string>(nullable: false),
                    PasswordDatabase = table.Column<string>(maxLength: 256, nullable: false),
                    UserType = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Patrimonies",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(maxLength: 40, nullable: false),
                    PatrimonyCategoryId = table.Column<long>(nullable: false),
                    UserId = table.Column<long>(nullable: false),
                    DateTime = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Patrimonies", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Patrimonies_PatrimonyCategories_PatrimonyCategoryId",
                        column: x => x.PatrimonyCategoryId,
                        principalTable: "PatrimonyCategories",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Patrimonies_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "PatrimonyItems",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false),
                    PatrimonyId = table.Column<long>(nullable: false),
                    EnvironmentId = table.Column<long>(nullable: false),
                    UserId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_PatrimonyItems", x => x.Id);
                    table.ForeignKey(
                        name: "FK_PatrimonyItems_Environments_EnvironmentId",
                        column: x => x.EnvironmentId,
                        principalTable: "Environments",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_PatrimonyItems_Patrimonies_PatrimonyId",
                        column: x => x.PatrimonyId,
                        principalTable: "Patrimonies",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_PatrimonyItems_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Movements",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    DateTime = table.Column<DateTime>(nullable: false),
                    PatrimonyItemId = table.Column<long>(nullable: false),
                    OriginEnvironmentId = table.Column<long>(nullable: false),
                    DestinyEnvironmentId = table.Column<long>(nullable: false),
                    UserId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Movements", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Movements_Environments_DestinyEnvironmentId",
                        column: x => x.DestinyEnvironmentId,
                        principalTable: "Environments",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Movements_Environments_OriginEnvironmentId",
                        column: x => x.OriginEnvironmentId,
                        principalTable: "Environments",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Movements_PatrimonyItems_PatrimonyItemId",
                        column: x => x.PatrimonyItemId,
                        principalTable: "PatrimonyItems",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Movements_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Movements_DestinyEnvironmentId",
                table: "Movements",
                column: "DestinyEnvironmentId");

            migrationBuilder.CreateIndex(
                name: "IX_Movements_OriginEnvironmentId",
                table: "Movements",
                column: "OriginEnvironmentId");

            migrationBuilder.CreateIndex(
                name: "IX_Movements_PatrimonyItemId",
                table: "Movements",
                column: "PatrimonyItemId");

            migrationBuilder.CreateIndex(
                name: "IX_Movements_UserId",
                table: "Movements",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_Patrimonies_PatrimonyCategoryId",
                table: "Patrimonies",
                column: "PatrimonyCategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Patrimonies_UserId",
                table: "Patrimonies",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_PatrimonyItems_EnvironmentId",
                table: "PatrimonyItems",
                column: "EnvironmentId");

            migrationBuilder.CreateIndex(
                name: "IX_PatrimonyItems_PatrimonyId",
                table: "PatrimonyItems",
                column: "PatrimonyId");

            migrationBuilder.CreateIndex(
                name: "IX_PatrimonyItems_UserId",
                table: "PatrimonyItems",
                column: "UserId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Movements");

            migrationBuilder.DropTable(
                name: "PatrimonyItems");

            migrationBuilder.DropTable(
                name: "Environments");

            migrationBuilder.DropTable(
                name: "Patrimonies");

            migrationBuilder.DropTable(
                name: "PatrimonyCategories");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
