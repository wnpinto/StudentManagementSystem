package com.example.studentmoneymanagement;

import java.util.HashMap;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class AnalysePurchasesCustomViewPie extends View{

	private Paint paint = new Paint();
	private PurchasesAnalysis2 purchasesAnalysis;
	private HashMap<String, Integer> priceDataPoints;


	public AnalysePurchasesCustomViewPie(Context context) {
		super(context);
		purchasesAnalysis = new PurchasesAnalysis2(context);
		priceDataPoints =  retrievePriceDataPoints();
	}

	public AnalysePurchasesCustomViewPie(Context context, AttributeSet attrs) {
		super(context, attrs);
		purchasesAnalysis = new PurchasesAnalysis2(context);
		priceDataPoints =  retrievePriceDataPoints();
		invalidate();
		requestLayout();
	}

	@Override
	protected void onDraw(Canvas canvas){
			drawPricePie(canvas);
	}

	private void drawPricePie(Canvas canvas) {
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setStrokeWidth(4);
		paint.setAntiAlias(true);
		paint.setShadowLayer(4, 2, 2, color.darker_gray);
		
		int billsCount = priceDataPoints.get("bills"), 
				entertainmentCount = priceDataPoints.get("entertainment"), 
				groceriesCount = priceDataPoints.get("groceries");
		
		
		int totalCategories = billsCount + entertainmentCount + groceriesCount;
		if(totalCategories == 0)
			totalCategories = 1;
		RectF rect = new RectF(10, 10, getWidth()/2, getHeight()/2);
		
		paint.setColor(0xFFFC7F03);
		canvas.drawArc(rect, 0, ((360*billsCount)/totalCategories), true, paint);
		paint.setColor(0xFFFFFFFF);
		canvas.drawArc(rect, ((360*billsCount)/totalCategories), ((360*entertainmentCount)/totalCategories ), true, paint);
		paint.setColor(0x165F26AB);
		canvas.drawArc(rect, ((360*billsCount)/totalCategories) + ((360*entertainmentCount)/totalCategories ), ((360*groceriesCount)/totalCategories), true, paint);
		
		paint.setShadowLayer(200, 200, 200, 0x00000000);
	}

	private HashMap<String, Integer> retrievePriceDataPoints() {
		return purchasesAnalysis.getDistinctCategoryData();
	}


	@Override
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

}
