function main() {
	fetch("http://localhost:8080/WebAppExercises/students")
	.then(response => response.json())
	.then(studentList => printStudents(studentList));
}

function printStudents(studentList) {
	const tableBody = document.getElementById("tbody");
	for(var student of studentList){
		var row = tableBody.insertRow();
		
		var	studentId = row.insertCell(0);
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
		
		var deleteButton = row.insertCell(6);
		deleteButton.innerHTML = '<button type="button" class="btn btn-outline-primary">Update</button>';	
		var deleteButton = row.insertCell(7);
		deleteButton.innerHTML = '<button type="button" class="btn btn-outline-danger">Delete</button>';
		
		console.log(student);
	}
}

main();

// CONTROL F5 DELETES CACHE WHILE RELOADING