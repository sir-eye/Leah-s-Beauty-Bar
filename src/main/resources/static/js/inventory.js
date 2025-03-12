// Sample Household Inventory Data
const inventoryData = [
    { item: "Toilet Paper", location: "Bathroom", quantity: 5 },
    { item: "Canned Beans", location: "Pantry", quantity: 10 },
    { item: "Shampoo", location: "Bathroom", quantity: 2 },
    { item: "Flashlight", location: "Garage", quantity: 1 },
    { item: "Batteries", location: "Storage Room", quantity: 8 },
    { item: "Milk", location: "Refrigerator", quantity: 1 },
    { item: "Bread", location: "Kitchen", quantity: 2 },
    { item: "Laundry Detergent", location: "Laundry Room", quantity: 3 }
];

// Function to Populate Table
function populateInventoryTable() {
    const tableBody = document.querySelector("#inventory-table tbody");

    inventoryData.forEach(item => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${item.item}</td>
            <td>${item.location}</td>
            <td>${item.quantity}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Run function when the page loads
document.addEventListener("DOMContentLoaded", populateInventoryTable);