package bupt768.parabola.MyViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ITTC-Jayvee on 2015/5/19.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;
    Canvas canvas;
    private int iViewHeight;
    private int iViewWidth;
    public static int changeHeight;
    public static int chageWidth;
    private final String DEBUG_TAG = "MySurfaceView";
    private Ball ball1;
    public static Manager manager = new Manager();

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context);
        init();
    }

    private void init() {
        holder = this.getHolder();
        holder.addCallback(this);
        this.canvas = holder.lockCanvas();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.canvas = holder.lockCanvas();
        if (null != canvas) {
            iViewHeight = canvas.getHeight();
            iViewWidth = canvas.getWidth();
            Log.d(DEBUG_TAG, "获取的视图高宽=" + iViewHeight + "x" + iViewWidth);
        }
        holder.unlockCanvasAndPost(canvas);
        MySurfaceViewThread msvt = new MySurfaceViewThread(holder);
        ball1 = new Ball("1",iViewWidth / 2, iViewHeight / 2, 100);
        manager.addSprite("ball1",ball1);
        msvt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    protected void Draw(Canvas canvas) {
        if (canvas == null) canvas = this.holder.lockCanvas();
        Paint p = new Paint();
//            int c=p.getColor();
        p.setColor(Color.BLUE);
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            canvas.drawCircle(iViewWidth / 2 + chageWidth, iViewHeight / 2, 100, p);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    private class MySurfaceViewThread extends Thread {
        boolean isStop;
        SurfaceHolder holder;

        public MySurfaceViewThread(SurfaceHolder holder) {
            this.holder = holder;
            this.isStop = false;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            while (!isStop) {
                try {
//                            synchronized (holder) {
                    canvas = holder.lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    for(ISprite sprite :manager.getSprites().values()){
                        sprite.onDraw(canvas);
                    }
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {// 无论如何都要提交
                    if (canvas != null) {
                        holder.unlockCanvasAndPost(canvas);// 结束锁定画图，并提交改变。
                    }
                }
            }
        }
    }
}
