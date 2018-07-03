package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.Nullable;

public class ActivityView {

    protected WeakReference<AppCompatActivity> activityRef;

    public ActivityView(){}

    public ActivityView(AppCompatActivity activity){
        activityRef = new WeakReference<>(activity);
    }

    @Nullable
    public AppCompatActivity getActivity() {
        return activityRef.get();
    }

    @Nullable
    public Context getContext(){
        return getActivity();
    }

    @Nullable
    public FragmentManager getFragmentManager() {
        AppCompatActivity activity = getActivity();
        return (activity != null) ? activity.getFragmentManager() : null;
    }

    public void showMessage(int resourceId){
        Toast.makeText(getContext(),resourceId,Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();

    }
}
