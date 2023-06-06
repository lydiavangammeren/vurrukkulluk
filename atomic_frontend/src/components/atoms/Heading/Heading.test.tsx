import React from 'react'
import { render, screen } from '@testing-library/react';
import { HeadingInterface  } from './Heading.interface'
import { defaults } from '../../../config'

/// Preferably each story instead of actual component
/// TODO: fix test based upon story entries
import { Heading3 } from './Heading.stories'

const testID = "Heading-" + Math.floor(Math.random()*90000) + 10000

describe("Heading", () => {

    it("Can render Heading", () => {
        render(<Heading3 testID={ testID } text="test"/>)
        let defaultCreated = screen.getByTestId(testID)
        expect(defaultCreated).not.toBeNull()
    })

})