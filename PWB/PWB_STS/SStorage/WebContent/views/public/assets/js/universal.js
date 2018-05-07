//#region Initial Handlers
window.addEventListener("load", onLoadUniversal);

/**
 * @type {HTMLElement}
 */
var head;

/**
 * Handles Window Load and set shared stuff
 * @param {Event} ev
 */
function onLoadUniversal(ev) {
    // Initializing
    head = document.querySelector("head");
    // Title
    const title = document.querySelector("head title");
    title.innerHTML = title.innerHTML.replace("{0}", APP_NAME);
    // Fav Icon
    const link = document.createElement("link");
    link.setAttribute("rel", "shortcut icon");
    link.setAttribute("href", APP_ICON_SRC);
    link.setAttribute("type", "image/x-icon");
    head.appendChild(link);
    // Logos
    const logos = Array.from(document.querySelectorAll(".e-logo"));
    for(let logo of logos) {
        logo.setAttribute("src", APP_ICON_SRC);
        logo.setAttribute("alt", APP_NAME + " Logo");
    }
    // Names
    const names = Array.from(document.querySelectorAll(".e-name"));
    for(let name of names) {
        name.innerHTML = name.innerHTML.replace("{0}", APP_NAME);
    }
    // Routers
    const routers = Array.from(document.querySelectorAll(".router"));
    for(let router of routers) {
        let classList = router.classList;
        for(let className of classList)
            if(ROUTES[className]) {
                router.setAttribute("href", ROUTES[className]);
                break;
            }
    }
    // Forms
    const forms = Array.from(document.querySelectorAll("form"));
    for(let form of forms) {
        form.setAttribute("onsubmit", "return false");
        if(form.getAttribute("data-automate") != null)
            form.addEventListener("submit", onFormSend);
    }
    // Showing Body
    document.querySelector("body").style.display = "unset";
}
//#endregion

//#region Custom Handlers
/**
 * 
 * @param {Event} e 
 */
function onFormSend(e) {
    let formData = new FormData(e.srcElement);
    let keys = Array.from(formData.keys());
    let payload = {};
    for(let key of keys) {
        payload[key] = formData.get(key);
    }
    let method = e.srcElement.getAttribute("data-method");
    let router = ROUTES[e.srcElement.getAttribute("data-router")];
    let callback = window[e.srcElement.getAttribute("data-callback")];
    if(!callback)
        throw Error("Invalid method " + e.srcElement.getAttribute("data-callback") + " as callback");
    // Performing HTTP Request
    fetch(router, {
        headers: {
            "Content-Type": "application/json",
        },
        method: e.srcElement.getAttribute("data-method"),
        body: JSON.stringify(payload)
    })
        .then(function (fulfilled) {
            callback(fulfilled);
        }, function(rejected) {
            callback(rejected);
        })
        .catch(function (rejected) {
            callback(rejected);
        });
}
//#endregion