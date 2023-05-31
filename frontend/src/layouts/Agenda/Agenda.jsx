import React, { useState, useEffect } from "react";
import api from "../../lib/recipeAPI";
import AgendaItem from "./AgendaItem";

const Agenda = () => {

  const [agendaItems, setAgendaItems] = useState([]);

  useEffect(() => {
    const getData = async () => {
      try{
        // const response = await api.get('/agenda');
        // setAgendaMonths(response.data);
        const response = await api.get("/calendar");
        setAgendaItems(response.data);
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

 
  const firstFive = agendaItems.slice(0, 5);
  
  return (
    <div className="Agenda">
      <div className="agenda-title">
        <span>Agenda</span>
      </div>
      {firstFive.map((item, index)=> {
        return(
          <AgendaItem item={item} />
        )
      })}
    </div>
  );
};

export default Agenda;
