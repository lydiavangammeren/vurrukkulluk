import React from "react";

const Comment = ({comment}) => {
  const userImage = comment.user.image;
  const userName = comment.user.name;
  const commentTxt = comment.commentText;

  return (
    <div className="Comment">
      <div className="comment_img">
        {userImage &&
          <img src={require(`../../assets/images/${userImage}`)} alt={userImage} />
        }
      </div>
      <div className="comment_info">
        <h3>{userName}</h3>
        <p>
          {commentTxt}
        </p>
      </div>
    </div>
  )
}

export default Comment;