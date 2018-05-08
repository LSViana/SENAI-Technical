//#region Initial Handlers
window.addEventListener("load", onLoadUniversal);

/**
 * @type {HTMLElement}
 */
var head;

// Global Variables
var forms, names, body, title, link, logos, text, routers;

/**
 * Handles Window Load and set shared stuff
 * @param {Event} ev
 */
function onLoadUniversal(ev) {
    // Initializing
    head = document.querySelector("head");
    // Title
    updateTitle();
    // Scripts
    importScripts();
    // Fav Icon
    updateFavIcon();
    // Logos
    updateLogos();
    // Texts
    updateTexts();
    // Names
    updateNames();
    // Routers
    updateRouters();
    // Forms
    addFormEventHandler();
    // Showing Body
    showBody();
}
function showBody() {
    document.querySelector("body").style.display = "";
}

function addFormEventHandler() {
    forms = Array.from(document.querySelectorAll("form"));
    for (let form of forms) {
        form.setAttribute("onsubmit", "return false");
        if (form.getAttribute("data-automate") != null)
            form.addEventListener("submit", onFormSend);
    }
}

function importScripts() {
    body = document.querySelector("body");
    const addresses = [
        "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js",
        "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js",
        "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js",
        "https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.0/js/mdb.min.js",
    ];
    //
    let index = 0;
    for(let address of addresses) {
        let script = document.createElement("script");
        script.setAttribute("src", addresses[index]);
        script.setAttribute("type", "text/javascript");
        body.appendChild(script)
        index++;
    }
}

function updateRouters() {
    routers = Array.from(document.querySelectorAll(".e-router"));
    for(let router of routers) {
        let classList = router.classList;
        for(let className of classList) {
            if(ROUTES[className]) {
                if(router.tagName.toLocaleLowerCase() == "script")
                    router.setAttribute("src", ROUTES[className]);
                else
                    router.setAttribute("href", ROUTES[className]);
                break;
            }
        }
    }
}

function updateNames() {
    names = Array.from(document.querySelectorAll(".e-name"));
    for (let name of names) {
        name.innerHTML = name.innerHTML.replace("{0}", APP_NAME);
    }
}

function updateTitle() {
    title = document.querySelector("head title");
    title.innerHTML = title.innerHTML.replace("{0}", APP_NAME);
}

function updateFavIcon() {
    link = document.createElement("link");
    link.setAttribute("rel", "shortcut icon");
    link.setAttribute("href", APP_FAVICON_SRC);
    link.setAttribute("type", "image/x-icon");
    head.appendChild(link);
}

function updateLogos() {
    logos = Array.from(document.querySelectorAll(".e-logo"));
    for (let logo of logos) {
        logo.setAttribute("src", APP_ICON_SRC);
        logo.setAttribute("alt", APP_NAME + " Logo");
    }
}

function updateTexts() {
    texts = Array.from(document.querySelectorAll(".e-text"));
    for (let text of texts) {
        let index = 0;
        let classList = text.classList;
        for (let className of classList) {
            if (TEXTS[className]) {
                text.innerHTML = text.innerHTML.split(`{${index}}`).join(TEXTS[className]);
                index++;
            }
        }
    }
}

//#endregion

//#region Custom Handlers
/**
 * 
 * @param {Event} e 
 */
function onFormSend(e) {
    let form = e.srcElement;
    let formData = new FormData(form);
    let keys = Array.from(formData.keys());
    let payload = {};
    for(let key of keys) {
        payload[key] = formData.get(key);
    }
    let method = form.getAttribute("data-method");
    let router = ROUTES[form.getAttribute("data-router")];
    let callback = window[form.getAttribute("data-callback")];
    if(!callback)
        throw Error("Invalid method " + form.getAttribute("data-callback") + " as callback");
    // Performing HTTP Request
    fetch(router, {
        headers: {
            "Content-Type": "application/json",
        },
        method: form.getAttribute("data-method"),
        body: JSON.stringify(payload)
    })
        .then(function (fulfilled) {
            callback(fulfilled, form);
        }, function(rejected) {
            callback(rejected, form);
        });
}
//#endregion