import React from "react";
import { useLocation, Link } from "react-router-dom";

import mainIcon   from "../assets/images/main-icon.png";
import okIcon     from "../assets/icons/check-circle.svg";
import errorIcon  from "../assets/icons/error-red.svg";
import warnIcon   from "../assets/icons/warn-yellow.svg";

import "./ResultPage.css";

export const ResultPage = () => {

  const location = useLocation();
  console.log("[ResultPage] location.state:", location.state);

  const apiResponse = location.state?.result;
  const resultArray = Array.isArray(apiResponse)
    ? apiResponse                     
    : apiResponse?.data ?? [];           

  console.log("[ResultPage] resultArray:", resultArray);

  const parsedList = resultArray.map((item) => ({
    type: item.errorLevel === 1 ? "error" : "warn",
    message: `${item.errMsg}\n${item.solveMsg}`
  }));

  console.log("[ResultPage] parsedList:", parsedList);

  const hasError = parsedList.some((item) => item.type === "error");
  console.log("[ResultPage] hasError:", hasError);

  const getIconByType = (type) => {
    switch (type) {
      case "error":
        return errorIcon;
      case "warn":
        return warnIcon;
      default:
        return warnIcon;
    }
  };

  return (
    <div className="result-container">
      {/* 헤더 */}
      <header className="result-header">
        <img src={mainIcon} alt="메인 아이콘" className="header-icon" />
      </header>

      {/* 본문 */}
      <main className="result-body">
        {/* ① 호환 여부 섹션 */}
        <section className="compat-section">
          {!hasError && (
            <img src={okIcon} alt="호환 가능" className="compat-icon" />
          )}
          <h2 className="compat-text">
            {hasError ? "호환이 불가합니다." : "호환이 가능합니다."}
          </h2>
        </section>

        {/* ② 상세 결과 리스트 */}
        {parsedList.length > 0 && (
          <section className="detail-section">
            <h3 className="detail-heading">Detailed Message</h3>
            <p className="detail-sub">검사 결과</p>

            {parsedList.map((item, index) => (
              <article key={index} className="detail-card">
                <img
                  src={getIconByType(item.type)}
                  alt={item.type}
                  className="detail-icon"
                />
                <div className="detail-content">
                  <p className="detail-text" style={{ whiteSpace: "pre-line" }}>
                    {item.message}
                  </p>
                </div>
              </article>
            ))}
          </section>
        )}

        {/* ③ 홈으로 */}
        <div className="result-actions">
          <Link to="/" className="home-btn">
            처음으로
          </Link>
        </div>
      </main>
    </div>
  );
};
