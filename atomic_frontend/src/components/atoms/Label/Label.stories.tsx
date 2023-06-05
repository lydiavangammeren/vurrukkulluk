import React from 'react'
import { Story, Meta } from '@storybook/react'
import LabelSrc from './Label'
import { LabelInterface  } from './Label.interface'
import { defaults } from '../../../config'
/// TODO: Adapt Stories
const LabelMeta: Meta<typeof LabelSrc> = {
    title: "atoms/Label",
    component: LabelSrc,
    argTypes: {
        testID: { table: { disable: true } }
    }
}

const testID = "Label" + Math.floor(Math.random() * 90000) + 10000

const Template: Story<LabelInterface> = (args) => <LabelSrc {...args} />

export const Label = Template.bind({})
Label.args = {
    testID: testID,
    value: "Hello world!",
    size: "xl",
    style: {}
} as LabelInterface

export default LabelMeta