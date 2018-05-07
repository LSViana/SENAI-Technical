/**
 * Add a error descriptor next to the form element
 * @param {HTMLFormElement} form 
 * @param {String} inputName
 * @param {String} message
 */
function addFormError(form, inputName, message) {
    let input = document.querySelector(`#${form.id} *[name='${inputName}']`);
    //
    let divEl = document.createElement("div");
    let pIcon = document.createElement("p");
    pIcon.innerText = "!";
    let pEl = document.createElement("p");
    pEl.innerText = message;
    // Adding to DOM
    divEl.appendChild(pIcon);
    divEl.appendChild(pEl);
    input.parentElement.appendChild(divEl);
    // Adding CSS Classes
    divEl.classList.add(FORM_ERROR);
}