const create = document.getElementById("create");

create.addEventListener("click", () => {
    let pn = create.getAttribute("data-pn");
    
    let p = new URLSearchParams();
    p.append("productNum", pn);

    fetch("../cart/create", {
        method: "POST",
        body: p
    })
    .then(r => r.text())
    .then(r => {
        r = r.trim();
        
        if (r == 1) { // 1: 신규 등록 성공
            if (confirm("장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?")) {
                location.href = "../cart/list";
            }
        } else if (r == 2) { // 2: 이미 담긴 상품 (중복)
            if (confirm("이미 장바구니에 있는 상품입니다. 장바구니로 이동하시겠습니까?")) {
                location.href = "../cart/list";
            }
        } else if (r == -1) { // -1: 로그인 필요
            alert("로그인이 필요한 서비스입니다.");
            location.href = "../member/login";
        } else { // 0: 기타 DB 에러 등
            alert("장바구니 등록에 실패했습니다.");
        }
    })
    .catch(err => {
        console.error("Error:", err);
        alert("서버 통신 중 오류가 발생했습니다.");
    });
});