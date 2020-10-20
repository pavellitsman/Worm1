package com.example.worm0.Model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    public int dx = 1;
    public int dy = 1;
    public int xMin = -40;
    public int xMax = 40;
    public int yMin = -20;
    public int yMax = 20;

    public ArrayList<BodyPart> m_body = new ArrayList<>();


    public Model()
    {
        m_body.add(new BodyPart(0,0, true));
        m_body.add(new BodyPart(-1,0, false));
        m_body.add(new BodyPart(-2,0, false));
        m_body.add(new BodyPart(-3,0, false));
    }

    public void Move(int scX, int scY)
    {
        int hedX = m_body.get(0).m_x;
        int hedY = m_body.get(0).m_y;

        hedX += scX * dx;
        hedY += scY * dy;

        if(hedX < xMin)
            hedX = xMax;
        if(hedX > xMax)
            hedX = xMin;

        if(hedY < yMin)
            hedY = yMax;
        if(hedY > yMax)
            hedY = yMin;

        //Move
        //body
        int bodySize = m_body.size() ;
        BodyPart curPart;
        BodyPart nextPart;
        for(int i = m_body.size() - 1; i > 0 ; --i)
        {
            curPart = m_body.get(i);
            nextPart = m_body.get(i-1);

            curPart.m_x = nextPart.m_x;
            curPart.m_y = nextPart.m_y;
        }
        //head
        curPart = m_body.get(0);
        curPart.m_x = hedX;
        curPart.m_y = hedY;

    }

}
