package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class NextButtonClickedObserver extends BusObserver<NextButtonClickedObserver.NextButtonClicked> {
    public NextButtonClickedObserver(){ super(NextButtonClicked.class);}

    public static class NextButtonClicked{

    }
}
