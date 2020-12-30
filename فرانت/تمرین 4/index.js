
a = 0;

//create variable div and nested it p to handle number in middle of block
function myFunction() {
    var par = document.createElement("div");
    // par.innerHTML = (a+=1);
    par.innerHTML = `<p class = inner>${a+=1}</p>`
    //bg color of variable is randomly change
    par.style.backgroundColor = getRandomColor();
    //variable append to document body to show in page
    document.body.appendChild(par);
}

//generate random color with 6 loop to create hexacode
function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

//create random color with change to hexacode and last six charecter

// function getRandomColor() {
//     var color = '#' + Math.random().toString(16).substr(-6);
//     return color;
// }
