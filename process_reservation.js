document.addEventListener("DOMContentLoaded", function () {
    const submitBtn = document.getElementById("submitBtn");
    submitBtn.addEventListener("click", saveReservationData);
});

function saveReservationData() {
    const fullName = document.getElementById("fullName").value;
    const flightNumber = document.getElementById("flightNumber").value;
    const departureDate = document.getElementById("departureDate").value;
    const seats = document.getElementById("seats").value;
    const classType = document.getElementById("class").value;

    const xmlString = `
        <reservation>
            <fullName>${fullName}</fullName>
            <flightNumber>${flightNumber}</flightNumber>
            <departureDate>${departureDate}</departureDate>
            <seats>${seats}</seats>
            <class>${classType}</class>
        </reservation>
    `;

    const blob = new Blob([xmlString], { type: "application/xml" });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = "reservation.xml";
    link.click();
}
