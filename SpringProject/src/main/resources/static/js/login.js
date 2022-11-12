
async function logUser(login, password) {
    const request = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify({login: login, password: password}),
        headers: {
            'Content-Type': 'application/json',
            'charset': 'utf-8',
        },
    })
    const json = await request.json()
    return json
}

window.onload = function () {
    const form = document.getElementById('logform')
    form.addEventListener('submit', function (e) {
        e.preventDefault()
        const login = form.elements.user_login.value
        const password = form.elements.pasword.value
        console.log(login, password)
        logUser(login, password).then(
            value => {
                console.log(value)
                if (value != '') {
                    document.cookie = `id=${value['id']}`
                    window.location.href = '/profile'
                }
                else {
                    alert("Ошибка входа, попробуйте еще")
                }
            }
        ).catch(
            v => {
                alert('Ошибка входа. Попробуйте еще')
            }
        )
    })
}