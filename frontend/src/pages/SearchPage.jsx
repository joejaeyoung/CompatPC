import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

import mainIcon from "../assets/images/main-icon.png";
import searchIcon from "../assets/icons/search.svg";
import "./SearchPage.css";

const dummyProducts = Array.from({ length: 10 }, (_, i) => ({
  id: i + 1,
  name: `Title ${i + 1}`,
  imageUrl: ""
}));

export const SearchPage = () => {
  const { partName } = useParams();
  const navigate = useNavigate();
  const [query, setQuery] = useState("");

  // ✅ localStorage에서 선택값 가져오기
  const stored = localStorage.getItem("selectedParts");
  const prevSelected = stored ? JSON.parse(stored) : {};

  const handleSelect = (product) => {
    const updatedSelected = {
      ...prevSelected,
      [partName]: { name: product.name }
    };

    localStorage.setItem("selectedParts", JSON.stringify(updatedSelected)); // ✅ 저장

    navigate("/main", {
      state: { selected: updatedSelected }
    });
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
            placeholder={`${partName} Search`}
            className="search-input"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
          <button className="search-btn" onClick={() => console.log("검색", query)}>
            <img src={searchIcon} alt="Search icon" />
          </button>
        </div>

        <section className="product-list">
          {dummyProducts
            .filter((p) => p.name.toLowerCase().includes(query.toLowerCase()))
            .map((p) => (
              <article key={p.id} className="product-card">
                <div className="prod-image" />
                <div className="prod-title">{p.name}</div>
                <button className="select-btn" onClick={() => handleSelect(p)}>
                  선택하기
                </button>
              </article>
            ))}
        </section>
      </main>
    </div>
  );
};
