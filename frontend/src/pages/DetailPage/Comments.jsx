import React from "react";
import Comment from "./Comment";

const Comments = ({comments}) => {
  
  return (
    <div className="Comments">
      {comments && comments.map((comment, index) => (
        <Comment comment={comment} key={index} />
      ))}
    </div>
  );
};

export default Comments;
