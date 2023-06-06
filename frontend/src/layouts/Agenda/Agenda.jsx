import React, { useState, useEffect, useRef } from "react";
import api from "../../lib/recipeAPI";
import AgendaItem from "./AgendaItem";
import { useDatabase } from "../../hooks";

const Agenda = () => {

  // AGENDA TIJDELIJK UIT VANWEGE DATABASE CONNECTIE
  // const [ agendaItems, isLoaded ] = [[], true];
  const [ agendaItems, isLoaded ] = useDatabase('calendar');

  const currentMonth = useRef(12);

  // const formattedDate = new Intl.DateTimeFormat('nl-NL').format(date);
 

  const firstFive = isLoaded ? agendaItems.slice(0, 5): [];
  
  return (
    <div className="Agenda">
      <div className="agenda-title">
        <span>Agenda</span>
      </div>
      {firstFive.map((item, index)=> {
        return(
          <AgendaItem key = {index} item={item} currentMonth={currentMonth} />
        )
      })}
    </div>
  );
};

export default Agenda;
