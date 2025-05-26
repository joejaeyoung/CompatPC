import React, { useState, useEffect } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import "./MainPage.css";
import axios from "axios";

import chevronDown from "../assets/icons/chevron-down.svg";
import mainIcon from "../assets/images/main-icon.png";

/* 이미지 리소스 */
import cpuImg from "../assets/images/cpu.png";
import coolerImg from "../assets/images/cooler.png";
import mainboardImg from "../assets/images/mainboard.png";
import ramImg from "../assets/images/ram.png";
import gpuImg from "../assets/images/gpu.png";
import ssdImg from "../assets/images/ssd.png";
import hddImg from "../assets/images/hdd.png";
import caseImg from "../assets/images/case.png";
import psuImg from "../assets/images/psu.png";

/* 카드 메타데이터 */
const parts = [
  { partName: "CPU", image: cpuImg },
  { partName: "Cooler", image: coolerImg },
  { partName: "Mainboard", image: mainboardImg },
  { partName: "RAM", image: ramImg, options: ["capacity", "quantity"] },
  { partName: "GPU", image: gpuImg },
  { partName: "SSD", image: ssdImg, options: ["count"] },
  { partName: "HDD", image: hddImg, options: ["count"] },
  { partName: "Case", image: caseImg },
  { partName: "PSU", image: psuImg }
];

/* 드롭다운 값 */
const getOptionValues = (part, opt) => {
  if (opt === "capacity") return [4, 8, 16, 32, 48, 64];
  if (opt === "quantity") return Array.from({ length: 32 }, (_, i) => i + 1);
  if (opt === "count") return Array.from({ length: 10 }, (_, i) => i + 1);
  return [];
};

/* 드롭다운 라벨 한글 매핑 */
const optionLabels = {
  capacity: "용량",
  quantity: "개수",
  count: "개수"
};

export const MainPage = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [selected, setSelected] = useState({});
  const [cpuOption, setCpuOption] = useState({
    hasCooler: false,
    hasGPU: false
  });

  useEffect(() => {
    const saved = localStorage.getItem("selectedParts");
    if (saved) {
      setSelected(JSON.parse(saved));
    }
  }, []);

  useEffect(() => {
    const st = location.state;
    if (st?.selected) {
      setSelected(st.selected);
      localStorage.setItem("selectedParts", JSON.stringify(st.selected));
      window.history.replaceState({}, document.title);
    }
  }, [location.state]);

  const missingParts = () =>
    parts
      .filter(({ partName }) => {
        if (partName === "Cooler" && cpuOption.hasCooler) return false;
        if (partName === "GPU" && cpuOption.hasGPU) return false;
        return !selected[partName]?.name;
      })
      .map((p) => p.partName);

  const [dropdowns, setDropdowns] = useState({});
  const toggle = (p, o) =>
    setDropdowns((prev) => ({
      ...prev,
      [`${p}-${o}`]: !prev[`${p}-${o}`]
    }));
  const choose = (p, o, v) =>
    setSelected((prev) => ({
      ...prev,
      [p]: { ...prev[p], [o]: v }
    }));

  const handleCheck = async () => {
    const miss = missingParts();
    if (miss.length) {
      alert(`선택하지 않은 부품: ${miss.join(", ")}`);
      return;
    }

    const payload = {
      CPU: {
        ...selected["CPU"],
        hasCooler: cpuOption.hasCooler,
        hasIntegratedGraphics: cpuOption.hasGPU
      },
      Cooler: selected["Cooler"],
      Mainboard: selected["Mainboard"],
      RAM: {
        ...selected["RAM"],
        quantity: selected["RAM"]?.quantity || 1,
        capacity: selected["RAM"]?.capacity || 8
      },
      GPU: selected["GPU"],
      SSD: {
        ...selected["SSD"],
        count: selected["SSD"]?.count || 1
      },
      HDD: {
        ...selected["HDD"],
        count: selected["HDD"]?.count || 1
      },
      Cases: selected["Case"],
      PowerSupply: selected["PSU"]
    };

    console.log("📦 서버 전송용 JSON payload:", payload);

    try {
      const baseUrl = import.meta.env.VITE_API_BASE_URL;
      const response = await axios.post(
        `${baseUrl}/api/v1/computer/valid`,
        payload,
        {
          headers: {
            "Content-Type": "application/json"
          }
        }
      );

      console.log("✅ 서버 응답:", response.data);

      navigate("/result", {
        state: {
          result: response.data
        }
      });
    } catch (error) {
      console.error("❌ 서버 요청 실패:", error);
      alert("서버 요청 중 오류가 발생했습니다.");
    }
  };

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
        {parts.map(({ partName, image, options }) => (
          <article key={partName} className="card">
            <img src={image} alt={partName} className="part-image" />

            <div className="card-body">
              <div className="part-title">{partName}</div>
              <div className="part-desc">
                {selected[partName]?.name || "부품을 선택해 주세요."}
              </div>

              {partName === "Cooler" && (
                <label className="cpu-extra">
                  <input
                    type="checkbox"
                    checked={cpuOption.hasCooler}
                    onChange={(e) =>
                      setCpuOption((s) => ({
                        ...s,
                        hasCooler: e.target.checked
                      }))
                    }
                  />
                  CPU 내장 쿨러 사용
                </label>
              )}
              {partName === "GPU" && (
                <label className="cpu-extra">
                  <input
                    type="checkbox"
                    checked={cpuOption.hasGPU}
                    onChange={(e) =>
                      setCpuOption((s) => ({
                        ...s,
                        hasGPU: e.target.checked
                      }))
                    }
                  />
                  CPU 내장 그래픽 사용
                </label>
              )}

              {!(partName === "Cooler" && cpuOption.hasCooler) &&
                !(partName === "GPU" && cpuOption.hasGPU) && (
                  <Link to={`/search/${partName}`} className="select-button">
                    선택하기
                  </Link>
                )}

              {options && (
                <div className="extra-section">
                  {options.map((opt) => (
                    <div key={opt} className="select">
                      <span>{optionLabels[opt] || opt}</span>
                      <div
                        className="dropdown"
                        onClick={() => toggle(partName, opt)}
                      >
                        <div className="dropdown-selected">
                          {selected[partName]?.[opt] || "Value"}
                          <img src={chevronDown} alt="" className="chevron-icon" />
                        </div>
                        {dropdowns[`${partName}-${opt}`] && (
                          <ul className="dropdown-menu">
                            {getOptionValues(partName, opt).map((v) => (
                              <li
                                key={v}
                                className="dropdown-item"
                                onClick={(e) => {
                                  e.stopPropagation();
                                  choose(partName, opt, v);
                                  toggle(partName, opt);
                                }}
                              >
                                {v}
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
        <button className="check-button" onClick={handleCheck}>
          검사하기
        </button>
      </div>
    </div>
  );
};
