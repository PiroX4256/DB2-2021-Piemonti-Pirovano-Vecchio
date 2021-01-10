// this function is executed when document is ready and makes variables not end in public scope
$(document).ready(function() {
    $("#loginbutton").on("click", function(e) {
        e.preventDefault();
        let form = document.getElementById("loginForm");
        if (form.checkValidity()) {
            let username = document.getElementById("username").value;
            // MD5 digest the password for safer communication between server and client
            // let hashedPassword = CryptoJS.MD5(document.getElementById("password").value).toString();
            let formData = {username: username, password: document.getElementById("password").value};
            $.ajax({
                type: "POST",
                url: 'signin',
                data: JSON.stringify(formData),
                success: function(response) {
                    localStorage.setItem('token', response);
                    $.ajax({
                        url: "getHomePage",
                        type: "GET",
                        headers: { Authorization: $`Bearer ${localStorage.getItem("token")}` },
                        data: formData,
                        error: function(err) {
                            console.log($`ERROR:: ${err.status}`);
                            switch (err.status) {
                                case "400":
                                    // bad request
                                    break;
                                case "401":
                                    // unauthorized
                                    break;
                                case "403":
                                    // forbidden
                                    break;
                                default:
                                    //Something bad happened
                                    break;
                            }
                        },
                        success: function(data) {
                            console.log("Success!");
                        }
                    });
                },
                error: function(req) {
                    document.getElementById("errormessage").textContent = req.responseText;
                },
                dataType: "json"
            });
        } else {
            form.reportValidity();
        }
    })
})