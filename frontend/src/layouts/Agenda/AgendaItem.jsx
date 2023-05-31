import React from "react";
import Moment from "moment";
import { BsCalendarCheckFill } from "react-icons/bs";

const AgendaItem = ({item, currentMonth, setCurrentMonth}) => {
  const months = ["Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober", "November", "December"];
  const days = ["Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"];

  const date = new Date(item.date);

  return (
    <div className="agenda_item">
      <div className="agenda_calendar">
        <BsCalendarCheckFill size={48} color="white"/>
      </div>
      <div className="agenda_item_info">
        <div className="agenda_item_title">
          <span>{item.title}</span>
        </div>
        <div className="agenda_item_date">
          <span>{`${Moment(date).format('DD-MM-YYYY HH:mm')}`}</span>
          <span>{days[date.getDay()]}</span>
        </div>
        <p>{item.description}</p>
      </div>
    </div>
  );
};

export default AgendaItem;
