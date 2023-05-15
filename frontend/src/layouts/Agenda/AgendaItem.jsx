import React from "react";

const AgendaItem = () => {
  return (
    <div className="Agenda_item">
      <div className="agenda-image-title">
        <div className="agenda-image">
          <img src={require("../../assets/images/today-32.png")} alt="calendar" />
        </div>
        <div className="agenda-title">
          <h3>Elke woensdagmiddag: Vegetarisch koken</h3>
        </div>
      </div>
      <p>Een workshop vegetarisch koken, onder leiding van Trientje Hupsakee</p>
    </div>
  );
};

export default AgendaItem;
