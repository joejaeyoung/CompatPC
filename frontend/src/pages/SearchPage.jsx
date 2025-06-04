import React, { useState, useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";

import mainIcon from "../assets/images/main-icon.png";
import searchIcon from "../assets/icons/search.svg";
import leftIcon from "../assets/icons/arrow-left.svg";
import rightIcon from "../assets/icons/arrow-right.svg";
import "./SearchPage.css";

export const SearchPage = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const partType = searchParams.get("part");

  // UI용 이름 매핑 (MainPage의 partName과 일치)
  const partKeyMap = {
    cpu: "CPU",
    cooler: "Cooler",
    mainboard: "Mainboard",
    ram: "RAM",
    gpu: "GPU",
    ssd: "SSD",
    hdd: "HDD",
    case: "Case",
    psu: "PSU"
  };

  // 실제 API 요청 경로 매핑
  const endpointMap = {
    cpu: "cpu",
    cooler: "cooler",
    mainboard: "mainboard",
    ram: "ram",
    gpu: "gpu",
    ssd: "ssd",
    hdd: "hdd",
    case: "case",
    psu: "powersupply" // 수정된 경로
  };

  const lowerPart = partType?.toLowerCase();
  const partKey = partKeyMap[lowerPart];
  const endpoint = endpointMap[lowerPart];

  const [items, setItems] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [query, setQuery] = useState("");

  const stored = localStorage.getItem("selectedParts");
  const prevSelected = stored ? JSON.parse(stored) : {};

const handleSelect = (product) => {
  if (!partKey) {
    alert("알 수 없는 부품 유형입니다.");
    return;
  }

  const updatedSelected = {
    ...prevSelected,
    [partKey]: { id: product.id, name: product.name }
  };

  console.log("🟢 선택된 부품 저장:", updatedSelected);
  localStorage.setItem("selectedParts", JSON.stringify(updatedSelected));
  navigate("/main", { state: { updated: true } });
};


  useEffect(() => {
    if (!endpoint) return;
    const url = `/api/v1/computer/${endpoint}`;
    console.log("▶️ Fetching:", url);
    fetch(url)
      .then(res => res.json())
      .then(json => {
        console.log("✅ 서버에서 받은 데이터:", json.data);
        if (json.status === "OK") {
          setItems(json.data);
          setFiltered(json.data);
        } else {
          setItems([]);
          setFiltered([]);
        }
      })
      .catch(err => {
        console.error("❌ 요청 오류:", err);
        setItems([]);
        setFiltered([]);
      });
  }, [endpoint]);

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

  return (
    <div className="search-container">
      <header className="search-header">
        <img src={mainIcon} alt="Main icon" className="header-icon" />
        <input
          type="text"
          placeholder={`${partKey || "부품"} 검색`}
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          className="search-input"
        />
        <button className="search-btn">
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
                  src={item.img || ""}
                  alt={item.name}
                  className="prod-image"
                />
                <div className="prod-title">{item.name}</div>
                <button className="select-btn" onClick={() => handleSelect(item)}>
                  선택하기
                </button>
              </article>
            ))}
          </section>
        )}

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
