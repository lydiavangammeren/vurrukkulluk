import React from "react";
import Comment from "./Comment";
import "../css/Comments.css";

const Comments = () => {
  const comments = [
    {
      id: "1",
      image: "VeganBurger.jpg",
      user_name: "Tommy Tuup",
      comment: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu."
    },
    {
      id: "2",
      image: "VeganBurger.jpg",
      user_name: "Bennie Blind",
      comment: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu."
    },
    {
      id: "3",
      image: "VeganBurger.jpg",
      user_name: "Sammy Suf",
      comment: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu."
    },
    {
      id: "4",
      image: "VeganBurger.jpg",
      user_name: "Katinka Cool",
      comment: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu."
    },
  ];
  return (
    <div className="Comments">
      {/* <Comment 
      name="bla"
      comment="bla bla "
      image="VeganBurger.jpg"/> */}
      {comments.map((comment) => (
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
