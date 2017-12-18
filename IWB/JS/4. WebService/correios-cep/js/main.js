// WebServices to be used
const CEP_endpoint = "https://viacep.com.br/ws/*/json/";

window.addEventListener("load", function() {
    var inputCep = document.getElementById("cep");
    // Trigger a function when the element lost the focus
    inputCep.addEventListener("blur", function() {
        // Call the WebService with the supplied CEP
        loadAddress(inputCep.value);
    });
});

/**
 * Load data into input based on the supplied CEP through {CEP_endpoint}
 * @param {String} CEP 
 */
function loadAddress(CEP) {
    // Creating the URL to request against WebService
    var url = CEP_endpoint.replace("*", CEP);
    // Getting data through 'fetch' method
    var promise = fetch(url);
    // Responding at any answer returned from ViaCep
    promise.then(function(response) {
        response.json().then(function(json) {
            var addressObject = json;
            if(addressObject.erro) {
                console.log("The supplied CEP is invalid!");
            }
            else {

                for(var value in addressObject) {
                    if(value == "cep") {
                        continue;
                    }
                    setFieldValue(value, addressObject[value]);
                }
            }
        })
    },
    function(reason) {
        console.log("Error with reason.");
        console.log(reason);
    });
}

function setFieldValue(field, value) {
    var currentInput = document.getElementById(field);
    if(currentInput)
        currentInput.value = value;
}