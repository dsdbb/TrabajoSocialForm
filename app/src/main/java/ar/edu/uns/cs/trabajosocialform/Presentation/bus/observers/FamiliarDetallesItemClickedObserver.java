package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class FamiliarDetallesItemClickedObserver extends BusObserver<FamiliarDetallesItemClickedObserver.FamiliarDetallesItemClicked> {
    public FamiliarDetallesItemClickedObserver(){ super(FamiliarDetallesItemClicked.class); }

    public static class FamiliarDetallesItemClicked{

        private int position;

        public FamiliarDetallesItemClicked(int position){
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}
