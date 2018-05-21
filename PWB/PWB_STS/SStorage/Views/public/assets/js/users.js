/**
 * Callback the User Register Operation
 * @param {Response} response 
 * @param {HTMLFormElement} form 
 */
function handleRegisterUser(response, form) {
    if(response.ok) {
        if(form.id && form.id.name && form.id.value == getId()) {
            let modal = $("#modal-userchanged");
            if(modal) {
                modal.modal();
                modal.on('hidden.bs.modal', function (e) {
                    performLogout();
                    })
            }
        } else {
            window.location.href = ROUTES["router-main"];
        }
    } else {
        let customHeaderKeys = Array.from(response.headers.keys()).filter(function(key) { return key.toLowerCase().startsWith("x-") });
        for(let key of customHeaderKeys) {
            let message = response.headers.get(key);
            addFormError(form, key.substr(2), message);
        }
    }
}