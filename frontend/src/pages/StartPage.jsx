import React from "react";
import { useNavigate } from "react-router-dom";
import "./StartPage.css"; 

export const StartPage = () => {
  const navigate = useNavigate();

  return (
    <div className="container">
      <div className="title">지능형 시스템 4조</div>
      <div className="subtitle">컴퓨터 부품 호환성 검사</div>

      <button
        className="button"
        onClick={() => navigate("/main")}
      >
        시작하기
      </button>

      <div className="tooltip">
        본 서비스는 제조사에서 제공한 상품 정보를 바탕으로 하고 있으며, 일부 제품은 호환성 확인 대상에 포함되지 않을 수 있습니다.
        <br />
        검사 결과는 참고용으로 제공되며, 정확한 정보가 필요할 경우 판매처나 제조사에 문의하시기 바랍니다.
      </div>
    </div>
  );
};
export default StartPage;