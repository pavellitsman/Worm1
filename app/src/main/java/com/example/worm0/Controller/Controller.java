package com.example.worm0.Controller;

import android.view.KeyEvent;

import com.example.worm0.Model.Model;
import com.example.worm0.View.View;

public class Controller
{
    public Model m_model;
    public View m_view;

    public Controller(Model model,  View view)
    {
        m_model = model;
        m_view  = view;
    }

    public void OnTimer(int dt)
    {

    }

    public void OnKeyDown(int keyCode)
    {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_UP:
                m_model.Move(0,1);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                m_model.Move(0,-1);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                m_model.Move(-1,0);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                m_model.Move(1,0);
                break;
        }

        m_view.Update();
    }

}
