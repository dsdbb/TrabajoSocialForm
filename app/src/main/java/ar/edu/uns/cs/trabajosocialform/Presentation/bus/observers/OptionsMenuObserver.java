package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

import android.view.Menu;

public abstract class OptionsMenuObserver extends BusObserver<OptionsMenuObserver.OptionsMenu> {
    public OptionsMenuObserver(){super(OptionsMenu.class);}

    public static class OptionsMenu{

        private Menu menu;

        public OptionsMenu(Menu menu){
            this.menu = menu;
        }

        public Menu getMenu(){
            return menu;
        }

    }
}
