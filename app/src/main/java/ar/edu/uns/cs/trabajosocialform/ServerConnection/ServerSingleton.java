package ar.edu.uns.cs.trabajosocialform.ServerConnection;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Class that manage the requests to database using Volley library
 */
public class ServerSingleton {

    private static ServerSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private ServerSingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized ServerSingleton getInstance(Context context){
        if(instance==null){
            instance = new ServerSingleton(context);
        }

        return instance;
    }

    public RequestQueue getRequestQueue() {

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
