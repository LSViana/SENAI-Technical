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
    // Getting input
    let input = document.querySelector(`#${form.id} *[name='${inputName}']`);
    if (!input)
        return;
    // Removing old errors
    let oldErrors = Array.from(document.querySelectorAll(`#${form.id} div[data-entity-error=${inputName}]`));
    for (let oldError of oldErrors) {
        oldError.remove();
    }
    //
    if (!form.id) {
        console.log(form);
        throw Error("The Form must have an ID");
    }
    //
    let divEl = document.createElement("div");
    let formattedText = FORM_ERROR_HTML;
    formattedText = formattedText.replace("{0}", message);
    divEl.innerHTML = formattedText;
    input.parentElement.appendChild(divEl);
    //
    divEl.setAttribute("data-entity-error", inputName);
    divEl.classList.add(FORM_ERROR);
}

let inputRadios = Array.from(document.querySelectorAll("label input[type=radio]")).map(function (el) {
    return el.parentElement
});
for (let input of inputRadios) {
    input.addEventListener("click", handleRadioLabelClick);
}

/**
 * Handles the click at Label's Elements of Forms that contain input[type=radio]
 * @param {MouseEvent} ev
 */
function handleRadioLabelClick(ev) {
    let siblings = Array.from(ev.srcElement.parentElement.children).filter(function (el) {
        return el.tagName;
    });
    for (let element of siblings) {
        element.classList.remove("active");
        Array.from(element.children).filter(function (child) {
            return child.tagName.toLowerCase() == "input"
        }).forEach(function (el) {
            el.removeAttribute("checked")
        });
    }
    Array.from(ev.srcElement.children).filter(function (child) {
        return child.tagName.toLowerCase() == "input"
    }).forEach(function (el) {
        el.setAttribute("checked", "")
    });
    ev.srcElement.classList.add("active");
}