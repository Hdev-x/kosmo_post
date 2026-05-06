const all = document.getElementById("all");
const selectdel = document.getElementById("selectdel")
const list = document.getElementById("list")

getList()

function getList() {
    fetch("./cart-list")
        .then(r => r.text())
        .then(r => {
            r = r.trim()
            list.innerHTML = r
        })
}

selectdel.addEventListener("click", () => {
    let pn = new URLSearchParams()
    let ch = document.querySelectorAll(".ch")
    ch.forEach((c) => {
        if (c.checked) {
            pn.append("productNum", c.getAttribute("data-pn"))
        }
    })

    fetch("./delete", {
        method: "POST",
        body: pn
    })
        .then(r => r.text())
        .then(r => {
            r = r.trim()
            if (r > 0) {
                getList()
                ch.forEach((c) => {
                    if (c.checked) {
                        c.parentElement.parentElement.parentElement.remove()
                    }
                })
            }
        })
})



list.addEventListener("click", (e) => {
    if (e.target.classList.contains("del")) {
        let pn = e.target.previousElementSibling.getAttribute("data-pn")
        let p = new URLSearchParams()
        p.append("productNum", pn)

        fetch("./delete", {
            method: "POST",
            body: p
        })
            .then(r => r.text())
            .then(r => {
                r = r.trim();
                if (r > 0) {
                    getList()
                    e.target.parentElement.parentElement.remove();
                } else {
                    alert('삭제 실패')
                }
            })
    }
    if (e.target.classList.contains("ch")) {
        let chs = document.getElementsByClassName("ch")
        let r = true
        for (let c of chs) {
            if (!c.checked) {
                r = false
            }
        }
        all.checked = r
    }


})




all.addEventListener("click", () => {
    let ch = document.querySelectorAll(".ch");
    ch.forEach((c) => {
        c.checked = all.checked;
    });
})


