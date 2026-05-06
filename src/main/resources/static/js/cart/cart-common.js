// 장바구니 담기 공통 화살표 함수
const addCart = (productNum) => {
    const p = new URLSearchParams();
    p.append("productNum", productNum);

    fetch("/cart/create", {
        method: "POST",
        body: p
    })
    .then(r => r.text())
    .then(r => {
        const result = r.trim();
        if (result === "1") {
            if (confirm("장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?")) {
                location.href = "/cart/list";
            }
        } else if (result === "2") {
            if (confirm("이미 장바구니에 있는 상품입니다. 장바구니로 이동하시겠습니까?")) {
                location.href = "/cart/list";
            }
        } else if (result === "-1") {
            alert("로그인이 필요한 서비스입니다.");
            location.href = "/member/login";
        } else {
            alert("장바구니 등록에 실패했습니다.");
        }
    })
    .catch(err => console.error("Error:", err));
};