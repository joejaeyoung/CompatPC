# CompatPC
지능형시스템 팀프로젝트 : 컴퓨터 부품을 사용자로부터 받아 컴퓨터 부품 간의 호환성을 확인해 진단 결과를 알려줌

## 주제
문제 상황 정의 – 게임 및 문서 작업을 위해 일반적인 데스크톱 PC 견적에 호환성 문제가 있는지 알아보기 위해서는 개인의 노력과 많은 시간이 소요된다.
아이디어 도출 - PC 견적의 호환성을 검사하는 전문가시스템을 만들어서 웹 서비스 형태로 제공하면 보다 짧은 시간안에 호환성 여부에 대해 알려줄 수 있다.

## 구성



## API 명세

| index    | 기능                | HTTP | API PATH                     | 서버 담당자 | 서버 개발 현황             | 프론트 담당자 | 프론트 개발 현황 |
| -------- | ----------------- | ---- | ---------------------------- | ------ | -------------------- | ------- | --------- |
|          | 부품 호환 정도 체크       | POST | /api/v1/computer/valid       | 조재영    | Service 계층 메인 로직 미구현 |         |           |
| 부품 정보 확인 | case 정보 확인        | GET  | /api/v1/computer/case        | 조재영    | 구현 완         |         |           |
|          | cooler 정보 확인      | GET  | /api/v1/computer/cooler      | 조재영    | 구현 완         |         |           |
|          | cpu 정보 확인         | GET  | /api/v1/computer/cpu         | 조재영    | 구현 완         |         |           |
|          | gpu 정보 확인        | GET  | /api/v1/computer/gpu         | 조재영    | 구현 완        |         |           |
|          | hdd 정보 확인         | GET  | /api/v1/computer/hdd         | 조재영    | 구현 완        |         |           |
|          | mainboard 정보 확인   | GET  | /api/v1/computer/mainboard   | 조재영    | 구현 완        |         |           |
|          | powersupply 정보 확인 | GET  | /api/v1/computer/powersupply | 조재영    | 구현 완         |         |           |
|          | ram 정보 확인         | GET  | /api/v1/computer/ram         | 조재영    | 구현 완        |         |           |
|          | ssd 정보 확인         | GET  | /api/v1/computer/ssd         | 조재영    | 구현 완         |         |           |

