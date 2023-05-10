// Wrapper component for tabs
import React from "react";
import Tabs from "./Tabs";

const TabWrapper = () => {
  return (
    <>
      <h1>Tabs Demo</h1>
      <Tabs>
        <div label="Ingredienten">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod tempor incididunt ut labore et dolore magna aliqua.
        </div>
        <div label="Bereidingswijze">
          Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
          nisi ut aliquip ex ea commodo consequat
        </div>
        <div label="Opmerkingen">
          Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
          dolore eu fugiat nulla pariatur.
        </div>
      </Tabs>
    </>
  );
};

export default TabWrapper;
