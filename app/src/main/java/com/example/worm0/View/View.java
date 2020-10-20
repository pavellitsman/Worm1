package com.example.worm0.View;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.worm0.Model.Model;

public class View
{
    public Model m_model = null;
    public MyGLSurfaceView gLView = null;
    public View(Context context, Model model)
    {
        m_model = model;
        gLView = new MyGLSurfaceView(context, model);
    }

    public void Update()
    {
        gLView.requestRender();
    }

}
