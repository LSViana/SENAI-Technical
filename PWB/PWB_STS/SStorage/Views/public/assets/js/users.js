/**
 * Handle the login request to the API
 * @param {Response} response
 * @param {HTMLFormElement} form
 */
function handleLogin(response, form) {
    if (response.ok) {
        // Getting data from response
        let username = response.headers.get(XUSERNAME);
        let token = response.headers.get(XTOKEN);
        // Saving data to LocalStorage
        localStorage.setItem(XUSERNAME, username);
        localStorage.setItem(XTOKEN, token);
    } else if (response.status == 404) {
        let oldErrors = Array.from(document.querySelectorAll(".form-error"));
        for(let error of oldErrors)
            error.remove();
        //
        addFormError(form, "password", response.headers.get(XREASON));
    } else {
        alert("Error");
    }
}