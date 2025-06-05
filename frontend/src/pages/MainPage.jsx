import React, { useState, useEffect, useCallback } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import "./MainPage.css";
import axios from "axios";

import chevronDown from "../assets/icons/chevron-down.svg";
import closeIcon from "../assets/icons/close.svg";
import mainIcon from "../assets/images/main-icon.png";

import cpuImg from "../assets/images/cpu.png";
import coolerImg from "../assets/images/cooler.png";
import mainboardImg from "../assets/images/mainboard.png";
import ramImg from "../assets/images/ram.png";
import gpuImg from "../assets/images/gpu.png";
import ssdImg from "../assets/images/ssd.png";
import hddImg from "../assets/images/hdd.png";
import caseImg from "../assets/images/case.png";
import psuImg from "../assets/images/psu.png";

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

const getOptionValues = (part, opt) => {
  if (opt === "capacity") return [4, 8, 16, 32, 48, 64];
  if (opt === "quantity") return Array.from({ length: 32 }, (_, i) => i + 1);
  if (opt === "count") return Array.from({ length: 10 }, (_, i) => i + 1);
  return [];
};

const optionLabels = {
  capacity: "용량",
  quantity: "개수",
  count: "개수"
};

export const MainPage = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [selected, setSelected] = useState({});
  const [cpuOption, setCpuOption] = useState({ hasCooler: false, hasGPU: false });
  const [dropdowns, setDropdowns] = useState({});
  const [ssdSelections, setSsdSelections] = useState([]);
  const [hddSelections, setHddSelections] = useState([]);
  const [tempSelectedSSD, setTempSelectedSSD] = useState(null);
  const [tempSelectedHDD, setTempSelectedHDD] = useState(null);

  const handleCpuExtraToggle = useCallback(
    (field) => (e) => {
      const checked = e.target.checked;
      setCpuOption((s) => ({ ...s, [field]: checked }));

      setSelected((prev) => {
        const updated = { ...prev };
        if (field === "hasCooler" && checked) delete updated["Cooler"];
        if (field === "hasGPU" && checked) delete updated["GPU"];
        localStorage.setItem("selectedParts", JSON.stringify(updated));
        return updated;
      });
    },
    []
  );

  useEffect(() => {
    const saved = localStorage.getItem("selectedParts");
    const savedSSD = localStorage.getItem("ssdSelections");
    const savedHDD = localStorage.getItem("hddSelections");

    if (saved) {
      const parsed = JSON.parse(saved);
      if (parsed["SSD"] && !Array.isArray(parsed["SSD"])) setTempSelectedSSD(parsed["SSD"]);
      if (parsed["HDD"] && !Array.isArray(parsed["HDD"])) setTempSelectedHDD(parsed["HDD"]);
      delete parsed["SSD"];
      delete parsed["HDD"];
      setSelected(parsed);
    }
    if (savedSSD) setSsdSelections(JSON.parse(savedSSD));
    if (savedHDD) setHddSelections(JSON.parse(savedHDD));
  }, [location.state]);

  const toggle = (p, o) => setDropdowns((prev) => ({ ...prev, [`${p}-${o}`]: !prev[`${p}-${o}`] }));

  const choose = (p, o, v) => {
    if (p === "SSD") {
      if (!tempSelectedSSD) return;
      const newItem = { ...tempSelectedSSD, count: v };
      const updated = [...ssdSelections, newItem];
      setSsdSelections(updated);
      setTempSelectedSSD(null);
      localStorage.setItem("ssdSelections", JSON.stringify(updated));
      return;
    }
    if (p === "HDD") {
      if (!tempSelectedHDD) return;
      const newItem = { ...tempSelectedHDD, count: v };
      const updated = [...hddSelections, newItem];
      setHddSelections(updated);
      setTempSelectedHDD(null);
      localStorage.setItem("hddSelections", JSON.stringify(updated));
      return;
    }
    const updated = { ...selected, [p]: { ...selected[p], [o]: v } };
    setSelected(updated);
    localStorage.setItem("selectedParts", JSON.stringify(updated));
  };

  const removeSSD = (id) => {
    const updated = ssdSelections.filter((item) => item.id !== id);
    setSsdSelections(updated);
    localStorage.setItem("ssdSelections", JSON.stringify(updated));
  };

  const removeHDD = (id) => {
    const updated = hddSelections.filter((item) => item.id !== id);
    setHddSelections(updated);
    localStorage.setItem("hddSelections", JSON.stringify(updated));
  };

  const missingParts = () =>
    parts
      .filter(({ partName }) => {
        if (partName === "Cooler" && cpuOption.hasCooler) return false;
        if (partName === "GPU" && cpuOption.hasGPU) return false;
        if (partName === "SSD") return ssdSelections.length === 0;
        if (partName === "HDD") return hddSelections.length === 0;
        return !selected[partName]?.name;
      })
      .map((p) => p.partName);

  const handleReset = () => {
    localStorage.clear();
    setSelected({});
    setCpuOption({ hasCooler: false, hasGPU: false });
    setSsdSelections([]);
    setHddSelections([]);
    setTempSelectedSSD(null);
    setTempSelectedHDD(null);
  };

