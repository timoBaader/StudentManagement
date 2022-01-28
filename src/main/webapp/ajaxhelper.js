function getDataFromServer(url, callbackFunction) {
	fetch(url)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				alert("getDataFromServer failed: " + "failed to load data.");
				throw "HTTP status code is " + response.status;
			}
		})
		.then(data => callbackFunction(data));		
}

function printStudents(studentList) {
	const tableBody = document.getElementById("tbody");
	for (var student of studentList) {
		var row = tableBody.insertRow();
		
		row.id = "rowNr" + student.id;

		var studentId = row.insertCell(0);
		studentId.innerHTML = student.id;
		var lastName = row.insertCell(1);
		lastName.innerHTML = student.lastName;
		var firstName = row.insertCell(2);
		firstName.innerHTML = student.firstName;
		var street = row.insertCell(3);
		street.innerHTML = student.address;
		var postcode = row.insertCell(4);
		postcode.innerHTML = student.postCode;
		var postOffice = row.insertCell(5);
		postOffice.innerHTML = student.postOffice;

		var updateButton = row.insertCell(6);
		updateButton.innerHTML = '<button type="button" onclick="updateStudent(' + student.id + ')" class="btn btn-outline-primary">Update</button>';

		var deleteButton = row.insertCell(7);
		deleteButton.innerHTML = '<button type="button" onclick="deleteStudent(' + student.id + ')" class="btn btn-outline-danger">Delete</button>';

		console.log(student);
	}
}

function postDataToServer(url, processAddResponse, method, resetForm, id){	
		
	fetch(url, method)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw "HTTP status code is " + response.status;
			}
		})
		.then(status => processAddResponse(status, resetForm, id))
		.catch(errorText => alert("postDataToServer failed: " + errorText));	
}








