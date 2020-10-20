package com.example.worm0.View;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.worm0.Model.Model;
import com.example.worm0.View.MyGLRenderer;

public class MyGLSurfaceView extends GLSurfaceView
{
    public Model m_model = null;

    private final MyGLRenderer renderer;

    public MyGLSurfaceView(Context context, Model model){
        super(context);

        m_model = model;
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        renderer = new MyGLRenderer(model);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }



}
