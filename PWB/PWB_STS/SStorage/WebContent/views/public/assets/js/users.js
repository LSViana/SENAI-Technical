/**
 * Handle the login request to the API
 * @param {Response} response 
 */
function handleLogin(response) {
    if (response.ok) {
        alert("okay");
        // Handle Headers Here
    } else if (response.status == 404) {
        alert("Invalid username or password");
    } else {
        alert("Error");
    }
}