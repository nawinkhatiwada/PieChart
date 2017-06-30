package com.androidragger.expandablefloatingactionbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by brainovation on 6/30/17.
 */

public class PieChartView extends View {
    private Paint piePaint; // to draw pie graph
    private RectF rectF;    // view within which pie graph will be drawn
    private float[] data;      // data set to be used to draw slices of pie graph

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setDither(true);
        piePaint.setStyle(Paint.Style.FILL);
    }

    public void setData(float[] data) {
        this.data = data;
        invalidate();
    }

    private float[] pieSegment() {
        float[] segValues = new float[this.data.length];
        float Total = getTotal();
        for (int i = 0; i < this.data.length; i++) {
            segValues[i] = (this.data[i] / Total) * 360;
        }
        return segValues;
    }

    private float getTotal() {
        float total = 0;
        for (float val : this.data) {
            total += val;
        }
        return total;
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (data != null) {
            int top = 0;
            int left = 0;
            int endBottom = getHeight();
            rectF = new RectF(left, top, endBottom, endBottom);
            float[] segment = pieSegment();
            float segStartPoint = 0;
            for (float aSegment : segment) {
                Random rnd = new Random();
                int color = Color.argb(255, (int) aSegment, rnd.nextInt(256), rnd.nextInt(256));
                piePaint.setColor(color);
                canvas.drawArc(rectF, segStartPoint, aSegment, true, piePaint);
                segStartPoint += aSegment;
            }
        }
    }
}
