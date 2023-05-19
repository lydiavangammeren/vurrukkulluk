import React from "react";
import Comment from "./Comment";

const Comments = ({comments}) => {
  
  return (
    <div className="Comments">
      {comments && comments.map((comment) => (
        <Comment
          name={comment.user_name}
          comment={comment.comment}
          image={comment.image}
        />
      ))}
    </div>
  );
};

export default Comments;
