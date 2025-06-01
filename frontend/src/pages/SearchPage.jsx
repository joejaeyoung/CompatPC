import React, { useState, useEffect } from "react";
import { Link, useSearchParams } from "react-router-dom";
import searchIcon from "../assets/icons/search.svg";
import leftIcon   from "../assets/icons/arrow-left.svg";
import rightIcon  from "../assets/icons/arrow-right.svg";
import "./SearchPage.css";
//searchpage 수정함. 김학수
export const SearchPage = () => {
  // 1) URL에서 part 쿼리 읽기
  const [searchParams] = useSearchParams();
  const partType = searchParams.get("part"); // e.g. "cpu", "cooler", "case" 등

  // 2) 상태 관리
  const [items, setItems] = useState([]);            // 전체 목록
  const [filtered, setFiltered] = useState([]);      // 검색 후 목록
  const [query, setQuery]       = useState("");      // 검색어

  // 3) 마운트 시 / partType 변화 시: API 호출
  useEffect(() => {
    if (!partType) return;
    const url = `/api/v1/computer/${partType}`;
    console.log("▶️ Fetching:", url);
    fetch(url)
        .then(res => {
          console.log("◀️ Response status:", res.status, res.statusText);
          return res.json();
        })
        .then(json => {
          if (json.status === "OK") {
            setItems(json.data);
            setFiltered(json.data);
          } else {
            setItems([]);
            setFiltered([]);
          }
        })
        .catch(err => {
          console.error(err);
          setItems([]);
          setFiltered([]);
        });
  }, [partType]);

  // 4) query가 바뀔 때마다 필터링
  useEffect(() => {
    const q = query.trim().toLowerCase();
    if (!q) {
      setFiltered(items);
    } else {
      setFiltered(
          items.filter(item =>
              item.name.toLowerCase().includes(q)
          )
      );
    }
  }, [query, items]);

  // 5) 렌더링
  return (
      <div className="search-container">
        <header className="search-header">
          <Link to="/main">
            <img src={leftIcon} alt="뒤로" />
          </Link>
          <input
              type="text"
              placeholder="검색어를 입력하세요"
              value={query}
              onChange={e => setQuery(e.target.value)}
              className="search-input"
          />
          <button onClick={() => {/* 필요시 API 재호출 */}}>
            <img src={searchIcon} alt="검색" />
          </button>
        </header>

        <main className="search-body">
          {filtered.length === 0 ? (
              <p className="no-results">검색 결과가 없습니다.</p>
          ) : (
              <section className="product-list">
                {filtered.map(item => (
                    <article key={item.id} className="product-card">
                      <img
                          src={item.img}
                          alt={item.name}
                          className="prod-image"
                      />
                      <div className="prod-title">{item.name}</div>
                      <Link to="/main" className="select-btn">
                        선택하기
                      </Link>
                    </article>
                ))}
              </section>
          )}

          {/* (선택사항) 페이지네이션 */}
          <nav className="pagination">
            <button disabled>
              <img src={leftIcon} alt="이전" />
            </button>
            <button disabled>
              <img src={rightIcon} alt="다음" />
            </button>
          </nav>
        </main>
      </div>
  );
};
