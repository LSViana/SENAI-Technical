//#region Application
const APP_NAME = "SStorage";
const APP_ICON_SRC = "/assets/img/logo.svg";
const APP_FAVICON_SRC = "/assets/img/logo.png";
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
  "router-about": "/views/about/about.html",
  // CSS
  "router-css-main": "http://localhost:3000/assets/css/main.css",
  "router-css-universal": "http://localhost:3000/assets/css/universal.css",
  "router-css-constants": "http://localhost:3000/assets/css/constants.css",
  "router-css-fa": "http://localhost:3000/assets/external/font-awesome.min.css",
  "router-css-bs": "http://localhost:3000/assets/external/bootstrap.min.css",
  "router-css-mdb": "http://localhost:3000/assets/external/mdb.min.css",
  // API
  "api-login": SERVER + API_PREFIX + USERS_PREFIX + "/authenticate"
};
//#endregion

//#region Other Assets
const ASSETS = {
 "mini-box": "/assets/img/mini-box.svg"
};
//#endregion

//#region CSS Classes
const FORM_ERROR = "form-error";
//#endregion