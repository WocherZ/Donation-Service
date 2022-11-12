async function regUser(login, nickname, password) {
    const request = await fetch('/api/registration', {
        method: 'POST',
        body: JSON.stringify({login: login, password: password, nickname: nickname}),
        headers: {
            'Content-Type': 'application/json',
            'charset': 'utf-8',
        },
    }).then (
        response => {
            if (response.status === 200) {
                return response.json()
            }
            else {
                throw new Error("Ошибка регистрации")
            }
        }
    )
}

window.onload = function () {
    const form = document.getElementById('regform')
    form.addEventListener('submit', function (e) {
        e.preventDefault()
        const login = form.elements.log_in.value
        const password = form.elements.pass_word.value
        const nickname = form.elements.nick_name.value
        console.log(login, password, nickname)
        regUser(login, password, nickname).then(
            function(value) {
                console.log(value)
                window.location.href = '/login'
            },
            function(error) {
                alert("My error!!!")
            }
        )
    })
}