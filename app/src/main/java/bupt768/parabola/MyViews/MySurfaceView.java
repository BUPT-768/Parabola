package bupt768.parabola.MyViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private final String DEBUG_TAG = "MySurfaceView";

    public MySurfaceView(Context context) {
        super(context);
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
        msvt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    class MySurfaceViewThread extends Thread {
        boolean isStop;
        SurfaceHolder holder;

        public MySurfaceViewThread(SurfaceHolder holder) {
            this.holder = holder;
            this.isStop = false;
        }

        @Override
        public void run() {
            while (!isStop) {
                Canvas canvas = null;
                try {
                    synchronized (holder) {
                        canvas = holder.lockCanvas();
                        canvas.drawColor(Color.WHITE);
                        Paint p = new Paint();
                        p.setColor(Color.RED);
                        canvas.drawCircle(iViewWidth/2, iViewHeight/2, 100, p);
                    }
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
