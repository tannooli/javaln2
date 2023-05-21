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
    
    .then(response => response.json()).then(data => { 
        document.getElementById("answer").innerHTML = data.answer;
        console.log(data.answer); 
        // Display the answer in the console                 
    }) .catch(error => {console.error('Error:', error);});
}