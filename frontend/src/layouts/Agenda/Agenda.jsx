import React, { useState, useEffect } from "react";
import api from "../../lib/recipeAPI";
import AgendaItems from "./AgendaItems";

const Agenda = () => {

  const [agendaMonths, setAgendaMonths] = useState([]);

  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get('/agenda');
        setAgendaMonths(response.data);
      } catch(err){
        if(err.response){
          //Not in the 200 response range
          console.log(err.response.data);
          console.log(err.response.status);
          console.log(err.response.headers);
        }else{
          console.log(`Error: ${err.message}`)
        }
      }
    }
    getData();
  }, [])


  return (
    <div className="Agenda">
      <h1>Agenda</h1>
      {agendaMonths.map((month, index) => {
        return (
          <>
            <h2>{month.month}</h2>
            <AgendaItems items={month.items} key={index}/>
          </>
        )
      })}
    </div>
  );
};

export default Agenda;
