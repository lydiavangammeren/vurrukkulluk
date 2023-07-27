// import "./SearchResult.css";
import useAddRecipeContext from "../../../hooks/useAddRecipeContext";
import { useState, useEffect } from "react";
import { useAppContext } from "../../../contexts";

const smallSteps = ["stuks", "kg", "l", "mespunt", "snufje", "theelepel"];

export const SearchResult = ({ result, setSearchValue }) => {
  const { handleChange, baseUrl } = useAppContext();
  const { addItem } = useAddRecipeContext();
  const { id, name, amount, calories, price, imageId, articleunits} = result;
  const [quantity, setQuantity] = useState(1);
  const [articleUnitId, setArticleUnitId] = useState(0)
  const [unitName, setUnitName] = useState("");

  // const [] = useState();

  useEffect(()=>{
    setQuantity(amount)
    setArticleUnitId(articleunits[0].id);
    setUnitName(articleunits[0].defUnit.name);
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
    console.log("articleUnitId: ", e.target.value)
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
              step={smallSteps.includes(unitName) ? 1 : 25}
              min={0}
              />
              <select onChange={(e) => {
                console.log("Name + unitId: ", e.target.value);
                setUnitName(e.target.value.split("|")[0]); 
                setArticleUnitId(e.target.value.split("|")[1])
                }}>
                {articleunits.map((articleunit)=> {
                  return <option value={`${articleunit.unit.name}|${articleunit.id}`}>{articleunit.unit.name}</option>
                })}
              </select>

          </div>
        </div>
        
        <button onClick={handleAdd} value={articleUnitId} name="ingredients" className="add_ingredient">{`Add`}</button>
        
      </div>
  );
};