const handleCheck = async () => {
  const miss = missingParts();
  if (miss.length) {
    alert(`선택하지 않은 부품: ${miss.join(", ")}`);
    return;
  }

  const ssdId = ssdSelections.flatMap((item) =>
    Array(item.count).fill(item.id)
  );

  let m2ssdCount = 0;
  let satassdCount = 0;

  ssdSelections.forEach((item) => {
    const name = item.name.toUpperCase();
    if (name.includes("M.2")) m2ssdCount += item.count;
    else if (name.includes("SATA")) satassdCount += item.count;
  });

  const hddCount = hddSelections.reduce((sum, item) => sum + item.count, 0);

  const payload = {
    cpuId: selected["CPU"]?.id,
    coolerId: cpuOption.hasCooler ? null : selected["Cooler"]?.id,
    mainboardId: selected["Mainboard"]?.id,
    ramId: selected["RAM"]?.id,
    ramQunatatiy: selected["RAM"]?.quantity || 1,
    ramCapacity: selected["RAM"]?.capacity || 8,
    gpuId: cpuOption.hasGPU ? null : selected["GPU"]?.id,
    ssdId,
    m2ssdCount, 
    satassdCount,
    hddCount,
    caseId: selected["Case"]?.id,
    psuId: selected["PSU"]?.id
  };

  console.log("📦 전송할 Payload:", payload);

    Object.keys(payload).forEach((key) => {
    if (payload[key] === null || payload[key] === undefined) {
      delete payload[key];
    }
  });

  try {
    const baseUrl = import.meta.env.VITE_API_BASE_URL;
    const response = await axios.post(`${baseUrl}/api/v1/computer/validate`, payload, {
      headers: { "Content-Type": "application/json" }
    });

    navigate("/result", { state: { result: response.data } });
  } catch (err) {
    console.error("❌ 서버 요청 실패:", err);
    alert("서버 요청 중 오류가 발생했습니다.");
  }
};



  return (
    <div className="main-container">
      <header className="main-header">
        <img src={mainIcon} alt="Main icon" className="main-header-icon" />
      </header>

      <section className="main-title-with-reset">
        <h1>호환성 검사</h1>
        <div className="title-subrow">
          <p>부품 선택</p>
          <button className="reset-button" onClick={handleReset}>초기화</button>
        </div>
      </section>

      <section className="card-list">
        {parts.map(({ partName, image, options }) => (
          <article key={partName} className="card">
            <img src={image} alt={partName} className="part-image" />

            <div className="card-body">
              <div className="part-title">{partName}</div>
              <div className="part-desc">
                {partName === "SSD"
                  ? tempSelectedSSD?.name || "부품 선택 후 개수를 정하세요."
                  : partName === "HDD"
                  ? tempSelectedHDD?.name || "부품 선택 후 개수를 정하세요."
                  : selected[partName]?.name || "부품을 선택해 주세요."}
              </div>

              {partName === "Cooler" && (
                <label className="cpu-extra">
                  <input
                    type="checkbox"
                    checked={cpuOption.hasCooler}
                    onChange={handleCpuExtraToggle("hasCooler")}
                  />
                  CPU 내장 쿨러 사용
                </label>
              )}
              {partName === "GPU" && (
                <label className="cpu-extra">
                  <input
                    type="checkbox"
                    checked={cpuOption.hasGPU}
                    onChange={handleCpuExtraToggle("hasGPU")}
                  />
                  CPU 내장 그래픽 사용
                </label>
              )}

              {!(partName === "Cooler" && cpuOption.hasCooler) &&
                !(partName === "GPU" && cpuOption.hasGPU) && (
                  <Link
                    to={`/search?part=${partName.toLowerCase()}`}
                    className="select-button"
                  >
                    선택하기
                  </Link>
                )}

              {options && (
                <div className="extra-section">
                  {options.map((opt) => (
                    <div key={opt} className="select">
                      <span>{optionLabels[opt] || opt}</span>
                      <div className="dropdown" onClick={() => toggle(partName, opt)}>
                        <div className="dropdown-selected">
                          {(partName === "SSD"
                            ? tempSelectedSSD?.[opt]
                            : selected[partName]?.[opt]) || "Value"}
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

              {partName === "SSD" && ssdSelections.length > 0 && (
                <div className="ssd-selection-list">
                  {ssdSelections.map((item) => (
                    <div key={item.id} className="ssd-tag">
                      [{item.name} / {item.count}]
                      <img
                        src={closeIcon}
                        alt="삭제"
                        className="close-icon"
                        onClick={() => removeSSD(item.id)}
                      />
                    </div>
                  ))}
                </div>
              )}
              {partName === "HDD" && hddSelections.length > 0 && (
                <div className="ssd-selection-list">
                  {hddSelections.map((item) => (
                    <div key={item.id} className="ssd-tag">
                      [{item.name} / {item.count}]
                      <img
                        src={closeIcon}
                        alt="삭제"
                        className="close-icon"
                        onClick={() => removeHDD(item.id)}
                      />
                    </div>
                  ))}
                </div>
              )}
            </div>
          </article>
        ))}
      </section>

      <div className="check-btn-container">
        <button className="check-button" onClick={handleCheck}>검사하기</button>
      </div>
    </div>
  );
};
