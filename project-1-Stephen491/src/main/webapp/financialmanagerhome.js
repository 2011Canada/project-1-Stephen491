
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
        let receiptsText = document.createTextNode(data.receipts);

        id_cell.appendChild(idText);
        amount_cell.appendChild(amountText);
        submitted_cell.appendChild(submittedText);
        type_cell.appendChild(typeText);
        description_cell.appendChild(descriptionText);
        status_cell.appendChild(statusText);
        
    

       
    }

    //add resolved data
}


async function handleFormSubmit() {

    
    let formData = {
        amount: document.getElementById("amount-field").value,
        type_id: document.getElementById("select-type").value,
        description: document.getElementById("description").value,
        receipt: document.getElementById("file-selector").value
    }

    if(formData.amount&&formData.type_id&&formData.description) {
        try {
            let res = await fetch("http://localhost:8080/project-1-Stephen491/reimbursements",
            {method: "POST",
            credentials: "include",
            body: JSON.stringify(formData),
            headers:{
                "Content-Type": "application/json"
            }})
            console.log(res)
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