const QueryString = window.location.search;
const urlParams = new URLSearchParams(QueryString);
var id = urlParams.get("id");

function main() {
	var requestParameters = "id=" + id;

	var method = {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		//headers: { 'Content-Type': 'application/json' },
		body: requestParameters
	};

	// Returns the student data 
	fetch("http://localhost:8080/WebAppExercises/studentfetch", method)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				alert("getDataFromServer failed: " + "failed to load data.");
				throw "HTTP status code is " + response.status;
			}
		})
		.then(data => populateForm(data));
}


function populateForm(student) {
	document.getElementById("studentId").value = student.id;
	document.getElementById("firstName").value = student.firstName;
	document.getElementById("lastName").value = student.lastName;
	document.getElementById("address").value = student.address;
	document.getElementById("postCode").value = student.postCode;
	document.getElementById("postOffice").value = student.postOffice;
}

main();

function updateStudent() {

	var form = document.forms.updateStudentForm;
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

	// Returns the student data 
	fetch("http://localhost:8080/WebAppExercises/updateStudent", method)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				alert("getDataFromServer failed: " + "failed to load data.");
				throw "HTTP status code is " + response.status;
			}
		}).then(status => processResponseStatus(status));
}

function processResponseStatus(status) { 
	if (status.errorCode === 0) {
		alert("Student updated.");
		location.href = 'http://localhost:8080/WebAppExercises/studentListImproved.html'
	} else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
}













