console.log("form js");

// ============================================================================
// [STEP 1] DOM 요소 선택 (Spring의 @Autowired처럼 필요한 요소를 주입받는 과정)
// ============================================================================
const add = document.getElementById("add");           // "add" 버튼 선택
const result = document.getElementById("result");     // 파일이 들어갈 영역 선택

// ============================================================================
// [STEP 2] 상태 관리 변수 (자바의 counter 변수처럼 추적용)
// ============================================================================
let idx = 1;  // 파일 input마다 고유한 id를 부여하기 위한 카운터 (id1, id2, id3...)
let count=0;
// ============================================================================
// [STEP 3] "add" 버튼 클릭 이벤트 (사용자 액션 감지)
// ============================================================================
add.addEventListener("click", function () {

    if (count >= 5) {
        alert("더이상 추가할 수 없습니다.");
        return;
    }

    // ========== [3-1] 파일 input을 감싸줄 div 생성 ==========
    let d = document.createElement("div");
    d.id = `id${idx}`;  // div에 고유 id 부여 (나중에 삭제할 때 사용)

    // ========== [3-2] file input 생성 ==========
    let i = document.createElement("input");  // <input> 태그 생성

    // ========== [3-3] input의 속성들을 설정 (HTML의 속성을 JS로 동적 추가) ==========
    i.type = "file";                          // type="file" (파일 선택용)
    i.name = "attach";                        // name="attach" (서버 전송 시 파라미터 이름)
    i.className = "form-control";             // Bootstrap CSS 클래스 추가

    // ========== [3-4] div 안에 input 넣기 ==========
    d.append(i);  // 자바의 list.add()처럼 요소 추가

    // ========== [3-5] 삭제 버튼 생성 ==========
    let b = document.createElement("button");  // <button> 태그 생성

    // ========== [3-6] 버튼의 속성들 설정 ==========
    b.type = "button"                          // type="button" (폼 제출 방지)
    b.innerText = "삭제";                      // 버튼에 표시될 텍스트
    b.setAttribute("data-id", `id${idx}`)      // data-id="id1" 등 (삭제할 div의 id 저장)

    // ========== [3-7] 버튼에 CSS 클래스 추가 (클릭 감지용) ==========
    b.classList.add("del")  // class="del" 추가 (삭제 버튼이라고 표시)

    // ========== [3-8] div 안에 버튼 넣기 ==========
    d.append(b);  // div 안에 버튼 추가

    // ========== [3-9] result 영역에 완성된 div 추가 (prepend = 맨 위에 추가) ==========
    result.prepend(d);  // append는 맨 뒤, prepend는 맨 앞에 추가
    // 최신 파일이 맨 위에 보이도록 함

    // ========== [3-10] 다음 파일을 위해 idx 증가 ==========
    idx++; 
    count++; // 1 → 2 → 3... 계속 증가해서 고유한 id 생성
});

// ============================================================================
// [STEP 4] result 영역 내부 버튼 클릭 이벤트 (이벤트 위임 패턴)
// ============================================================================
// ※ 중요: result 내부의 모든 버튼 클릭을 감지함
//    (동적으로 생성된 버튼도 자동으로 감지 - 자바의 리스너 패턴과 유사)
result.addEventListener("click", function (e) {
    // ========== [4-1] 클릭 대상이 "del" 클래스를 가진 버튼인지 확인 ==========
    if (e.target.classList.contains("del")) {
        // e.target: 실제로 클릭된 요소 (버튼)
        // classList.contains("del"): 그 요소가 "del" 클래스를 가졌는지 확인 (자바의 instanceof 같은 개념)

        // ========== [4-2] 버튼의 부모(div)를 삭제 ==========
        e.target.parentElement.remove();
        count--;
        // parentElement: 부모 요소 (버튼을 감싸고 있는 div)
        // remove(): 그 div를 문서에서 제거 (자바의 list.remove() 같은 개념)
    }
})