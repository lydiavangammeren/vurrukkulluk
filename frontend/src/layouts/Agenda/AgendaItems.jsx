import React from "react";
import { BsCalendarCheckFill } from "react-icons/bs";

const AgendaItems = ({items}) => {
  return (
    <div className="agenda_items">
      {items.map((item, index) => (
        <div className="agenda_item" key={index}>
          <div className="agenda_calendar">
            <BsCalendarCheckFill size={48} color="white"/>
          </div>
          <div className="agenda_item_info">
            <h3>{item.title}</h3>
            <p>{item.description}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default AgendaItems;
