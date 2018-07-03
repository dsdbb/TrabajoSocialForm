package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

import android.view.MenuItem;

public abstract class OptionsItemSelectedObserver extends BusObserver<OptionsItemSelectedObserver.OptionsItemSelected> {
    public OptionsItemSelectedObserver(){ super(OptionsItemSelected.class); }

    public static class OptionsItemSelected{
        private MenuItem item;

        public OptionsItemSelected(MenuItem item){
            this.item = item;
        }

        public MenuItem getMenuItem(){
            return item;
        }
    }
}
