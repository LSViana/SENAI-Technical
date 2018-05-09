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
const ENV_PREFIX = "/environments";
const MOV_PREFIX = "/movements";
const PAT_PREFIX = "/patrimonies";
const PATCAT_PREFIX = "/patrimonycategories";
const PATITEM_PREFIX = "/patrimonyitems";
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
  "router-users": "/views/users/users.html",
  "router-envs": "/views/envs/envs.html",
  "router-movs": "/views/movs/movs.html",
  "router-pats": "/views/pats/pats.html",
  "router-patcats": "/views/patcats/patcats.html",
  "router-patitems": "/views/patitems/patitems.html",
  // CSS
  "router-css-main": "http://localhost:3000/assets/css/main.css",
  "router-css-login": "http://localhost:3000/assets/css/login.css",
  "router-css-universal": "http://localhost:3000/assets/css/universal.css",
  "router-css-constants": "http://localhost:3000/assets/css/constants.css",
  "router-css-fa": "http://localhost:3000/assets/external/font-awesome.min.css",
  "router-css-bs": "http://localhost:3000/assets/external/bootstrap.min.css",
  "router-css-mdb": "http://localhost:3000/assets/external/mdb.min.css",
  // JS
  "router-js-form": "http://localhost:3000/assets/js/form.js",
  "router-js-login": "http://localhost:3000/assets/js/login.js",
  "router-js-users": "http://localhost:3000/assets/js/users.js",
  // API
  "api-login": SERVER + API_PREFIX + USERS_PREFIX + "/authenticate",
  "api-logout": SERVER + API_PREFIX + USERS_PREFIX + "/logout",
  "api-list-user": SERVER + API_PREFIX + USERS_PREFIX,
  "api-list-env": SERVER + API_PREFIX + ENV_PREFIX,
  "api-list-mov": SERVER + API_PREFIX + MOV_PREFIX,
  "api-list-pat": SERVER + API_PREFIX + PAT_PREFIX,
  "api-list-patcat": SERVER + API_PREFIX + PATCAT_PREFIX,
  "api-list-patitem": SERVER + API_PREFIX + PATITEM_PREFIX,
};
//#endregion

//#region Texts
const TEXTS = {
  "explore": "Explore",
  "manage": "Manage",
  "login": "Login",
  "logout": "Logout"
}
//#endregion

//#region CSS Classes
const FORM_ERROR = "form-error";
//#endregion