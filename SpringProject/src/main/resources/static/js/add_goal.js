async function addGoal(id, ) {
    await fetch(`/api/user/${id}/goals`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'charset': 'utf-8',
        },
        body: JSON.stringify({})
    })
}

