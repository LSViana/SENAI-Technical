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
    let input = document.querySelector(`#${form.getAttribute("id")} *[name='${inputName}']`);
    if (!input)
        return;
    // Removing old errors
    let oldErrors = Array.from(document.querySelectorAll(`#${form.getAttribute("id")} div[data-entity-error=${inputName}]`));
    for (let oldError of oldErrors) {
        oldError.remove();
    }
    //
    if (!form.getAttribute("id")) {
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
    let radio = ev.srcElement;
    selectRadioButton(radio);
}

/**
 * Selects the Radio Button and deselects the siblings
 * @param {HTMLInputElement} radio 
 */
function selectRadioButton(radio) {
    let siblings = Array.from(radio.parentElement.children).filter(function (el) {
        return el.tagName;
    });
    for (let element of siblings) {
        element.classList.remove("active");
        Array.from(element.children).filter(function (child) {
            return child.tagName.toLowerCase() == "input";
        }).forEach(function (el) {
            el.removeAttribute("checked");
        });
    }
    Array.from(radio.children).filter(function (child) {
        return child.tagName.toLowerCase() == "input";
    }).forEach(function (el) {
        el.setAttribute("checked", "");
    });
    radio.classList.add("active");
}

/**
 * Fills a form with the values from the object
 * @param {HTMLFormElement} form 
 * @param {Object} value 
 */
function fillForm(form, value) {
    let formId = form.getAttribute("id");
    if(!formId) {
        console.log(form);
        throw Error(`The form must have ID`);
    }
    let inputNames = Array.from(document.querySelectorAll(`#${form.getAttribute("id")} input[name]`)).map(function(el) { return el.name });
    // Getting unique values
    inputNames = inputNames.filter(function(el, index) { return inputNames.indexOf(el) == index });
    for(let property in value) {
        if(form[property]) {
            // Removing property from Input Names to avoid required
            let indexOfName = inputNames.indexOf(property);
            if(indexOfName != -1)
                inputNames.splice(indexOfName, 1);
            // Text Fields
            if(form[property].name) {
                /**
                 * @type {HTMLInputElement}
                 */
                let input = form[property];
                input.value = value[property];
            } else {
                /**
                 * @type {Array}
                 */
                let obj = form[property];
                // Radio Buttons
                if(obj.constructor.name == "RadioNodeList") {
                    for(let radio of obj) {
                        if(radio.name == property) {
                            if(radio.value == value[property]) {
                                selectRadioButton(radio.parentElement);
                            }
                        }
                    }
                }
            }
        }
    }
    // Removing required from the not needed input fields, according to values received from API
    for(let inputName of inputNames) {
        let input = form[inputName];
        if(input && input.name) {
            input.removeAttribute("required");
        }
    }
}

function verifyEntityIdPresence() {
    let location = new URL(window.location.href);
    let id = location.searchParams.get("id");
    // Verifying ID presence
    if(id) {
        let forms = Array.from(document.querySelectorAll("form[data-router]"));
        for(let form of forms) {
            let value = getEntity(ROUTES[form.getAttribute("data-router")], id, function(value) {
                fillForm(form, value);
            });
        }
    }
}

verifyEntityIdPresence();