import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import { StartPage }  from "./pages/StartPage";
import { MainPage }   from "./pages/MainPage";
import { ResultPage } from "./pages/ResultPage";
import { SearchPage } from "./pages/SearchPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/"        element={<StartPage  />} />
        <Route path="/main"    element={<MainPage   />} />
        <Route path="/result"  element={<ResultPage />} />
        <Route path="/search"  element={<SearchPage />} />
      </Routes>
    </Router>
  );
}

export default App;
