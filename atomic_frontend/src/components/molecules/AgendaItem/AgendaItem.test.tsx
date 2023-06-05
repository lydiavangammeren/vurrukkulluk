import React from 'react'
import { render, screen } from '@testing-library/react';
import { AgendaItemInterface  } from './AgendaItem.interface'
import { defaults } from '../../../config'

/// Preferably each story instead of actual component
/// TODO: fix test based upon story entries
import { AgendaItem } from './AgendaItem.stories'

const testID = "AgendaItem-" + Math.floor(Math.random()*90000) + 10000

describe("AgendaItem", () => {

    it("Can render AgendaItem", () => {
        render(<AgendaItem testID={ testID } />)
        let defaultCreated = screen.getByTestId(testID)
        expect(defaultCreated).not.toBeNull()
    })

})