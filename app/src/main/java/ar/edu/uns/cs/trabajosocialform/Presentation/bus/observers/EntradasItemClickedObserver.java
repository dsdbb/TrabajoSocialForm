package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class EntradasItemClickedObserver extends BusObserver<EntradasItemClickedObserver.EntradasItemClicked> {
    public EntradasItemClickedObserver(){ super(EntradasItemClicked.class);}

    public static class EntradasItemClicked{

        private int position;
        public EntradasItemClicked(int position){
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}
