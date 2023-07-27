// import "./SearchResult.css";
import useAddRecipeContext from "../../../hooks/useAddRecipeContext";
import { useState, useEffect } from "react";
import { useAppContext } from "../../../contexts";

export const SearchResult = ({ result, setSearchValue }) => {
  const { handleChange, baseUrl } = useAppContext();
  const { addItem } = useAddRecipeContext();
  const { id, name, unit, amount, calories, price, imageId} = result;
  const [quantity, setQuantity] = useState(1);

  // const [] = useState();

  useEffect(()=>{
    setQuantity(amount)
  }, [result])

  // const increment = () => {
  //   setQuantity(prev => prev + 1)
  // }
  // const decrement = () => {
  //   setQuantity(prev => prev - 1)
  // }
  const handleChangeQuantity = (e) => {
    setQuantity(e.target.value)
  }

  const handleAdd = e => {
    const name = e.target.name
    const value = {
      // articleId: e.target.value,
      articleunitId: e.target.value,
      amount: quantity
    }
    addItem(name, value);
    setSearchValue('');
  }



  return (
      <div className="ingredient_result">
        <div className="result_image">
          <img src={baseUrl + imageId} />
          {/* <div style={{border:"solid", backgroundColor:"green", width:"5rem", height:"5rem"}} >
            Test
          </div> */}
        </div>
        <div className="result_content">
          <h3 className="pretty greenFont">{name}</h3>
          {/* <div className="result_info">
            <div>
              <h5>Inhoud:</h5>
              <span>{`${amount} ${unit}`}</span>
            </div>
            <div>
              <h5>Calorieën:</h5>
              <span>{calories}</span>
            </div>
            <div>
              <h5>Prijs:</h5>
              <span>{price}</span>
            </div>
          </div> */}
          <div>
            <label>Hoeveelheid:</label>
            <input 
              type="number" 
              value={quantity} 
              onChange={handleChangeQuantity}
              step={unit === "stuks" ? 1 : 25}
              min={0}
              />
            {unit}

          </div>
        </div>
        
        <button onClick={handleAdd} value={id} name="ingredients" className="add_ingredient">{`Add`}</button>
        
      </div>
  );
};