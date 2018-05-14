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
    let inputNameLC = inputName.toLowerCase();
    // Getting input
    let input = Array.from(document.querySelectorAll(`#${form.getAttribute("id")} *[name]`)).filter(function (el) {
        return el.getAttribute("name").toLowerCase() == inputNameLC;
    })[0];
    if (!input)
        return;
    // Removing old errors
    removeFormInputErrors(form, inputName);
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

function removeFormErrors(form) {
    let oldErrors = Array.from(document.querySelectorAll(`#${form.getAttribute("id")} div[data-entity-error]`));
    for (let oldError of oldErrors) {
        oldError.remove();
    }
}

function removeFormInputErrors(form, inputName) {
    let oldErrors = Array.from(document.querySelectorAll(`#${form.getAttribute("id")} div[data-entity-error=${inputName}]`));
    for (let oldError of oldErrors) {
        oldError.remove();
    }
    return oldErrors;
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
    if (!formId) {
        console.log(form);
        throw Error(`The form must have ID`);
    }
    // Setting Update Text to the Button
    let button = document.querySelector(`#${form.getAttribute("id")} button[type=submit]`);
    button.innerText = button.getAttribute("data-update-text");
    //
    let inputNames = Array.from(document.querySelectorAll(`#${form.getAttribute("id")} input[name]`)).map(function (el) {
        return el.name
    });
    // Getting unique values
    inputNames = inputNames.filter(function (el, index) {
        return inputNames.indexOf(el) == index
    });
    for (let property in value) {
        if (form[property]) {
            // Removing property from Input Names to avoid required
            let indexOfName = inputNames.indexOf(property);
            if (indexOfName != -1)
                inputNames.splice(indexOfName, 1);
            // Text Fields
            if (form[property].name) {
                /**
                 * @type {HTMLInputElement}
                 */
                let input = form[property];
                let inputValue = value[property];
                input.value = inputValue;
                let idParts = input.id.split(".");
                if (idParts.length < 2) {
                    // Adding "active to the label if there is any data"
                    if (inputValue) {
                        let inputId = input.getAttribute("id");
                        if (!inputId) {
                            console.log(input);
                            throw Error("The input must have an ID");
                        }
                        let label = document.querySelector(`label[for=${inputId}]`);
                        if (label)
                            label.classList.add("active");
                    }
                } else {
                    // Adding options to DropDown
                    fillInputDropdown(input, function () {
                        let aEl = document.querySelector(`a[data-entity-item="${property}"][data-entity-id="${value[property].id}"][data-input-id="${input.getAttribute("id")}"]`);
                        selectDropDownItem(aEl);
                    });
                }
            } else {
                /**
                 * @type {Array}
                 */
                let obj = form[property];
                // Radio Buttons
                if (obj.constructor.name == "RadioNodeList") {
                    for (let radio of obj) {
                        if (radio.name == property) {
                            if (radio.value == value[property]) {
                                selectRadioButton(radio.parentElement);
                            }
                        }
                    }
                }
            }
        }
    }
    // Removing required from the not needed input fields, according to values received from API
    for (let inputName of inputNames) {
        let input = form[inputName];
        if (input && input.name) {
            input.removeAttribute("required");
        }
    }
}

/**
 * Input element that corresponds to the DropDown to be filled
 * @param {HTMLInputElement} input 
 * @param {()} callback
 */
function fillInputDropdown(input, callback) {
    let entityManyIdentifier = input.getAttribute("data-many-identifier");
    let buttonsDropdown = Array.from(document.querySelectorAll(`*[data-input-many-identifier="${entityManyIdentifier}"]`));
    for (let buttonDropdown of buttonsDropdown) {
        let input = document.getElementById(buttonDropdown.getAttribute("data-input-id"));
        if (!input)
            throw Error(`Invalid 'data-input-id' attribute: ${buttonDropdown.getAttribute("data-input-id")}`);
        let entityRouter = ROUTES[buttonDropdown.getAttribute("data-router")];
        let propertyLabels = buttonDropdown.getAttribute("data-element-label").split(" ");
        let listElement = document.getElementById(`${buttonDropdown.getAttribute("data-list-element")}`);
        if (!listElement)
            throw Error(`ID not found: ${buttonDropdown.getAttribute("data-list-element")}`);
        let token = getToken();
        let headers = {};
        headers[XTOKEN] = token;
        fetch(entityRouter, {
            headers,
            method: "GET"
        }).then(function (response) {
            response.json().then(function (value) {
                for (let item of value) {
                    let text = "";
                    for (let property of propertyLabels)
                        text += item[property] + " ";
                    let aEl = document.createElement("a");
                    aEl.setAttribute("class", "dropdown-item");
                    aEl.innerText = text;
                    aEl.setAttribute("data-input-id", input.getAttribute("id"));
                    aEl.setAttribute("data-entity-id", item.id);
                    aEl.setAttribute("data-entity-item", entityManyIdentifier);
                    aEl.options = {
                        button: buttonDropdown,
                        input: input,
                        item: item
                    };
                    // Adding handlers
                    aEl.addEventListener("click", function () {
                        selectDropDownItem(aEl);
                    });
                    // Appending elements to the List Element
                    listElement.appendChild(aEl);
                }
                // End fill of operations
                if (callback)
                    callback();
            }, function (rejected) {
                console.error(rejected);
            });
        }, function (rejected) {
            console.error(rejected);
        });
    }
}

/**
 * Selects the anchor element, changes its button text and input value
 * @param {HTMLAnchorElement} aEl The <a> (anchor) element to select
 */
function selectDropDownItem(aEl) {
    aEl.options.button.innerText = aEl.innerText;
    aEl.options.input.setAttribute("value", aEl.getAttribute("data-entity-id"));
    aEl.options.input.entity = aEl.options.item;
}

function verifyEntityIdPresence() {
    let location = new URL(window.location.href);
    let id = location.searchParams.get("id");
    let forms = Array.from(document.querySelectorAll("form[data-router]"));
    // Verifying ID presence
    if (id) {
        for (let form of forms) {
            let value = getEntity(ROUTES[form.getAttribute("data-router")], id, function (value) {
                fillForm(form, value);
            });
        }
    }
    // Filling all DropDown properties
    for (let form of forms) {
        if (!form.getAttribute("id")) {
            console.log(form);
            throw Error("Form must have an ID");
        }
        // If there is no ID, it is, the Form hasn't been filled, if it did, avoid this operation because it is going to duplicate all drop down items
        if (!new URL(window.location.href).searchParams.get("id")) {
            let inputs = Array.from(document.querySelectorAll(`#${form.getAttribute("id")} input[id]`));
            for (let input of inputs) {
                let idParts = input.getAttribute("id").split(".");
                if (idParts.length > 1) {
                    fillInputDropdown(input, null);
                }
            }
        }
    }
}

verifyEntityIdPresence();