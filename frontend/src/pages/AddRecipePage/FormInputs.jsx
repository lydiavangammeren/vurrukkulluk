import DetailsForm from "./DetailsForm"
import IngredientsForm from "./IngredientsForm"
import PreparationForm from "./PreparationForm"
import useAddRecipeContext  from "../../hooks/useAddRecipeContext"

const FormInputs = () => {

    const { page } = useAddRecipeContext()

    const display = {
        0: <DetailsForm />,
        1: <IngredientsForm />,
        2: <PreparationForm />
    }


    const content = (
        <div className="form_inputs">
            {display[page]}
        </div>
    )


    return content
}
export default FormInputs