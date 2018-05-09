// This JS file is added to pages after window.onload event

const FORM_ERROR_HTML = `<div class="card bg-danger m-0">
<div class="card-body p-2 d-flex flex-row align-items-center">
    <i class="fa fa-exclamation m-2 mt-1"></i>
    <p class="text-center m-0 font-small">{0}</p>
</div>
</div>`;

/**
 * Add a error descriptor next to the form element
 * @param {HTMLFormElement} form 
 * @param {String} inputName
 * @param {String} message
 */
function addFormError(form, inputName, message) {
    // Removing old errors
    let oldErrors = Array.from(document.querySelectorAll(`.${FORM_ERROR}`));
    for(let oldError of oldErrors) {
        oldError.remove();
    }
    //
    if (!form.id) {
        console.log(form);
        throw Error("The Form must have an ID");
    }
    let input = document.querySelector(`#${form.id} *[name='${inputName}']`);
    //
    let divEl = document.createElement("div");
    let formattedText = FORM_ERROR_HTML;
    formattedText = formattedText.replace("{0}", message);
    divEl.innerHTML = formattedText;
    input.parentElement.appendChild(divEl);
    //
    divEl.classList.add(FORM_ERROR);
}