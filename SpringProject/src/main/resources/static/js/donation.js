
async function sendDonation(key, nickname, msg, amount) {
    const request = await fetch(`/api/donate/${key}`, {
        method: 'POST',
        body: JSON.stringify({userNickName: nickname, text: msg, amount: amount}),
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
                throw new Error("Ошибка при отправке доната")
            }
        }
    )
}

window.addEventListener('load', function() {
    const form = document.getElementById('donate-form')

    form.addEventListener('submit', function(e) {
        e.preventDefault()
        let nickname = form.elements.nick_name.value
        let msg = form.elements.msg.value
        let amount = form.elements.money.value
        let pay_choice = form.elements.choice.textContent
        const url = document.location.href
        let key = _.last(url.split('/'))
        if (nickname != '' && sum != '' && pay_choice != 'Способ оплаты') {
            sendDonation(key, nickname, msg, amount).then(
                function(value) {
                    alert("Донат отправлен успешно")
                },
                function(error) {
                    alert(error)
                }
            )
        }
    })
})