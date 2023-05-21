function compute() {
    fetch('/api/answer', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ paragraph: document.getElementById("paragraph").value, question: document.getElementById("question").value })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data.answer);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}