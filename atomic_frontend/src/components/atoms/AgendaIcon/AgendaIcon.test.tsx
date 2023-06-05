import React from 'react'
import { render, screen } from '@testing-library/react';
import { AgendaIconInterface  } from './AgendaIcon.interface'

/// Preferably each story instead of actual component
/// TODO: fix test based upon story entries
import AgendaIcon from './AgendaIcon'

const testID = "AgendaIcon-" + Math.floor(Math.random()*90000) + 10000

describe("AgendaIcon", () => {

    it("Can render AgendaIcon", () => {
        render(<AgendaIcon testID={ testID } />)
        let defaultCreated = screen.getByTestId(testID)
        expect(defaultCreated).not.toBeNull()
    })

})