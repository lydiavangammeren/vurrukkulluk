import React from 'react'
import { StoryFn, ComponentMeta } from '@storybook/react'
import AgendaIconSrc from './AgendaIcon'
import { AgendaIconInterface  } from './AgendaIcon.interface'
/// TODO: Adapt Stories
const AgendaIconMeta: ComponentMeta<typeof AgendaIconSrc> = {
    title: "atoms/AgendaIcon",
    component: AgendaIconSrc,
    argTypes: {
        testID: { table: { disable: true } }
    }
}

const testID = "AgendaIcon-" + Math.floor(Math.random() * 90000) + 10000

const Template: StoryFn<AgendaIconInterface> = (args) => <AgendaIconSrc {...args} />

export const AgendaIconWhite = Template.bind({})
AgendaIconWhite.args = {
    testID: testID,
    color: "white",
    size: "m",
} as AgendaIconInterface

export const AgendaIconBlackXs = Template.bind({})
AgendaIconBlackXs.args = {
    testID: testID,
    color: "black",
    size: "xs",
} as AgendaIconInterface
export const AgendaIconBlackS = Template.bind({})
AgendaIconBlackS.args = {
    testID: testID,
    color: "black",
    size: "s",
} as AgendaIconInterface
export const AgendaIconBlackM = Template.bind({})
AgendaIconBlackM.args = {
    testID: testID,
    color: "black",
    size: "m",
} as AgendaIconInterface

export const AgendaIconBlackL = Template.bind({})
AgendaIconBlackL.args = {
    testID: testID,
    color: "black",
    size: "l",
} as AgendaIconInterface

export default AgendaIconMeta