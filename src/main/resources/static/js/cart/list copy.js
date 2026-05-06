const all = document.getElementById("all");
const ch = document.querySelectorAll(".ch")//document.getElementsByClassName("ch");
const del = document.querySelectorAll(".del");
const selectdel = document.getElementById("selectdel")
const list = document.getElementById("list")

getList()

function getList(){
    fetch("./cart-list")
    .then(r=>r.text())
    .then(r=>{
        r=r.trim()
        list.innerHTML=r
    })
}

selectdel.addEventListener("click",()=>{
    let pn = new URLSearchParams()
    ch.forEach((c)=>{
        if(c.checked){
            pn.append("productNum", c.getAttribute("data-pn"))
        }
    })

    fetch("./delete", {
        method:"POST",
        body:pn
    })
    .then(r=>r.text())
    .then(r=>{
        r=r.trim()
        if(r>0){
            ch.forEach((c)=>{
                if(c.checked){
                    c.parentElement.parentElement.parentElement.remove()
                }
            })
        }
    })
})


for(let d of del){
    
    d.addEventListener("click", ()=>{
        let pn = d.previousElementSibling.getAttribute("data-pn")
        let p = new URLSearchParams();
        p.append("productNum", pn);

        fetch("./delete", {
            method:"POST",
            body:p
        })
        .then(r=>r.text())
        .then(r=>{
            r = r.trim();
            if(r>0){
               d.parentElement.parentElement.remove(); 
            }else {
                alert('삭제 실패')
            }
        })
    })
}

all.addEventListener("click", ()=>{
    ch.forEach((c)=>{
        c.checked=all.checked;
    });
})

ch.forEach(c=>{
    c.addEventListener("click", ()=>{
        let r=true;
        ch.forEach((e)=>{
            if(!e.checked){
                r=false;
            }
        })

        all.checked=r;

    })
})