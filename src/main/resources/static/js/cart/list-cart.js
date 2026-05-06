document.querySelectorAll('.add-to-cart').forEach(btn => {
    btn.addEventListener('click', function(e) {
        // 부모 요소(a 태그)로 이벤트가 퍼지는 것을 막음 (상세페이지 이동 방지)
        e.preventDefault();
        e.stopPropagation();

        const productNum = this.getAttribute('data-pn');
        
        // 기존에 만들어둔 장바구니 담기 fetch 로직 호출
        let p = new URLSearchParams();
        p.append("productNum", productNum);

        fetch("/cart/create", { // 장바구니 추가 URL (본인의 설정에 맞게 수정)
            method: "POST",
            body: p
        })
        .then(r => r.text())
        .then(r => {
            if(r.trim() > 0) {
                if(confirm("장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?")) {
                    location.href = "/cart/list";
                }
            } else {
                alert("장바구니 담기 실패");
            }
        });
    });
});