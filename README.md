# CASH BOOK JSP MODEL2
-개인의 가계부를 관리하는 웹 애플리케이션입니다. 사용자는 자신의 수입과 지출 내역을 기록하고, 이를 기반으로 가계부를 관리할 수 있습니다. 또한, 회원가입 및 로그인 기능을 통해 여러 사용자가 각자 관리할 수 있도록 구현되었습니다.
## ERD 모델
![image](https://github.com/user-attachments/assets/9a5374ff-1c0a-4fc3-a3ec-91f9b7b04312)
- JSP Model2 MVC 기반을 토대로 로직을 짜면서 outId 추가 후 회원 탈퇴시 같은 이메일로 재가입 불가능 추가

## 이전 버전과의 차이점
이 프로젝트는 cashbook_mvc1(Model1)<https://github.com/LYUN555/cashbook_mvc1.git> 구조를 기반으로 개인 공부하여 MVC2(Model2) 구조로 마이그레이션한 결과물입니다.

#### 주요 변화:
- **비즈니스 로직 분리**: 기존에는 JSP에서 데이터 처리와 UI를 모두 관리했지만, 이번 프로젝트에서는 서블릿을 도입하여 비즈니스 로직을 처리하도록 구조를 개선했습니다.
- **UI와 로직의 역할 분리**: JSP는 View 역할에만 집중하며, 동적 데이터는 서블릿에서 처리 후 전달됩니다.
- **코드 재사용성 강화**: DAO 패턴을 도입하여 데이터베이스 접근 로직을 별도로 관리합니다.
- **jsp model2 MVC** 아키텍처를 따라서 구현하였습니다.
- **서블릿과 jsp**의 역할을 명확하게 분리해서 유지보수가 용이하도록 설계를 짰습니다.

## 기술 스택
- **언어**: Java 17
- **웹 기술**: JSP, JSTL, Servlet, CSS
- **데이터베이스**: MySQL
- **프레임워크**: JSP Servlet

## 주요 기능
- **수입/지출 관리**: 일자별로 사용자가 자신의 수입과 지출 내역을 기록.
- **회원 기능**: 회원가입, 로그인, 로그아웃 및 회원 탈퇴.
- **공지사항**: 관리자 또는 시스템이 제공하는 공지사항을 사용자에게 표시.
- **페이징 처리**: 공지사항과 캐시에서 데이터가 많을 경우 페이징으로 처리.

## 차후 개선해야 할 점
- 영수증 이미지 관리: 파일 업로드 및 관리를 추가
- 공지사항에 파일 업로드 기능: 공지사항에 파일 업로드 및 다운로드를 추가
- 비밀번호 암호화: 로그인 시 사용자의 비밀번호는 SHA-256으로 암호화.

### 느낀점
- 비즈니스 로직과 뷰의 역할을 분리하면서 서블릿을 사용해 비즈니스 로직을 처리하고, JSP는 오로지 사용자에게 보여지는 View를 담당하게 됨으로써 코드의 유지보수성과 가독성이 크게 향상되었다는 점을 느꼈습니다.
- MVC1에서는 각 페이지마다 중복된 코드가 많아지고 로직이 많아지면서 관리하는 것에 복잡함을 느꼈지만 MVC2 서블릿에서 비지니스 코드에 집중적으로 처리하여 관리가 굉장히 용이해졌습니다.
- MVC1에서 MVC2 로 마이그레이션 하는 과정에서 서블릿과 JSP의 역할을 구분하는 학습을 했고 이를 통해 MODEL2 MVC 아키텍처에 대해 이해를 하게 되었습니다
- MVC2 방식이 적용된 후 코드의 재사용성과 유지보수가 향상되어 다음 프로젝트에서도 이 지식을 유용하게 활용할 수 있게 된 것 같습니다. 
