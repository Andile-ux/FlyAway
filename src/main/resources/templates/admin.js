document.addEventListener("DOMContentLoaded", function () {
    const adminLoginForm = document.getElementById("admin-login-form");
    const flightManagementSection = document.getElementById("flight-management-section");
    const flightAddForm = document.getElementById("flight-add-form");
    const flightList = document.getElementById("flight-list");

    adminLoginForm.addEventListener("submit", function (e) {
        e.preventDefault();

        const username = document.getElementById("admin").value;
        const password = document.getElementById("admin@Flyaway").value;

        // Make an AJAX request to the server for admin authentication
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/admin-auth", true); // Replace with the actual server endpoint
        xhr.setRequestHeader("Content-Type", "application/json");

        const data = {
            username: username,
            password: password,
        };

        xhr.onload = function () {
            if (xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);

                if (response.authenticated) {
                    showFlightManagementSection();
                    loadFlightList();
                } else {
                    alert("Admin authentication failed. Please check your username and password.");
                }
            } else {
                console.error("Admin authentication error.");
            }
        };

        xhr.send(JSON.stringify(data));
    });

    flightAddForm.addEventListener("submit", function (e) {
        e.preventDefault();
        const source = document.getElementById("source").value;
        const destination = document.getElementById("destination").value;
        const airline = document.getElementById("airline").value;
        const ticketPrice = document.getElementById("ticket-price").value;

        const newFlight = {
            source,
            destination,
            airline,
            ticketPrice,
        };

        // Make an AJAX request to add the flight
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/add-flight", true); // Replace with the actual server endpoint
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onload = function () {
            if (xhr.status === 200) {
                const addedFlight = JSON.parse(xhr.responseText);
                updateFlightList(addedFlight);
                clearFlightForm();
            } else {
                console.error("Error adding flight");
            }
        };

        xhr.send(JSON.stringify(newFlight));
    });

    function showFlightManagementSection() {
        flightManagementSection.style.display = "block";
        adminLoginForm.style.display = "none";
    }

    function loadFlightList() {
        // Make an AJAX request to load flight data (omitted for simplicity)
    }

    function updateFlightList(flight) {
        const flightItem = document.createElement("div");
        flightItem.innerHTML = `
            <p><strong>Source:</strong> ${flight.source}</p>
            <p><strong>Destination:</strong> ${flight.destination}</p>
            <p><strong>Airline:</strong> ${flight.airline}</p>
            <p><strong>Ticket Price:</strong> ${flight.ticketPrice}</p>
            <hr>
        `;
        flightList.appendChild(flightItem);
    }

    function clearFlightForm() {
        document.getElementById("source").value = "";
        document.getElementById("destination").value = "";
        document.getElementById("airline").value = "";
        document.getElementById("ticket-price").value = "";
    }
});
