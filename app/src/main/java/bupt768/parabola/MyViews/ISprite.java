package bupt768.parabola.MyViews;

import android.graphics.Canvas;

/**
 * Created by Mock on 2015/5/27.
 */
public interface ISprite {

    String getID();
    int getX();
    int getY();
    void onDraw(Canvas canvas);
    void dealMessage(Message message);
    void sendMessage(Message message);

}
