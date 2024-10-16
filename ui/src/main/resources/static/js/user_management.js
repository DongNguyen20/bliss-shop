function openModal() {
    document.getElementById("addUserModal").style.display = "flex";
}

function closeModal() {
    document.getElementById("addUserModal").style.display = "none";
}

document.getElementById("addUserForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    // Display the entered values (for demo purposes)
    alert("User added: " + username + " (" + email + ")");
    // Close the modal
    closeModal();
    // Optionally, clear the form fields
    document.getElementById("addUserForm").reset();
});
