
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
    clearTableData() 
    await getReimbursementTableData()

    form.style.display = "none";
    goBackButton.style.display = "none";
    
    requestsTable.style.display = "";
   

}


function toggleShowForm(reimburseInfo) {
    let goBackButton = document.getElementById("leave-form-button")
    let requestsTable = document.getElementById("reimbursements-table")
    let search = document.getElementById("searchStatus")
    let searchLabel = document.getElementById("search-label")
    goBackButton.style.display = "block";

    
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
      //  let author_email = newRow.insertCell();
        let resolver_cell = newRow.insertCell();
 //       let resolver_email_cell = newRow.insertCell();
        let status_cell = newRow.insertCell();
        let receipts_cell = newRow.insertCell();

        let idText = document.createTextNode(data.id);
        let amountText = document.createTextNode(data.amount);
        let submittedText = document.createTextNode(data.dateSubmitted)
        let typeText = document.createTextNode(data.type);
        let descriptionText = document.createTextNode(data.description);
        let authorText = document.createTextNode(data.author);
        let resolverText;
        if(data.resolver!=0) {
             resolverText = document.createTextNode(data.resolver)
        }
        else {
            resolverText = document.createTextNode("Pending")
        }
        let statusText = document.createTextNode(data.status);
        let receiptsText = document.createTextNode(data.receipts);
        let resolvedText;
        if(data.resolved) {
            resolvedText = document.createTextNode(data.resolved)
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
        resolver_cell.appendChild(resolverText)
        resolved_cell.appendChild(resolvedText);
      
       

    //add resolved data
}


async function handleFormSubmit(reimbInfo) {

    
    let formData = {
        reimb_id: reimbInfo.reimburse-id,
        status_id: document.getElementById("select-status").value,
    }

    if(formData.reimb_id&&formData.status_id) {
        try {
            let res = await fetch("http://localhost:8080/project-1-Stephen491/updatereimbursements",
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
        "reimburse-id": clickedRow.getAttribute("reimburse-id"),
        "author": clickedRow.getAttribute("author"),
        "description": clickedRow.getAttribute("description"),
        "amount": clickedRow.getAttribute("amount"),
        "status": clickedRow.getAttribute("status"),
        "type": clickedRow.getAttribute("type")

    }
    toggleShowForm(reimburseInfo)


}

