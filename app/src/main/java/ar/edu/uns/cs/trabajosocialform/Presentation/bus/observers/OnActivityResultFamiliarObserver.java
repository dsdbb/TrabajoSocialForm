package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

import android.content.Intent;

public abstract class OnActivityResultFamiliarObserver extends BusObserver<OnActivityResultFamiliarObserver.OnActivityResultFamiliar> {
    public OnActivityResultFamiliarObserver(){ super(OnActivityResultFamiliar.class); }

    public static class OnActivityResultFamiliar{

        private Intent intent;

        public OnActivityResultFamiliar(Intent intent){
            this.intent = intent;
        }

        public Intent getIntent() {
            return intent;
        }
    }

}
