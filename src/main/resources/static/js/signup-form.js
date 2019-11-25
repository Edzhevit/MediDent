$(document).ready(function () {
    $("#signupForm").validate({
        rules: {
            firstName: "required",
            lastName: "required",
            password: {
                required: true,
                minLength: 5
            },
            confirmPassword: {
                required: true,
                minLength: 5,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            phoneNumber: "required",
            age: "required"
        },
        messages: {
            firstName: "Please enter your first name",
            lastName: "Please enter your last name",
            password: {
                required: "Please provide a password",
                minLength: "Your password must be at least 5 characters long"
            },
            confirmPassword: {
                required: "Please provide a password",
                minLength: "Your password must be at least 5 characters long",
                equalTo: "Please enter the same password as above"
            }
        }
    })
});