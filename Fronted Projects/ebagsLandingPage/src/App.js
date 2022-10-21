import React, { useState } from "react";
import TopNav from "./components/topNav";
import NavBar from "./components/Navbar";
import SmallCard from "./components/smallCard";
import BigCard from "./components/BigCard";
import Description from "./components/Description";
import Bottom from "./components/Bottom";
import Footer from "./components/Footer"

function App() {
  const [pos, setPos] = useState(false);

  const scroll = () => {
    if (window.scrollY >= 20) {
      console.log(window.scrollY);
      setPos(true);
    } else {
      setPos(false);
    }
  };

  window.addEventListener("scroll", scroll);

  return (
    <div className="w-full overflow-hidden" >
      <TopNav />
      <div
        style={{
          top: pos ? "-10px" : "",
          transition: "all 0.2s ease",
          zIndex: "100",
        }}
        className={pos ? "fixed w-full" : "relative"}
      >
        <NavBar />
      </div>

      <section
        className=" h-screen bg-Hero bg-cover
    font-Inter md:bg-top bg-center"
      ></section>

      <section className="mt-12">
        <div className="mx-10 grid md:grid-cols-3 lg:gap-5 justify-items-center">
          <SmallCard />
        </div>
      </section>

      <section className="mt-12">
        <div className="grid md:grid-cols-2 gap-1 justify-items-center mx-1">
          <BigCard />
        </div>
      </section>

      <section className="mt-12">
        <Description />
      </section>
      <section className="mt-12">
        <Bottom />
      </section>
      <section className="mt-12 mb-12">
        <Footer />
      </section>
    </div>
  );
}

export default App;
