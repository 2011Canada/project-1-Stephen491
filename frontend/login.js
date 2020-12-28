

function handleLogin() {
  
    let invalidLogin = true
    let errorElement

    let email = document.getElementById("floating-email").value
    let password = document.getElementById("floating-password").value

    const credentials = {email,
        password}


    try {
        let res = fetch("http://localhost:8080/servlet/login",
            {method: "POST",
            credentials: "include",
            body: JSON.stringify(credentials),
            headers:{
                "Content-Type": "application/json"
            }})


    }
    catch(e){
        console.log(e)
    }
    //make fetch request to servlet
    //await fetch("serverURL", "postrequest")

 
    if(invalidLogin) {
        console.log(`Bad credentials. email: ${email} password: ${password}`)
        errorElement = document.getElementById("login-error");
        errorElement.innerText = "Sorry, you've entered in invalid credentials. Please try again."
        errorElement.style.display = "block"


    }
    else {
        
        console.log("Valid login")
        window.location.href="http://localhost:8080/project-1-Stephen491/home"; 

    }


}