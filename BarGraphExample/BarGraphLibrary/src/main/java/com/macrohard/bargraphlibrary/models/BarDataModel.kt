package com.macrohard.bargraphlibrary.models

open class BarDataModel {
    var dataLabel = ""
    var barValue = 0
    var backgroundColor = 0
    var backgroundDrawable = 0

    constructor(barValue: Int,
                backgroundColor: Int,
                backgroundDrawable:Int){
        this.barValue = barValue
        this.dataLabel = this.barValue.toString()
        this.backgroundColor = backgroundColor
        this.backgroundDrawable = backgroundDrawable
    }

    constructor(dataLabel:String,
                barValue: Int,
                backgroundColor: Int,
                backgroundDrawable:Int){
        this.dataLabel = dataLabel
        this.barValue = barValue
        this.backgroundColor = backgroundColor
        this.backgroundDrawable = backgroundDrawable
    }
}