import React from 'react'
import { Story, ComponentMeta } from '@storybook/react'
import HeadingSrc from './Heading'
import { HeadingInterface  } from './Heading.interface'
import { defaults } from '../../../config'
/// TODO: Adapt Stories
const HeadingMeta: ComponentMeta<typeof HeadingSrc> = {
    title: "atoms/Heading",
    component: HeadingSrc,
    argTypes: {
        testID: { table: { disable: true } }
    }
}

const testID = "Heading-" + Math.floor(Math.random() * 90000) + 10000

const Template: Story<HeadingInterface> = (args) => <HeadingSrc {...args} />

export const Heading1 = Template.bind({})
Heading1.args = {
    testID: testID,
    size: "xl",
    text: "Vurrukkuluk",
}
export const Heading2 = Template.bind({})
Heading2.args = {
    testID: testID,
    colorVar: "white",
    size: "l",
    text: "Agenda",
}
export const Heading3 = Template.bind({})
Heading3.args = {
    testID: testID,
    colorVar: "black",
    size: "m",
    text: "Eggs & Veggies",
}

export const Heading4 = Template.bind({})
Heading4.args = {
    testID: testID,
    size: "s",
    text: "Vegan Burger Bun",
}

export default HeadingMeta