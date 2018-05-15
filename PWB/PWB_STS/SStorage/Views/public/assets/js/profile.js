/**
 * Callback the User Register Operation
 * @param {Response} response 
 * @param {HTMLFormElement} form 
 */
function handleChangeName(response, form, data) {
    if (response.ok) {
        localStorage.setItem(XUSERNAME, data.firstName + " " + data.lastName);
        //
        window.location.reload();
    } else {
        let customHeaderKeys = Array.from(response.headers.keys()).filter(function (key) {
            return key.toLowerCase().startsWith("x-")
        });
        for (let key of customHeaderKeys) {
            let message = response.headers.get(key);
            addFormMessage(form, key.substr(2), message, FORM_SUCCESS, FORM_SUCCESS_HTML);
        }
    }
}

function handleChangePassword(response, form, data) {
    if (response.ok) {
        addFormMessage(form, "password", "Password successfully changed", FORM_SUCCESS, FORM_SUCCESS_HTML);
    } else {
        let customHeaderKeys = Array.from(response.headers.keys()).filter(function (key) {
            return key.toLowerCase().startsWith("x-")
        });
        for (let key of customHeaderKeys) {
            let message = response.headers.get(key);
            addFormMessage(form, key.substr(2), message, FORM_ERROR, FORM_ERROR_HTML);
        }
    }
}