import React from "react";
import Home from "./components/Home";
import './App.css'

const App = () => {
  return (
    <div className="flex flex-col w-[100vw] h-[100vh] justify-center items-center bg-gray-200">
      <Home/>
    </div>
  );
};

export default App;
