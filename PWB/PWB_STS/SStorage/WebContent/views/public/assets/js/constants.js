//#region Application
const APP_NAME = "SStorage";
const APP_ICON_SRC = "https://upload.wikimedia.org/wikipedia/en/1/10/Stroman_High_School%2C_Victoria%2C_Texas%2C_S_Logo.png";
//#endregion

//#region WebService
const SERVER = "http://localhost:8080/sstorage";
const API_PREFIX = "/api/v1";
const USERS_PREFIX = "/users";
const XREASON = "X-Reason";
const XTOKEN = "X-Auth-Token";
//#endregion

//#region Routes
const ROUTES = {
  // Pages
  "router-login": "/views/users/login.html",
  "router-main": "/views/main/main.html",
  "router-register": "/views/users/register.html",
  // API
  "api-login": SERVER + API_PREFIX + USERS_PREFIX + "/authenticate"
};
//#endregion