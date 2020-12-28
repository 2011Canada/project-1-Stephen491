

async function handleLogin() {
  
    let invalidLogin = true
    let errorElement

    let email = document.getElementById("floating-email").value
    let password = document.getElementById("floating-password").value

    const credentials = {email,
        password}


    try {
        let res = await fetch("http://localhost:8080/project-1-Stephen491/login",
            {method: "POST",
            credentials: "include",
            body: JSON.stringify(credentials),
            headers:{
                "Content-Type": "application/json"
            }})
        if(res.status==200) {
            invalidLogin = false
            console.log(res);
            window.location.href=res.url
        }

    }
    catch(e){
        console.log(e)
    }

 
    if(invalidLogin) {
        console.log(`Bad credentials. email: ${email} password: ${password}`)
        errorElement = document.getElementById("login-error");
        errorElement.innerText = "Sorry, you've entered in invalid credentials. Please try again."
        errorElement.style.display = "block"


    }


}