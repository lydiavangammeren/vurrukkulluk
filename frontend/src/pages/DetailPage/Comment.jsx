import React from "react";

const Comment = ({name, comment, image}) => {
  return (
    <div className="Comment">
      <div className="comment_img">
        <img src={require(`../../assets/images/${image}`)} alt={image}
        />
      </div>
      <div className="comment_info">
        <h3>{name}</h3>
        <p>
          {comment}
        </p>
      </div>
    </div>
  )
}

export default Comment;