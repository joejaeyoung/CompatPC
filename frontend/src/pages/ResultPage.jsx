import React from "react";
import { Link } from "react-router-dom";

import mainIcon   from "../assets/images/main-icon.png";
import okIcon     from "../assets/icons/check-circle.svg";  // ✔︎ 아이콘
import errorIcon  from "../assets/icons/error-red.svg";    // ⨯ 아이콘
import warnIcon   from "../assets/icons/warn-yellow.svg";  // ▲ 아이콘

import "./ResultPage.css";

export const ResultPage = () => {
  /* ⚠️ 아직 API 연결 전 – 더미 데이터 */
  const hasError = false;           // true 면 “호환이 불가합니다” 로 바꾸면 됨

  return (
    <div className="result-container">
      {/* ───── 헤더 (메인 아이콘만) ───── */}
      <header className="result-header">
        <img src={mainIcon} alt="메인 아이콘" className="header-icon" />
      </header>

      {/* ───── 본문 ───── */}
      <main className="result-body">
        {/* ① 결과 섹션 (성공 / 실패 여부) */}
        <section className="compat-section">
          <img
            src={okIcon}
            alt=""
            className={`compat-icon ${hasError ? "hidden" : ""}`}
          />
          <h2 className="compat-text">
            {hasError ? "호환이 불가합니다." : "호환이 가능합니다."}
          </h2>
        </section>

        {/* ② 상세 오류 리스트 – 예시는 두 개 카드 */}
        <section className="detail-section">
          <h3 className="detail-heading">Detailed Error</h3>
          <p  className="detail-sub">Error solution</p>

          <article className="detail-card">
            <img src={errorIcon} alt="" className="detail-icon" />
            <div className="detail-content">
              <h4 className="detail-title">Title</h4>
              <p className="detail-text">
                Body text for whatever you’d like to say. Add main takeaway
                points, quotes, anecdotes, or even a very very short story.
              </p>
            </div>
          </article>

          <article className="detail-card">
            <img src={warnIcon} alt="" className="detail-icon" />
            <div className="detail-content">
              <h4 className="detail-title">Title</h4>
              <p className="detail-text">
                Body text for whatever you’d like to say. Add main takeaway
                points, quotes, anecdotes, or even a very very short story.
              </p>
            </div>
          </article>
        </section>

        {/* ③ 홈으로 버튼 */}
        <div className="result-actions">
          <Link to="/" className="home-btn">처음으로</Link>
        </div>
      </main>
    </div>
  );
};
