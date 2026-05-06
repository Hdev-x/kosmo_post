// ==========================================
// 1. DOM 요소 선택
// ==========================================
const reviewBtn = document.getElementById("review_btn");
const reviewContents = document.getElementById("review_contents");
const reviewListArea = document.getElementById("review_list");
const reviewCountSpan = document.getElementById("review_count"); // 배지(숫자) 부분

// 전역 변수: 상품 번호 (버튼의 data-pn 속성에서 가져옴)
const productNum = reviewBtn.getAttribute("data-pn");


// ==========================================
// 2. 기능별 함수 정의
// ==========================================

/**
 * [목록 호출] 서버에서 리뷰 리스트(JSP)를 가져와서 화면에 뿌림
 */
const getReviewList = () => {
    // 1) 호출할 때 반드시 상품번호를 파라미터로 붙임 (null 방지)
    fetch("../review/list?productNum=" + productNum)
        .then(response => response.text())
        .then(html => {
            // 2) 서버에서 받아온 HTML(table)을 review_list 구역에 삽입
            reviewListArea.innerHTML = html;

            // 3) [추가 기능] 리스트 안의 행(tr) 개수를 세서 상단 배지 업데이트
            // list.jsp 안에 <tbody> 내의 <tr> 개수를 찾습니다.
            const rowCount = reviewListArea.querySelectorAll("tbody tr").length;
            reviewCountSpan.innerText = rowCount;
        })
        .catch(error => {
            console.error("리스트 로딩 중 오류:", error);
        });
};

/**
 * [리뷰 등록] 사용자가 입력한 내용을 서버에 저장
 */
const registerReview = () => {
    const contents = reviewContents.value.trim();
    const star = document.getElementById("review_star").value;

    // 유효성 검사
    if (!contents) {
        alert("리뷰 내용을 입력해주세요.");
        reviewContents.focus();
        return;
    }

    // 별점을 선택하지 않았을 경우 체크 (선택 사항)
    if (star == "0") {
        alert("별점을 선택해주세요.");
        return;
    }

    // 전송 데이터 설정
    const params = new URLSearchParams();
    params.append("productNum", productNum);
    params.append("reviewContents", contents); // DTO의 변수명과 동일하게 세팅
    params.append("reviewStar", star);

    // 서버 통신
    fetch("../review/create", {
        method: "POST",
        body: params
    })
    .then(response => response.text())
    .then(result => {
        if (result.trim() == "1") {
            alert("리뷰가 등록되었습니다.");
            reviewContents.value = ""; // 입력창 비우기
            
            // ★ 3. 등록 후 별점 초기화 (다시 0점으로)
            document.getElementById("review_star").value = "0";
            document.querySelectorAll('#star_rating i').forEach(el => {
                el.classList.replace('fas', 'far');
            });

            // ★ 중요: 등록 성공 후 목록을 다시 불러와서 화면 갱신
            getReviewList();
        } else if (result.trim() == "-1") {
            alert("로그인이 필요한 서비스입니다.");
            location.href = "../member/login";
        } else {
            alert("리뷰 등록에 실패했습니다.");
        }
    })
    .catch(error => {
        console.error("등록 중 오류:", error);
        alert("서버 통신 오류가 발생했습니다.");
    });
};


// ==========================================
// 3. 실행 및 이벤트 바인딩
// ==========================================

// 페이지 접속 시 목록 자동 로드
getReviewList();

// 등록 버튼 클릭 시 함수 실행
reviewBtn.addEventListener("click", registerReview);






/**
 * [수정 모드 전환] 텍스트와 별점을 입력창으로 교체
 */
window.editMode = (reviewNum) => {
    const reviewItem = document.getElementById("review_item_" + reviewNum);
    const textZone = document.getElementById("review_text_" + reviewNum);
    const btnZone = document.getElementById("action_btns_" + reviewNum);
    
    // 기존 별점 점수 파악 (fas의 개수)
    const currentStar = reviewItem.querySelectorAll(".review-star .fas.fa-star").length;
    const originalContent = textZone.innerText.trim();

    // 1) 별점 영역을 클릭 가능한 수정용 별점으로 교체
    const starZone = reviewItem.querySelector(".review-star");
    let starHtml = `<div class="edit-star-rating h4 text-warning" data-review-num="${reviewNum}" style="cursor: pointer;">`;
    for (let i = 1; i <= 5; i++) {
        starHtml += `<i class="${i <= currentStar ? 'fas' : 'far'} fa-star" data-value="${i}"></i>`;
    }
    starHtml += `<input type="hidden" id="edit_star_${reviewNum}" value="${currentStar}"></div>`;
    starZone.innerHTML = starHtml;

    // 2) 내용 영역 교체
    textZone.innerHTML = `
        <textarea id="edit_contents_${reviewNum}" class="form-control mb-2" rows="3" style="resize:none;">${originalContent}</textarea>
    `;

    // 3) 버튼 영역 교체
    btnZone.innerHTML = `
        <button type="button" class="btn btn-sm btn-success mr-1" onclick="updateReview(${reviewNum})">저장</button>
        <button type="button" class="btn btn-sm btn-secondary" onclick="getReviewList()">취소</button>
    `;
};

/**
 * [리뷰 수정 실행]
 */
window.updateReview = (reviewNum) => {
    const newContent = document.getElementById("edit_contents_" + reviewNum).value.trim();
    const newStar = document.getElementById("edit_star_" + reviewNum).value;

    if (!newContent) {
        alert("수정할 내용을 입력해주세요.");
        return;
    }

    const params = new URLSearchParams();
    params.append("reviewNum", reviewNum);
    params.append("reviewContents", newContent);
    params.append("reviewStar", newStar); 

    fetch("../review/update", {
        method: "POST",
        body: params
    })
    .then(response => response.text())
    .then(result => {
        if (result.trim() == "1") {
            alert("리뷰가 수정되었습니다.");
            getReviewList(); 
        } else {
            alert("수정 실패");
        }
    })
    .catch(error => console.error("수정 중 오류:", error));
};

/**
 * [이벤트 위임] 수정 모드 내 별점 클릭 처리
 */
document.addEventListener("click", (e) => {
    // 수정 모드의 별을 클릭했을 때만 작동
    const starIcon = e.target.closest(".edit-star-rating i");
    if (starIcon) {
        const parent = starIcon.parentElement;
        const score = starIcon.getAttribute("data-value");
        const reviewNum = parent.getAttribute("data-review-num");
        
        // Hidden input 값 변경
        document.getElementById("edit_star_" + reviewNum).value = score;
        
        // 별 아이콘 색상 변경
        const stars = parent.querySelectorAll("i");
        stars.forEach((s, idx) => {
            if (idx < score) {
                s.classList.replace('far', 'fas');
            } else {
                s.classList.replace('fas', 'far');
            }
        });
    }
});

/**
 * [리뷰 삭제 실행]
 */
window.deleteReview = (reviewNum) => {
    if(!confirm("정말 이 리뷰를 삭제하시겠습니까?")) return;

    fetch("../review/delete?reviewNum=" + reviewNum, { method: "POST" })
    .then(response => response.text())
    .then(result => {
        if (result.trim() == "1") {
            alert("삭제되었습니다.");
            getReviewList();
        } else {
            alert("삭제 실패");
        }
    });
};