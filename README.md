# 스케줄 관리 웹 만들기
**API 명세서**

<details><summary>
</summary>
  ![image](https://github.com/user-attachments/assets/8fa147e8-fd49-40fa-a2b0-d6e7cb027ef7)
</details>

---
**ERD**

<details><summary>
</summary>
![image](https://github.com/user-attachments/assets/506bf200-7883-4249-b0b9-1d56249ce60b)
</details>

---
**SQL**

```SQL
    CREATE TABLE SCHEDULE (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    USER_ID BIGINT,
    USERNAME NVARCHAR(128) NOT NULL,
    TITLE NVARCHAR(256) NOT NULL,
    CONTENT NVARCHAR(1000) NOT NULL,
    CREATED_AT DATETIME NOT NULL,
    MODIFIED_AT DATETIME NOT NULL,
    CONSTRAINT SCHEDULE_TABLE_PK PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES USER(ID)
);

CREATE TABLE USER (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    USERNAME NVARCHAR(128) NOT NULL,
    EMAIL NVARCHAR(256) NOT NULL,
    CREATED_AT DATETIME NOT NULL,
    MODIFIED_AT DATETIME NOT NULL,
    CONSTRAINT USER_TABLE_PK PRIMARY KEY (ID)
);

ALTER TABLE USER ADD COLUMN PASSWORD NVARCHAR(128) NOT NULL;
ALTER TABLE SCHEDULE ADD COLUMN DELETED NVARCHAR(16) NOT NULL;

CREATE TABLE COMMENT (
    ID BIGINT NOT NULL  AUTO_INCREMENT,
    COMMENT NVARCHAR(256) NOT NULL,
    DELETED NVARCHAR(16) NOT NULL,
    CREATED_AT DATETIME NOT NULL,
    MODIFIED_AT DATETIME NOT NULL,
    USER_ID BIGINT,
    SCHEDULE_ID BIGINT,
    CONSTRAINT COMMENT_TABLE_PK PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES USER(ID),
    FOREIGN KEY (SCHEDULE_ID) REFERENCES SCHEDULE(ID)
);
  ```


---
## **요구 사항 [필수]**

### Lv 0. API 명세 및 ERD 작성   `필수`

**API 명세서 작성하기**
    API명세서는 프로젝트 root(최상위) 경로의 `README.md` 에 작성

**ERD 작성하기**
    ERD는 프로젝트 root(최상위) 경로의 `README.md` 에 첨부
      
**SQL 작성하기**
    설치한 데이터베이스(Mysql)에 ERD를 따라 테이블을 생성.

### Lv 1. 일정 CRUD  `필수`

- 일정을 생성, 조회, 수정, 삭제할 수 있습니다.
- 일정은 아래 필드를 가집니다.
    - `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일` 필드
    - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다. → `3주차 JPA Auditing 참고!`

### Lv 2. 유저 CRUD  `필수`

- 유저를 생성, 조회, 수정, 삭제할 수 있습니다.
- 유저는 아래와 같은 필드를 가집니다.
    - `유저명`, `이메일`, `작성일` , `수정일` 필드
    - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.
- 연관관계 구현
    - 일정은 이제 `작성 유저명` 필드 대신 `유저 고유 식별자` 필드를 가집니다.

### Lv 3. 회원가입  `필수`

- 유저에 `비밀번호` 필드를 추가합니다.
    - 비밀번호 암호화는 도전 기능에서 수행합니다.

### Lv 4. 로그인(인증)  `필수`

- 키워드
    
    **인터페이스**
    
    - HttpServletRequest / HttpServletResponse : 각 HTTP 요청에서 주고받는 값들을 담고 있습니다.
- **설명**
    - **Cookie/Session**을 활용해 로그인 기능을 구현합니다. → `2주차 Servlet Filter 실습 참고!`
    - 필터를 활용해 인증 처리를 할 수 있습니다.
    - `@Configuration` 을 활용해 필터를 등록할 수 있습니다.
- **조건**
    - `이메일`과 `비밀번호`를 활용해 로그인 기능을 구현합니다.
    - 회원가입, 로그인 요청은 인증 처리에서 제외합니다.
- **예외처리**
    - 로그인 시 이메일과 비밀번호가 일치하지 않을 경우 HTTP Status code 401을 반환합니다.

---
## 요구사항 [도전]
### Lv 5. 다양한 예외처리 적용하기  `도전`

- Validation을 활용해 다양한 예외처리를 적용해 봅니다. → `1주차 Bean Validation 참고!`
- 정해진 예외처리 항목이 있는것이 아닌 프로젝트를 분석하고 예외사항을 지정해 봅니다.
    - Ex) 할일 제목은 10글자 이내, 유저명은 4글자 이내
    - `@Pattern`을 사용해서 회원 가입 Email 데이터 검증 등
        - 정규표현식을 적용하되, 정규표현식을 어떻게 쓰는지 몰두하지 말 것!
        - 검색해서 나오는 것을 적용하는 것으로 충분!

### Lv 6. 비밀번호 암호화  `도전`

- Lv.3에서 추가한 `비밀번호` 필드에 들어가는 비밀번호를 암호화합니다.
    - 암호화를 위한 `PasswordEncoder`를 직접 만들어 사용합니다.
        - PasswordEncoder 참고 코드
            1. `build.gradle` 에 아래의 의존성을 추가해주세요.
                
                ```java
                implementation 'at.favre.lib:bcrypt:0.10.2'
                ```
                
            2. `config` 패키지가 없다면 추가하고, 아래의 클래스를 추가해주세요.
                
                ```java
                import at.favre.lib.crypto.bcrypt.BCrypt;
                import org.springframework.stereotype.Component;
                
                @Component
                public class PasswordEncoder {
                
                    public String encode(String rawPassword) {
                        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
                    }
                
                    public boolean matches(String rawPassword, String encodedPassword) {
                        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
                        return result.verified;
                    }
                }
                ```

