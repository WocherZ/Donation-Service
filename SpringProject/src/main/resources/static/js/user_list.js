async function getUsers() {
    await fetch('/api/users').then(
        response => {
            if (response.status == 200) {
                return response.json()
            }
            else {
                throw new Error('Ошибка получения пользователей')
            }
        }
    )
}


window.addEventListener('load', function () {
    OverlayScrollbars(document.getElementById('user-list'), {
        className       : "os-theme-dark",
        sizeAutoCapable : true,
        paddingAbsolute : true,
        scrollbars : {
            clickScrolling : true
        }
    });
    let users = document.getElementById('user-list')
    users.innerHTML = ''
    users.children = null
    getUsers().then(
        us => {
            let search = document.getElementById('search')
            for (let u of us) {
                let li = document.createElement('li')
                li.innerHTML = `<li class="user-list"><a href="user/${u['id']}">${u['nickname']}</a></li> `
                users.appendChild(li)
            }
            search.addEventListener('change', setTimeout(
                function () {
                    users.innerHTML = ''
                    users.children = null
                    for (let u of us) {
                        if (u['nickname'].toLowerCase().includes(search.value.toLowerCase())) {
                            let li = document.createElement('li')
                            li.innerHTML = `<li class="user-list"><a href="user/${u['id']}">${u['nickname']}</a></li> `
                            users.appendChild(li)
                        }
                    }
                }, 500
            ))
        }
    )

})
