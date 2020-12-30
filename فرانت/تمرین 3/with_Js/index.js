
// create instance of XHR
var http = new XMLHttpRequest();

//initalization variable with html url that product 25 employee in text format
var url = "https://5fe9b8ff8ede8b0017ff0ebc.mockapi.io/employees";

//if ready state equals 4 and status equals 200,,console show result in json format
function loadAll() {
    http.onreadystatechange = function () {
        if (http.readyState === 4 && http.status === 200) {
            let result = JSON.parse(http.responseText);
            console.log("All Property", result);
        }
    }
    http.open("get", url);
    http.send();

}
// console log all firstname in json format
function loadAllFirstname() {
    http.onreadystatechange = function () {
        if (http.readyState === 4 && http.status === 200) {
            let result = JSON.parse(http.responseText);
            for (let index = 0; index < result.length; index++) {
                console.log("All FirstNames",result[index].firstname);

            }
            console.log(result.length);
        }
    }
    http.open("get", url);
    http.send();
}
// console log all lastname in json format
function loadAllLastname() {
    http.onreadystatechange = function () {
        if (http.readyState === 4 && http.status === 200) {
            let result = JSON.parse(http.responseText);
            for (let index = 0; index < result.length; index++) {
                console.log("All LastName",result[index].lastname);
            }
        }
    }
    http.open("get", url);
    http.send();
}
// console log all phone number in json format
function loadAllPhone() {
    http.onreadystatechange = function () {
        if (http.readyState === 4 && http.status === 200) {
            let result = JSON.parse(http.responseText);
            console.log("All Phone Number");
            for (let index = 0; index < result.length; index++) {
                console.log(result[index].phone);
            }
        }
    }
    http.open("get", url);
    http.send();
}
