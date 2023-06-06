# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm run storybook`

Runs the storybook interface
Open [http://localhost:6006] (http://localhost:6006)

The page will reload if you make edits

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

## atomic bomb

Use the command [atomic-bomb](https://www.npmjs.com/package/atomic-bomb) to create a new atom, molecule, organism or page

```bash
npm run atomic-bomb -- --type atom --name Label
# creates /src/components/atoms/Label

npm run atomic-bomb -- --name Button,Heading
# creates /src/components/atoms/Button and /src/components/atoms/Heading (default type = atom)

npm run atomic-bomb -- --type molecule --name "Button Bar","Menu Item"
# creates /src/components/molecules/ButtonBar and /src/components/molecules/MenuItem
```

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).
