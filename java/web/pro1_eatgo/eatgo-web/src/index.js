(async ()=> {
    const url = 'http://localhost:8080/res';
    const response = await fetch(url);
    const data = await response.json();
    console.log(data);

    const element = document.getElementById('app');
    element.innerHTML =
        `
            ${data.map(data =>`
                <p>
                    ${data.id}
                    ${data.name}
                    ${data.addr}
                </p>
            `).join('')}
        `;
})();