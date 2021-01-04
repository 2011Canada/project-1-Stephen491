
let onPending;


async function setup() {
    let resolvedRequestsTable = document.getElementById("resolved-reimbursements-table")
    let newReimbursementForm = document.getElementById("new-reimbursement-form")
    newReimbursementForm.style.setProperty("display", "none");
    resolvedRequestsTable.style.setProperty("display", "none");
    onPending = true; 

    await getReimbursementTableData()
    //post request to get data
}


function toggleRequests() {
    let button = document.getElementById("toggle-requests-button")
    let pendingRequestsTable = document.getElementById("pending-reimbursements-table")
    let resolvedRequestsTable = document.getElementById("resolved-reimbursements-table")


    if(button.innerText==="Show resolved requests") {
        onPending = false;
        button.innerText = "Show pending requests"
        pendingRequestsTable.style.setProperty("display", "none");
        resolvedRequestsTable.style.display = ""
    }
    else {
        onPending = true; 
        button.innerText = "Show resolved requests"
        resolvedRequestsTable.style.setProperty("display", "none");
        pendingRequestsTable.style.display = ""

    }

    document.getElementById("feedback").style.setProperty("display", "none");

}

async function leaveForm() {
    let toggleRequests = document.getElementById("toggle-requests-button")
    let pendingRequestsTable = document.getElementById("pending-reimbursements-table")
    let goBackButton = document.getElementById("leave-form-button")
    let form = document.getElementById("new-reimbursement-form");
    let createReimbursementButton = document.getElementById("create-reimbursement")
    let resolvedRequestsTable = document.getElementById("resolved-reimbursements-table")
    clearTableData() 
    await getReimbursementTableData()

    form.style.display = "none";
    toggleRequests.style.display = "block";
    goBackButton.style.display = "none";
    if(onPending) {
        pendingRequestsTable.style.display = "";
    }
    else {
        resolvedRequestsTable.style.display = "";
    }
    createReimbursementButton.style.display = "block"

}


function toggleShowForm() {
    let toggleRequests = document.getElementById("toggle-requests-button")
    let button = document.getElementById("create-reimbursement")
    let goBackButton = document.getElementById("leave-form-button")
    let pendingRequestsTable = document.getElementById("pending-reimbursements-table")
    let resolvedRequestsTable = document.getElementById("resolved-reimbursements-table")

    button.style.display = "none"
    goBackButton.style.display = "block";

    if(onPending) {
        pendingRequestsTable.style.setProperty("display", "none");
        
    }
    else {
        resolvedRequestsTable.style.setProperty("display", "none");
    }

    let form = document.getElementById("new-reimbursement-form");
    form.style.display = ""
    toggleRequests.style.display = "none";
    document.getElementById("feedback").style.display = "none";

}
async function logout() {
    try {
        let res = await fetch("http://localhost:8080/project-1-Stephen491/logout")
        window.location.replace(res.url);



    }
    catch(e) {
        console.log(e)
    }
}

async function getReimbursementTableData() {
    console.log("called")
    let res = await fetch("http://localhost:8080/project-1-Stephen491/reimbursements")
    let bodyjson = await res.json()
    console.log(bodyjson);
    bodyjson.forEach(insertNewRow)



}


function clearTableData() {
 
   if (document.getElementById("pending-reimbursements-table-body")) {
        document.getElementById("pending-reimbursements-table-body").innerHTML = '';
   }
   if (document.getElementById("resolved-reimbursements-table-body")) {
        document.getElementById("resolved-reimbursements-table-body").innerHTML = '';
   }

}

function insertNewRow(data) {
    
    if(data.status_id !== 3 && data.status_id !==4) {
        
        let table = document.getElementById("pending-reimbursements-table-body")
        let newRow = table.insertRow();
        newRow.setAttribute("reimburse-id", data.id)
        newRow.setAttribute("author", data.author)
        let id_cell = newRow.insertCell();
        let amount_cell = newRow.insertCell();
        let submitted_cell = newRow.insertCell();
        let type_cell = newRow.insertCell();
        let description_cell = newRow.insertCell();
        let status_cell = newRow.insertCell();
        let receipts_cell = newRow.insertCell();
        

        let idText = document.createTextNode(data.id);
        let amountText = document.createTextNode(data.amount);
        let submittedText = document.createTextNode(data.dateSubmitted)
        let typeText = document.createTextNode(data.type);
        let descriptionText = document.createTextNode(data.description);
        let statusText = document.createTextNode(data.status);
        let receiptsText = document.createTextNode("No Receipt");
        if(data.hasReceipt) {
            receiptsText = document.createTextNode("Has receipt")
            receipts_cell.setAttribute("onClick", "downloadReceipt()")

        }

        id_cell.appendChild(idText);
        amount_cell.appendChild(amountText);
        submitted_cell.appendChild(submittedText);
        type_cell.appendChild(typeText);
        description_cell.appendChild(descriptionText);
        status_cell.appendChild(statusText);
        receipts_cell.appendChild(receiptsText)
    

       
    }
    else {
        let table = document.getElementById("resolved-reimbursements-table-body")
        let newRow = table.insertRow();
        newRow.setAttribute("reimburse-id", data.id)
        newRow.setAttribute("author", data.author)
        let id_cell = newRow.insertCell();
        let amount_cell = newRow.insertCell();
        let submitted_cell = newRow.insertCell();
        let resolved_cell = newRow.insertCell();
        let type_cell = newRow.insertCell();
        let description_cell = newRow.insertCell();
        let resolver_username_cell = newRow.insertCell();
        let resolver_fullname_cell = newRow.insertCell();
        let resolver_email_cell = newRow.insertCell();
        let status_cell = newRow.insertCell();
        let receipts_cell = newRow.insertCell();

        let idText = document.createTextNode(data.id);
        let amountText = document.createTextNode(data.amount);
        let submittedText = document.createTextNode(data.dateSubmitted)
        let typeText = document.createTextNode(data.type);
        let descriptionText = document.createTextNode(data.description);
        let receiptsText = document.createTextNode("");
        if(data.hasReceipt) {
            receiptsText = document.createTextNode("Has receipt")
            receipts_cell.setAttribute("onClick", "downloadReceipt()")


        }
     
        let resolverUsername;
        let resolverFullName;
        let resolverEmail;
        if(data.resolver!=0) {
           
             resolverUsername = document.createTextNode(data.resolver_username)
             resolverFullName = document.createTextNode(data.resolver_firstName+" "+data.resolver_lastName)
             resolverEmail = document.createTextNode(data.resolver_email)
        }
        let statusText = document.createTextNode(data.status);
        let resolvedText;
        if(data.dateResolved) {
            resolvedText = document.createTextNode(data.dateResolved)
        }
        else {
            resolvedText = document.createTextNode("Pending")
        }

        id_cell.appendChild(idText);
        amount_cell.appendChild(amountText);
        submitted_cell.appendChild(submittedText);
        type_cell.appendChild(typeText);
        description_cell.appendChild(descriptionText);
        status_cell.appendChild(statusText);
        receipts_cell.appendChild(receiptsText)
        resolver_username_cell.appendChild(resolverUsername)
        resolver_fullname_cell.appendChild(resolverFullName)
        resolver_email_cell.appendChild(resolverEmail)
        resolved_cell.appendChild(resolvedText);

    }

    //add resolved data
}


