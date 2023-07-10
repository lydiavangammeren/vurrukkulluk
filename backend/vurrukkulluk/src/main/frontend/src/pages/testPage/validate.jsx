validate.validators.myAsyncValidator = function(value) {
  return new validate.Promise(function(resolve, reject) {
    setTimeout(function() {
      if (value === "foo") resolve();
      else resolve("is not foo");
    }, 100);
  });
};

var constraints = {name: {myAsyncValidator: true}}
  , success = alert.bind(this, "The validations passed")
  , error = function(errors) {
      alert(JSON.stringify(errors, null, 2));
    };

// Will call the success callback
validate.async({name: "foo"}, constraints).then(success, error);

// Will call the error callback with {name: ["Name is not foo"]} as the first argument
validate.async({name: "bar"}, constraints).then(success, error);