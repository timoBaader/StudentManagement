function getForm() {
	var form = document.forms.addStudentForm;
	var requestParameters = "id=" + form["studentId"].value +
		"&firstName=" + form["firstName"].value +
		"&lastName=" + form["lastName"].value +
		"&address=" + form["address"].value +
		"&postCode=" + form["postCode"].value +
		"&postOffice=" + form["postOffice"].value;
		
		var method = {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		//headers: { 'Content-Type': 'application/json' },
		body: requestParameters
	};

	var resetFrom = true;
	//postMethod(requestParameters);
	postDataToServer("http://localhost:8080/WebAppExercises/addStudent", processResponseStatus, method, resetFrom);
}

function processResponseStatus(status, resetForm) { 
	if (status.errorCode === 0) {
		alert("Student added.");
		if(resetForm)
		{
			document.getElementById("addStudentForm").reset();
		}
	} else if (status.errorCode === 1) {
		alert("Cannot add student. The id is already in use!");
	} else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
}