async function handleFormSubmit() {

    
    let formData = {
        amount: document.getElementById("amount-field").value,
        type_id: document.getElementById("select-type").value,
        description: document.getElementById("description").value,
        receipt: document.getElementById("file-selector").files[0]
    }
       if(formData.receipt) {
        let reader = new FileReader(); 
        let fileByteArray = []
        reader.onload = async function(evt) {
            if (evt.target.readyState == reader.DONE) {
                var arrayBuffer = evt.target.result,
                    array = new Uint8Array(arrayBuffer);
                for (var i = 0; i < array.length; i++) {
                    fileByteArray.push(array[i]);
                 }
             }
            formData.receipt = fileByteArray;
            console.log(formData.receipt)
            if(formData.amount&&formData.type_id&&formData.description) {
                try {
                    let res = await fetch("http://localhost:8080/project-1-Stephen491/reimbursements",
                    {method: "POST",
                    credentials: "include",
                    body: JSON.stringify(formData),
                    headers:{
                        "Content-Type": "application/json"
                    }})
                    if(res.status==200) {
                        document.getElementById("feedback").style.setProperty("display", "block");
                        document.getElementById("feedback-text").innerText = "Reimbursement request has been successfully submitted."
                        document.getElementById("feedback").style.setProperty("background-color", "rgb(50, 168, 84)");
                    }
                    else {
                        document.getElementById("feedback").style.setProperty("display", "block");
                        document.getElementById("feedback-text").innerText = "An error has occured, please try again."
                        document.getElementById("feedback").style.setProperty("background-color", "rgb(168, 54, 50)");
                    }
                    leaveForm();
                    
                }
                catch(e)
                {
                    console.log(e)
                    leaveForm();
                }    
            }
            else {
                console.log("Please fill all of the information.")
            }


        }
        reader.readAsArrayBuffer(formData.receipt);
        
    }
    else {
    
        if(formData.amount&&formData.type_id&&formData.description) {
            try {
                let res = await fetch("http://localhost:8080/project-1-Stephen491/reimbursements",
                {method: "POST",
                credentials: "include",
                body: JSON.stringify(formData),
                headers:{
                    "Content-Type": "application/json"
                }})
                if(res.status==200) {
                    document.getElementById("feedback").style.setProperty("display", "block");
                    document.getElementById("feedback-text").innerText = "Reimbursement request has been successfully submitted."
                    document.getElementById("feedback").style.setProperty("background-color", "rgb(50, 168, 84)");
                }
                else {
                    document.getElementById("feedback").style.setProperty("display", "block");
                    document.getElementById("feedback-text").innerText = "An error has occured, please try again."
                    document.getElementById("feedback").style.setProperty("background-color", "rgb(168, 54, 50)");
                }
                leaveForm();
                
            }
            catch(e)
            {
                console.log(e)
                leaveForm();
            }    
        }
        else {
            console.log("Please fill all of the information.")
        }
}

}

async function downloadReceipt(){
    clickedRow = event.target.closest("tr")
    let receiptQueryInfo = {
        reimb_owner_id: clickedRow.getAttribute("author"),
        reimb_id: clickedRow.getAttribute("reimburse-id")
    }

    console.log(receiptQueryInfo)
    try {
        let res = await fetch("http://localhost:8080/project-1-Stephen491/receipt",
        {method: "POST",
        credentials: "include",
        body: JSON.stringify(receiptQueryInfo),
        headers:{
            "Content-Type": "application/json"
        }})
        var blob = await res.blob()
        var filename = res.headers.get("Content-Disposition").split("filename=")[1];
      
        saveData(blob, filename)
        console.log(blob)
    
    }
       
    catch(e) {
        console.log(e)
    }



    
}
function saveData(blob, fileName) {
    var a = document.createElement("a");
    document.body.appendChild(a);
    a.style = "display: none";

    var url = window.URL.createObjectURL(blob);
    a.href = url;
    a.download = fileName;
    a.click();
    window.URL.revokeObjectURL(url);
}