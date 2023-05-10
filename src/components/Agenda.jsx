import React from "react";
import AgendaItem from "./AgendaItem";
import "../css/Agenda.css";

const Agenda = () => {
  const months = [
    "Juni",
    "Augustus",
    "September",
    
  ];
  return (
    <div className="Agenda">
      <h1>Agenda</h1>
      {months.map((name) => (
        <>
          <h2>{name}</h2>
          <AgendaItem />
        </>
      ))}
    </div>
  );
};

export default Agenda;
