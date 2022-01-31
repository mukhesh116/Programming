var Name = "Apple";
function function1() {
    var num = 45;
    document.getElementById('function1_result1').innerHTML = "Global variable assined above the function: " + Name;
    document.getElementById('function1_result2').innerHTML = "Global variable assined above the function: " + num;
    //document.writeln("Global variable assined above the function: "+Name);
    //document.writeln("<br>Local variable assined inside the function: "+num);
}