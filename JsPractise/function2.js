function function2(num1, num2, action) {
    var num1, num2, result;
    var action;
    if (action == 'sum') {
        result = Number(num1) + Number(num2);
    }
    if (action == 'sub') {
        result = Number(num1) - Number(num2);
    }
    if (action == 'mul') {
        result = Number(num1) * Number(num2);
    }
    if (action == 'div') {
        result = Number(num1) / Number(num2);
    }
    document.getElementById('function2_result').innerHTML = result;
    //document.write("Sum of the numbers : "+result);
}