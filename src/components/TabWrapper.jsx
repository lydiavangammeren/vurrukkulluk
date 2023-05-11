// Wrapper component for tabs
import React from "react";
import Tabs from "./Tabs";
import Ingredients from "./Ingredients";
import Preparation from "./Preparation";
import { useParams } from "react-router-dom";

const TabWrapper = () => {
  const { id } = useParams();

  const prepSteps = [
    {
      recept_id: "1",
      prep_steps: [
        {
          step: 1,
          desc: "Recept 1 Lorem ipsum bla bla bla",
        },
        {
          step: 2,
          desc: "Lorem ipsum bla bla bla",
        },
      ],
    },

    {
      recept_id: "2",
      prep_steps: [
        {
          step: 1,
          desc: "Recept 2 Lorem ipsum bla bla bla",
        },
        {
          step: 2,
          desc: "Lorem ipsum bla bla bla",
        },
      ],
    },

    {
      recept_id: "3",
      prep_steps: [
        {
          step: 1,
          desc: "Recept 3 Lorem ipsum bla bla bla",
        },
        {
          step: 2,
          desc: "Lorem ipsum bla bla bla",
        },
      ],
    },
    {
      recept_id: "4",
      prep_steps: [
        {
          step: 1,
          desc: "Recept 4 Lorem ipsum bla bla bla",
        },
        {
          step: 2,
          desc: "Lorem ipsum bla bla bla",
        },
      ],
    },
  ];

  const prepstepsForId = prepSteps.find((x) => x.recept_id === id).prep_steps;
  return (
    <>
      <h1>Tabs Demo</h1>
      <Tabs>
        <div label="Ingredienten">
          <Ingredients />
        </div>
        <div label="Bereidingswijze">
          <Preparation prepsteps={prepstepsForId} />
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
