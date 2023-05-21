function compute() {
    const paragraph = document.getElementById("paragraph").value;
    const question = document.getElementById("question").value;
    const payload = [paragraph, question];

    fetch('/api/answer', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data.answer);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}