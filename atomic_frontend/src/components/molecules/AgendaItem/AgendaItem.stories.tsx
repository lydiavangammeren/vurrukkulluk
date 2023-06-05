import React from 'react'
import { Story, ComponentMeta } from '@storybook/react'
import AgendaItemSrc from './AgendaItem'
import { AgendaItemInterface  } from './AgendaItem.interface'
import { defaults } from '../../../config'
/// TODO: Adapt Stories
const AgendaItemMeta: ComponentMeta<typeof AgendaItemSrc> = {
    title: "molecules/AgendaItem",
    component: AgendaItemSrc,
    argTypes: {
        testID: { table: { disable: true } }
    }
}

const testID = "${$NAME}-" + Math.floor(Math.random() * 90000) + 10000

const Template: Story<AgendaItemInterface> = (args) => <AgendaItemSrc {...args} />

export const AgendaItem = Template.bind({})
AgendaItem.args = {
    testID: testID,
    props: [],
    style: {}
}

export default AgendaItemMeta