export const constraints = {
  name: {
    presence: {
      message: "mag niet leeg zijn"
    },
    length: {
      minimum: 6,
      message: "ten minste 2 letters"
    },
    format: {
      pattern: "[A-z]+",
      flags: "i",
      message: "mag alleen letters bevatten"
    }
  },
  email: {
    presence: {
      message: "mag niet leeg zijn"
    },
    email: {
      message: "is geen valide email adres"
    }
  },
  firstPassword: {
    presence: {
      message: "mag niet leeg zijn"
    },
    length: {
      minimum: 4,
      maximum: 32
    },
    format: {

    }
  }, 
  secondPassword: {
    presence: {
      message: "mag niet leeg zijn"
    },
    equality: "firstPassword"
  }
}