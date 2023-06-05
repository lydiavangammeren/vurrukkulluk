import React from 'react'
import { render, screen } from '@testing-library/react';
import { LabelInterface  } from './Label.interface'
import { defaults } from '../../../config'

/// Preferably each story instead of actual component
/// TODO: fix test based upon story entries
import { Label } from './Label.stories'

const testID = "Label-" + Math.floor(Math.random()*90000) + 10000

describe("Label", () => {

    it("Can render Label", () => {
        render(<Label testID={ testID } />)
        let defaultCreated = screen.getByTestId(testID)
        expect(defaultCreated).not.toBeNull()
    })

})