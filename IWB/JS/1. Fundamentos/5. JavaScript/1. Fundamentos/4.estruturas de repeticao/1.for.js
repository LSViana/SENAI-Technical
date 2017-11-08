window.addEventListener('load', () => {
    var buttonAmount = document.getElementById('amount-input');
    var inputNumber = document.getElementById('elements-amount');
    var listInput = document.getElementById('list');
    //
    buttonAmount.addEventListener('click', () => {
        listInput.innerHTML = "";
        var amount = Number(inputNumber.value);
        //
        var lastChild;
        for(; amount > 0; amount--) {
            var liNumber = document.createElement('li');
            liNumber.innerHTML = amount;
            if(lastChild != null) {
                list.insertBefore(liNumber, lastChild);
            }
            else {
                list.appendChild(liNumber);
            }
            lastChild = liNumber;
        }
    });
});