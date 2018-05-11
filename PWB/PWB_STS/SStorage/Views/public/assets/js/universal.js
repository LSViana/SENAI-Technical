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
    // Scripts
    // importScripts();
    // Verifying Access Control
    verifyAccessControl();
    // Title
    updateTitle();
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
    // Updating DOM according to Login State
    updateDOMWithAuthLevel();
    // Perform API Calls
    performAPICalls();
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

function performAPICalls() {
    performTableLists();
}

function performTableLists() {
    let tables = Array.from(document.querySelectorAll("table"));
    for (let table of tables) {
        let tableId = table.id;
        if (!tableId) {
            console.log(table);
            console.error(`Table must have an ID`);
            continue;
        }
        let tbody = document.querySelector(`#${tableId} tbody`);
        let route = ROUTES[table.getAttribute("data-source")];
        if (!route) {
            console.error(`Invalid 'data-source' specified: ${table.getAttribute("data-source")}`);
            continue;
        }
        let tableHeaders = Array.from(document.querySelectorAll(`#${tableId} > thead > tr > th`));
        let mappings = [];
        let actions = {};
        for (let header of tableHeaders) {
            if (header.getAttribute("data-mapping")) {
                // Starts from 1
                let index = mappings.push([]) - 1;
                let mappingParts = header.getAttribute("data-mapping").split(" ");
                for (let mappingPart of mappingParts) {
                    mappings[index].push(mappingPart);
                }
            } else if (header.getAttribute("data-table-action")) {
                actions[header.getAttribute("data-table-action")] = header.getAttribute("data-icon");
            }
        }
        // Getting Path to Edit Page, According to the Current Entity
        let location = window.location;
        let editAddress;
        if (location.port)
            editAddress = [location.protocol + "//" + location.hostname + ":" + location.port + location.pathname.substr(0, location.pathname.lastIndexOf("/"))].join("") + EDITPAGE + `?id={0}`;
        else
            editAddress = [location.protocol + "//" + location.hostname + location.pathname.substr(0, location.pathname.lastIndexOf("/"))].join("") + EDITPAGE + `?id={0}`;
        // Getting data from API
        let headers = {};
        headers[XTOKEN] = getToken();
        fetch(route, {
            method: "GET",
            headers
        }).then(function (response) {
            response.json()
                .then(function (data) {
                    for (let item of data) {
                        let tr = document.createElement("tr");
                        tr.setAttribute("data-entity-id", item.id);
                        for (let mapping of mappings) {
                            let td = document.createElement("td");
                            for (let property of mapping) {
                                td.innerText += item[property] + " ";
                            }
                            tr.appendChild(td);
                        }
                        for (let action in actions) {
                            // Building elements
                            let td = document.createElement("td");
                            td.setAttribute("data-td-action", action);
                            let aEl = document.createElement("a");
                            aEl.classList.add("d-flex");
                            aEl.classList.add("justify-content-center");
                            let fa_Icon = document.createElement("i");
                            fa_Icon.classList.add("fa");
                            fa_Icon.classList.add(actions[action]);
                            aEl.appendChild(fa_Icon);
                            td.appendChild(aEl);
                            tr.appendChild(td);
                            // Adding custom routes to @EDIT and @DELETE operations
                            if (action == "edit") {
                                aEl.setAttribute("href", editAddress.replace("{0}", item.id));
                            } else if (action == "delete") {
                                aEl.addEventListener("click", function (ev) {
                                    deleteEntity(route, item.id, function (response) {
                                        if (response.status == 200) tr.remove();
                                    });
                                });
                            }
                        }
                        tbody.appendChild(tr);
                    }
                }, function (error) {
                    console.error(error);
                })
        }, function (rejected) {
            console.error(rejected);
        });
    }
}

/**
 * Returns an Object from the address supplied
 * @param {String} address 
 * @param {Number} id
 * @param {(value:Object)} callback
 */
function getEntity(address, id, callback) {
    let headers = {};
    headers[XTOKEN] = getToken();
    fetch(address + "/" + id, {
        headers,
        method: "GET"
    }).then(function (response) {
        response.json()
            .then(function (value) {
                callback(value);
            }, function (rejected) {
                callback(value);
            });
    }, function (rejected) {
        callback(value);
    });
}

function verifyAccessControl() {
    let body = document.querySelector("body");
    let loggedIn = isLoggedIn();
    if (loggedIn) {
        let authPage = body.getAttribute("min-auth");
        if (authPage != null) {
            let authLevel = getAuthLevel();
            if (authLevel < authPage) {
                let route = body.getAttribute("min-auth-router");
                if (!route)
                    throw Error("When using 'min-auth' you must define an attribute 'min-auth-router' with the destiny if not allowed");
                window.location.href = ROUTES[route];
            }
        } else if (body.getAttribute("only-out"))
            window.location.href = ROUTES[body.getAttribute("only-out")];
    } else {
        if (body.getAttribute("only-in"))
            window.location.href = ROUTES[body.getAttribute("only-in")];
    }
}

/**
 * Performs a HTTP DELETE request to the specified route adding "/{id}" to its end
 * @param {String} route 
 * @param {Number} id 
 * @param {Function} callback
 */
function deleteEntity(route, id, callback) {
    let headers = {};
    headers[XTOKEN] = getToken();
    fetch(route + "/" + id, {
        headers,
        method: "DELETE"
    }).then((response) => {
        callback(response);
    }, (rejected) => {
        callback(rejected);
    });
}

