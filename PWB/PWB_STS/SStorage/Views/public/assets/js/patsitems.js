/**
 * Callback the User Register Operation
 * @param {Response} response 
 * @param {HTMLFormElement} form 
 */
function handleLoadPatsItems(data) {
    let manyOneElements = Array.from(document.querySelectorAll(".many-one"));
    for(let element of manyOneElements) {
        console.log(element);
    }
    console.log(data);
}