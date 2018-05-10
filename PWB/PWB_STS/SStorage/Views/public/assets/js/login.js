// This JS file is added to pages after window.onload event

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
        let id = response.headers.get(XID);
        let authLevel = response.headers.get(XAUTHLEVEL);
        // Saving data to LocalStorage
        localStorage.setItem(XUSERNAME, username);
        localStorage.setItem(XTOKEN, token);
        localStorage.setItem(XID, id);
        localStorage.setItem(XAUTHLEVEL, authLevel);
        // Redirecting to home page
        window.location.href = ROUTES["router-main"];
    } else if (response.status == 404) {
        let oldErrors = Array.from(document.querySelectorAll(".form-error"));
        for (let error of oldErrors)
            error.remove();
        //
        addFormError(form, "password", response.headers.get(XREASON));
    } else {
        alert("Error");
    }
}