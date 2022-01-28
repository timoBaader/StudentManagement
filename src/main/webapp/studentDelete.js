var text = "Delete student data?";

// Delete function for studentDelete.html
function getForm() {
	if (confirm(text) == true) {
		var form = document.forms.deleteStudentForm;
		var id = form["studentId"].value;
		var requestParameters = "id=" + id;

		var method = {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded" },
			//headers: { 'Content-Type': 'application/json' },
			body: requestParameters
		};
		var resetFrom = true;
		postDataToServer("http://localhost:8080/WebAppExercises/deleteStudent", processResponseStatus2, method, resetFrom, id);
	}
}


// Delete function for the button on studentListImproved.html
function deleteStudent(id) {
	if (confirm(text) == true) {
		var requestParameters = "id=" + id;

		var method = {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded" },
			//headers: { 'Content-Type': 'application/json' },
			body: requestParameters
		};
		var resetFrom = false;
		postDataToServer("http://localhost:8080/WebAppExercises/deleteStudent", processResponseStatus, method, resetFrom, id);
	}

}

function processResponseStatus(status, resetForm, id) {
	if (status.errorCode === 0) {
		alert("Student data deleted.");
		document.getElementById("rowNr" + id).style.display = "none";
		if (resetForm) {
			document.getElementById("deleteStudentForm").reset();
		}
	} else if (status.errorCode === -1) {
		alert("Student data not deleted. Unknown student id!");
	} else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
}

function processResponseStatus2(status, resetForm, id) {
	if (status.errorCode === 0) {
		alert("Student data deleted.");
		if (resetForm) {
			document.getElementById("deleteStudentForm").reset();
		}
	} else if (status.errorCode === -1) {
		alert("Student data not deleted. Unknown student id!");
	} else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
}