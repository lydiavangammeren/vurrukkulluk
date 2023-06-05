import React from 'react'

export interface LabelInterface {
    value?: string
    style?: React.CSSProperties | any,
    size?: 'xs' | 's' | 'm' | 'l' | 'xl' | 'xxl',
    testID?: string,
}
