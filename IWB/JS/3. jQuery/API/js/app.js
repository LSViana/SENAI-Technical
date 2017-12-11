const localStorageUsers = "app-users";
var usersJSON = window.localStorage.getItem(localStorageUsers);
if(usersJSON == undefined || usersJSON == "undefined")
    users = new Array();
else
    users = JSON.parse(usersJSON);
// Recovering data from Local Storage
users.forEach(function(value, index, array) {
    addTableRow(value);
});
// Business Functions
function hasRepeats(user) {
    // Verifying repetitions
    {
        if(users.some(
            function(value, index, array) {
                if(value.name.trim() == user.name.trim()) {
                    return 1;
                }
                else {
                    return 0;
                }
            })
        ) {
            alert("Make sure the data is not repeated!");
            return 1;
        }
        return 0;
    }
}
function addTableRow(user) {
    // Creating the table row
    let tr = $("<tr>");
    let tdName = $("<td>").text(user.name + " " + user.lastName);
    let tdDateOfBirth = $("<td>").text(user.dateOfBirth);
    let tdCpf = $("<td>").text(user.cpf);
    // Appeding the row to the table
    $(tr)
    .append(tdName)
    .append(tdDateOfBirth)
    .append(tdCpf);
    $("#tabelaCadastros tbody").append(tr);    
    // Saving to local storage
    window.localStorage.setItem(localStorageUsers, JSON.stringify(users));
    clearFields();
}
function clearFields() {
    $("#inputNome").val("");
    $("#inputSobrenome").val("");
    $("#inputDataNascimento").val("");
    $("#inputCpf").val("");
};
function getData(users) {
    for(var user in users) {
        console.log(user);
    }
}
function isValid(obj) {
    for(var prop in obj) {
        if((obj[prop].toString()).length == 0)
            return false;
    }
    return true;
}
//
var btnRegister;
$(document).ready(function() {
    btnRegister = $("#btnCadastrar");
    btnRegister.on("click", save);
});
/**
 * The data inserted by the user at HTML inputs
 * @returns {Object} - An object with all data came from inputs
 */
function getUserData() {
    return {
        name : $("#inputNome").val(),
        lastName : $("#inputSobrenome").val(),
        dateOfBirth : $("#inputDataNascimento").val(),
        cpf : $("#inputCpf").val(),
    };
}
function save(event) {
    var user = getUserData();
    //
    if(isValid(user)) {
        if(hasRepeats(user))
            return;
        users.push(user);
        addTableRow(user);   
    }
    else {
        alert("Fill all the fields and try again!");
    }
}