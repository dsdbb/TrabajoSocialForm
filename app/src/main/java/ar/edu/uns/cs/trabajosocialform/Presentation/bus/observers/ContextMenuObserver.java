package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

import android.view.Menu;

public abstract class ContextMenuObserver extends BusObserver<ContextMenuObserver.ContextMenu> {
    public ContextMenuObserver(){ super(ContextMenu.class); }

    public static class ContextMenu {

        private Menu menu;

        public ContextMenu(Menu menu) {
            this.menu = menu;
        }

        public Menu getMenu() {
            return menu;
        }
    }
}