package com.capstone.berrets.view.qualityDetection.custom

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

fun roundedShape(
	backgroundColor: Int,
	strokeColor: Int,
	cornerRadius: Float,
	strokeWidth: Int
): Drawable {
	return GradientDrawable().apply {
		shape = GradientDrawable.RECTANGLE
		setColor(backgroundColor)
		setStroke(strokeWidth, strokeColor)
		setCornerRadius(cornerRadius)
	}
}