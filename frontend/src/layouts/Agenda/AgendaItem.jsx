import React from "react";
import Moment from "moment";
import { BsCalendarCheckFill } from "react-icons/bs";

const AgendaItem = ({item, currentMonth}) => {
  const months = ["Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober", "November", "December"];
  const days = ["Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"];

  const date = new Date(item.date);
  const monthName = () => { 
    //return <div><span>{months[date.getMonth()]}</span></div>
    if (currentMonth.current != date.getMonth()) { 
       return <div><span>{months[date.getMonth()]}</span></div>; 
     } 
     else return <></>; 
  }

  currentMonth.current = ((currentMonth.current != date.getMonth()) ? date.getMonth() : currentMonth.current);
  // console.log(monthName());
  // console.log(currentMonth.current);
  return (
    <div className="agenda_item">
      <div className="agenda_calendar">
        <BsCalendarCheckFill size={48} color="white"/>
      </div>
      <div className="agenda_item_info">
        <div className="month_name"><span>{monthName()}</span></div>
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
