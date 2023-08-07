import React from "react";
import Moment from "moment";
import { BsCalendarCheckFill } from "react-icons/bs";

const generateICalData = (event) => {
  const startDate = event.startDate.toISOString(); // Convert to ISO format
  const endDate = event.endDate.toISOString();
  const eventName = event.title;
  const description = event.description;
  
  // Construct the iCalendar data
  const iCalData = `BEGIN:VCALENDAR
VERSION:2.0
BEGIN:VEVENT
DTSTART:${startDate}
DTEND:${endDate}
SUMMARY:${eventName}
DESCRIPTION:${description}
END:VEVENT
END:VCALENDAR`;
  
  return iCalData;
};

const AgendaItem = ({item, currentMonth}) => {
  // console.log("Agendaitem: ", item)
  const months = ["Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober", "November", "December"];
  const days = ["Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"];
  
  const event = {
    startDate: '',
    endDate: '',
    eventName: '',
    description: ''
  }
  
  const date = new Date(item.date);
  
  event.startDate = date;
  event.endDate = Moment(date).add(1, 'hours').toDate();
  event.name = item.title;
  event.description = item.description;
  
  // console.log("Event: ", event)
  const iCalData = generateICalData(event);
  
  const monthName = () => { 
    //return <div><span>{months[date.getMonth()]}</span></div>
    if (currentMonth.current != date.getMonth()) { 
       return <div><span>{months[date.getMonth()]}</span></div>; 
     } 
     else return <></>; 
  }

  const downloadCalendar = () => {
    const blob = new Blob([iCalData], { type: 'text/calendar' });
    const url = URL.createObjectURL(blob);
    
    // Create a temporary link and simulate a click to trigger download
    const link = document.createElement('a');
    link.href = url;
    link.download = `${event.name}.ics`;
    link.click();
    
    // Clean up the URL object
    URL.revokeObjectURL(url);
  };

  currentMonth.current = ((currentMonth.current != date.getMonth()) ? date.getMonth() : currentMonth.current);
  // console.log(monthName());
  // console.log(currentMonth.current);
  return (
    <div className="agenda_item" onClick={downloadCalendar}>
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
