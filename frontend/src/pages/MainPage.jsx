import React, { useState } from "react";
import "./MainPage.css";
import { Link } from "react-router-dom";

import chevronDown from "../assets/icons/chevron-down.svg";
import mainIcon    from "../assets/images/main-icon.png";

import cpu      from "../assets/images/cpu.png";
import cooler   from "../assets/images/cooler.png";
import mainboard from "../assets/images/mainboard.png";
import ram      from "../assets/images/ram.png";
import gpu      from "../assets/images/gpu.png";
import ssd      from "../assets/images/ssd.png";
import hdd      from "../assets/images/hdd.png";
import pcCase   from "../assets/images/case.png";
import psu      from "../assets/images/psu.png";

const parts = [
  { name: "CPU",       image: cpu },
  { name: "Cooler",    image: cooler },
  { name: "Mainboard", image: mainboard },
  { name: "RAM",       image: ram, options: ["용량", "개수"] },
  { name: "GPU",       image: gpu },
  { name: "SSD",       image: ssd, options: ["개수"] },
  { name: "HDD",       image: hdd, options: ["개수"] },
  { name: "Case",      image: pcCase },
  { name: "PSU",       image: psu },
];

/* 🆕 옵션값 헬퍼 */
const getOptionValues = (partName, optionName) => {
  if (optionName === "용량") return [4, 8, 16, 32, 48, 64];

  if (optionName === "개수") {
    if (partName === "RAM")        return Array.from({ length: 32 }, (_, i) => i + 1);
    if (partName === "SSD" || partName === "HDD")
                                   return Array.from({ length: 10 }, (_, i) => i + 1);
  }
  return [];
};

export const MainPage = () => {
  const [dropdowns, setDropdowns] = useState({});

  const toggleDropdown = (part, opt) =>
    setDropdowns((prev) => ({
      ...prev,
      [`${part}-${opt}`]: {
        ...prev[`${part}-${opt}`],
        open: !prev[`${part}-${opt}`]?.open,
      },
    }));

  const selectOption = (part, opt, value) =>
    setDropdowns((prev) => ({
      ...prev,
      [`${part}-${opt}`]: { selected: value, open: false },
    }));

  const selected = (part, opt) => dropdowns[`${part}-${opt}`]?.selected || "Value";
  const isOpen   = (part, opt) => dropdowns[`${part}-${opt}`]?.open;

  return (
    <div className="main-container">
      <header className="main-header">
        <img src={mainIcon} alt="Main icon" className="main-header-icon" />
      </header>

      <section className="main-title">
        <h1>호환성 검사</h1>
        <p>부품 선택</p>
      </section>

      <section className="card-list">
        {parts.map((part) => (
          <article key={part.name} className="card">
            <img src={part.image} alt={part.name} className="part-image" />

            <div className="card-body">
              <div className="part-title">{part.name}</div>
              <div className="part-desc">부품을 선택해 주세요.</div>

              <Link to="/search" className="select-button">선택하기</Link>

              {part.options && (
                <div className="extra-section">
                  {part.options.map((opt) => (
                    <div key={opt} className="select">
                      <span>{opt}</span>

                      <div
                        className="dropdown"
                        onClick={() => toggleDropdown(part.name, opt)}
                      >
                        <div className="dropdown-selected">
                          {selected(part.name, opt)}
                          <img src={chevronDown} alt="" className="chevron-icon" />
                        </div>

                        {isOpen(part.name, opt) && (
                          <ul className="dropdown-menu">
                            {getOptionValues(part.name, opt).map((val) => (
                              <li
                                key={val}
                                className="dropdown-item"
                                onClick={(e) => {
                                  e.stopPropagation();
                                  selectOption(part.name, opt, val);
                                }}
                              >
                                {val}
                              </li>
                            ))}
                          </ul>
                        )}
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          </article>
        ))}
      </section>

      <div className="check-btn-container">
        <Link to="/result" className="check-button">검사하기</Link>
      </div>
    </div>
  );
};
