//#region Application
const APP_NAME = "SStorage";
const APP_ICON_SRC = "/assets/img/darkblue_logo.png";
const APP_SECOND_ICON_SRC = "/assets/img/sweetred_logo.png";
//#endregion

//#region WebService
const SERVER = "http://localhost:8080/sstorage";
const API_PREFIX = "/api/v1";
const USERS_PREFIX = "/users";
const XREASON = "X-Reason";
const XTOKEN = "X-Auth-Token";
const XUSERNAME = "X-Username";
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

//#region CSS Classes
const FORM_ERROR = "form-error";
//#endregion