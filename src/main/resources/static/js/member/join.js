/**
 * [회원가입 유효성 검사 - 기초문법 버전 (for...of, if만 사용)]
 * * 작성 목적: 자바/스프링 개발자를 위한 JS 동작 원리 이해
 * 핵심 개념: DOM 제어, 이벤트 리스너, 클로저, 비동기적 사고
 */

// 1. IIFE (Immediately Invoked Function Expression): 즉시 실행 함수
// 자바의 private class나 패키지 스코프처럼, 변수들이 외부(다른 js파일)와 충돌하지 않도록 격리시킨 공간입니다.
(function() {
    // 'use strict': 엄격 모드 활성화. 자바처럼 변수 선언 누락 등을 에러로 잡아줍니다.
    'use strict';

    /* -------------------------------------------------------------------------
     * [STEP 1] DOM(Document Object Model) 요소 상수화
     * HTML 태그들을 자바의 객체처럼 가져오는 과정입니다. (Spring의 DI/Bean 주입과 비슷한 맥락)
     * ------------------------------------------------------------------------- */

    // document: 웹 페이지 문서 전체를 담고 있는 내장 객체
    // getElementById: ID값을 식별자로 특정 태그 객체를 리턴함
    const joinForm = document.getElementById("joinForm");
    const username = document.getElementById("username");
    const password = document.getElementById("password");
    const passwordCheck = document.getElementById("passwordCheck");

    // 결과 메시지를 출력할 공간 (div/span 태그들)
    const usernameResult = document.getElementById("usernameResult");
    const passwordResult = document.getElementById("passwordResult");
    const passwordCheckResult = document.getElementById("passwordCheckResult");

    /* -------------------------------------------------------------------------
     * [STEP 2] 상태 관리 객체 (State Management)
     * 자바의 DTO나 Map처럼 현재 검증 상태를 boolean값으로 저장해둡니다.
     * 이 객체의 값이 모두 true여야 최종 제출(Submit)이 가능하게 설계합니다.
     * ------------------------------------------------------------------------- */
    const status = {
        isIdChecked: false,
        isPwChecked: false,
        isPwMatch: false
    };

    /* -------------------------------------------------------------------------
     * [STEP 3] 유효성 검사 로직 (Validation Logic) - 함수화
     * 자바의 '메서드'를 만드는 과정입니다. 필요할 때마다 재사용하기 위해 분리합니다.
     * (화살표 함수 → function 키워드로 변경)
     * ------------------------------------------------------------------------- */

    // 아이디 검사 함수
    function checkUsername() {
        // .value: input창에 입력된 값 (Getter)
        // .trim(): 자바의 trim()과 동일. 앞뒤 공백 제거
        const val = username.value.trim();

        // val이 비어있다면 (JS에서는 빈 문자열이 false로 취급됨)
        if (!val) {
            usernameResult.textContent = "아이디를 입력해주세요."; // .textContent: 태그 안에 글자 쓰기
            usernameResult.style.color = "red"; // .style.color: CSS 색상 직접 변경
            status.isIdChecked = false;
        } else {
            // [현업 포인트] 실제론 여기서 Regex(정규식)를 사용하여 영문/숫자 조합 등을 체크함
            usernameResult.textContent = "사용 가능한 아이디입니다.";
            usernameResult.style.color = "green";
            status.isIdChecked = true;
        }
    }

    // 비밀번호 길이 검사 함수
    function checkPassword() {
        const val = password.value;

        if (val.length < 6) {
            passwordResult.textContent = "비밀번호는 6자 이상이어야 합니다.";
            passwordResult.style.color = "red";
            status.isPwChecked = false;
        } else {
            passwordResult.textContent = "사용 가능한 비밀번호입니다.";
            passwordResult.style.color = "green";
            status.isPwChecked = true;
        }
        // [중요] 비밀번호를 수정할 때마다 '비밀번호 확인' 칸과의 일치 여부도 실시간 재검사
        checkPasswordMatch();
    }

    // 비밀번호 일치 여부 검사 함수
    function checkPasswordMatch() {
        const p1 = password.value;
        const p2 = passwordCheck.value;

        // 확인 칸이 비어있을 때
        if (!p2) {
            passwordCheckResult.textContent = "비밀번호 확인을 입력해주세요.";
            passwordCheckResult.style.color = "red";
            status.isPwMatch = false;
        } else if (p1 === p2) {
            // 일치할 때
            passwordCheckResult.textContent = "비밀번호가 일치합니다.";
            passwordCheckResult.style.color = "green";
            status.isPwMatch = true;
        } else {
            // 다를 때
            passwordCheckResult.textContent = "비밀번호가 일치하지 않습니다.";
            passwordCheckResult.style.color = "red";
            status.isPwMatch = false;
        }
    }

    /* -------------------------------------------------------------------------
     * [STEP 4] 이벤트 리스너(Event Listener) 등록
     * "언제" 로직을 실행할지 브라우저에 감시 위임을 하는 과정입니다.
     * ------------------------------------------------------------------------- */

    // blur: 포커스가 나갈 때 (입력을 마치고 다른 곳을 클릭할 때)
    username.addEventListener('blur', checkUsername);
    password.addEventListener('blur', checkPassword);

    // input: 키보드로 글자를 입력할 때마다 '즉시' (실시간 반응형 UI)
    passwordCheck.addEventListener('input', checkPasswordMatch);

    // 추가 요구사항: 일치하지 않은 상태로 포커스가 나갔을 때만 입력창 지우기
    passwordCheck.addEventListener('blur', function() {
        if (!status.isPwMatch && passwordCheck.value !== "") {
            passwordCheck.value = ""; // 입력창 비우기 (Setter)
            passwordCheckResult.textContent = "일치하지 않아 입력값이 초기화되었습니다.";
        }
    });

    /* -------------------------------------------------------------------------
     * [STEP 5] 최종 제출 제어 (Form Submission)
     * 스프링부트 컨트롤러(@PostMapping)로 데이터를 보내기 전 최종 게이트 키퍼 역할입니다.
     * (forEach → for...of로 변경)
     * ------------------------------------------------------------------------- */

    // joinForm의 submit 이벤트가 발생했을 때 (버튼 클릭 혹은 엔터 키 입력 시)
    joinForm.onsubmit = function(e) {
        // e.preventDefault(): HTML 폼의 기본 동작(페이지 새로고침/이동)을 중단시킴.
        // 유효성 검사가 완료될 때까지 서버로의 전송을 잠시 멈추는 핵심 코드입니다.
        e.preventDefault();

        // required 속성이 있는 모든 input 태그를 배열(NodeList)로 가져옴
        const requiredInputs = joinForm.querySelectorAll('input[required]');
        let isAllFilled = true;

        // 자바의 for-each문과 동일 (for...of 사용)
        for (const input of requiredInputs) {
            if (!input.value.trim()) {
                isAllFilled = false;
            }
        }

        // 검증 시나리오에 따른 최종 분기 처리 (Early Exit 패턴)
        if (!isAllFilled) {
            alert("모든 필수 항목을 채워주세요.");
        } else if (!status.isPwChecked) {
            alert("비밀번호 형식을 확인해주세요.");
            password.focus(); // 해당 칸으로 마우스 커서 이동
        } else if (!status.isPwMatch) {
            alert("비밀번호가 서로 일치하지 않습니다.");
            passwordCheck.focus();
        } else {
            // 모든 검증 통과 (자바의 모든 비즈니스 로직 성공 상황)
            if (confirm("회원가입을 진행하시겠습니까?")) {
                // 검증이 완벽하므로 폼을 실제로 제출함 (스프링 컨트롤러 호출)
                this.submit();
            }
        }
    };

    // [현업 팁] JSP 버튼이 type="button"일 경우를 대비해,
    // 해당 버튼 클릭 시 form 객체에 'submit' 이벤트를 강제로 발생(Dispatch)시킵니다.
    document.getElementById("joinBtn").onclick = function() {
        joinForm.dispatchEvent(new Event('submit'));
    };

})(); // IIFE 종료