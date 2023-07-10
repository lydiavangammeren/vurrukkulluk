export const constraints = {
  name: {
    presence: {
      allowEmpty: false,
      message: "^Naam mag niet leeg zijn"
    },
    length: {
      minimum: 2,
      message: "^Naam moet ten minste 2 letters zijn"
    },
    format: {
      pattern: "[A-z][A-z0-9-_\\s]{1,23}",
      flags: "i",
      message: "^Naam mag alleen letters bevatten"
    }
  },
  type: {
    optionPicked: true
  },
  region: {
    optionPicked: true
  },
  persons: {
    numericality: {
      onlyInteger: true,
      notLessThan: 1,
      message: "^%{value} is niet toegestaan",
    },
    // numericality: {
    //   notLessThan: 1,
    //   message: "^Minimaal voor 1 persoon"
    // }
  },
  // afbeelding
  description: {
    presence: {
      allowEmpty: false,
      message: "^Beschrijving mag niet leeg zijn"
    }
  }
}