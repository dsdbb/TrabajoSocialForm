package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

import android.view.MenuItem;

public abstract class ContextItemSelectedObserver extends BusObserver<ContextItemSelectedObserver.ContextItemSelected> {
    public ContextItemSelectedObserver(){ super(ContextItemSelected.class); }

    public static class ContextItemSelected{

        private MenuItem item;

        public ContextItemSelected(MenuItem item){
            this.item = item;
        }

        public MenuItem getMenuItem(){
            return item;
        }
    }
}
