document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('uploadForm');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const fileInput = document.getElementById('excelFile');
        const file = fileInput.files[0];

        if (!file) {
            alert('Please, select an Excel file unless you cannot upload it.');
            return;
        }

        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await fetch('/api/excelupload', {
                method: 'POST',
                body: formData,
            });

            if (response.ok) {
                const result = await response.json();
                document.getElementById('result').innerHTML = `File uploaded successfully: ${result.message}`;
            } else {
                alert('Error uploading file');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});
