export const constraints = {
  name: {
    presence: {
      message: "^Naam mag niet leeg zijn"
    },
    length: {
      minimum: 2,
      message: "^Naam moet ten minste 2 letters zijn"
    },
    format: {
      pattern: "[A-z][A-z0-9-_]{1,23}",
      flags: "i",
      message: "^Naam mag alleen letters bevatten"
    }
  },
  email: {
    presence: {
      message: "mag niet leeg zijn"
    },
    email: {
      message: "^'%{value}' is geen valide email adres"
    }
  },
  password: {
    presence: {
      message: "^Wachtwoord mag niet leeg zijn"
    },
    length: {
      minimum: 8,
      maximum: 32,
      tooShort: "^Wachtwoord is te kort, moet ten minste 8 karakters zijn",
      tooLong: "^Wachtwoord is te lang, mag uiterlijk 32 karakters zijn"
    },
    format: {
      pattern: "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,32}",
      message: "^Wachtwoord moet een kleine letter, een hoofdletter en een speciaal teken bevatten"
    }
  }, 
  confirmPassword: {
    equality: {
      attribute: "wachtwoord",
      message: "^Moet gelijk zijn aan het wachtwoord"
    }
  }
}