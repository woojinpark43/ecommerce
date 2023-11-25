# ecommerce

상품을 자유롭게 살수 있는 사용자들을 위한 e commerce 플랫폼 백엔드 서버

## ERD
![image](https://github.com/woojinpark43/ecommerce/assets/23221982/5b7c5e11-d063-4a77-a522-435f68e33bbc)



## 프로젝트 기능 및 설계
- 회원가입 기능
    - 사용자는 회원가입을 할 수 있다. 일반적으로 모든 사용자는 회원가입시 USER 권한 (일반 권한)을 지닌다.
    - 회원가입시 아이디와 패스워드를 입력받으며, 아이디는 unique 해야한다.

- 로그인 기능
    - 사용자는 로그인을 할 수 있다. 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야한다.

- 상품명 검색 기능
    - 로그인하지 않은 사용자를 포함한 모든 사용자는 상품명을 조회할 수 있다.
    - 상품을 검색하면 상품 정보 및 사진들을 요약한 리스트를 볼수있다.
    - 요약 정보들은 상품명, 사진, 내고 외 별점등과 같은 정보들이다.

- 상품 담기
    - 로그인하지 않은 사용자는 상품을 담을 수 없다.
    - 상품 담기에 담을 상품명, 개수를 담는다.
    - 상품 담기가 많을 수 있기 때문에 별도의 API로 구성한다.

- 장바구니 조회
    - 로그인하지 않은 사용자는 장바구니를 조회할 수 없다.
    - 로그인된 사용자가 담은 모든 상품을 볼 수 있다.
    - 상품 담기에 쓰이는 별도의 API를 사용한다.

- 장바구니 상품 삭제
    - 로그인하지 않은 사용자는 상품을 조회할 수 없다.
    - 로그인된 사용자가 담은 모든 상품을 삭제 할 수 있다.
    - 상품 담기에 쓰이는 별도의 API를 사용한다.

- 결제 하기
    - 장바구니에 담긴 상품을 구매할 수 있다.
    - 결제가 많을 수 있기 때문에 별도의 API로 구성한다.

- 결제 취소
    - 결제 내역을 취소할 수 있다.


## Trouble Shooting



### Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>
