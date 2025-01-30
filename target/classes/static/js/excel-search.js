document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("excelSearchForm").addEventListener("submit", async function (event) {
        event.preventDefault();

        const sort = document.getElementById("sort").value;
        const sortDir = document.getElementById("sortDir").value;
        const page = document.getElementById("page").value;
        const size = document.getElementById("size").value;
        const filter = document.getElementById("filter").value;

        const requestBody = {
            filterCriteria: filter
        };

        const response = await fetch("/api/excelsearch?sortDir=" + sortDir + "&sort=" + sort + "&page=" + page + "&size=" + size, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        });

        if (response.ok) {
            const data = await response.json();
            displayResults(data);
        } else {
            console.error("Error fetching data");
        }
    });

    function displayResults(data) {
        const resultsDiv = document.getElementById("results");
        resultsDiv.style.display = 'block';
        resultsDiv.innerHTML = "";

        data.forEach(item => {
            const div = document.createElement("div");
            div.textContent = `File: ${item.fileName}`;
            resultsDiv.appendChild(div);
        });
    }
});
