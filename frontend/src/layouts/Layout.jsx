import React from 'react'
import Header from "./Header";
import Agenda from "./Agenda";
import Login from "./Login";
import Footer from "./Footer";

const Layout = ({children}) => {
  return (
    <div className="App">
      <Header />
      <section>
        <div className="side">
          <Agenda />
          <Login />
        </div>
        <div className="pageContainer">
          {children}
        </div>
      </section>
      <Footer />
    </div>
  )
}

export default Layout
