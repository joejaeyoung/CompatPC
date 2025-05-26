import React from "react";
import { useLocation, Link } from "react-router-dom";

import mainIcon   from "../assets/images/main-icon.png";
import okIcon     from "../assets/icons/check-circle.svg";
import errorIcon  from "../assets/icons/error-red.svg";
import warnIcon   from "../assets/icons/warn-yellow.svg";

import "./ResultPage.css";

export const ResultPage = () => {
  const location = useLocation();
  const result = location.state?.result || [];

  const parsedList = Array.isArray(result) ? result.map((item) => ({
    type: item.errorType === 1 ? "error" : "warn",
    message: `${item.errmsg}\n${item.solution}`
  })) : [];

  const hasError = parsedList.some((item) => item.type === "error");

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
      <header className="result-header">
        <img src={mainIcon} alt="메인 아이콘" className="header-icon" />
      </header>

      <main className="result-body">
        <section className="compat-section">
          {!hasError && <img src={okIcon} alt="호환 가능" className="compat-icon" />}
          <h2 className="compat-text">
            {hasError ? "호환이 불가합니다." : "호환이 가능합니다."}
          </h2>
        </section>

        {parsedList.length > 0 && (
          <section className="detail-section">
            <h3 className="detail-heading">Detailed Message</h3>
            <p className="detail-sub">검사 결과</p>

            {parsedList.map((item, index) => (
              <article key={index} className="detail-card">
                <img src={getIconByType(item.type)} alt={item.type} className="detail-icon" />
                <div className="detail-content">
                  <p className="detail-text" style={{ whiteSpace: "pre-line" }}>
                    {item.message}
                  </p>
                </div>
              </article>
            ))}
          </section>
        )}

        <div className="result-actions">
          <Link to="/" className="home-btn">처음으로</Link>
        </div>
      </main>
    </div>
  );
};
