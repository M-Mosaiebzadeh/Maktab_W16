
//initalization variable with html url that product 25 employee in text format
let myUrl = "https://5fe9b8ff8ede8b0017ff0ebc.mockapi.io/employees";

//when document completly loaded,with click in button ,make a ajax request 
$(document).ready(function () {
    $(".load_all").on("click", function () {
        $.ajax({
            url: myUrl,
            type: "GET",
            success: function (data) {
                console.log(data);
            }, error: function (err) {
                console.error(err);
            }
        });
    })
});

//if request successful,make header and write order list of firstname
//after 1500 ms button is hide
$(document).ready(function () {
    $(".load_firstname").on("click", function () {
        $.ajax({
            url: myUrl,
            type: "GET",
            success: function (data) {
                console.log(data);
                $("body").append("<h4>load all employee firstname</h4>\n\n<ol class = 'list'></ol>");
                data.forEach((employee) => {
                    $(".list").append(`<li>${employee.firstname}</li>`);
                });
            }, error: function (err) {
                console.error(err);
            }
        }); 
        setTimeout(() => {
            $(".load_firstname").hide();
        }, 1500);
    });
});

$(document).ready(function () {
    $(".load_lastname").on("click", function () {
        $.ajax({
            url: myUrl,
            type: "GET",
            success: function (data) {
                console.log(data);
                $("body").append("<h4>load all employee lastname</h4>\n\n<ol class = 'list2'></ol>");
                data.forEach((employee) => {
                    $(".list2").append(`<li>${employee.lastname}</li>`);
                });
            }, error: function (err) {
                console.error(err);
            }
        }); 
        setTimeout(() => {
            $(".load_lastname").hide();
        }, 1500);
    });
});

//after 1000 ms ,make random color for list of phone number
$(document).ready(function () {
    $(".load_phone").on("click", function () {
        $.ajax({
            url: myUrl,
            type: "GET",
            success: function (data) {
                console.log(data);
                $("body").append("<h4>load all employee phone</h4>\n\n<ol class = 'list3'></ol>");
                data.forEach((employee) => {
                    $(".list3").append(`<li class = "row">${employee.phone}</li>`);
                    setTimeout(() => {
                        $(".row").css("color", function getRandomColor() {
                                 var color = '#' + Math.random().toString(16).substr(-6);
                                return color;
                             })
                    }, 1000);
                });
            }, error: function (err) {
                console.error(err);
            }
        }); 
        setTimeout(() => {
            $(".load_phone").hide();
        }, 1500);
    });
});
