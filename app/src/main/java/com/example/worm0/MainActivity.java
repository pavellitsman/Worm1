package com.example.worm0;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.worm0.Controller.Controller;
import com.example.worm0.Model.Model;
import com.example.worm0.View.MyGLSurfaceView;
import com.example.worm0.View.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity
{
    Timer timer = null;
    //MVC
    Model m_model = null;
    View m_view = null;
    Controller m_controller = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create mvc
        m_model = new Model();
        m_view = new View(this, m_model);
        setContentView(m_view.gLView);
        m_controller = new Controller(m_model, m_view);

        //create timer
        timer = new Timer();
        timer.schedule(new AppMainTimer(), 0, 1000);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        m_controller.OnKeyDown(keyCode);
        return super.onKeyUp(keyCode, event);
    }

    //Timer
    class AppMainTimer extends TimerTask
    {
        @Override
        public void run()
        {
            m_controller.OnTimer(1000);
        };
    }

}
