package ua.lpnu.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class myGlSurfaceView extends GLSurfaceView {

    ua.lpnu.opengl.Renderer myRender;

    public myGlSurfaceView(Context context) {
        super(context);
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        super.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        // Set the Renderer for drawing on the GLSurfaceView
        myRender = new ua.lpnu.opengl.Renderer();

        setRenderer(myRender);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        myRender.onPause();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        myRender.onResume();
    }

}
