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
const XID = "X-Id";
const XAUTHLEVEL = "X-Auth-Level";
//#endregion

//#region Common Pages Addresses
const EDITPAGE = "/form.html";
//#endregion


//#region Routes
const ROUTES = {
  // Pages
  "router-login": "/views/users/login.html",
  "router-main": "/views/main/main.html",
  "router-register": "/views/users/register.html",
  "router-about": "/views/about/about.html",
  "router-users": "/views/users/users.html",
  "router-user-form": "/views/users/form.html",
  "router-envs": "/views/envs/envs.html",
  "router-env-form": "/views/envs/form.html",
  "router-movs": "/views/movs/movs.html",
  "router-mov-form": "/views/movs/form.html",
  "router-pats": "/views/pats/pats.html",
  "router-pat-form": "/views/pats/form.html",
  "router-patcats": "/views/patcats/patcats.html",
  "router-patcat-form": "/views/patcats/form.html",
  "router-patitems": "/views/patitems/patitems.html",
  "router-patitem-form": "/views/patitems/form.html",
  // CSS
  "router-css-main": "/assets/css/main.css",
  "router-css-login": "/assets/css/login.css",
  "router-css-users": "/assets/css/users.css",
  "router-css-universal": "/assets/css/universal.css",
  "router-css-constants": "/assets/css/constants.css",
  "router-css-fa": "https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css",
  "router-css-bs": "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css",
  "router-css-mdb": "https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.0/css/mdb.min.css",
  // JS
  "router-js-form": "/assets/js/form.js",
  "router-js-login": "/assets/js/login.js",
  "router-js-users": "/assets/js/users.js",
  "router-js-envs": "/assets/js/envs.js",
  "router-js-patcats": "/assets/js/patcats.js",
  "router-js-pats": "/assets/js/pats.js",
  // API
  "api-login": SERVER + API_PREFIX + USERS_PREFIX + "/authenticate",
  "api-logout": SERVER + API_PREFIX + USERS_PREFIX + "/logout",
  "api-user": SERVER + API_PREFIX + USERS_PREFIX,
  "api-env": SERVER + API_PREFIX + ENV_PREFIX,
  "api-mov": SERVER + API_PREFIX + MOV_PREFIX,
  "api-pat": SERVER + API_PREFIX + PAT_PREFIX,
  "api-patcat": SERVER + API_PREFIX + PATCAT_PREFIX,
  "api-patitem": SERVER + API_PREFIX + PATITEM_PREFIX,
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