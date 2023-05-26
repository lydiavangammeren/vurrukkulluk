import React from "react";

const Comment = ({comment}) => {
  return (
    <div className="Comment">
      <div className="comment_img">
        <img src={require(`../../assets/images/${comment.image}`)} alt={comment.image}
        />
      </div>
      <div className="comment_info">
        <h3>{comment.name}</h3>
        <p>
          {comment.comment}
        </p>
      </div>
    </div>
  )
}

export default Comment;