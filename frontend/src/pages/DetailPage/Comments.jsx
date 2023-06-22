import React, { useRef, useState } from "react";
import Comment from "./Comment";
import usePostData from "../../hooks/usePostData";
import api from "../../lib/recipeAPI";
import axios from "axios";

const Comments = ({comments, recipeId}) => {
  const [txtVisible, setTxtVisible] = useState(false);
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

  // const postData = async (url, body) => {
  //   const headers = {
  //     'Authorization': `Bearer ${user.token}`,
  //     'Content-Type': 'application/json'
  //   }
  //   console.log('Headers: ' , headers)

  //   // try{
  //   //   axios({
  //   //     method: 'POST',
  //   //     url: 'http://localhost:8080/comments',
  //   //     // headers : headers,
  //   //     headers: {
  //   //       Authorization: `Bearer ${user.token}`
  //   //     },
  //   //     data: body
  //   //   })
  //   // } catch (err){
  //   //   console.log('Comment error: ' , err)
  //   // }
  //   try{
  //     const response = await api.post(url,
  //       JSON.stringify(body),
  //       {
  //         headers: headers
          
  //       }
  //     );

  //     // setData({status: response.status, payLoad: response.data});
  //     // setLoaded(true);

  //   } catch(err){
  //     if(err.response){
  //       // setData({status: err.response.status, payLoad: err.response.data});
  //       //Not in the 200 response range
  //       console.log(err.response.data);
  //       console.log(err.response.status);
  //       console.log(err.response.headers);
  //     }else{
  //       console.log(`Error: ${err.message}`)
  //       // setData({status: 500, payLoad: err.message});
  //     }
  //     // setLoaded(true);
  //   }
  // }

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
