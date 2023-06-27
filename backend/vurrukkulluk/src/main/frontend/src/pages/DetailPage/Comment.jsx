import React from "react";
import { useAppContext } from "../../contexts";

const Comment = ({comment}) => {
  const userImage = comment.user.image;
  const userImageId = comment.user.imageId;
  const userName = comment.user.name;
  const commentTxt = comment.commentText;
  const { baseUrl } = useAppContext();


  return (
    <div className="Comment">
      <div className="comment_img">
        <img src={baseUrl + userImageId} alt={userImage} />
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