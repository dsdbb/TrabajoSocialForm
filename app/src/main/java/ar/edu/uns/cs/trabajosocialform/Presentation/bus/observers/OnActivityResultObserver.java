package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

import android.graphics.Bitmap;

public abstract class OnActivityResultObserver extends BusObserver<OnActivityResultObserver.OnActivityResult> {
    public OnActivityResultObserver(){ super(OnActivityResult.class); }

    public static class OnActivityResult{

        private Bitmap photo;

        public OnActivityResult(Bitmap photo){
            this.photo = photo;
        }

        public Bitmap getPhoto(){
            return photo;
        }
    }
}
