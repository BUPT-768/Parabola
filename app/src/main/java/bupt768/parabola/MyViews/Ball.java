
package bupt768.parabola.MyViews;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.HashMap;

/**
// * Created by Mock on 2015/5/25.
// */

public class Ball implements ISprite {
    private int x;
    private int y;
    private static int offsetX;
    private static int offsetY;
    private int radius;
    private String id;
    private Message message;

    public Ball(String id,int x,int y,int radius){
        this.id=id;
        this.x=x;
        this.y=y;
        this.radius=radius;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public void onDraw(Canvas canvas){

        Paint p=new Paint();
//            int c=p.getColor();
        p.setColor(Color.BLUE);
        if(canvas!=null){
            canvas.drawCircle(this.x+offsetX, this.y+offsetY, radius, p);
        }
    }

    @Override
    public void dealMessage(Message message) {
        MessageType messageType=message.getMessagetype();

        switch (messageType) {
            case Control:
                HashMap<String,Integer>action= (HashMap<String, Integer>) message.getAction();
                if(action.containsKey("offsetX")){
                    this.offsetX+=action.get("offsetX");
                }
                if(action.containsKey("offsetY")){
                    this.offsetY+=action.get("offsetY");
                }
                break;
            case Things:
                break;
        }
    }

    @Override
    public void sendMessage(Message message) {

    }
}

