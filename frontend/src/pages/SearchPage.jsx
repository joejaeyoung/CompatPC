import React, { useState } from "react"; 
import { Link } from "react-router-dom";

import mainIcon   from "../assets/images/main-icon.png";
import searchIcon from "../assets/icons/search.svg";
import leftIcon   from "../assets/icons/arrow-left.svg";
import rightIcon  from "../assets/icons/arrow-right.svg";

import "./SearchPage.css";

const dummyProducts = Array.from({ length: 10 }, (_, i) => ({
  id: i + 1,
  title: `Title ${i + 1}`,
}));

export const SearchPage = () => {
  const [query, setQuery] = useState(""); // 검색어 상태 저장

  const handleSearch = () => {
    // 나중에 API 호출 연결 시 여기에 로직 추가
    console.log("검색어:", query);
  };

  return (
    <div className="search-container">
      <header className="search-header">
        <img src={mainIcon} alt="Main icon" className="header-icon" />
      </header>

      <main className="search-body">
        <div className="search-box">
          <input
            type="text"
            placeholder="Search"
            className="search-input"
            value={query}
            onChange={(e) => setQuery(e.target.value)} // 입력 가능
          />
          <button className="search-btn" onClick={handleSearch}> {/* 클릭 가능 */}
            <img src={searchIcon} alt="Search icon" />
          </button>
        </div>

        <section className="product-list">
          {dummyProducts.map((p) => (
            <article key={p.id} className="product-card">
              <div className="prod-image" />
              <div className="prod-title">{p.title}</div>
              <Link to="/main" className="select-btn">
                선택하기
              </Link>
            </article>
          ))}
        </section>

        <nav className="pagination">
          <button className="page-nav" disabled>
            <img src={leftIcon} alt="" />
            <span>Previous</span>
          </button>

          <ul className="page-list">
            <li className="page current">1</li>
            <li className="page">2</li>
            <li className="page">3</li>
            <li className="gap">…</li>
            <li className="page">67</li>
            <li className="page">68</li>
          </ul>

          <button className="page-nav" disabled>
            <span>Next</span>
            <img src={rightIcon} alt="" />
          </button>
        </nav>
      </main>
    </div>
  );
};
