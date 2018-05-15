/**
 * Callback the User Register Operation
 * @param {Response} response 
 * @param {HTMLFormElement} form 
 */
function handleRegisterPatItem(response, form) {
    if (response.ok) {
        window.location.href = ROUTES["router-patitems"];
    } else {
        let customHeaderKeys = Array.from(response.headers.keys()).filter(function (key) {
            return key.toLowerCase().startsWith("x-")
        });
        for (let key of customHeaderKeys) {
            let message = response.headers.get(key);
            addFormError(form, key.substr(2), message);
        }
    }
}

/**
 * Requesting Removal of the Corresponding Patrimony Item
 * @param {Number} id 
 */
function requestRemoval(id) {
    let headers = {};
    headers[XTOKEN] = getToken();
    let route = ROUTES["api-patitem-request-removal"].replace("{id}", id);
    //
    fetch(route, {
        headers,
        method: "GET"
    }).then(function(response) {
        window.location.reload(true);
    });
}

/**
 * Remove from usage the Patrimony Item with the specified ID
 * @param {Number} id 
 */
function removeUsage(id) {
    let headers = {};
    headers[XTOKEN] = getToken();
    let route = ROUTES["api-patitem-remove"].replace("{id}", id);
    //
    fetch(route, {
        headers,
        method: "GET"
    }).then(function(response) {
        window.location.reload(true);
    });
}

/**
 * Actiave to usage the Patrimony Item with the specified ID
 * @param {Number} id 
 */
function activateItem(id) {
    let headers = {};
    headers[XTOKEN] = getToken();
    let route = ROUTES["api-patitem-activate"].replace("{id}", id);
    //
    fetch(route, {
        headers,
        method: "GET"
    }).then(function(response) {
        window.location.reload(true);
    });
}