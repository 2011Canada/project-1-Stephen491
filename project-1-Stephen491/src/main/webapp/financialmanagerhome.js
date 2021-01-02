
let onPending;
let currentReimb;

async function setup() {
    let newReimbursementForm = document.getElementById("new-reimbursement-form")
    newReimbursementForm.style.setProperty("display", "none");
    onPending = true; 

    await getReimbursementTableData()
    
    //post request to get data
}



async function leaveForm() {
    let requestsTable = document.getElementById("reimbursements-table")
    let goBackButton = document.getElementById("leave-form-button")
    let form = document.getElementById("new-reimbursement-form");
    let searchLabel = document.getElementById("search-label");
    let searchInput = document.getElementById("searchStatus"); 



    clearTableData() 
    await getReimbursementTableData()

    form.style.display = "none";
    goBackButton.style.display = "none";
    
    requestsTable.style.display = "";

    searchLabel.style.display = "";
    searchInput.style.display = "";
   
    currentReimb = null;
}


function toggleShowForm(reimburseInfo) {
    let goBackButton = document.getElementById("leave-form-button")
    let requestsTable = document.getElementById("reimbursements-table")
    let search = document.getElementById("searchStatus")
    let searchLabel = document.getElementById("search-label")
    goBackButton.style.display = "block";

    document.getElementById("feedback").style.display = "none";
    requestsTable.style.setProperty("display", "none");
    searchLabel.style.setProperty("display", "none");
    search.style.setProperty("display", "none")
        
    let form = document.getElementById("new-reimbursement-form");

    document.getElementById("author-id").innerText = reimburseInfo.author;
    document.getElementById("reimb-amount").innerText = reimburseInfo.amount;
    document.getElementById("reimb-description").innerText = reimburseInfo.description;
    document.getElementById("reimb-type").innerText = reimburseInfo.type;
    document.getElementById("reimb-status").innerText = reimburseInfo.status;

    form.style.display = ""
    currentReimb = reimburseInfo
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
    let res = await fetch("http://localhost:8080/project-1-Stephen491/allreimbursements")
    let bodyjson = await res.json()
    console.log(bodyjson);
    bodyjson.forEach(insertNewRow)



}


function clearTableData() {
 
  
    document.getElementById("reimbursements-table-data").innerHTML = '';
   

}

function insertNewRow(data) {
    
   
        
        let table = document.getElementById("reimbursements-table-data")
        let newRow = table.insertRow();
        newRow.setAttribute("reimburse-id", data.id)
        newRow.setAttribute("author", data.author)
        newRow.setAttribute("status", data.status)
        newRow.setAttribute("amount", data.amount)
        newRow.setAttribute("description", data.description)
        newRow.setAttribute("type", data.type)
        newRow.setAttribute("onClick", "openOption()")
        let id_cell = newRow.insertCell();
        let amount_cell = newRow.insertCell();
        let submitted_cell = newRow.insertCell();
        let resolved_cell = newRow.insertCell();
        let type_cell = newRow.insertCell();
        let description_cell = newRow.insertCell();
        let author_cell = newRow.insertCell();
        let author_username_cell = newRow.insertCell();
        let author_fullname_cell = newRow.insertCell();
        let author_email_cell = newRow.insertCell();

        let resolver_cell = newRow.insertCell();
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
        let authorText = document.createTextNode(data.author);
        let authorUsername = document.createTextNode(data.author_username);
        let authorFullName = document.createTextNode(data.author_firstName+" "+data.author_lastName)
        let authorEmail = document.createTextNode(data.author_email)
        let resolverText;
        let resolverUsername;
        let resolverFullName;
        let resolverEmail;
        if(data.resolver!=0) {
             resolverText = document.createTextNode(data.resolver)
             resolverUsername = document.createTextNode(data.resolver_username)
             resolverFullName = document.createTextNode(data.resolver_firstName+" "+data.resolver_lastName)
             resolverEmail = document.createTextNode(data.resolver_email)
        }
        else {
            resolverText = document.createTextNode("Pending")
            resolverUsername = document.createTextNode("pending");
            resolverFullName = document.createTextNode("pending");
            resolverEmail = document.createTextNode("pending");
        }
        let statusText = document.createTextNode(data.status);
        let receiptsText = document.createTextNode(data.receipts);
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
        author_cell.appendChild(authorText);
        author_username_cell.appendChild(authorUsername)
        author_fullname_cell.appendChild(authorFullName)
        author_email_cell.appendChild(authorEmail)
        resolver_cell.appendChild(resolverText)
        resolver_username_cell.appendChild(resolverUsername)
        resolver_fullname_cell.appendChild(resolverFullName)
        resolver_email_cell.appendChild(resolverEmail)
        resolved_cell.appendChild(resolvedText);
      
       

    //add resolved data
}


async function handleUpdateSubmit() {
    
    console.log("Update submitted")
    console.log("blah")
    let formData = {
        status_id: document.getElementById("select-status").value,
        reimb_id: currentReimb.reimburse_id
    }

    console.log(formData)
    if(formData.reimb_id&&formData.status_id) {
        try {
            let res = await fetch("http://localhost:8080/project-1-Stephen491/updatereimbursements",
            {method: "POST",
            credentials: "include",
            body: JSON.stringify(formData),
            headers:{
                "Content-Type": "application/json"
            }})
            if(res.status==200) {
                
                document.getElementById("feedback-text").innerText = "Reimbursement update has been successfully submitted."
                document.getElementById("feedback").style.setProperty("display", "block");
                document.getElementById("feedback").style.setProperty("background-color", "rgb(50, 168, 84)");
            }
            else {
                document.getElementById("feedback-text").innerText = "An error has occured, please try again."
                document.getElementById("feedback").style.setProperty("display", "block");
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

function filter() {

    var input, filter, table, tr, i;
    input = document.getElementById("searchStatus");
    filter = input.value.toUpperCase();
    table = document.getElementById("reimbursements-table-data");
    tr = table.getElementsByTagName("tr");
    
    for (i = 0; i < tr.length; i++) {
      if (tr[i].getAttribute("status").toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }

}

function openOption() {
    clickedRow = event.target.closest("tr")
    let reimburseInfo = {
        "reimburse_id": clickedRow.getAttribute("reimburse-id"),
        "author": clickedRow.getAttribute("author"),
        "description": clickedRow.getAttribute("description"),
        "amount": clickedRow.getAttribute("amount"),
        "status": clickedRow.getAttribute("status"),
        "type": clickedRow.getAttribute("type")

    }
    toggleShowForm(reimburseInfo)


}


   


