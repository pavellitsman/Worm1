package com.example.worm0.View;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.example.worm0.Model.BodyPart;
import com.example.worm0.Model.Model;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer{

    public MyGLRenderer(Model model)
    {
        m_model = model;
    }
    public Model m_model = null;

    private BodyPartDrawer m_bodyPartDrawer;
    private FieldDrawer m_fieldDrawer;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.5f, 0.0f, 1.0f);

        m_bodyPartDrawer = new BodyPartDrawer(); //todo -  хз почему, ное сли это вынести в объявление переменной то класс не работает.
        m_fieldDrawer = new FieldDrawer(); //todo -  хз почему, ное сли это вынести в объявление переменной то класс не работает.
    }

    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private final float[] vpMatrix = new float[16];
    private final float[] modelMatrix = new float[16];
    private final float[] mvpMatrix = new float[16];

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        float screenRatio = (float)(width) / (float)(height);
        float fieldRatio = (float)(m_model.xMax - m_model.xMin)/ (float)(m_model.yMax - m_model.yMin);
        float scaleX = 1;
        float scaleY = 1;

     if(screenRatio < fieldRatio)
        {
            scaleX = 1;
            scaleY = fieldRatio / screenRatio;
        }
        else
        {
            scaleX = screenRatio/fieldRatio;
            scaleY = 1;
        }

        scaleX *= 1.1f;
        scaleY *= 1.1f;

        GLES20.glViewport(0, 0, width, height);
        Matrix.frustumM(projectionMatrix, 0, m_model.xMin*scaleX, m_model.xMax*scaleX, m_model.yMin*scaleY, m_model.yMax*scaleY, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        float[] scratch = new float[16];

             // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, 3.1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        //field
        m_fieldDrawer.draw(vpMatrix,(m_model.xMax - m_model.xMin-2)/2, (m_model.yMax - m_model.yMin-2)/2);


        //worm
        for (BodyPart curBodyPart: m_model.m_body)
        {
            Matrix.setIdentityM(modelMatrix,0);
            Matrix.translateM(modelMatrix,0, curBodyPart.m_x, curBodyPart.m_y,0);
            Matrix.multiplyMM(mvpMatrix, 0, vpMatrix, 0, modelMatrix, 0);
            m_bodyPartDrawer.draw(mvpMatrix, curBodyPart.m_head);
        }

    }

    //render shader
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

}
