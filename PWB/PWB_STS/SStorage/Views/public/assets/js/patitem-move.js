function handleMovePatrimonyItem(form, data) {
    let freshEnvironment = data.environment.id;
    let patItemId = data.id;
    // Performing the access to the move operation
    /* /{idPatrimonyItem}/{idEnvOrigin}/{idEnvDestiny} */
    let route = SERVER + API_PREFIX + MOV_PREFIX + `/move/${patItemId}/${freshEnvironment}`;
    let headers = {};
    headers[XTOKEN] = getToken();
    // Request
    fetch(route,
    {
        headers,
        method: "GET"
    }).then(function(response) {
        if (response.ok) {
            window.location.href = ROUTES["router-patitems"];
        } else if (Math.floor(response.status / 100) == 4) {
            addFormError(form, "environment", "You're not allowed to perform this action.");
        }
    }).catch(function(reason) {
        addFormError(form, "environment", "This operation couldn't be completed.");
    });
}