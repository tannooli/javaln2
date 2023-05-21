function compute() {
    const paragraph = document.getElementById("paragraph").value;
    const question = document.getElementById("question").value;
    const payload = { paragraph: paragraph, question: question };

    fetch('/api/answer', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(console.log(response))
};