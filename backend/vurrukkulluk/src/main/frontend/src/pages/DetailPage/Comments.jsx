import React, { useEffect, useRef, useState } from "react";
import Comment from "./Comment";
import usePostData from "../../hooks/usePostData";
import api from "../../lib/recipeAPI";
import axios from "axios";

const Comments = ({comments, keepRecipeCache, recipeId}) => {
  // const [txtVisible, setTxtVisible] = useState(false);
  const commentText = useRef('');
  const user = JSON.parse(localStorage.getItem('user'))

  const [data, isLoaded, postData] = usePostData();

  const handleSubmit = () => {
    if(commentText.current === "" ) console.log('Leeg')

    const body = {
      commentText: commentText.current,
      recipeId: recipeId,
      userId: user.id
    }
    // console.log(localStorage.getItem('user'))
    console.log('Post to database: ' , body)
// 
    // postData('/comments', body)
    postData('/comments', body)
  }


  const renderCreateComment = () => {
    if(localStorage.getItem('user') === null) return;
    return (
      <div className="create_comment">
        <textarea 
          className="comment_text" 
          // style={txtVisible ? {} : {display:"none"}}
          ref={commentText}
          // value={commentText.current}
          onChange={e => {commentText.current = e.target.value}}
        />
        <button onClick={handleSubmit}>
          Maak opmerking
        </button>
      </div>
    )
  }
  
  useEffect(()=>{
    if(isLoaded) keepRecipeCache(false);
  }, [isLoaded])

  return (
    <div className="Comments">
      <div className="comments_list">
        {comments && comments.map((comment, index) => (
          <Comment comment={comment} key={index} />
        ))}
      </div>
      {renderCreateComment()}
    </div>
  );
};

export default Comments;