function updateDOMWithAuthLevel() {
    let loggedIn = isLoggedIn();
    // Vanishing components that shouldn't be shown on LoggedIn
    if (loggedIn) {
        // Removing elements that shouldn't be shown at any auth level
        let vanishLogin = Array.from(document.querySelectorAll(".vanish-login"));
        for (let vanish of vanishLogin) {
            vanish.style.display = "none";
        }
        // Removing elements that shouldn't be shown according to auth level
        let authLevel = getAuthLevel();
        let unauthorizedElements = Array.from(document.querySelectorAll(`*[min-auth-show]`)).filter(function (el) {
            return Number(el.getAttribute("min-auth-show")) > authLevel
        });
        for (let el of unauthorizedElements) {
            el.style.display = "none";
        }
    } else {
        // Showing components that shouldn't be shown on LoggedIn
        let showLogin = Array.from(document.querySelectorAll(".show-login"));
        for (let show of showLogin) {
            show.style.display = "unset";
        }
    }
}

// function importScripts() {
//     // body = document.querySelector("body");
//     const addresses = [
//         "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js",
//         "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js",
//         "https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.0/js/mdb.min.js",
//         "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js",
//     ];
//     //
//     let index = 0;
//     for (let address of addresses) {
//         let script = document.createElement("script");
//         script.setAttribute("src", addresses[index]);
//         script.setAttribute("type", "text/javascript");
//         head.appendChild(script)
//         index++;
//     }
// }

function updateRouters() {
    routers = Array.from(document.querySelectorAll(".e-router"));
    let loggedIn = isLoggedIn();
    for (let router of routers) {
        if (loggedIn && router.getAttribute("data-action-in")) {
            let methodName = router.getAttribute("data-action-in");
            let method = window[methodName];
            if (!method)
                throw Error(`Method not found ${methodName}`);
            router.addEventListener("click", method);
        } else if (loggedIn && router.getAttribute("data-router-in")) {
            let routerName = router.getAttribute("data-router-in");
            if (router.tagName.toLocaleLowerCase() == "script")
                router.setAttribute("src", ROUTES[routerName]);
            else
                router.setAttribute("href", ROUTES[routerName]);
        } else {
            let classList = router.classList;
            for (let className of classList) {
                if (ROUTES[className]) {
                    if (router.tagName.toLocaleLowerCase() == "script")
                        router.setAttribute("src", ROUTES[className]);
                    else
                        router.setAttribute("href", ROUTES[className]);
                    break;
                }
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
    let loggedIn = isLoggedIn();
    for (let text of texts) {
        let index = 0;
        let textList = loggedIn ? text.getAttribute("data-text-in").split(" ") : text.getAttribute("data-text-out").split(" ");
        for (let textPart of textList) {
            if (TEXTS[textPart]) {
                text.innerHTML = text.innerHTML.split(`{${index}}`).join(TEXTS[textPart]);
                index++;
            }
        }
    }
}

//#endregion

//#region Custom Methods
/**
 * 
 * @param {Event} e 
 */
function onFormSend(e) {
    let form = e.srcElement;
    // Removing form errors before sending
    removeFormErrors(form);
    //
    let formData = new FormData(form);
    let keys = Array.from(formData.keys());
    let payload = {};
    for (let key of keys) {
        let input = form[key];
        if(input.constructor.name == "RadioNodeList") {
            payload[key] = input.value;
        } else if(input) {
            let idParts = input.getAttribute("id").split(".");
            if (idParts.length < 2) {
                payload[key] = formData.get(key);
            } else {
                if(input.entity && input.entity.id) {                
                    payload[key] = input.entity;
                } else {
                    addFormError(form, input.getAttribute("name"), "You must fill this field!");
                    return;
                }
            }
        }
    }
    //
    let router = ROUTES[form.getAttribute("data-router")];
    let callback = window[form.getAttribute("data-callback")];
    if (!callback)
        throw Error("Invalid method " + form.getAttribute("data-callback") + " as callback");
    // Performing HTTP Request
    let headers = {
        "Content-Type": "application/json",
    };
    let hasId = Boolean(form.id.value);
    if (isLoggedIn())
        headers[XTOKEN] = getToken();
    let method = hasId ? form.getAttribute("data-update-method") : form.getAttribute("data-method");
    if (hasId && form.id.value.toString().trim())
        router += `/${form.id.value.toString().trim()}`;
    fetch(router, {
            headers,
            // Verifying if it is an INSERT or UPDATE
            method,
            body: JSON.stringify(payload)
        })
        .then(function (response) {
            callback(response, form);
        }, function (rejected) {
            callback(rejected, form);
        });
}

/**
 * @returns {Boolean}
 */
function isLoggedIn() {
    return Boolean(getToken());
}

/**
 * @returns {String}
 */
function getToken() {
    return localStorage.getItem(XTOKEN);
}

/**
 * @returns {Number}
 */
function getAuthLevel() {
    return Number(localStorage.getItem(XAUTHLEVEL));
}

/**
 * @returns {Number}
 */
function getId() {
    return Number(localStorage.getItem(XID));
}

function performLogout() {
    let router = ROUTES["api-logout"];
    let tokenHeader = XTOKEN;
    let headers = {};
    headers[XTOKEN] = getToken();
    fetch(router, {
        method: "GET",
        headers
    }).then(function (res) {
        clearLocalStorage();
        window.location.href = ROUTES["router-main"];
    }, function (rejected) {
        alert("Something went out wrong with your logout operation. We've disconnected you, try again later.");
        clearLocalStorage();
        window.location.href = ROUTES["router-main"];
    });
}

/**
 * Clear all user sensitive data from Local Storage
 */
function clearLocalStorage() {
    localStorage.removeItem(XTOKEN);
    localStorage.removeItem(XUSERNAME);
    localStorage.removeItem(XAUTHLEVEL);
    localStorage.removeItem(XID);
}
//#endregion