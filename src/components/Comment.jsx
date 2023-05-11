import React from "react";
import "../css/Comment.css";

const Comment = (props) => {
  return (
    <div className="Comment">
      <div className="comment_img">
        <img src={require("../assets/images/" + props.image )} 
        width="100%"
        height="100%"
        />
      </div>
      <div className="comment_info">
        <h3>{props.name}</h3>
        <p>
          {props.comment}
        </p>
      </div>
    </div>
  )
}

export default Comment;